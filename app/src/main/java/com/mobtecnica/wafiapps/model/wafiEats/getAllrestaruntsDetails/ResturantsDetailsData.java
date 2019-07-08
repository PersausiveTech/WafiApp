
package com.mobtecnica.wafiapps.model.wafiEats.getAllrestaruntsDetails;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResturantsDetailsData {

    @SerializedName("Restaurant")
    @Expose
    private Restaurant restaurant;
    @SerializedName("listShopLocations")
    @Expose
    private List<ListShopLocation> listShopLocations = null;
    @SerializedName("listShopCuisines")
    @Expose
    private List<ListShopCuisine> listShopCuisines = null;

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<ListShopLocation> getListShopLocations() {
        return listShopLocations;
    }

    public void setListShopLocations(List<ListShopLocation> listShopLocations) {
        this.listShopLocations = listShopLocations;
    }

    public List<ListShopCuisine> getListShopCuisines() {
        return listShopCuisines;
    }

    public void setListShopCuisines(List<ListShopCuisine> listShopCuisines) {
        this.listShopCuisines = listShopCuisines;
    }

}
