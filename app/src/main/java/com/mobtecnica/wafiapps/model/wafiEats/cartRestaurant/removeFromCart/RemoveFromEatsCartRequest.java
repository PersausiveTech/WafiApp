package com.mobtecnica.wafiapps.model.wafiEats.cartRestaurant.removeFromCart;

/**
 * Created by SIby on 10-06-2017.
 */

public class RemoveFromEatsCartRequest {
    String apiToken;
    String emailID;
    String cartItemID;
    String menuID;

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

    public String getCartItemID() {
        return cartItemID;
    }

    public void setCartItemID(String cartItemID) {
        this.cartItemID = cartItemID;
    }

    public String getMenuID() {
        return menuID;
    }

    public void setMenuID(String menuID) {
        this.menuID = menuID;
    }
}
