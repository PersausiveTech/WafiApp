package com.mobtecnica.wafiapps.model.profile.get_address;

import com.mobtecnica.wafiapps.utils.Constants;

/**
 * Created by SIby on 10-01-2017.
 */

public class GetUserAddressRequest {
    String apiToken;
    String guid;

    public GetUserAddressRequest() {}
    public GetUserAddressRequest(String guid) {
        setGuid(guid);
        setApiToken(Constants.API_TOKEN);
    }

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
