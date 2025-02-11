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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserDTO toUserDTO(){

        return new UserDTO(this.id, this.name);
    }
}
