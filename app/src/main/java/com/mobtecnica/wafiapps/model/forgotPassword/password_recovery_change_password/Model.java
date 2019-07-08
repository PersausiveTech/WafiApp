package com.mobtecnica.wafiapps.model.forgotPassword.password_recovery_change_password;

/**
 * Created by SIby on 23-01-2017.
 */


public class Model
{
    private String NewPassword;

    private String ConfirmNewPassword;

    public String getNewPassword ()
    {
        return NewPassword;
    }

    public void setNewPassword (String NewPassword)
    {
        this.NewPassword = NewPassword;
    }

    public String getConfirmNewPassword ()
    {
        return ConfirmNewPassword;
    }

    public void setConfirmNewPassword (String ConfirmNewPassword)
    {
        this.ConfirmNewPassword = ConfirmNewPassword;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [NewPassword = "+NewPassword+", ConfirmNewPassword = "+ConfirmNewPassword+"]";
    }
}