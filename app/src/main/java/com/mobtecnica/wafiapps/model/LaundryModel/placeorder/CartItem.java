package com.mobtecnica.wafiapps.model.LaundryModel.placeorder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartItem {

    @SerializedName("MenuID")
    @Expose
    private String menuID;
    @SerializedName("ItemId")
    @Expose
    private String itemId;
    @SerializedName("ServiceID")
    @Expose
    private String serviceID;
    @SerializedName("Quantity")
    @Expose
    private String quantity;
    @SerializedName("Price")
    @Expose
    private String price;

    public String getMenuID() {
        return menuID;
    }

    public void setMenuID(String menuID) {
        this.menuID = menuID;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
