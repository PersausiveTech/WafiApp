package com.mobtecnica.wafiapps.fragments.wafi_main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.activity.Login_page_one;
import com.mobtecnica.wafiapps.manager.AuthManager;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.model.forgotPassword.password_recovery_confirmation_token.ForgotPasswordTokenRequest;
import com.mobtecnica.wafiapps.utils.BaseFragment;
import com.mobtecnica.wafiapps.utils.Constants;
import com.mobtecnica.wafiapps.utils.Utils;

/**
 * Created by siby on 28-Dec-16.
 */

public class ForgotPasswordOTPFragment extends BaseFragment implements View.OnClickListener {
    View rootview;
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().matches(AuthManager.BROADCAST_FORGOTPASSWORD_TOKEN)) {
                if (intent.getAction().matches(AuthManager.BROADCAST_FORGOTPASSWORD_TOKEN)) {
                    if (intent.getBooleanExtra(AuthManager.BROADCAST_FORGOTPASSWORD_TOKEN_STATUS, false)) {

                        //         Toast.makeText(context, "success", Toast.LENGTH_SHORT).show();
                        navigateToForgotPasswordConfirmFragemt();
                    } else {
                        Utils.makeSnackBar(rootview,  R.string.failed, Snackbar.LENGTH_SHORT);
                    }
                }
            }
        }
    };
    private EditText et_user_email;
    private View btn_submit_forgot_pass;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_forgot_otp_password, container, false);
        initializeViews();
        buttonClickListners();
        return rootview;

    }

    private void buttonClickListners() {
        btn_submit_forgot_pass.setOnClickListener(this);
    }

    private void initializeViews() {
        IntentFilter intent = new IntentFilter();
        intent.addAction(AuthManager.BROADCAST_FORGOTPASSWORD_TOKEN);
        addBroadcastListener(receiver, intent);

        et_user_email = (EditText) rootview.findViewById(R.id.et_user_email);
        btn_submit_forgot_pass = rootview.findViewById(R.id.btn_submit_forgot_pass);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit_forgot_pass:
                showLoadingDialog();
                ForgotPasswordTokenRequest request = new ForgotPasswordTokenRequest();
                request.setEmail(ObjectFactory.getInstance().getAppPreference(getActivity()).getRecoveryEmail());
                request.setResetCode(et_user_email.getText().toString().trim());
                request.setApiToken(Constants.API_TOKEN);
                ObjectFactory.getInstance().geLoginManager(getActivity()).passwordRecoveryConfirmationToken(request);
                break;
            default:
                break;
        }
    }

    private void navigateToForgotPasswordConfirmFragemt() {
        Fragment fragment = new ForgotPasswordConfirmFragment();
        if (getActivity() instanceof Login_page_one) {
            ((Login_page_one) getActivity()).addFragment(fragment);
        }
    }
}
