package com.mobtecnica.wafiapps.model.profile.update_address;

/**
 * Created by SIby on 11-01-2017.
 */


public class Model {
    Address address;

    public Model(Address address) {
        this.address = address;
    }

    public Model() {
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}