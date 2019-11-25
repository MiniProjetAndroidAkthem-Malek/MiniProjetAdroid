package com.example.miniprojectakthemmalek.model.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


@SuppressWarnings("ALL")
@Entity(tableName = "t_user")
public class User implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    int id;

    @ColumnInfo(name = "username")
    @SerializedName("username")
    String username;

    @ColumnInfo(name = "password")
    @SerializedName("password")
    String password;

    @ColumnInfo(name = "email")
    @SerializedName("email")
    String email;

    @ColumnInfo(name = "first_name")
    @SerializedName("first_name")
    String first_name;

    @ColumnInfo(name = "last_name")
    @SerializedName("last_name")
    String last_name;

    @ColumnInfo(name = "phone_number")
    @SerializedName("phone_number")
    Long phone_number;

    @ColumnInfo(name = "address")
    @SerializedName("address")
    String address;

    @ColumnInfo(name = "isActive")
    @SerializedName("isActive")
    int isActive;

    @ColumnInfo(name = "rememberMe")
    int rememberMe;

    @Ignore
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Ignore
    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
    @Ignore
    public User(int id, String username, String password, String email, String first_name, String last_name, String address) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
    }
    @Ignore
    public User(String username, String password, String email, String first_name, String last_name, Long phone_number, String address, int isActive) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.address = address;
        this.isActive = isActive;
    }

    public User(String username, String password, String email, String first_name, String last_name, Long phone_number, String address, int isActive, int rememberMe) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.address = address;
        this.isActive = isActive;
        this.rememberMe=rememberMe;
    }
    @Ignore
    public User(int id, String username, String password, String email, String first_name, String last_name, Long phone_number, String address, int isActive) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.address = address;
        this.isActive = isActive;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Long getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(Long phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int isActive() {
        return isActive;
    }

    public void setActive(int active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", phone_number=" + phone_number +
                ", address='" + address + '\'' +
                ", isActive=" + isActive +
                '}';
    }



    public int getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(int rememberMe) {
        this.rememberMe = rememberMe;
    }
}
