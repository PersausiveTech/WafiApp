package com.mobtecnica.wafiapps.model.LaundryModel.laundry_checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AreaList {
    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getCustGuid() {
        return custGuid;
    }

    public void setCustGuid(String custGuid) {
        this.custGuid = custGuid;
    }



    @SerializedName("apiToken")
    @Expose
    private String apiToken;
    @SerializedName("custGuid")
    @Expose
    private String custGuid;

}
