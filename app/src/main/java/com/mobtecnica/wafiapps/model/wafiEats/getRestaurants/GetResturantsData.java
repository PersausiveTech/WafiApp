
package com.mobtecnica.wafiapps.model.wafiEats.getRestaurants;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetResturantsData {

    @SerializedName("RestaurantsInArea")
    @Expose
    private List<RestaurantsInArea> restaurantsInArea = null;

    public List<RestaurantsInArea> getRestaurantsInArea() {
        return restaurantsInArea;
    }

    public void setRestaurantsInArea(List<RestaurantsInArea> restaurantsInArea) {
        this.restaurantsInArea = restaurantsInArea;
    }

}
