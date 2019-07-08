
package com.mobtecnica.wafiapps.model.wafiEats.Offers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EatsGetAllOffersResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("statusCode")
    @Expose
    private String statusCode;
    @SerializedName("data")
    @Expose
    private EatsOffersData data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public EatsOffersData getData() {
        return data;
    }

    public void setData(EatsOffersData data) {
        this.data = data;
    }

}
