package com.mobtecnica.wafiapps.model.discountCoupon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by SIby on 31-03-2017.
 */

public class UpdateModelCoupon {

    @SerializedName("checkout_attributes")
    @Expose
    private List<Checkout_attributes_Coupon> checkoutAttributes = null;
    @SerializedName("discountcouponcode")
    @Expose
    private String discountcouponcode;
    @SerializedName("item_quantity")
    @Expose
    private List<Item_quantity_Coupon> itemQuantity = null;

    public List<Checkout_attributes_Coupon> getCheckoutAttributes() {
        return checkoutAttributes;
    }

    public void setCheckoutAttributes(List<Checkout_attributes_Coupon> checkoutAttributes) {
        this.checkoutAttributes = checkoutAttributes;
    }

    public String getDiscountcouponcode() {
        return discountcouponcode;
    }

    public void setDiscountcouponcode(String discountcouponcode) {
        this.discountcouponcode = discountcouponcode;
    }

    public List<Item_quantity_Coupon> getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(List<Item_quantity_Coupon> itemQuantity) {
        this.itemQuantity = itemQuantity;
    }
}
