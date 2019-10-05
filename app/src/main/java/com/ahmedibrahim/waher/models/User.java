package com.ahmedibrahim.waher.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by cca on 05/12/2018.
 */


public class User extends RealmObject {

    @SerializedName("user_name")
    public String user_name;
    @SerializedName("user_password")
    public String user_password;
    @SerializedName("user_email")
    public String user_email;
    @SerializedName("user_id")
    public int user_id;
    @SerializedName("user_token")
    public String user_token;


    public int id;
    public boolean isAdmin;

    public String name;
    public String email;
    public int id_id;


    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId_id(int id_id) {
        this.id_id = id_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getId_id() {
        return id_id;
    }
}