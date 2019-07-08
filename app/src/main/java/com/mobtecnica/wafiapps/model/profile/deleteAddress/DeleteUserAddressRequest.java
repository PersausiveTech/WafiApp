package com.mobtecnica.wafiapps.model.profile.deleteAddress;

/**
 * Created by Ashik V Ashraf on 09-08-2017.
 */

public class DeleteUserAddressRequest {
    String apiToken;
    String guid;
    String addressId;

    public DeleteUserAddressRequest() {
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

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }
}
