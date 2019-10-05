package com.ahmedibrahim.waher.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by cca on 23/12/2018.
 */

public class Customer implements Serializable {


    @SerializedName("message")
    public String message;

    public String name;
    public String title;
    public String type;
    public String id;
    public int user_id;

    public int status;


    public Customer(String type) {

        this.type = type;
    }
}
