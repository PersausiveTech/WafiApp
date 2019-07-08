package com.mobtecnica.wafiapps.model.cart.removeFromCart;

/**
 * Created by SIby on 22-03-2017.
 */
public class UpdateModelRemove {
    private Item_quantity[] item_quantity;

    private String[] removefromcart;

    private Checkout_attributes[] checkout_attributes;

    public Item_quantity[] getItem_quantity ()
    {
        return item_quantity;
    }

    public void setItem_quantity (Item_quantity[] item_quantity)
    {
        this.item_quantity = item_quantity;
    }

    public String[] getRemovefromcart ()
    {
        return removefromcart;
    }

    public void setRemovefromcart (String[] removefromcart)
    {
        this.removefromcart = removefromcart;
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
        return "ClassPojo [item_quantity = "+item_quantity+", removefromcart = "+removefromcart+", checkout_attributes = "+checkout_attributes+"]";
    }
}
