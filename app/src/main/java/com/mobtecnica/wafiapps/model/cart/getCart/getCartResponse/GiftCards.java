package com.mobtecnica.wafiapps.model.cart.getCart.getCartResponse;

import com.mobtecnica.wafiapps.model.CustomProperties;

public class GiftCards {
    private String CouponCode;
    private String Amount;
    private String Remaining;
    private String Id;
    private CustomProperties customProperties;

    public String getCouponCode() {
        return CouponCode;
    }

    public void setCouponCode(String couponCode) {
        CouponCode = couponCode;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getRemaining() {
        return Remaining;
    }

    public void setRemaining(String remaining) {
        Remaining = remaining;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public CustomProperties getCustomProperties() {
        return customProperties;
    }

    public void setCustomProperties(CustomProperties customProperties) {
        this.customProperties = customProperties;
    }
}
