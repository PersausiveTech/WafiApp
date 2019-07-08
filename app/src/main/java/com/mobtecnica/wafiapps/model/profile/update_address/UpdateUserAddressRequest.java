package com.mobtecnica.wafiapps.model.profile.update_address;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SIby on 10-01-2017.
 */

public class UpdateUserAddressRequest {
    private String guid;
    private String apiToken;
    private String addressId;
    private Model model;

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

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}

