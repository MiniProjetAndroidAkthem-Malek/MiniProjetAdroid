package com.example.miniprojectakthemmalek.model.entities;

import com.google.gson.annotations.SerializedName;

public class Tag {

    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;

    public Tag(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
