package com.mobtecnica.wafiapps.model.wafiEats.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EatsHomeResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("statusCode")
    @Expose
    private String statusCode;
    @SerializedName("data")
    @Expose
    private EatsHomeData data;

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

    public EatsHomeData getData() {
        return data;
    }

    public void setData(EatsHomeData data) {
        this.data = data;
    }

}