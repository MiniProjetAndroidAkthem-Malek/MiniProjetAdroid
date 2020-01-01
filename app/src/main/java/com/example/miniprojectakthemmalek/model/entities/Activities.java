package com.example.miniprojectakthemmalek.model.entities;

import com.example.miniprojectakthemmalek.model.entities.Enums.ActivityType;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class Activities {

    @SerializedName("id")
    int id;

    @SerializedName("username")
    String username;

    @SerializedName("actWith")
    String actWith;

    @SerializedName("actIn")
    Timestamp actIn;

    @SerializedName("type")
    ActivityType activityType;

    @SerializedName("idActWith")
    int idActWith;

    public Activities(String username, String actWith, ActivityType activityType) {
        this.username = username;
        this.actWith = actWith;
        this.activityType = activityType;
    }

    public Activities(String username, String actWith, ActivityType activityType, int idActWith) {
        this.username = username;
        this.actWith = actWith;
        this.activityType = activityType;
        this.idActWith = idActWith;
    }

    public Activities(String username, String actWith, Timestamp actIn, ActivityType activityType) {
        this.username = username;
        this.actWith = actWith;
        this.actIn = actIn;
        this.activityType = activityType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getActWith() {
        return actWith;
    }

    public void setActWith(String actWith) {
        this.actWith = actWith;
    }

    public Timestamp getActIn() {
        return actIn;
    }

    public void setActIn(Timestamp actIn) {
        this.actIn = actIn;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdActWith() {
        return idActWith;
    }

    public void setIdActWith(int idActWith) {
        this.idActWith = idActWith;
    }
}
