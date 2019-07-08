package com.mobtecnica.wafiapps.model.wafiEats.getMenuOptions;

/**
 * Created by SIby on 08-05-2017.
 */

public class GetMenuOptionsRequest {
    String apiToken;
    String menuID;
    String shopID;
    String locID;

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getMenuID() {
        return menuID;
    }

    public void setMenuID(String menuID) {
        this.menuID = menuID;
    }

    public String getShopID() {
        return shopID;
    }

    public void setShopID(String shopID) {
        this.shopID = shopID;
    }

    public String getLocID() {
        return locID;
    }

    public void setLocID(String locID) {
        this.locID = locID;
    }
}
