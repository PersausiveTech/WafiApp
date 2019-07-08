package com.mobtecnica.wafiapps.utils;


import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;

import com.google.firebase.analytics.FirebaseAnalytics;

//import android.util.Log;

public abstract class BaseFragment extends Fragment {

    private BroadcastReceiver broadcastReceiver = null;
    private IntentFilter intentFilter = null;
    private String title = "";
    private Utils.BUTTON_TYPE buttonType = Utils.BUTTON_TYPE.CART;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerReceivers();
        try {
            FirebaseAnalytics.getInstance(getContext()).setCurrentScreen(getActivity(), this.getClass().getSimpleName(), null);
//            Log.d("NAME",""+this.getClass().getSimpleName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        unregisterReceivers();
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        registerReceivers();
    }

    @Override
    public void onPause() {
//        unregisterReceivers();
        super.onPause();
    }

    public void registerReceivers() {
        if (broadcastReceiver != null && intentFilter != null && getActivity() != null) {
            unregisterReceivers();
            LocalBroadcastManager.getInstance(getActivity().getApplicationContext()).registerReceiver(broadcastReceiver, intentFilter);
        }
    }

    public void unregisterReceivers() {
        if (broadcastReceiver != null && intentFilter != null && getActivity() != null) {
            LocalBroadcastManager.getInstance(getActivity().getApplicationContext()).unregisterReceiver(broadcastReceiver);
        }
    }

    public void addBroadcastListener(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        this.broadcastReceiver = broadcastReceiver;
        this.intentFilter = intentFilter;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setButtonType(Utils.BUTTON_TYPE buttonType) {
        this.buttonType = buttonType;
    }

    public Utils.BUTTON_TYPE getButtonType() {
        return buttonType;
    }

    public void showLoadingDialog() {
        Intent intent = new Intent(Constants.Login.BROADCAST_PROGRESS_WHEEL);
        intent.putExtra(Constants.Login.BROADCAST_ISDISPLAY_PROGRESS_WHEEL, true);
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
    }

    public void dismissLoadingDialog() {
        if (getActivity() != null) {
            Intent intent = new Intent(Constants.Login.BROADCAST_PROGRESS_WHEEL);
            intent.putExtra(Constants.Login.BROADCAST_ISDISPLAY_PROGRESS_WHEEL, false);
            LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
        }
    }
}
