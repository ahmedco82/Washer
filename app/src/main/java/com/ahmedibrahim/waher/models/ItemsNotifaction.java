package com.ahmedibrahim.waher.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by cca on 01/01/2019.
 */

public class ItemsNotifaction {

    @SerializedName("user_id")
    public int user_id;

    @SerializedName("title")
    public String title;

    @SerializedName("message")
    public String message;
}
