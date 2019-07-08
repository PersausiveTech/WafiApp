
package com.mobtecnica.wafiapps.model.wafiEats.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstCartSubItem {

    @SerializedName("MenuSubItem")
    @Expose
    private String menuSubItem;

    public String getMenuSubItem() {
        return menuSubItem;
    }

    public void setMenuSubItem(String menuSubItem) {
        this.menuSubItem = menuSubItem;
    }

}
