package com.mobtecnica.wafiapps.model.cart.getCart.getCartResponse;

/**
 * Created by SIby on 20-03-2017.
 */

public class GiftCardBox
{
    private String Message;

    private boolean Display;

    private String IsApplied;

//    private String CustomProperties;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public boolean getDisplay ()
    {
        return Display;
    }

    public void setDisplay (boolean Display)
    {
        this.Display = Display;
    }

    public String getIsApplied ()
    {
        return IsApplied;
    }

    public void setIsApplied (String IsApplied)
    {
        this.IsApplied = IsApplied;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Message = "+Message+", Display = "+Display+", IsApplied = "+IsApplied+", CustomProperties =]";
    }
}

