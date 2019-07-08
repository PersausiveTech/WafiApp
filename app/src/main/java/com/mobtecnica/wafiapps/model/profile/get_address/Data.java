package com.mobtecnica.wafiapps.model.profile.get_address;

/**
 * Created by SIby on 10-01-2017.
 */

public class Data {
    private Addresses[] Addresses;

   // private String CustomProperties;

    public Addresses[] getAddresses() {
        return Addresses;
    }

    public void setAddresses(Addresses[] Addresses) {
        this.Addresses = Addresses;
    }

//    public String getCustomProperties() {
//        return CustomProperties;
//    }
//
//    public void setCustomProperties(String CustomProperties) {
//        this.CustomProperties = CustomProperties;
//    }

    @Override
    public String toString() {
        return "ClassPojo [Addresses = " + Addresses + ", CustomProperties ]";
    }
}
