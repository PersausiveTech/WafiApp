package com.mobtecnica.wafiapps.model.wishlist.addToWishlist;

/**
 * Created by SIby on 23-02-2017.
 */

public class Data
{
    private String value;

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
}
