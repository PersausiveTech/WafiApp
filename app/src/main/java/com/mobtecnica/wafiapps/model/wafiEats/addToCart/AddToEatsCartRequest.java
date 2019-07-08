package com.mobtecnica.wafiapps.model.wafiEats.addToCart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddToEatsCartRequest {

    @SerializedName("apiToken")
    @Expose
    private String apiToken;
    @SerializedName("emailID")
    @Expose
    private String emailID;
    @SerializedName("model")
    @Expose
    private Model model;

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

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

}
