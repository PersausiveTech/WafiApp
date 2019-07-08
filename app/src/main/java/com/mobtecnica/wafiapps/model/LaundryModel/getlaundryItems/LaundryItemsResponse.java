package com.mobtecnica.wafiapps.model.LaundryModel.getlaundryItems;

/**
 * Created by SIby on 04-04-2017.
 */

public class LaundryItemsResponse {
    private String statusCode;

    private DataLaundryItems data;

    private String success;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public DataLaundryItems getData() {
        return data;
    }

    public void setData(DataLaundryItems data) {
        this.data = data;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "ClassPojo [statusCode = " + statusCode + ", data = " + data + ", success = " + success + "]";
    }
}
