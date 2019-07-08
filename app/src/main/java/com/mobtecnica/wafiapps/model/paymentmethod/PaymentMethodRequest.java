package com.mobtecnica.wafiapps.model.paymentmethod;

import com.mobtecnica.wafiapps.utils.Constants;

public class PaymentMethodRequest {
//    @Override
//    public String toString() {
//        return "PaymentMethodRequest{" +
//                "guid='" + guid + '\'' +
//                ", apiToken='" + apiToken + '\'' +
//                '}';
//    }

    private String guid;
    private String apiToken;

    public PaymentMethodRequest(String guid) {
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

}
