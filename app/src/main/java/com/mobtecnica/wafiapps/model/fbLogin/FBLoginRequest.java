package com.mobtecnica.wafiapps.model.fbLogin;

/**
 * Created by Ashik V Ashraf on 09-08-2017.
 */

public class FBLoginRequest {
    String apitoken;
    FbParameters fbparameters;

    public FbParameters getFbparameters() {
        return fbparameters;
    }

    public void setFbparameters(FbParameters fbparameters) {
        this.fbparameters = fbparameters;
    }

    public FBLoginRequest() {
    }

    public String getApitoken() {
        return apitoken;
    }

    public void setApitoken(String apitoken) {
        this.apitoken = apitoken;
    }


}
