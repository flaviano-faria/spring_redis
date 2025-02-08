package com.redis.domain;

public class User {

    private String id;
    private String name;

    public User(String id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public User toUser(String id, String name){
        return new User(id, name);
    }
}
