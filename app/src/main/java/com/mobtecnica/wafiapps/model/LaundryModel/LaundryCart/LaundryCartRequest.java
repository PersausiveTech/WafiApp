package com.mobtecnica.wafiapps.model.LaundryModel.LaundryCart;

/**
 * Created by SIby on 12-04-2017.
 */

public class LaundryCartRequest {
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
