package com.example.miniprojectakthemmalek.model.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

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

    @ColumnInfo(name = "sexe")
    @SerializedName("sexe")
    String sexe;

    @ColumnInfo(name = "partner")
    @SerializedName("partner")
    String partner;

    @ColumnInfo(name = "birth_date")
    @SerializedName("birth_date")
    String birth_date;

    @ColumnInfo(name = "job")
    @SerializedName("job")
    String job;

    @ColumnInfo(name = "number_children")
    @SerializedName("number_children")
    int numChildren;

    @ColumnInfo(name = "number_children_disabilities")
    @SerializedName("number_children_disabilities")
    int numDisableChildren;

    @ColumnInfo(name = "isActive")
    @SerializedName("isActive")
    int active;

    @ColumnInfo(name = "rememberMe")
    int rememberMe;

    @ColumnInfo(name = "theme_r")
    int theme_r;

    @ColumnInfo(name = "theme_g")
    int theme_g;

    @ColumnInfo(name = "theme_b")
    int theme_b;


    @Ignore
    public User(String username, String first_name, String last_name) {
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public User(String username, String password, String sexe, String birth_date, int numChildren, int numDisableChildren) {
        this.username = username;
        this.password = password;
        this.sexe = sexe;
        this.birth_date = birth_date;
        this.numChildren = numChildren;
        this.numDisableChildren = numDisableChildren;
    }
@Ignore
    public User(String username, String partner) {
        this.username = username;
        this.partner = partner;
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
    public User(String username, String password, String email, String first_name, String last_name, Long phone_number, String address, int isActive, int rememberMe,int theme_r,int theme_g,int theme_b) {

        this.username = username;
        this.password = password;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.address = address;
        this.rememberMe=rememberMe;
        this.theme_r=theme_r;
        this.theme_g =theme_g;
        this.theme_b=theme_b;

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



    public int getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(int rememberMe) {
        this.rememberMe = rememberMe;
    }

    public int getTheme_r() {
        return theme_r;
    }

    public void setTheme_r(int theme_r) {
        this.theme_r = theme_r;
    }

    public int getTheme_g() {
        return theme_g;
    }

    public void setTheme_g(int theme_g) {
        this.theme_g = theme_g;
    }

    public int getTheme_b() {
        return theme_b;
    }

    public void setTheme_b(int theme_b) {
        this.theme_b = theme_b;
    }


    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String  birth_date) {
        this.birth_date = birth_date;
    }

    public int getNumChildren() {
        return numChildren;
    }

    public void setNumChildren(int numChildren) {
        this.numChildren = numChildren;
    }

    public int getNumDisableChildren() {
        return numDisableChildren;
    }

    public void setNumDisableChildren(int numDisableChildren) {
        this.numDisableChildren = numDisableChildren;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
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
                ", sexe='" + sexe + '\'' +
                ", partner='" + partner + '\'' +
                ", birth_date='" + birth_date + '\'' +
                ", job='" + job + '\'' +
                ", numChildren=" + numChildren +
                ", numDisableChildren=" + numDisableChildren +
                ", active=" + active +
                ", rememberMe=" + rememberMe +
                ", theme_r=" + theme_r +
                ", theme_g=" + theme_g +
                ", theme_b=" + theme_b +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, first_name, last_name, phone_number, address, sexe, partner, rememberMe, theme_r, theme_g, theme_b);
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
