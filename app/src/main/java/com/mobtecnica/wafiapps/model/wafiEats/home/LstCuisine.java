
package com.mobtecnica.wafiapps.model.wafiEats.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstCuisine {

    @SerializedName("CuisineID")
    @Expose
    private Integer cuisineID;
    @SerializedName("Cuisine")
    @Expose
    private String cuisine;

    public Integer getCuisineID() {
        return cuisineID;
    }

    public void setCuisineID(Integer cuisineID) {
        this.cuisineID = cuisineID;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

}
