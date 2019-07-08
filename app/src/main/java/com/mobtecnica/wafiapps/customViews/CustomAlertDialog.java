package com.mobtecnica.wafiapps.customViews;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.mobtecnica.wafiapps.R;

/**
 * Created by Jilsha on 28-03-2018.
 */

@SuppressLint("ValidFragment")
public class CustomAlertDialog extends DialogFragment implements View.OnClickListener{
    String Message,Title,Button1,Button2;
    static View v;

    public void setCustomAlertClickListener(CustomAlertClickListener customAlertClickListener) {
        this.customAlertClickListener = customAlertClickListener;
    }

    CustomAlertClickListener customAlertClickListener;
    @SuppressLint("ValidFragment")
    public CustomAlertDialog(String message, String title, String button1, String button2) {
        this.Message = message;
        this.Title = title;
        this.Button1 = button1;
        this.Button2 = button2;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.custom_alertdialog, container, false);
        View title= v.findViewById(R.id.title);
        View message=v.findViewById(R.id.message);
        View button1=v.findViewById(R.id.cancel);
        View button2=v.findViewById(R.id.ok);
        if(!TextUtils.isEmpty((CharSequence) Message)){
            ((TextView)message).setText(Message);
        }
        if(!TextUtils.isEmpty((CharSequence) Title)){
            ((TextView)title).setText(Title);
        }
        else
        {
            title.setVisibility(View.GONE);
        }
        if(!TextUtils.isEmpty((CharSequence) Button1)){
            ((Button)button1).setText(Button1);
        }
        else
        {
            button1.setVisibility(View.GONE);
        }

        if(!TextUtils.isEmpty((CharSequence) Button2)){
            ((Button)button2).setText(Button2);
        }
        else
        {
            button2.setVisibility(View.GONE);
        }
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.cancel:
                if(customAlertClickListener!=null)
                {
                    customAlertClickListener.onNegative(this);
                }
                break;
            case R.id.ok:
                if(customAlertClickListener!=null)
                {
                    customAlertClickListener.onPositive(this);
                }
                break;
        }
    }

    public interface CustomAlertClickListener{
        void onPositive(DialogFragment dialogFragment);
        void onNegative(DialogFragment dialogFragment);
    }

}
