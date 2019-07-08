package com.mobtecnica.wafiapps.model.forgotPassword.password_change;

import com.mobtecnica.wafiapps.model.BaseUserRequest;

/**
 * Created by SIby on 23-01-2017.
 */


public class ChangePasswordRequest extends BaseUserRequest {
    private Model model;

    public ChangePasswordRequest(String guid, String oldPassword, String newPassword, String confirmNewPassword) {
        super();
        setGuid(guid);
        setModel(new Model(oldPassword, newPassword, confirmNewPassword));
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

}