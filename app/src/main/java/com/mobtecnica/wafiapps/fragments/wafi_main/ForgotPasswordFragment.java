package com.mobtecnica.wafiapps.fragments.wafi_main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.activity.Login_page_one;
import com.mobtecnica.wafiapps.manager.AuthManager;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.model.forgotPassword.ForgotPasswordRequest;
import com.mobtecnica.wafiapps.model.forgotPassword.Model;
import com.mobtecnica.wafiapps.utils.BaseFragment;
import com.mobtecnica.wafiapps.utils.Constants;
import com.mobtecnica.wafiapps.utils.Utils;

/**
 * Created by siby on 28-Dec-16.
 */

public class ForgotPasswordFragment extends BaseFragment implements View.OnClickListener {
    View rootview;
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().matches(AuthManager.FORGOTPASSWORD_RESPONSE)) {
                if (intent.getBooleanExtra(AuthManager.FORGOTPASSWORD_RESPONSE_STATUS, false)) {
                    navigateToForgotPasswordOTPFragemt();
                }else{
                    String message =Constants.SERVER_ERROR;
                    try{
                        message=intent.getStringExtra(AuthManager.FORGOTPASSWORD_RESPONSE_MESSAGE);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    Utils.makeSnackBar(btn_submit_forgot_pass,message, Snackbar.LENGTH_LONG);
                }
            }
        }
    };
    private AppCompatEditText et_user_email;
    private AppCompatImageButton btn_submit_forgot_pass;
    private AppCompatImageView btn_back_register;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_forgot_password, container, false);
        initializeViews();
        buttonClickListners();
        return rootview;

    }

    private void buttonClickListners() {
        btn_submit_forgot_pass.setOnClickListener(this);
        btn_back_register.setOnClickListener(this);
    }

    private void initializeViews() {
        IntentFilter intent = new IntentFilter();
        intent.addAction(AuthManager.FORGOTPASSWORD_RESPONSE);
        intent.addAction(AuthManager.BROADCAST_FORGOTPASSWORD_TOKEN);
        addBroadcastListener(receiver, intent);

        et_user_email = rootview.findViewById(R.id.et_user_email);
        et_user_email.setTypeface(Utils.getTypefaceLatoRegular(getContext()));
        btn_submit_forgot_pass = (AppCompatImageButton) rootview.findViewById(R.id.btn_submit_forgot_pass);
        btn_back_register = (AppCompatImageView) rootview.findViewById(R.id.btn_back_register);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit_forgot_pass:
                boolean status = isEmailValid();
                if (status) {
                    //api call
                    showLoadingDialog();
                    ForgotPasswordRequest request = new ForgotPasswordRequest();
                    Model model = new Model();
                    String user_email = et_user_email.getText().toString().trim();
                    model.setEmail(user_email);
                    request.setApiToken(Constants.API_TOKEN);
                    request.setModel(model);
                    ObjectFactory.getInstance().geLoginManager(getContext()).passwordRecoverySend(request);

                }

                break;
            case R.id.btn_back_register:
                getFragmentManager().popBackStack();
                break;
            default:
                break;
        }
    }

    private boolean isEmailValid() {
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(et_user_email.getText().toString()).matches()) {
            return true;
        } else {
            et_user_email.setError(Html.fromHtml(getString(R.string.invalid_email)));
            return false;
        }

    }


    private void navigateToForgotPasswordOTPFragemt() {
        try {
            Fragment fragment = new ForgotPasswordOTPFragment();
            if (getActivity() instanceof Login_page_one) {
                ((Login_page_one) getActivity()).addFragment(fragment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
