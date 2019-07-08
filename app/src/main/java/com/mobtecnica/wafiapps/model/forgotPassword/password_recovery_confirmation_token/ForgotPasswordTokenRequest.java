package com.mobtecnica.wafiapps.model.forgotPassword.password_recovery_confirmation_token;

/**
 * Created by SIby on 23-01-2017.
 */

public class ForgotPasswordTokenRequest
{
    private String apiToken;

    private String Email;

    private String resetCode;

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
        return Email;
    }

    public void setEmail (String email)
    {
        this.Email = email;
    }

    public String getResetCode()
    {
        return resetCode;
    }

    public void setResetCode(String resetCode)
    {
        this.resetCode = resetCode;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [apiToken = "+apiToken+", Email = "+ Email +", resetCode = "+ resetCode +"]";
    }
}

