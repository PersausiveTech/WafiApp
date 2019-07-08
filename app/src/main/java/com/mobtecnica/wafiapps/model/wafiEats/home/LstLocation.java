
package com.mobtecnica.wafiapps.model.wafiEats.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstLocation {

    @SerializedName("LocationID")
    @Expose
    private Integer locationID;
    @SerializedName("Location")
    @Expose
    private String location;

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
