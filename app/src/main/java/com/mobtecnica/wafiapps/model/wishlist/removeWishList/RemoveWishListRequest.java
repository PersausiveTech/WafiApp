package com.mobtecnica.wafiapps.model.wishlist.removeWishList;

import java.util.List;

/**
 * Created by SIby on 31-01-2017.
 */


public class RemoveWishListRequest {
    private String guid;

    private String apiToken;

    private List<Item_quantity> item_quantity;

    private List<String> removefromcart;

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

    public List<Item_quantity> getItem_quantity() {
        return item_quantity;
    }

    public void setItem_quantity(List<Item_quantity> item_quantity) {
        this.item_quantity = item_quantity;
    }

    public List<String> getRemovefromcart() {
        return removefromcart;
    }

    public void setRemovefromcart(List<String> removefromcart) {
        this.removefromcart = removefromcart;
    }

    @Override
    public String toString() {
        return "ClassPojo [guid = " + guid + ", apiToken = " + apiToken + ", item_quantity = " + item_quantity + ", removefromcart = " + removefromcart + "]";
    }
}

