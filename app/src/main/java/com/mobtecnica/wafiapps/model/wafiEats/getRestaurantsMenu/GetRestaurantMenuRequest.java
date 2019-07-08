
package com.mobtecnica.wafiapps.model.wafiEats.getRestaurantsMenu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetRestaurantMenuRequest {

    @SerializedName("apiToken")
    @Expose
    private String apiToken;
    @SerializedName("LocationID")
    @Expose
    private String locationID;
    @SerializedName("ShopID")
    @Expose
    private String shopID;

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

    public String getShopID() {
        return shopID;
    }

    public void setShopID(String shopID) {
        this.shopID = shopID;
    }

}
