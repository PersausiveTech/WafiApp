
package com.mobtecnica.wafiapps.model.wishlist.addToCart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddToCartFromWishListRequest {

    @SerializedName("apiToken")
    @Expose
    private String apiToken;
    @SerializedName("guid")
    @Expose
    private String guid;
    @SerializedName("updateModel")
    @Expose
    private UpdateModel updateModel;

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

    public UpdateModel getUpdateModel() {
        return updateModel;
    }

    public void setUpdateModel(UpdateModel updateModel) {
        this.updateModel = updateModel;
    }

}
