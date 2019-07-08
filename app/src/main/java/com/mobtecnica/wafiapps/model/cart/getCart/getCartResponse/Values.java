package com.mobtecnica.wafiapps.model.cart.getCart.getCartResponse;

/**
 * Created by SIby on 20-03-2017.
 */


public class Values
{
    private String Name;

    private boolean IsPreSelected;

    private String ColorSquaresRgb;

    private String Id;

    private String PriceAdjustment;

    private String groupName;

    public String getName ()
    {
        return Name;
    }

    public void setName (String Name)
    {
        this.Name = Name;
    }

    public boolean getIsPreSelected ()
    {
        return IsPreSelected;
    }

    public void setIsPreSelected (boolean IsPreSelected)
    {
        this.IsPreSelected = IsPreSelected;
    }

    public String getId ()
    {
        return Id;
    }

    public void setId (String Id)
    {
        this.Id = Id;
    }

    public String getColorSquaresRgb() {
        return ColorSquaresRgb;
    }

    public void setColorSquaresRgb(String colorSquaresRgb) {
        ColorSquaresRgb = colorSquaresRgb;
    }

    public String getPriceAdjustment() {
        return PriceAdjustment;
    }

    public void setPriceAdjustment(String priceAdjustment) {
        PriceAdjustment = priceAdjustment;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Name = "+Name+", IsPreSelected = "+IsPreSelected+", ColorSquaresRgb = "+ColorSquaresRgb+", Id = "+Id+", PriceAdjustment = "+PriceAdjustment+", CustomProperti]";
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}


