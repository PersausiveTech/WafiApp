package com.mobtecnica.wafiapps.model.wafiEats.getRestaurants;

/**
 * Created by SIby on 05-05-2017.
 */

public class GetResturantsRequest {
    String apiToken;
    String locationID;
    String cuisineID;

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getLocationID() {
        return locationID;
    }

    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }

    public String getCuisineID() {
        return cuisineID;
    }

    public void setCuisineID(String cuisineID) {
        this.cuisineID = cuisineID;
    }
}
