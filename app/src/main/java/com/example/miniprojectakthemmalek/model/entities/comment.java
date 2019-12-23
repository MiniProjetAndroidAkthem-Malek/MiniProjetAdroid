package com.example.miniprojectakthemmalek.model.entities;

import com.google.gson.annotations.SerializedName;

public class comment {

    @SerializedName("id")
    int id;
    @SerializedName("post")
    int post;
    @SerializedName("username")
    String username;
    @SerializedName("comment")
    String comment;

    public comment(int id, int post, String username, String comment) {
        this.id = id;
        this.post = post;
        this.username = username;
        this.comment = comment;
    }

    public comment(int post, String username, String comment) {
        this.post = post;
        this.username = username;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPost() {
        return post;
    }

    public void setPost(int post) {
        this.post = post;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
