package com.mobtecnica.wafiapps.customViews;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.text.TextUtils;

import com.mobtecnica.wafiapps.BuildConfig;
import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.activity.SplashActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;

public class NotificationCreaterAsyncTask extends AsyncTask<String, Void, Bitmap> {

    private Context mContext;
    private String title, message, imageUrl,message_id,id,deep_link;
    String GROUP_KEY_WORK_EMAIL = "com.android.example.WORK_EMAIL";
    public static final int NOTIFICATION_REQUESTCODE = 555;
    public static final String KEY_FROM_NOTIFICATION = "fromNotification";
    public static final String KEY_ID = "id";
    public static final String KEY_MESSAGE_ID = "message_id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_DEEP_LINK= "deep_link";

    public NotificationCreaterAsyncTask(Context context, String title, String message, String imageUrl, String message_id, String id, String deep_link) {
        super();
        this.mContext = context;
        this.title = title;
        this.message = message;
        this.imageUrl = imageUrl;
        this.message_id = message_id;
        this.id = id;
        this.deep_link = deep_link;
    }

    @Override
    protected Bitmap doInBackground(String... params) {

        InputStream in;
        try {
            if (TextUtils.isEmpty(this.imageUrl)) {
                return null;
            }
            URL url = new URL(this.imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            in = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(in);
            return myBitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);

        Intent intent = new Intent(mContext, SplashActivity.class);
        intent.putExtra(KEY_FROM_NOTIFICATION, true);
        intent.putExtra(KEY_ID, id);
        intent.putExtra(KEY_MESSAGE_ID, message_id);
        intent.putExtra(KEY_TITLE, title);
        intent.putExtra(KEY_DEEP_LINK, deep_link);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, NOTIFICATION_REQUESTCODE, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder notifBuilder = new Notification.Builder(mContext)
                .setContentIntent(pendingIntent)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_stat_wafi_logo)
                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher))
                .setSound(defaultSoundUri)
                .setPriority(Notification.PRIORITY_HIGH)
                .setDefaults(Notification.DEFAULT_ALL);
        if (result != null) {
            notifBuilder.setStyle(new Notification.BigPictureStyle().bigPicture(result));
        }

//                    .build();


        int id = getID();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

//                String id = 0;
// The id of the channel.
            String id_channel = "my_channel_01_";

// The user-visible name of the channel.
            CharSequence name = "channel_name";

// The user-visible description of the channel.
            String description = "channel_description";

            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel mChannel = null;
            mChannel = new NotificationChannel(id_channel, name, importance);


// Configure the notification channel.
            mChannel.setDescription(description);

            mChannel.enableLights(true);
// Sets the notification light color for notifications posted to this
// channel, if the device supports this feature.
            mChannel.setLightColor(Color.WHITE);

//                mChannel.enableVibration(true);
//                mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            notifBuilder.setChannelId(id_channel);
            notificationManager.createNotificationChannel(mChannel);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            notifBuilder.setGroup(GROUP_KEY_WORK_EMAIL);
            notifBuilder.setGroupSummary(true);
        }
//            notifBuilder.getNotification().flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(BuildConfig.APPLICATION_ID, id, notifBuilder.build());
    }

    private final static AtomicInteger c = new AtomicInteger(0);

    public static int getID() {
        return c.incrementAndGet();
    }
}