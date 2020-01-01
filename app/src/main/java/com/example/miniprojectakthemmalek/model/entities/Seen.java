package com.example.miniprojectakthemmalek.model.entities;

public class Seen {



    int id;

    int id_message;
    String username;
    String seen_in;

    public Seen(int id_message, String username) {
        this.id_message = id_message;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_message() {
        return id_message;
    }

    public void setId_message(int id_message) {
        this.id_message = id_message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSeen_in() {
        return seen_in;
    }

    public void setSeen_in(String seen_in) {
        this.seen_in = seen_in;
    }
}
