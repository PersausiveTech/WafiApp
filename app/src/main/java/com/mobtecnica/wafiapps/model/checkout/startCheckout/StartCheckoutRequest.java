package com.mobtecnica.wafiapps.model.checkout.startCheckout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


/**
 * Created by SIby on 20-04-2017.
 */

public class StartCheckoutRequest {

    @SerializedName("apiToken")
    @Expose
    private String apiToken;

    @SerializedName("guid")
    @Expose
    private String guid;

    @SerializedName("checkout_attributes")
    @Expose
    private CheckoutAttributeCheckout[] checkoutAttributes = null;
    @SerializedName("item_quantity")
    @Expose
    private ItemQuantityCheckout[] itemQuantity = null;

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

    public CheckoutAttributeCheckout[] getCheckoutAttributes() {
        return checkoutAttributes;
    }

    public void setCheckoutAttributes(CheckoutAttributeCheckout[] checkoutAttributes) {
        this.checkoutAttributes = checkoutAttributes;
    }

    public ItemQuantityCheckout[] getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(ItemQuantityCheckout[] itemQuantity) {
        this.itemQuantity = itemQuantity;
    }
}
