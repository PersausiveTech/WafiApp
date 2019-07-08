
package com.mobtecnica.wafiapps.model.wafiEats.getRestaurantsMenu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CuisineList {

    @SerializedName("ShopID")
    @Expose
    private Integer shopID;
    @SerializedName("CuisineID")
    @Expose
    private Integer cuisineID;
    @SerializedName("Cuisine")
    @Expose
    private String cuisine;

    public Integer getShopID() {
        return shopID;
    }

    public void setShopID(Integer shopID) {
        this.shopID = shopID;
    }

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
