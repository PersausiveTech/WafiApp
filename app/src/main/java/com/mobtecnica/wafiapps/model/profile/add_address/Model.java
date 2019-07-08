package com.mobtecnica.wafiapps.model.profile.add_address;

/**
 * Created by SIby on 11-01-2017.
 */


public class Model
{ private Address Address;

    public Address getAddress ()
    {
        return Address;
    }

    public void setAddress (Address Address)
    {
        this.Address = Address;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Address = "+Address+"]";
    }
}