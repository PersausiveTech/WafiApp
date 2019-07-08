package com.mobtecnica.wafiapps.model.wishlist.newWishList;

/**
 * Created by SIby on 26-04-2017.
 */

public class GetWishListRequest {
    String apiToken;

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

    String guid;
}
