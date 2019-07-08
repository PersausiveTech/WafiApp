package com.mobtecnica.wafiapps.model.login;

/**
 * Created by SIby on 09-01-2017.
 */

public class LoginRequest {
    String apiToken;
    String email;
    String password;

    public String getApiToken() {
        return apiToken;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
