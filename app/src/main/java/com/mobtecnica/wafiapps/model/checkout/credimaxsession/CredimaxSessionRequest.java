
package com.mobtecnica.wafiapps.model.checkout.credimaxsession;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CredimaxSessionRequest {

    @SerializedName("apiTokenCrediMax")
    @Expose
    private String apiToken;

    @SerializedName("guid")
    @Expose
    private String guid;

    @SerializedName("orderId")
    @Expose
    private String orderId;

    @SerializedName("sessionId")
    @Expose
    private String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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
