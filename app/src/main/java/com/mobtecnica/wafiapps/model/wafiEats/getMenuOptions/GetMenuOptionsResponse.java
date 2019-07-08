
package com.mobtecnica.wafiapps.model.wafiEats.getMenuOptions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetMenuOptionsResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("statusCode")
    @Expose
    private String statusCode;
    @SerializedName("data")
    @Expose
    private GetMenuOptionsData data;

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

    public GetMenuOptionsData getData() {
        return data;
    }

    public void setData(GetMenuOptionsData data) {
        this.data = data;
    }

}
