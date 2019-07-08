package com.mobtecnica.wafiapps.model.forgotPassword.password_recovery_change_password;

/**
 * Created by SIby on 23-01-2017.
 */


public class ForgotPasswordChangeRequest
{
    private Model model;

    private String apiToken;

    private String email;

    private String token;

    public Model getModel ()
    {
        return model;
    }

    public void setModel (Model model)
    {
        this.model = model;
    }

    public String getApiToken ()
    {
        return apiToken;
    }

    public void setApiToken (String apiToken)
    {
        this.apiToken = apiToken;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getToken ()
    {
        return token;
    }

    public void setToken (String token)
    {
        this.token = token;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [model = "+model+", apiToken = "+apiToken+", email = "+email+", token = "+token+"]";
    }
}