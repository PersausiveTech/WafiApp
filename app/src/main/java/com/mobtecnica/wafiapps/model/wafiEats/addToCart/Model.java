
package com.mobtecnica.wafiapps.model.wafiEats.addToCart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Model {

    @SerializedName("MenuID")
    @Expose
    private String menuID;
    @SerializedName("CheckedItems")
    @Expose
    private String checkedItems;
    @SerializedName("Rate")
    @Expose
    private String rate;
    @SerializedName("ShopID")
    @Expose
    private String shopID;
    @SerializedName("ShopLocationID")
    @Expose
    private String shopLocationID;
    @SerializedName("quantity")
    @Expose
    private String quantity;

    public String getMenuID() {
        return menuID;
    }

    public void setMenuID(String menuID) {
        this.menuID = menuID;
    }

    public String getCheckedItems() {
        return checkedItems;
    }

    public void setCheckedItems(String checkedItems) {
        this.checkedItems = checkedItems;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getShopID() {
        return shopID;
    }

    public void setShopID(String shopID) {
        this.shopID = shopID;
    }

    public String getShopLocationID() {
        return shopLocationID;
    }

    public void setShopLocationID(String shopLocationID) {
        this.shopLocationID = shopLocationID;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

}
