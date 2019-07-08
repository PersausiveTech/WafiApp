
package com.mobtecnica.wafiapps.model.checkout.opcSaveShipping;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OpcSaveShippingRequest {

    @SerializedName("apiToken")
    @Expose
    private String apiToken;
    @SerializedName("guid")
    @Expose
    private String guid;
    @SerializedName("shipping_address_id")
    @Expose
    private String shippingAddressId;

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

    public String getShippingAddressId() {
        return shippingAddressId;
    }

    public void setShippingAddressId(String shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }

}
