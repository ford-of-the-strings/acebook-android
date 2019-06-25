package com.fots.acebook.models;

import java.io.Serializable;

public class Post implements Serializable {


    private String body;
    private String created;
    private String uid;

    public String getUid() { return uid;}

    public void setUid(String name) { uid = name; }


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
    public Post(String b, String time, String name) {

        body = b;
        created = time;
        uid = name;
    }
}
