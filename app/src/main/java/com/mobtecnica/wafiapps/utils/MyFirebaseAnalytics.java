package com.mobtecnica.wafiapps.utils;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import com.google.firebase.analytics.FirebaseAnalytics;

// Firebase analytics
public final class MyFirebaseAnalytics {
    private static final class Type {
        private static final String NOTIFICATION = "notification";
    }

    private static final class Event {
        private static final String NOTIFICATION_DELIVERED = "notification_delivered";
        private static final String NOTIFICATION_OPENED = "notification_opened";
    }

    private static final class Param {
        private static final String MESSAGE_ID = "message_id";
        private static final String ID = "id";
        private static final String USER_NAME = "user_name";
        private static final String USER_EMAILID = "user_emailid";
    }

    public static void logNotificationDeliveredEvent(Context context, String message_id, String id, String title, String user_name, String user_email) {
        logFirebaseEvent(context, message_id, id, title, Type.NOTIFICATION, Event.NOTIFICATION_DELIVERED, user_name, user_email);
    }

    public static void logNotificationOpenedEvent(Context context, String message_id, String id, String title, String user_name, String user_email) {
        logFirebaseEvent(context, message_id, id, title, Type.NOTIFICATION, Event.NOTIFICATION_OPENED, user_name, user_email);
    }

    private static void logFirebaseEvent(Context context, String message_id, String id, String name, String type, String event, String user_name, String user_email) {
        Bundle bundle = new Bundle();
        bundle.putString(Param.ID, id);
        bundle.putString(Param.MESSAGE_ID, message_id);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, type);
        if (!TextUtils.isEmpty(user_name)) {
            bundle.putString(Param.USER_NAME, user_name);
        }
        if (!TextUtils.isEmpty(user_email)) {
            bundle.putString(Param.USER_EMAILID, user_email);
        }
        FirebaseAnalytics.getInstance(context).logEvent(event, bundle);
    }

}