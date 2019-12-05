package com.example.miniprojectakthemmalek.model.entities;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class Follow {

    @SerializedName("id")
    int id;

    @SerializedName("username")
    String username;

    @SerializedName("following")
    String following;

    @SerializedName("followOn")
    Timestamp followOn;

    public Follow(String username, String following) {
        this.username = username;
        this.following = following;
    }

    public Follow(String username, String following, Timestamp followOn) {
        this.username = username;
        this.following = following;
        this.followOn = followOn;
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

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public Timestamp getFollowOn() {
        return followOn;
    }

    public void setFollowOn(Timestamp followOn) {
        this.followOn = followOn;
    }

    @Override
    public String toString() {
        return "Follow{" +
                "username='" + username + '\'' +
                ", following='" + following + '\'' +
                ", followOn=" + followOn +
                '}';
    }
}
