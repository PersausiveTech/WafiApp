package com.mobtecnica.wafiapps.model.forgotPassword;

/**
 * Created by SIby on 23-01-2017.
 */


public class ForgotPasswordRequest {
    private Model model;

    private String apiToken;

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    @Override
    public String toString() {
        return "ClassPojo [model = " + model + ", apiToken = " + apiToken + "]";
    }
}