
package com.mobtecnica.wafiapps.model.LaundryModel.removeFromCart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RemoveFromCartRequestLaundry {

    @SerializedName("apiToken")
    @Expose
    private String apiToken;
    @SerializedName("emailID")
    @Expose
    private String emailID;
    @SerializedName("updateModel")
    @Expose
    private UpdateModel updateModel;

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public UpdateModel getUpdateModel() {
        return updateModel;
    }

    public void setUpdateModel(UpdateModel updateModel) {
        this.updateModel = updateModel;
    }

}
