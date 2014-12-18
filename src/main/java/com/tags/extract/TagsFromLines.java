package com.tags.extract;

import com.wepage.info.WebPageInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TagsFromLines {
    List<String> urlTags;
    List<String> tags = Arrays.asList("java", "eclipse", "spring", "finan", "econom", "manag", "mobil", "tech", "media", "entertain", "sport", "tax");

    public WebPageInfo extractTags(WebPageInfo webPageInfo, List<String> tagLines) {
        urlTags = new ArrayList<String>();
        for (String src : tagLines) {
            if (src.startsWith("tag")) {
                addMetaTags(src, urlTags);
            } else if (src.contains("tag")) {
                processFromLine(src);
            } else {
                processFromLine(src);
            }
        }
        extractTagFromTitle(webPageInfo);
        webPageInfo.setTags(urlTags);
        return webPageInfo;
    }

    private void extractTagFromTitle(WebPageInfo webPageInfo) {
        StopWordRemovalAndStemming stopRemove = new StopWordRemovalAndStemming();
        try {
            String str = stopRemove.process(webPageInfo.getTitle());
            processFromLine(str);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void processFromLine(String src) {
        String[] result = src.split("\\s");
        for (String s : result) {
            if (isValidWord(s) && checkFeasible(s)) {
                urlTags.add(s);
            }
        }
        /*for (int x = 0; x < result.length; x++) {
            if (isValidWord(result[x])) {
                if (checkFeasible(result[x])) {
                    urlTags.add(result[x]);
                }
            }
        }*/
    }

    private boolean checkFeasible(String str) {
        if (str.equals("tag"))
            return false;
        if (tags.contains(str))
            return true;
        return false;
    }

    private boolean isValidWord(String str) {
        int i = 0;
        while (i < str.length()) {
            if ((str.charAt(i) >= 'a' && str.charAt(i) <= 'z') || (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z')) {
                i++;
            } else {
                return false;
            }
        }
        return true;
    }

    private void addMetaTags(String src, List<String> urlTags) {
        String[] result = src.split("\\s");
        for (int x = 1; x < result.length; x++) {
            urlTags.add(result[x]);
            tags.add(result[x]);
            System.out.println(result[x]);
        }

    }
}
