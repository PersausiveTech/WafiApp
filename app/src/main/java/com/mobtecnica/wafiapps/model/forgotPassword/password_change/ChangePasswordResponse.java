package com.mobtecnica.wafiapps.model.forgotPassword.password_change;

import com.mobtecnica.wafiapps.model.forgotPassword.Data;

/**
 * Created by SIby on 23-01-2017.
 */
public class ChangePasswordResponse {
    private String statusCode;
    private String errorMessage;

    private Data data;

    private boolean success;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "ClassPojo [statusCode = " + statusCode + ", data = " + data + ", success = " + success + "]";
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}