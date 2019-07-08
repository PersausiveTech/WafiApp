package com.mobtecnica.wafiapps.model.wishlist.newWishList;

import java.util.ArrayList;

/**
 * Created by SIby on 26-04-2017.
 */

public class GetWishListData {
    private ArrayList<GetWishListItems> Items;

    private String DisplayTaxShippingInfo;

    private String DisplayAddToCart;

    private String ShowProductImages;

    private String ShowSku;

    private String CustomerFullname;

    private String CustomerGuid;

    private String IsEditable;

    private String[] Warnings;

    private String EmailWishlistEnabled;

    public ArrayList<GetWishListItems> getItems() {
        return Items;
    }

    public void setItems(ArrayList<GetWishListItems> items) {
        Items = items;
    }

    public String getDisplayTaxShippingInfo() {
        return DisplayTaxShippingInfo;
    }

    public void setDisplayTaxShippingInfo(String displayTaxShippingInfo) {
        DisplayTaxShippingInfo = displayTaxShippingInfo;
    }

    public String getDisplayAddToCart() {
        return DisplayAddToCart;
    }

    public void setDisplayAddToCart(String displayAddToCart) {
        DisplayAddToCart = displayAddToCart;
    }

    public String getShowProductImages() {
        return ShowProductImages;
    }

    public void setShowProductImages(String showProductImages) {
        ShowProductImages = showProductImages;
    }

    public String getShowSku() {
        return ShowSku;
    }

    public void setShowSku(String showSku) {
        ShowSku = showSku;
    }

    public String getCustomerFullname() {
        return CustomerFullname;
    }

    public void setCustomerFullname(String customerFullname) {
        CustomerFullname = customerFullname;
    }

    public String getCustomerGuid() {
        return CustomerGuid;
    }

    public void setCustomerGuid(String customerGuid) {
        CustomerGuid = customerGuid;
    }

    public String getIsEditable() {
        return IsEditable;
    }

    public void setIsEditable(String isEditable) {
        IsEditable = isEditable;
    }

    public String[] getWarnings() {
        return Warnings;
    }

    public void setWarnings(String[] warnings) {
        Warnings = warnings;
    }

    public String getEmailWishlistEnabled() {
        return EmailWishlistEnabled;
    }

    public void setEmailWishlistEnabled(String emailWishlistEnabled) {
        EmailWishlistEnabled = emailWishlistEnabled;
    }
}
