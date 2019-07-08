
package com.mobtecnica.wafiapps.model.checkout.opcSaveShippingMethod;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OpcSaveShippingMethodRequest {

    @SerializedName("apiToken")
    @Expose
    private String apiToken;
    @SerializedName("guid")
    @Expose
    private String guid;
    @SerializedName("shippingoption")
    @Expose
    private String shippingoption;

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

    public String getShippingoption() {
        return shippingoption;
    }

    public void setShippingoption(String shippingoption) {
        this.shippingoption = shippingoption;
    }
}
