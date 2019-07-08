package com.mobtecnica.wafiapps.model.forgotPassword.password_change;

/**
 * Created by SIby on 23-01-2017.
 */


public class Model {

    private String OldPassword;
    private String NewPassword;
    private String ConfirmNewPassword;

    public Model(String oldPassword, String newPassword, String confirmNewPassword) {
        setOldPassword(oldPassword);
        setNewPassword(newPassword);
        setConfirmNewPassword(confirmNewPassword);
    }

    public void setOldPassword(String oldPassword) {
        OldPassword = oldPassword;
    }

    public void setNewPassword(String newPassword) {
        NewPassword = newPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        ConfirmNewPassword = confirmNewPassword;
    }
}

