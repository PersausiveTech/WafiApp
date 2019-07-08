package com.mobtecnica.wafiapps.model.wafiEats.cartRestaurant;

/**
 * Created by SIby on 10-06-2017.
 */

public class CartRequest {
    String apiToken;
    String emailID;

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }
}
