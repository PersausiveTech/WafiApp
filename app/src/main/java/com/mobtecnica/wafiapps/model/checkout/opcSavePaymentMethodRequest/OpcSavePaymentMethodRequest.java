
package com.mobtecnica.wafiapps.model.checkout.opcSavePaymentMethodRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OpcSavePaymentMethodRequest {

    @SerializedName("apiToken")
    @Expose
    private String apiToken;

    @SerializedName("guid")
    @Expose
    private String guid;

    @SerializedName("paymentmethod")
    @Expose
    private String paymentmethod;

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

    public String getPaymentmethod() {
        return paymentmethod;
    }

    public void setPaymentmethod(String paymentmethod) {
        this.paymentmethod = paymentmethod;
    }
}
