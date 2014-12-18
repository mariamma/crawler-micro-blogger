package com.webcrawler.crawler;

import org.jsoup.nodes.Document;

public interface Crawler {
    public void startCrawl(String url);

    public void process(Document content);

    public void endCrawl();
}
