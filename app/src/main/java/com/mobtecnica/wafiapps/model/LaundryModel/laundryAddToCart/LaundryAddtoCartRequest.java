package com.mobtecnica.wafiapps.model.LaundryModel.laundryAddToCart;

/**
 * Created by SIby on 18-04-2017.
 */

public class LaundryAddtoCartRequest {
    private LaundryAddToCartModel model;

    private String emailID;

    private String apiToken;

    public LaundryAddToCartModel getModel() {
        return model;
    }

    public void setModel(LaundryAddToCartModel model) {
        this.model = model;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    @Override
    public String toString() {
        return "ClassPojo [model = " + model + ", emailID = " + emailID + ", apiToken = " + apiToken + "]";
    }
}
