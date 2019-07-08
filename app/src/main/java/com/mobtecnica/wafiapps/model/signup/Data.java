package com.mobtecnica.wafiapps.model.signup;

/**
 * Created by SIby on 09-01-2017.
 */

public class Data {
    private String guid;

    private String Email;

    private String customerid;

    private String Registered;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getRegistered() {
        return Registered;
    }

    public void setRegistered(String Registered) {
        this.Registered = Registered;
    }

    @Override
    public String toString() {
        return "ClassPojo [guid = " + guid + ", Email = " + Email + ", customerid = " + customerid + ", Registered = " + Registered + "]";
    }
}