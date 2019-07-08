package com.mobtecnica.wafiapps.model.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BasicRequest {

    @SerializedName("apiToken")
    @Expose
    private String apiToken;
    @SerializedName("guid")
    @Expose
    private String guid;

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

}
