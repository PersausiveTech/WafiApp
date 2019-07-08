package com.mobtecnica.wafiapps.model.LaundryModel.priceLIst;

/**
 * Created by SIby on 03-04-2017.
 */

public class PriceListResponse {

    private String statusCode;

    private DataLaundry data;

    private String success;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public DataLaundry getData() {
        return data;
    }

    public void setData(DataLaundry data) {
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
