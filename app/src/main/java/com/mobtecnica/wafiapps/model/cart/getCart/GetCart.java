package com.mobtecnica.wafiapps.model.cart.getCart;

import com.mobtecnica.wafiapps.utils.Constants;

/**
 * Created by SIby on 22-02-2017.
 */


public class GetCart{

    public GetCart(){}

    public GetCart(String guid){
        setGuid(guid);
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

//    @Override
//    public String toString() {
//        return "ClassPojo [guid = " + guid + ", apiToken = " + apiToken + "]";
//    }
}

