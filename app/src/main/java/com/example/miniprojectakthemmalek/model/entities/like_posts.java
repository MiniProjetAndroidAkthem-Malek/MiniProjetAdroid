package com.example.miniprojectakthemmalek.model.entities;

import com.google.gson.annotations.SerializedName;

public class like_posts {
    @SerializedName("id")
    int id;
    @SerializedName("id_post")
    int id_post;
    @SerializedName("username")
    String username;

    public like_posts(int id, int id_post, String username) {
        this.id = id;
        this.id_post = id_post;
        this.username = username;
    }

    public like_posts(int id_post, String username) {
        this.id_post = id_post;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_post() {
        return id_post;
    }

    public void setId_post(int id_post) {
        this.id_post = id_post;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
