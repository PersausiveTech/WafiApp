package com.mobtecnica.wafiapps.model.LaundryModel.laundry_checkout;

/**
 * Created by siby on 16-Jun-17.
 */

public class Bodyy {

    private String Name;

    private String Disabled;

    public String getName ()
    {
        return Name;
    }

    public void setName (String Name)
    {
        this.Name = Name;
    }

    public String getDisabled ()
    {
        return Disabled;
    }

    public void setDisabled (String Disabled)
    {
        this.Disabled = Disabled;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Name = "+Name+", Disabled = "+Disabled+"]";
    }
}
