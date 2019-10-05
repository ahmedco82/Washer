package com.ahmedibrahim.waher.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by cca on 16/12/2018.
 */

public class Request{

    @SerializedName("user_id")
    public int user_id;
    @SerializedName("name")
    public String name;
    @SerializedName("title")
    public String title;
    @SerializedName("total_parts")
    public int total_parts;
    @SerializedName("total_price")
    public int total_price;
    @SerializedName("status")
    public String status;
    @SerializedName("day")
    public String day;
    @SerializedName("hour")
    public String hour;

}
