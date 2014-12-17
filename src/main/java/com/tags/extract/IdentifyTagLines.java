package com.tags.extract;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class IdentifyTagLines {

	private Document document;
	int MAX_SIZE = 20;

	public void setDoc(Document doc) {
		document = doc;
	}

	public Document getDoc() {
		return document;
	}

	public void setTagToDoc() {

	}

	public void process() throws IOException {
		List<String> blocks = identifyNeighbourBlocks();
		List<String> tagLines = new ArrayList<String>();
		for (String str : blocks) {
			System.out.println(str);
			StopWordRemovalAndStemming stopRemove = new StopWordRemovalAndStemming();
			try {
				String newStr = stopRemove.process(str);
				System.out.println("After removing stop words:: " + newStr);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		TagsFromLines tags = new TagsFromLines();
		tags.setLine(str);
		tags.extractTags();

	}

	private List<String> identifyNeighbourBlocks() {
		List<String> tagBlocks = new ArrayList<String>();
		Elements metaTag = document.getElementsByTag("meta");
		for (Element src : metaTag) {
			if (isValidDivLine(src)) {
				tagBlocks.add(src.toString());
			}
		}
		if (tagBlocks.size() == 0) {
			tagBlocks = identifyBlocksFromDocBody();
		}
		return tagBlocks;
	}

	public Boolean isValidDivLine(Element src) {
		String line = src.toString();
		if (!line.contains("tag"))
			return false;
		Elements value = src.getElementsByClass("code");
		if (!value.isEmpty())
			return false;
		value = src.select("script");
		if (!value.isEmpty())
			return false;
		return true;

	}

	public Element removeAnchorTags(Element src) {
		Elements anchTags = src.select("a");
		anchTags.remove();
		return src;
	}

	private List<String> identifyBlocksFromDocBody() {
		List<String> tagBlocks = new ArrayList<String>();
		Elements bodyTag = document.select("div:not(:has(div))");
		for (Element src : bodyTag) {
			src = removeAnchorTags(src);
			if ((src != null) && (src.hasText()) && (isValidDivLine(src))) {
				tagBlocks.add(src.toString());
			}
		}
		return tagBlocks;
	}

}
