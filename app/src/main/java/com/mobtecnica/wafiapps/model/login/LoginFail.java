package com.mobtecnica.wafiapps.model.login;

/**
 * Created by SIby on 10-01-2017.
 */

public class LoginFail
{
    private String errorMessage;

    private String statusCode;

    private String success;

    public String getErrorMessage ()
    {
        return errorMessage;
    }

    public void setErrorMessage (String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    public String getStatusCode ()
    {
        return statusCode;
    }

    public void setStatusCode (String statusCode)
    {
        this.statusCode = statusCode;
    }

    public String getSuccess ()
    {
        return success;
    }

    public void setSuccess (String success)
    {
        this.success = success;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [errorMessage = "+errorMessage+", statusCode = "+statusCode+", success = "+success+"]";
    }
}
