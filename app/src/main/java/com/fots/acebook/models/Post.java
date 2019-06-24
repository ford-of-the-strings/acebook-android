package com.fots.acebook.models;

import java.io.Serializable;

public class Post implements Serializable {

    private String title;
    private String body;
    private String created;

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

    public String getTime() {
        return created;
    }

    public void setTime(String time) {
        created = time;
    }

    public Post() {}
    public Post(String t, String b, String time) {
        title = t;
        body = b;
        created = time;
    }
}
