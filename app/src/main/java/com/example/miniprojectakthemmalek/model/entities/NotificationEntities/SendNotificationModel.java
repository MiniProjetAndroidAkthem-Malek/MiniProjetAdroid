package com.example.miniprojectakthemmalek.model.entities.NotificationEntities;

import com.google.gson.annotations.SerializedName;

public class SendNotificationModel {

    @SerializedName("title")
    private String title;
    @SerializedName("text")
    private  String text;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
