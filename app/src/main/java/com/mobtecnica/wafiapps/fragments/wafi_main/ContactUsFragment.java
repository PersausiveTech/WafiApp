package com.mobtecnica.wafiapps.fragments.wafi_main;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.telephony.PhoneNumberUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.utils.BaseFragment;
import com.mobtecnica.wafiapps.utils.Utils;
import com.zoho.salesiqembed.ZohoSalesIQ;

/**
 * Created by siby on 13-Jun-17.
 */

public class ContactUsFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;
    private TextView website;
    private CardView cv_phone;
    private CardView cv_whatsapp;
    private CardView cv_email;
    private int PHONE_PERMISSION_CODE = 23;
    private FloatingActionButton fbChat;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_contact_us, container, false);
        if (getUserVisibleHint()) {

        }
        initViews();
        onClickListners();
        return rootView;

    }

    private void onClickListners() {
        cv_phone.setOnClickListener(this);
        cv_whatsapp.setOnClickListener(this);
        cv_email.setOnClickListener(this);
        website.setOnClickListener(this);
        fbChat.setOnClickListener(this);
    }

    private void initViews() {
        cv_phone = (CardView) rootView.findViewById(R.id.cv_phone);
        cv_whatsapp = (CardView) rootView.findViewById(R.id.cv_whatsapp);
        cv_email = (CardView) rootView.findViewById(R.id.cv_email);
        website = (TextView) rootView.findViewById(R.id.website);
        fbChat = rootView.findViewById(R.id.fbChat);
        setTitle(getString(R.string.contact_us));
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cv_phone:
                if (isPhoneAllowed()) {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + "0097317678001"));
                    try {
                        startActivity(Intent.createChooser(intent, null));
                    } catch (android.content.ActivityNotFoundException ex) {
                    }
                } else requestCallPermission();

                break;
            case R.id.cv_whatsapp:
                try {
//                    Uri uri = Uri.parse("smsto:" + "+97334371428");
//                    Intent i = new Intent(Intent.ACTION_SENDTO, uri);
//                    i.putExtra("sms_body", "Hi just testing ");
//                    i.setPackage("com.whatsapp");
//                    startActivity(i);
//                    Intent i = new Intent(Intent.ACTION_SENDTO, Uri.parse("+97334371428"));
//                    i.setType("text/plain");
//                    i.setPackage("com.whatsapp");           // so that only Whatsapp reacts and not the chooser
//                    i.putExtra(Intent.EXTRA_SUBJECT, "Subject");
//                    i.putExtra(Intent.EXTRA_TEXT, " ");
//                    startActivity(i);

                    String smsNumber = "97334371428"; //without '+'
                    try {
                        Intent sendIntent = new Intent("android.intent.action.MAIN");
                        sendIntent.setComponent(new ComponentName("com.whatsapp","com.whatsapp.Conversation"));
                        sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(smsNumber)+"@s.whatsapp.net");//phone number without "+" prefix
                        startActivity(sendIntent);
                    } catch(Exception e) {
                        Utils.makeSnackBar(rootView,  R.string.whatsapp_not_found, Snackbar.LENGTH_SHORT);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.cv_email:
                sendEmail();
                break;
            case R.id.website:
                navigateToWebSite();
                break;
            case R.id.fbChat:
                ZohoSalesIQ.Chat.show();
                break;
            default:
                break;

        }
    }

    private void navigateToWebSite() {
        Uri uri = Uri.parse("https://www.wafiapps.com"); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(Intent.createChooser(intent, null));
    }

    private boolean isPhoneAllowed() {
        //Getting the permission status
        int result = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE);

        //If permission is granted returning true
        if (result == PackageManager.PERMISSION_GRANTED)
            return true;

        //If permission is not granted returning false
        return false;
    }

    private void requestCallPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CALL_PHONE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }

        //And finally ask for the permission
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, PHONE_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if (requestCode == PHONE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                cv_phone.performClick();
                //Displaying a toast
//                Toast.makeText(getActivity(), "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                Utils.makeSnackBar(rootView, R.string.denied_the_permission, Snackbar.LENGTH_LONG);
            }
        }

    }

    protected void sendEmail() {
        String[] TO = {"sales@wafiapps.com"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "");

        try {
            startActivity(Intent.createChooser(emailIntent, getString(R.string.send_email)));
        } catch (android.content.ActivityNotFoundException ex) {
        }
    }
}
