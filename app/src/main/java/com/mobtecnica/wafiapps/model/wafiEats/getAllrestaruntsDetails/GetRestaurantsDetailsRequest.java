package com.mobtecnica.wafiapps.model.wafiEats.getAllrestaruntsDetails;

/**
 * Created by SIby on 05-05-2017.
 */

public class GetRestaurantsDetailsRequest {
    String apiToken;
    String ShopID;

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getShopID() {
        return ShopID;
    }

    public void setShopID(String shopID) {
        ShopID = shopID;
    }
}
