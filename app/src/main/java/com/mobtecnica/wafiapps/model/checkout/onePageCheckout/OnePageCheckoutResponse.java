
package com.mobtecnica.wafiapps.model.checkout.onePageCheckout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OnePageCheckoutResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("statusCode")
    @Expose
    private String statusCode;
    @SerializedName("data")
    @Expose
    private DataOnePage data;

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

    public DataOnePage getData() {
        return data;
    }

    public void setData(DataOnePage data) {
        this.data = data;
    }

}
