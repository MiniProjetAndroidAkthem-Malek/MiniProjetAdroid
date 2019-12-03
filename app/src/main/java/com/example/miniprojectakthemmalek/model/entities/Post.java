package com.example.miniprojectakthemmalek.model.entities;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Post {

    @SerializedName("id")
    int id;
    @SerializedName("username")
    String username;
    @SerializedName("posted_in")
    Date posted_in;
    @SerializedName("description")
    String description;

    public Post(String username, String description) {
        this.username = username;
        this.description = description;
    }

    public Post(String username, Date posted_in, String description) {
        this.username = username;
        this.posted_in = posted_in;
        this.description = description;
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

    public Date getPosted_in() {
        return posted_in;
    }

    public void setPosted_in(Date posted_in) {
        this.posted_in = posted_in;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", posted_in=" + posted_in +
                ", description='" + description + '\'' +
                '}';
    }




}
