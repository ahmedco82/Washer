package com.ahmedibrahim.waher.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by cca on 24/01/2019.
 */

public class Itemchange {

    @SerializedName("value")
    private String value;
    @SerializedName("index")
    private int index;



    @SerializedName("message")
    private String message;

    public Itemchange(String value ) {
        this.value = value;
      //  this.index =indx;
    }
    public String getValue() {
        return value;
    }
    public int getIndex() {
        return index;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public void setIndex(int index) {
        this.index = index;
    }

    public String getMessage() {
        return message;
    }

}
