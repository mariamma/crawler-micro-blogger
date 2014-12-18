package com.wepage.info;

import java.util.List;


public class WebPageInfo {
    String url;
    String title;
    List<String> tagLines;
    List<String> tags;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getTagLines() {
        return tagLines;
    }

    public void setTagLines(List<String> tagLines) {
        this.tagLines = tagLines;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url2) {
        this.url = url2;
    }

}
