package com.mobtecnica.wafiapps.model.forgotPassword;

/**
 * Created by SIby on 23-01-2017.
 */

public class Data {
    private String guid;

    private String Email;

    private String customerid;

    private String passwordRecoveryToken;

    public String getResetCode() {
        return resetCode;
    }

    public void setResetCode(String resetCode) {
        this.resetCode = resetCode;
    }

    private String resetCode;

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

    public String getPasswordRecoveryToken() {
        return passwordRecoveryToken;
    }

    public void setPasswordRecoveryToken(String passwordRecoveryToken) {
        this.passwordRecoveryToken = passwordRecoveryToken;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    @Override
    public String toString() {
        return "ClassPojo [guid = " + guid + ", Email = " + Email + ", customerid = " + customerid + ", passwordRecoveryToken = " + passwordRecoveryToken + ", Message = " + Message + "]";
    }
}