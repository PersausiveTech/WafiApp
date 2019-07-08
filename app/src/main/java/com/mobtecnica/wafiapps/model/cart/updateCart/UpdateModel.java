package com.mobtecnica.wafiapps.model.cart.updateCart;

/**
 * Created by SIby on 20-03-2017.
 */

public class UpdateModel
{
    private Item_quantity[] item_quantity;

    private Checkout_attributes[] checkout_attributes;

    public Item_quantity[] getItem_quantity ()
    {
        return item_quantity;
    }

    public void setItem_quantity (Item_quantity[] item_quantity)
    {
        this.item_quantity = item_quantity;
    }

    public Checkout_attributes[] getCheckout_attributes ()
    {
        return checkout_attributes;
    }

    public void setCheckout_attributes (Checkout_attributes[] checkout_attributes)
    {
        this.checkout_attributes = checkout_attributes;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [item_quantity = "+item_quantity+", checkout_attributes = "+checkout_attributes+"]";
    }
}


