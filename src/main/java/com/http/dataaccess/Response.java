package com.http.dataaccess;

public class Response {
    private String body;
    private String head;

    public Response(String string) {
        body = string;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }
}
