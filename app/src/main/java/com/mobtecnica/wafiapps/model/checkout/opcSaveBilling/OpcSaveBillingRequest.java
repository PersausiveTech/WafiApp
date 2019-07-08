package com.mobtecnica.wafiapps.model.checkout.opcSaveBilling;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by SIby on 20-04-2017.
 */

public class OpcSaveBillingRequest {

    @SerializedName("apiToken")
    @Expose
    private String apiToken;

    @SerializedName("guid")
    @Expose
    private String guid;

    @SerializedName("billing_address_id")
    @Expose
    private String billingAddressId;

    @SerializedName("ShipToSameAddress")
    @Expose
    private String shipToSameAddress;

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

    public String getBillingAddressId() {
        return billingAddressId;
    }

    public void setBillingAddressId(String billingAddressId) {
        this.billingAddressId = billingAddressId;
    }

    public String getShipToSameAddress() {
        return shipToSameAddress;
    }

    public void setShipToSameAddress(String shipToSameAddress) {
        this.shipToSameAddress = shipToSameAddress;
    }
}
