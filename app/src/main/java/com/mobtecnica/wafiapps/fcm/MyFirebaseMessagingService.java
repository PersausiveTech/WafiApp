package com.mobtecnica.wafiapps.fcm;

import android.annotation.SuppressLint;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.mobtecnica.wafiapps.customViews.NotificationCreaterAsyncTask;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.utils.MyFirebaseAnalytics;

import static com.mobtecnica.wafiapps.utils.Logger.TAG;

/**
 * Created by ashik on 8/15/2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String NOTIFICATION_KEY_TITLE = "title";
    private static final String NOTIFICATION_KEY_MESSAGE = "message";
    private static final String NOTIFICATION_KEY_IMAGE_URL = "image_url";
    private static final String NOTIFICATION_KEY_ID = "id";
    private static final String NOTIFICATION_KEY_DEEP_LINK = "deep_link";

    @SuppressLint("WrongThread")
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        // ...
        String message_id = null, id = null, title = null, user_name = null, user_email = null, deep_link ="";
        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        try {
            message_id = remoteMessage.getMessageId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            //TODO improve this.
            id = remoteMessage.getData().get(NOTIFICATION_KEY_ID);
            title = remoteMessage.getData().get(NOTIFICATION_KEY_TITLE);
            if (remoteMessage.getData().containsKey(NOTIFICATION_KEY_DEEP_LINK)) {
                deep_link = remoteMessage.getData().get(NOTIFICATION_KEY_DEEP_LINK);
            }
            new NotificationCreaterAsyncTask(this, title, remoteMessage.getData().get(NOTIFICATION_KEY_MESSAGE),
                    remoteMessage.getData().get(NOTIFICATION_KEY_IMAGE_URL),message_id,id,deep_link).execute();
           }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            if (remoteMessage.getNotification().getTitle() != null) {
                title = remoteMessage.getNotification().getTitle();
            }

            String message = "";
            if (remoteMessage.getNotification().getBody() != null) {
                message = remoteMessage.getNotification().getBody();
            }
            deep_link="";

            if (remoteMessage.getData().containsKey(NOTIFICATION_KEY_DEEP_LINK)) {
                deep_link = remoteMessage.getData().get(NOTIFICATION_KEY_DEEP_LINK);
            }
            Log.e("notification", "received");
            new NotificationCreaterAsyncTask(this, title, message,
                    null,message_id,id, deep_link).execute();
        }

        try {
            user_email = ObjectFactory.getInstance().getAppPreference(this).getEmailId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            user_name = ObjectFactory.getInstance().getAppPreference(this).getCustomerDetailsResponse().getData().getCustomerInfo().getFirstName() + " " + ObjectFactory.getInstance().getAppPreference(this).getCustomerDetailsResponse().getData().getCustomerInfo().getLastName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        MyFirebaseAnalytics.logNotificationDeliveredEvent(this, message_id, id, title, user_name, user_email);

    }

}
