package com.example.miniprojectakthemmalek.model.entities;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class Message {

    @SerializedName("id")
    int id;
    @SerializedName("sender")
    String sender;

    @SerializedName("receiver")
    String receiver;

    @SerializedName("message")
    String message;

    @SerializedName("send_in")
    Timestamp send_in;


    public Message(String sender, String receiver, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getSend_in() {
        return send_in;
    }

    public void setSend_in(Timestamp send_in) {
        this.send_in = send_in;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
