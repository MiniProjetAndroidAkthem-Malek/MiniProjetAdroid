package com.example.miniprojectakthemmalek.model.entities;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class Event {

    @SerializedName("id")
    int id;

    @SerializedName("nom")
    String nom;

    @SerializedName("description")
    String description;

    @SerializedName("state")
    String state;

    @SerializedName("contry")
    String contry;

    @SerializedName("creator")
    String creator;

    @SerializedName("date")
    Timestamp date;


    public Event() {
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Event(String nom, String description, String state, String contry, String creator, Timestamp date) {
        this.nom = nom;
        this.description = description;
        this.state = state;
        this.contry = contry;
        this.creator = creator;
        this.date = date;
    }

    public Event(int id, String nom, String description, String state, String contry, String creator) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.state = state;
        this.contry = contry;
        this.creator = creator;
    }

    public Event(String nom, String description, String state, String contry, String creator) {
        this.nom = nom;
        this.description = description;
        this.state = state;
        this.contry = contry;
        this.creator = creator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getContry() {
        return contry;
    }

    public void setContry(String contry) {
        this.contry = contry;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Event(String nom, String description, String state, String contry) {
        this.nom = nom;
        this.description = description;
        this.state = state;
        this.contry = contry;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", state='" + state + '\'' +
                ", contry='" + contry + '\'' +
                ", creator='" + creator + '\'' +
                '}';
    }
}