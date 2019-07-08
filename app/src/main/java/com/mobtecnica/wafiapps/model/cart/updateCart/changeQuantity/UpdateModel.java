package com.mobtecnica.wafiapps.model.cart.updateCart.changeQuantity;

/**
 * Created by SIby on 27-03-2017.
 */


public class UpdateModel {
    private Item_quantity[] item_quantity;

    public Item_quantity[] getItem_quantity() {
        return item_quantity;
    }

    public void setItem_quantity(Item_quantity[] item_quantity) {
        this.item_quantity = item_quantity;
    }

    @Override
    public String toString() {
        return "ClassPojo [item_quantity = " + item_quantity + "]";
    }
}

