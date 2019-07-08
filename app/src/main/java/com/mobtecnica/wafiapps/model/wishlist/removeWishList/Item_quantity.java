package com.mobtecnica.wafiapps.model.wishlist.removeWishList;

/**
 * Created by SIby on 31-01-2017.
 */


public class Item_quantity
{
    private String Name;

    private String Value;

    public String getName ()
    {
        return Name;
    }

    public void setName (String Name)
    {
        this.Name = Name;
    }

    public String getValue ()
    {
        return Value;
    }

    public void setValue (String Value)
    {
        this.Value = Value;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Name = "+Name+", Value = "+Value+"]";
    }
}

