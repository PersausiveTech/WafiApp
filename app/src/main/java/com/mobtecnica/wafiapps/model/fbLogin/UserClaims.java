package com.mobtecnica.wafiapps.model.fbLogin;

/**
 * Created by Ashik V Ashraf on 09-08-2017.
 */

public class UserClaims {
    String First;
    String Last;
    String Email;

    public UserClaims() {
    }

    public String getFirst() {
        return First;
    }

    public void setFirst(String first) {
        First = first;
    }

    public String getLast() {
        return Last;
    }

    public void setLast(String last) {
        Last = last;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
