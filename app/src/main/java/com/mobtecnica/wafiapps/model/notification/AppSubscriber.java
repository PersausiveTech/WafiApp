package com.mobtecnica.wafiapps.model.notification;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by untanglembpr2 on 05/10/17.
 */

public class AppSubscriber {
    @SerializedName("SubscriberToken")
    @Expose
    String subscriberToken;
    @SerializedName("UserGuid")
    @Expose
    String userGuid;
    @SerializedName("UserId")
    @Expose
    String userId;
    @SerializedName("DeviceName")
    @Expose
    String deviceName;
    @SerializedName("Active")
    @Expose
    Boolean Active;

    public String getSubscriberToken() {
        return subscriberToken;
    }

    public void setSubscriberToken(String subscriberToken) {
        this.subscriberToken = subscriberToken;
    }

    public String getUserGuid() {
        return userGuid;
    }

    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Boolean getActive() {
        return Active;
    }

    public void setActive(Boolean active) {
        Active = active;
    }
}
