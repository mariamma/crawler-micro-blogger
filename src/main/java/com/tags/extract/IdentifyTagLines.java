package com.tags.extract;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.wepage.info.WebPageInfo;

public class IdentifyTagLines {

	private Document document;
	WebPageInfo webPageInfo;
	private String url;
	int MAX_SIZE = 20;
	
	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public WebPageInfo getWebPageInfo() {
		return webPageInfo;
	}

	public void setWebPageInfo(WebPageInfo webPageInfo) {
		this.webPageInfo = webPageInfo;
	}

	public void setDoc(Document doc) {
		document = doc;
	}

	public Document getDoc() {
		return document;
	}

	public void setUrl(String url2) {
		this.url = url2;
	}

	public String getUrl() {
		return url;
	}
	

	public WebPageInfo process() throws IOException {
		List<String> blocks = identifyNeighbourBlocks();
		List<String> tagLines = new ArrayList<String>();
		for (String str : blocks) {
			System.out.println(str);
			StopWordRemovalAndStemming stopRemove = new StopWordRemovalAndStemming();
			try {
				String newStr = stopRemove.process(str);
				System.out.println("After removing stop words:: " + newStr);
				tagLines.add(newStr);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		TagsFromLines tags = new TagsFromLines();
		webPageInfo = tags.extractTags(webPageInfo, tagLines);
		return webPageInfo;
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
		//anchTags.remove();
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
