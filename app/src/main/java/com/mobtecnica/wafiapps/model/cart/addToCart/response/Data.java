package com.mobtecnica.wafiapps.model.cart.addToCart.response;

/**
 * Created by SIby on 01-03-2017.
 */

public class Data
{
    private String value;
    private int CartItemsCount = -1;

    public String getValue ()
    {
        return value;
    }

    public void setValue (String value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [value = "+value+"]";
    }

    public int getCartItemsCount() {
        return CartItemsCount;
    }

    public void setCartItemsCount(int cartItemsCount) {
        CartItemsCount = cartItemsCount;
    }
}

