package com.fots.acebook.models;

import java.io.Serializable;

public class Post implements Serializable {


    private String body;
    private String created;


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
    public Post(String b, String time) {

        body = b;
        created = time;
    }
}
