package com.mobtecnica.wafiapps.model.giftCard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by SIby on 31-03-2017.
 */

public class UpdateModelGift {

    @SerializedName("checkout_attributes")
    @Expose
    private List<Checkout_attributesGift> checkoutAttributes = null;
    @SerializedName("giftcardcouponcode")
    @Expose
    private String giftcardcouponcode;
    @SerializedName("item_quantity")
    @Expose
    private List<Item_quantityGift> itemQuantity = null;

    public List<Checkout_attributesGift> getCheckoutAttributes() {
        return checkoutAttributes;
    }

    public void setCheckoutAttributes(List<Checkout_attributesGift> checkoutAttributes) {
        this.checkoutAttributes = checkoutAttributes;
    }

    public String getGiftcardcouponcode() {
        return giftcardcouponcode;
    }

    public void setGiftcardcouponcode(String giftcardcouponcode) {
        this.giftcardcouponcode = giftcardcouponcode;
    }

    public List<Item_quantityGift> getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(List<Item_quantityGift> itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

}
