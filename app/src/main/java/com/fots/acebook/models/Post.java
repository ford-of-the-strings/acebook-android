package com.fots.acebook.models;

import java.io.Serializable;
import java.util.Date;

public class Post implements Serializable {


    private String body;
    private Date created;


    public String getBody() {
        return body;
    }

    public void setBody(String b) {
        body = b;
    }

    public Date getTime() {
        return created;
    }

    public void setTime(Date time) {
        created = time;
    }

    public Post() {}
    public Post(String b, Date time) {

        body = b;
        created = time;
    }
}
