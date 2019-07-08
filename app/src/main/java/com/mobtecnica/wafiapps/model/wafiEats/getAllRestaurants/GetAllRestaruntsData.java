
package com.mobtecnica.wafiapps.model.wafiEats.getAllRestaurants;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.mobtecnica.wafiapps.model.wafiEats.getRestaurants.RestaurantsInArea;

public class GetAllRestaruntsData {

    @SerializedName("AllRestaurants")
    @Expose
    private List<RestaurantsInArea> allRestaurants = null;

    public List<RestaurantsInArea> getAllRestaurants() {
        return allRestaurants;
    }

    public void setAllRestaurants(List<RestaurantsInArea> allRestaurants) {
        this.allRestaurants = allRestaurants;
    }

}
