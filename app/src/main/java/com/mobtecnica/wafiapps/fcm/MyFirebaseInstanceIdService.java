package com.mobtecnica.wafiapps.fcm;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.model.notification.AppSubscriber;
import com.mobtecnica.wafiapps.model.notification.NotificationRegistrationRequest;
import com.mobtecnica.wafiapps.utils.Constants;
import com.mobtecnica.wafiapps.utils.Logger;
import com.mobtecnica.wafiapps.utils.Utils;

/**
 * Created by ashik on 8/15/2017.
 */

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Logger.d("refreshedToken" + refreshedToken);
        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
//    sendRegistrationToServer(refreshedToken);
        if (ObjectFactory.getInstance().getAppPreference(getApplicationContext()).getIsLoggedIn().equalsIgnoreCase("1")) {
            registerFCMTokenToServer();
        }
    }

    private void registerFCMTokenToServer() {
        if(ObjectFactory.getInstance().getAppPreference(this).getIsLoggedIn().equalsIgnoreCase("1")) {
            NotificationRegistrationRequest notificationRegistrationRequest = new NotificationRegistrationRequest();
            AppSubscriber appSubscriber = new AppSubscriber();
            notificationRegistrationRequest.setApitoken(Constants.API_TOKEN);
            appSubscriber.setActive(true);
            appSubscriber.setDeviceName(Utils.getDeviceName());
            String userId = ObjectFactory.getInstance().getAppPreference(this).getCustomerId();
            String token = FirebaseInstanceId.getInstance().getToken();
            String guid = ObjectFactory.getInstance().getAppPreference(this).getGuid();
            appSubscriber.setSubscriberToken(token);
            appSubscriber.setUserId(userId);
            appSubscriber.setUserGuid(guid);
            notificationRegistrationRequest.setAppSubscriber(appSubscriber);
            ObjectFactory.getInstance().getHomeManager(this).apiRegisterToken(notificationRegistrationRequest);
        }

    }



}
