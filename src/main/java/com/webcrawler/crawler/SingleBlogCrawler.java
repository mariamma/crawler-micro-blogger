package com.webcrawler.crawler;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.data.access.CallerDAO;
import com.data.access.CallerDAOImpl;
import com.http.dataaccess.UrlInfo;
import com.tags.extract.IdentifyTagLines;
import com.wepage.info.WebPageInfo;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class SingleBlogCrawler extends WebCrawler  {

	
    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|bmp|gif|jpe?g" 
                                                      + "|png|tiff?|mid|mp2|mp3|mp4"
                                                      + "|wav|avi|mov|mpeg|ram|m4v|pdf" 
                                                      + "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

    /**
     * You should implement this function to specify whether
     * the given url should be crawled or not (based on your
     * crawling logic).
     */
    @Override
    public boolean shouldVisit(WebURL url) {
            String href = url.getURL().toLowerCase();
            return !FILTERS.matcher(href).matches();// && href.startsWith("http://blog.vogella.com/");
    }

    /**
     * This function is called when a page is fetched and ready 
     * to be processed by your program.
     */
    @Override
    public void visit(Page page) {          
            String url = page.getWebURL().getURL();
            System.out.println("URL: " + url);
            WebPageInfo info = new WebPageInfo();
            CallerDAO dao = new CallerDAOImpl();
            info.setUrl(url);
            if (page.getParseData() instanceof HtmlParseData) {
                    HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
                    String text = htmlParseData.getText();
                    String html = htmlParseData.getHtml();
                    List<WebURL> links = htmlParseData.getOutgoingUrls();
                    Document doc = Jsoup.parse(html);
                    
                    	IdentifyTagLines tagging = new IdentifyTagLines();
                    	tagging.setDoc(doc);
						try {
							tagging.process();
						} catch (IOException e) {
							
							e.printStackTrace();
						}
					
                    System.out.println(htmlParseData.getTitle());
                    info.setTags(htmlParseData.getTitle());
                   
                    dao.write(info);
                  
                    System.out.println("Text length: " + text.length());
                    System.out.println("Html length: " + html.length());
                    System.out.println("Number of outgoing links: " + links.size());
                    
            }
    }

	private void invokeZemanta(String url, String text) {
		UrlInfo urlInfo = new UrlInfo();
		urlInfo.setUrl("http://api.zemanta.com/services/rest/0.0/");
		urlInfo.setMethod("POST");
		//urlInfo.setQueryString(queryString);
	}


}
