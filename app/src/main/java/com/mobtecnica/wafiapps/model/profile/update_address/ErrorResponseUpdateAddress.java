package com.mobtecnica.wafiapps.model.profile.update_address;

/**
 * Created by SIby on 11-01-2017.
 */

public class ErrorResponseUpdateAddress {
    private String errorMessage;

    private String statusCode;

    private String success;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "ClassPojo [errorMessage = " + errorMessage + ", statusCode = " + statusCode + ", success = " + success + "]";
    }
}
