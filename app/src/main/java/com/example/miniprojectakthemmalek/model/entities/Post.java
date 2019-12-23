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

    @SerializedName("group_name")
    String group_name;

    @SerializedName("position")
    String position;

    public Post(String username, String description) {
        this.username = username;
        this.description = description;
    }

    public Post(String username, Date posted_in, String description) {
        this.username = username;
        this.posted_in = posted_in;
        this.description = description;
    }

    public Post(String username, String description, String position) {
        this.username = username;
        this.description = description;
        this.position = position;
    }

    public Post(String username, String description, String group_name, String position) {
        this.username = username;
        this.description = description;
        this.group_name = group_name;
        this.position = position;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
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
