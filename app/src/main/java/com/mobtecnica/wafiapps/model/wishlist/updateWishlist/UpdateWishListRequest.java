package com.mobtecnica.wafiapps.model.wishlist.updateWishlist;

import java.util.List;

/**
 * Created by SIby on 31-01-2017.
 */


public class UpdateWishListRequest {
    private String guid;

    private String apiToken;

    private List<Item_quantityUpdate> item_quantity;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public List<Item_quantityUpdate> getItem_quantity() {
        return item_quantity;
    }

    public void setItem_quantity(List<Item_quantityUpdate> item_quantity) {
        this.item_quantity = item_quantity;
    }

    @Override
    public String toString() {
        return "ClassPojo [guid = " + guid + ", apiToken = " + apiToken + ", item_quantity = " + item_quantity + "]";
    }
}
