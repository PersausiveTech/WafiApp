package com.mobtecnica.wafiapps.model.LaundryModel.getlaundryItems;

/**
 * Created by SIby on 04-04-2017.
 */

public class LaundryItemsRequest {
    String apiToken;
    String LaundryType;

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getLaundryType() {
        return LaundryType;
    }

    public void setLaundryType(String laundryType) {
        LaundryType = laundryType;
    }
}
