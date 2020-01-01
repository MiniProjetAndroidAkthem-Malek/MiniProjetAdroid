package com.example.miniprojectakthemmalek.model.entities;

import com.example.miniprojectakthemmalek.model.entities.Enums.Role;
import com.example.miniprojectakthemmalek.model.entities.Enums.Status;
import com.google.gson.annotations.SerializedName;

public class GroupUser {

    @SerializedName("id")
    int id;
    @SerializedName("username")
    String username;
    @SerializedName("groupe_name")
    String groupe_name;
    @SerializedName("role")
    Role role;
    @SerializedName("status")
    Status status;

    public GroupUser() {
    }

    public GroupUser(String username, String groupe_name, Role role, Status status) {
        this.username = username;
        this.groupe_name = groupe_name;
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

    public String getGroupe_name() {
        return groupe_name;
    }

    public void setGroupe_name(String groupe_name) {
        this.groupe_name = groupe_name;
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
}
