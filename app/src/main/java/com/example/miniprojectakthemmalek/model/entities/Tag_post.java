package com.example.miniprojectakthemmalek.model.entities;

import com.google.gson.annotations.SerializedName;

public class Tag_post {

    @SerializedName("id")
    int id;
    @SerializedName("idpost")
    int idpost;
    @SerializedName("name")
    String name;

    public Tag_post(int idpost, String name) {
        this.idpost = idpost;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdpost() {
        return idpost;
    }

    public void setIdpost(int idpost) {
        this.idpost = idpost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
