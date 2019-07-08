package com.mobtecnica.wafiapps.activity;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.WindowManager;

import com.google.firebase.messaging.FirebaseMessaging;
import com.mobtecnica.wafiapps.BuildConfig;
import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.customViews.CustomAlertDialog;
import com.mobtecnica.wafiapps.customViews.NotificationCreaterAsyncTask;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.updatechecker.ForceUpdateChecker;
import com.mobtecnica.wafiapps.utils.BaseActivity;
import com.mobtecnica.wafiapps.utils.MyFirebaseAnalytics;
import com.mobtecnica.wafiapps.utils.Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class SplashActivity extends BaseActivity implements ForceUpdateChecker.OnUpdateNeededListener {

    int secondsDelayed = 2;
    String  deepLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        FirebaseMessaging.getInstance().subscribeToTopic("all");
        FirebaseMessaging.getInstance().subscribeToTopic("" + BuildConfig.VERSION_NAME);
        //Utils.getKeyHash(this);// to get the key hash
        ForceUpdateChecker.with(this).onUpdateNeeded(this).check();
        if (getIntent().hasExtra(NotificationCreaterAsyncTask.KEY_DEEP_LINK)) {
            deepLink = getIntent().getStringExtra(NotificationCreaterAsyncTask.KEY_DEEP_LINK);
        }
        printKeyHash(this);
        if (getIntent().hasExtra(NotificationCreaterAsyncTask.KEY_FROM_NOTIFICATION)) {
            String id = getIntent().getStringExtra(NotificationCreaterAsyncTask.KEY_ID);
            String message_id = getIntent().getStringExtra(NotificationCreaterAsyncTask.KEY_MESSAGE_ID);
            String title = getIntent().getStringExtra(NotificationCreaterAsyncTask.KEY_TITLE);
            if (getIntent().hasExtra(NotificationCreaterAsyncTask.KEY_DEEP_LINK)) {
                deepLink = getIntent().getStringExtra(NotificationCreaterAsyncTask.KEY_DEEP_LINK);
            }

            String user_email = null, user_name = null;
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
            try {
                MyFirebaseAnalytics.logNotificationOpenedEvent(this, message_id, id, title, user_name, user_email);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onUpdateNeeded(boolean isNeeded, final String updateUrl) {
        if (isNeeded) {
            CustomAlertDialog customAlertDialog = new CustomAlertDialog("Please, update app to new version to continue.", "New version available", "No, thanks", "Update");
            customAlertDialog.setCancelable(false);
            customAlertDialog.setCustomAlertClickListener(new CustomAlertDialog.CustomAlertClickListener() {
                @Override
                public void onPositive(DialogFragment dialogFragment) {
                    redirectStore(updateUrl);
                }

                @Override
                public void onNegative(DialogFragment dialogFragment) {
                    dialogFragment.dismiss();
                    finish();
                }
            });
            customAlertDialog.show(getFragmentManager(), "Alert");

        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (ObjectFactory.getInstance().getAppPreference(getApplicationContext()).getIsLoggedIn().matches("1")) {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        if(getIntent().getData()!=null)
                            intent.setData(getIntent().getData());
//                        if (!TextUtils.isEmpty(deepLink)) {
//                            Bundle bundle = getIntent().getExtras();
//                            if (bundle==null){
//                                bundle=new Bundle();
//                            }
//                            bundle.putString(NotificationCreaterAsyncTask.KEY_DEEP_LINK, deepLink);
//                            intent.putExtras(bundle);
//                        }
                        if(getIntent().getExtras()!=null)
                            intent.putExtras(getIntent().getExtras());
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(SplashActivity.this, Login_page_one.class);
                        intent.setData(getIntent().getData());
//                        if (!TextUtils.isEmpty(deepLink)) {
//                            Bundle bundle = getIntent().getExtras();
//                            if (bundle==null){
//                                bundle=new Bundle();
//                            }
//                            bundle.putString(NotificationCreaterAsyncTask.KEY_DEEP_LINK, deepLink);
//                            intent.putExtras(bundle);
//                        }

                        if(getIntent().getExtras()!=null)
                            intent.putExtras(getIntent().getExtras());
                        startActivity(intent);
                        finish();
                    }

                }
            }, secondsDelayed * 200);
        }
    }

    private void redirectStore(String updateUrl) {
        final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(updateUrl));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public static String printKeyHash(Activity context) {
        PackageInfo packageInfo;
        String key = null;
        try {
            //getting application package name, as defined in manifest
            String packageName = context.getApplicationContext().getPackageName();

            //Retriving package info
            packageInfo = context.getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);

            Log.e("Package Name=", context.getApplicationContext().getPackageName());

            for (Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                key = new String(Base64.encode(md.digest(), 0));

                // String key = new String(Base64.encodeBytes(md.digest()));
                Log.e("Key Hash=", key);
                Log.v("Key Hash=", key);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("Name not found", e1.toString());
        }
        catch (NoSuchAlgorithmException e) {
            Log.e("No such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }

        return key;
    }
}
