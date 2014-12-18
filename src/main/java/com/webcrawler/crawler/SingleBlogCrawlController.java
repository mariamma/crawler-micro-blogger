package com.webcrawler.crawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class SingleBlogCrawlController {
    public static void main(String[] args) throws Exception {

        //String crawlStorageFolder = "/Users/mariamma/data/crawl/root";
        //TODO: Use users home/config instead of hardcoding
        String crawlStorageFolder = "/Users/user/mariamma/data";
        int numberOfCrawlers = 7;

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);

        /*
         * Instantiate the controller for this crawl.
         */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        /*
         * For each crawl, you need to add some seed urls. These are the first
         * URLs that are fetched and then the crawler starts following links
         * which are found in these pages
         */
        // controller.addSeed("http://blog.vogella.com/");
        controller.addSeed("http://www.mkyong.com/");
        //controller.addSeed("http://www.forbes.com/sites/timworstall/2014/07/17/the-secret-to-why-yahoos-share-price-doesnt-reflect-the-value-of-the-alibaba-stake/");

        /*
         * Start the crawl. This is a blocking operation, meaning that your code
         * will reach the line after this only when crawling is finished.
         */
        controller.start(SingleBlogCrawler.class, numberOfCrawlers);
    }


}
