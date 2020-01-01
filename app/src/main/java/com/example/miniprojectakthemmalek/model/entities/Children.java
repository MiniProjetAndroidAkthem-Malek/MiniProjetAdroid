package com.example.miniprojectakthemmalek.model.entities;

import com.example.miniprojectakthemmalek.model.entities.Enums.Disease;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Children {

    @SerializedName("id")
    int id;

    @SerializedName("name")
    String name;

    @SerializedName("birthday")
    String birthday;

    @SerializedName("sexe")
    String sexe;

    @SerializedName("parent")
    String parent;

    @SerializedName("description")
    String description;

    @SerializedName("disease")
    Disease disease;


    public Children() {
    }

    public Children(String name, String birthday, String sexe, String parent, String description, Disease disease) {
        this.name = name;
        this.birthday = birthday;
        this.sexe = sexe;
        this.parent = parent;
        this.description = description;
        this.disease = disease;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }
}
