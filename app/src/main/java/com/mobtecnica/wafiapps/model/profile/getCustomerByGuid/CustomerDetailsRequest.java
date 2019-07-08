package com.mobtecnica.wafiapps.model.profile.getCustomerByGuid;

/**
 * Created by SIby on 10-01-2017.
 */

public class CustomerDetailsRequest {
    String apiToken;
    String guid;

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
}
