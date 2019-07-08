
package com.mobtecnica.wafiapps.model.wafiEats.getAllrestaruntsDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListShopLocation {

    @SerializedName("ShopID")
    @Expose
    private String shopID;
    @SerializedName("LocationID")
    @Expose
    private Integer locationID;
    @SerializedName("Location")
    @Expose
    private String location;

    public String getShopID() {
        return shopID;
    }

    public void setShopID(String shopID) {
        this.shopID = shopID;
    }

    public Integer getLocationID() {
        return locationID;
    }

    public void setLocationID(Integer locationID) {
        this.locationID = locationID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
