package com.example.miniprojectakthemmalek.model.entities;

import com.google.gson.annotations.SerializedName;

public class EventUser {

    @SerializedName("id")
    int id;
    @SerializedName("username")
    String username;
    @SerializedName("event_name")
    String event_name;
    @SerializedName("role")
    Role role;
    @SerializedName("status")
    Status status;

    public EventUser() {
    }

    public EventUser(int id, String username, String event_name, Role role, Status status) {
        this.id = id;
        this.username = username;
        this.event_name = event_name;
        this.role = role;
        this.status = status;
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

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public EventUser(String username, String event_name, Role role) {
        this.username = username;
        this.event_name = event_name;
        this.role = role;
    }

    public EventUser(String username, String event_name, Role role, Status status) {
        this.username = username;
        this.event_name = event_name;
        this.role = role;
        this.status = status;
    }
}
