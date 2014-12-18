package com.http.dataaccess;

import java.util.HashMap;
import java.util.Map;

public class UrlInfo {
    private String url;
    private String method;
    private Map<String, String> headers = new HashMap<String, String>();
    private String queryString;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {// text method &
        this.queryString = queryString;
    }

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

}