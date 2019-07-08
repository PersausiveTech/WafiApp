package com.mobtecnica.wafiapps.model.wafiEats.addToCart;

/**
 * Created by siby on 21-Jun-17.
 */

public class SubAttributeSelection {
    String attribute;
    int id;
    int MenuSubItemGroupID;

    public int getMenuSubItemGroupID() {
        return MenuSubItemGroupID;
    }

    public void setMenuSubItemGroupID(int menuSubItemGroupID) {
        MenuSubItemGroupID = menuSubItemGroupID;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
