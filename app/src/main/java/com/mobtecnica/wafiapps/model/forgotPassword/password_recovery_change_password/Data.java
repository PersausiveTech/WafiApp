package com.mobtecnica.wafiapps.model.forgotPassword.password_recovery_change_password;

/**
 * Created by SIby on 23-01-2017.
 */

public class Data {
    private String guid;

    private String Email;

    private String customerid;

    private String Message;

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

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    @Override
    public String toString() {
        return "ClassPojo [guid = " + guid + ", Email = " + Email + ", customerid = " + customerid + ", Message = " + Message + "]";
    }
}

