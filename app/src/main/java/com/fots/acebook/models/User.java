package com.fots.acebook.models;

public class User {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public User() {}

    public User(String n) {
        name = n;
    }
}
