package com.ahmedibrahim.waher.models;

/**
 * Created by cca on 07/12/2018.
 */

public class ProductRecyclerViewItem {


    private String productName;
    private String productInstantcName;
    // Save car image resource id.
    private int productImge;

    private int productCount;


    public ProductRecyclerViewItem(int productImge ,String productName ,String productInstantcName) {
        this.productName = productName;
        this.productImge = productImge;
        this.productInstantcName = productInstantcName;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductImge() {
        return productImge;
    }

    public void setProductName(String productName) {

        this.productName = productName;
    }

    public void setProductImge(int productImge) {
        this.productImge = productImge;
    }

    public String getProductInstantcName() {
        return productInstantcName;
    }

    public void setProductInstantcName(String productInstantcName) {
        this.productInstantcName = productInstantcName;
    }
}
