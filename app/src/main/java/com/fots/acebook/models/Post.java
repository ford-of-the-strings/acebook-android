package com.fots.acebook.models;

import java.io.Serializable;

public class Post implements Serializable {

    private String title;
    private String body;

    public String getTitle() {
        return title;
    }

    public void setTitle(String t) {
        title = t;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String b) {
        body = b;
    }

    public Post() {}
    public Post(String t, String b) {
        title = t;
        body = b;
    }
}
