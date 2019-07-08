package com.mobtecnica.wafiapps.model;

import com.mobtecnica.wafiapps.utils.Constants;

public class BaseUserRequest {
    public BaseUserRequest(){
        setApiToken(Constants.API_TOKEN);
    }
    private String guid;

    private String apiToken;

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

}
