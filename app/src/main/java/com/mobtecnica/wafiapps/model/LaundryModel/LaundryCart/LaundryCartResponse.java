package com.mobtecnica.wafiapps.model.LaundryModel.LaundryCart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by SIby on 12-04-2017.
 */

public class LaundryCartResponse {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("statusCode")
    @Expose
    private String statusCode;
    @SerializedName("data")
    @Expose
    private DataLaundryCart data;

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

    public DataLaundryCart getData() {
        return data;
    }

    public void setData(DataLaundryCart data) {
        this.data = data;
    }
}
