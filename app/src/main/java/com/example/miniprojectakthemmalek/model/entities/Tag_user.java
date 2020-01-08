package com.example.miniprojectakthemmalek.model.entities;

import com.google.gson.annotations.SerializedName;

public class Tag_user {

    @SerializedName("id")
    int id;

    @SerializedName("username")
    String username;

    @SerializedName("name")
    String name;


    public Tag_user(String username, String name) {
        this.username = username;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
