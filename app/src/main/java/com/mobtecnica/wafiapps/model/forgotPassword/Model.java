package com.mobtecnica.wafiapps.model.forgotPassword;

/**
 * Created by SIby on 23-01-2017.
 */


public class Model {
    private String Email;

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    @Override
    public String toString() {
        return "ClassPojo [Email = " + Email + "]";
    }
}

