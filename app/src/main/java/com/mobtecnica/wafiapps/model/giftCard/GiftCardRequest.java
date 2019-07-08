package com.mobtecnica.wafiapps.model.giftCard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by SIby on 31-03-2017.
 */

public class GiftCardRequest {
    @SerializedName("apiToken")
    @Expose

    private String apiToken;
    @SerializedName("guid")
    @Expose
    private String guid;
    @SerializedName("updateModel")
    @Expose
    private UpdateModelGift updateModel;

    /*public String getApiToken() {
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
    }*/

    public UpdateModelGift getUpdateModel() {
        return updateModel;
    }

    public void setUpdateModel(UpdateModelGift updateModel) {
        this.updateModel = updateModel;
    }

    @SerializedName("giftcardcouponcode")
    @Expose
    private String giftcardcouponcode;

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

    public String getGiftcardcouponcode() {
        return giftcardcouponcode;
    }

    public void setGiftcardcouponcode(String giftcardcouponcode) {
        this.giftcardcouponcode = giftcardcouponcode;
    }
}