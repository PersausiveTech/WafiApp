
package com.mobtecnica.wafiapps.model.wafiEats.checkout;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartItem {

    @SerializedName("Menu")
    @Expose
    private String menu;
    @SerializedName("Rate")
    @Expose
    private String rate;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("lstCartSubItems")
    @Expose
    private List<LstCartSubItem> lstCartSubItems = null;

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public List<LstCartSubItem> getLstCartSubItems() {
        return lstCartSubItems;
    }

    public void setLstCartSubItems(List<LstCartSubItem> lstCartSubItems) {
        this.lstCartSubItems = lstCartSubItems;
    }

}
