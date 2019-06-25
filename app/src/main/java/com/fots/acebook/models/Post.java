package com.fots.acebook.models;

import java.io.Serializable;
import java.util.Date;

public class Post implements Serializable {


    private String body;
    private Date created;
    private String uid;

    public String getUid() { return uid;}

    public void setUid(String id) { uid = id; }




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

    public Post(String b, Date time, String id) {

        body = b;
        created = time;
        uid = id;
    }
}
