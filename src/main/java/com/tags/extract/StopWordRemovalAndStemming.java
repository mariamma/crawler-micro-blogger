package com.tags.extract;

import org.apache.lucene.analysis.PorterStemFilter;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.apache.lucene.util.Version;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;


public class StopWordRemovalAndStemming {

    public String process(String input) throws IOException {
        Set<String> stopWords = new HashSet<String>();
        stopWords.add("a");
        stopWords.add("I");
        stopWords.add("the");
        stopWords.add("in");
        stopWords.add("is");
        stopWords.add("tabular");
        stopWords.add("and");
        stopWords.add("am");
        stopWords.add("all");
        stopWords.add("of");
        stopWords.add("on");
        stopWords.add("our");
        stopWords.add("with");
        stopWords.add("are");
        stopWords.add("it");
        stopWords.add("for");
        stopWords.add("to");
        stopWords.add("be");
        stopWords.add("this");
        stopWords.add("how");
        stopWords.add("by");
        stopWords.add("div");
        stopWords.add("class");
        stopWords.add("span");
        stopWords.add("link");
        stopWords.add("href");
        stopWords.add("http");
        stopWords.add("rel");
        stopWords.add("li");
        stopWords.add("br");
        stopWords.add("p");
        stopWords.add("h2");
        stopWords.add("h6");
        stopWords.add("meta");
        stopWords.add("property");
        stopWords.add("article");
        stopWords.add("content");
        stopWords.add("data");
        stopWords.add("amp");


        TokenStream tokenStream = new StandardTokenizer(
                Version.LUCENE_30, new StringReader(input));
        tokenStream = new StopFilter(true, tokenStream, stopWords);
        tokenStream = new PorterStemFilter(tokenStream);

        StringBuilder sb = new StringBuilder();
        TermAttribute termAttr = tokenStream.getAttribute(TermAttribute.class);
        while (tokenStream.incrementToken()) {
            if (sb.length() > 0) {
                sb.append(" ");
            }
            sb.append(termAttr.term());
        }
        return sb.toString();
    }
}
