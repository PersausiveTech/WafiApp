package com.mobtecnica.wafiapps.model.cart.getCart.getCartResponse;

/**
 * Created by SIby on 20-03-2017.
 */

public class TaxRates
{
    private String Value;

//    private String CustomProperties;

    private String Rate;

    public String getValue ()
    {
        return Value;
    }

    public void setValue (String Value)
    {
        this.Value = Value;
    }


    public String getRate ()
    {
        return Rate;
    }

    public void setRate (String Rate)
    {
        this.Rate = Rate;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Value = "+Value+", CustomPropertiRate = "+Rate+"]";
    }
}

