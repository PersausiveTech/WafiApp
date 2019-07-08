package com.mobtecnica.wafiapps.model.notification;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by untanglembpr2 on 05/10/17.
 */

public class NotificationRegistrationRequest {
    @SerializedName("apitoken")
    @Expose
    String apitoken;
    @SerializedName("appSubscriber")
    @Expose
    AppSubscriber appSubscriber;

    public String getApitoken() {
        return apitoken;
    }

    public void setApitoken(String apitoken) {
        this.apitoken = apitoken;
    }

    public AppSubscriber getAppSubscriber() {
        return appSubscriber;
    }

    public void setAppSubscriber(AppSubscriber appSubscriber) {
        this.appSubscriber = appSubscriber;
    }
}
