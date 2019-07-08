package com.mobtecnica.wafiapps.model.profile.cusomerDetails;

import com.mobtecnica.wafiapps.utils.Constants;

/**
 * Created by SIby on 22-02-2017.
 */

public class CustomerPersonalDetailsRequest {
    private String guid;

    private String apiToken;

    public CustomerPersonalDetailsRequest() {
    }

    public CustomerPersonalDetailsRequest(String guid) {
        setGuid(guid);
        setApiToken(Constants.API_TOKEN);
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    @Override
    public String toString() {
        return "ClassPojo [guid = " + guid + ", apiToken = " + apiToken + "]";
    }
}
