package com.mobtecnica.wafiapps.fragments.wafi_main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.activity.Login_page_one;
import com.mobtecnica.wafiapps.manager.AuthManager;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.model.forgotPassword.password_recovery_change_password.ForgotPasswordChangeRequest;
import com.mobtecnica.wafiapps.model.forgotPassword.password_recovery_change_password.Model;
import com.mobtecnica.wafiapps.utils.BaseFragment;
import com.mobtecnica.wafiapps.utils.Constants;
import com.mobtecnica.wafiapps.utils.Utils;

/**
 * Created by SIby on 23-01-2017.
 */

public class ForgotPasswordConfirmFragment extends BaseFragment implements View.OnClickListener {
    View rootView;
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().matches(AuthManager.BROADCAST_PASSWORD_RECOVERY_CHANGE_PASSWORD)) {
                if (intent.getBooleanExtra(AuthManager.BROADCAST_PASSWORD_RECOVERY_CHANGE_PASSWORD_STATUS, true)) {
                    Utils.makeSnackBar(rootView, R.string.password_changed, Snackbar.LENGTH_LONG);
                    navigateToLoginFragment();
                } else
                    Utils.makeSnackBar(rootView, R.string.failed, Snackbar.LENGTH_SHORT);
            }
        }
    };
    private EditText m_et_password, met_confirmPassword;
    private View btn_confirm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_forgot_password_change_pass, container, false);
        initilizeViews();
        onClickListners();
        return rootView;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_forgot_password:
                if (isValidPassword() && isValidConfirmPassword()) {
                    if ((m_et_password.getText().toString().trim()).matches(met_confirmPassword.getText().toString().trim())) {
                        changePasswordApiCall();
                    } else {
                        Utils.makeSnackBar(rootView, R.string.passwords_shouldbes_same, Snackbar.LENGTH_LONG);
                    }
                }
                break;
            default:
                break;

        }
    }

    private void changePasswordApiCall() {
        ForgotPasswordChangeRequest request = new ForgotPasswordChangeRequest();
        Model model = new Model();
        model.setConfirmNewPassword(met_confirmPassword.getText().toString().trim());
        model.setNewPassword(met_confirmPassword.getText().toString().trim());
        request.setModel(model);
        request.setApiToken(Constants.API_TOKEN);
        request.setToken(ObjectFactory.getInstance().getAppPreference(getContext()).getPasswordRrecoveryToken());
        //  request.setResetCode(ObjectFactory.getInstance().getAppPreference(getContext()).get);
        request.setEmail(ObjectFactory.getInstance().getAppPreference(getContext()).getRecoveryEmail());
        showLoadingDialog();
        ObjectFactory.getInstance().geLoginManager(getContext()).passwordRecoveryChangePassword(request);
    }

    private void initilizeViews() {
        IntentFilter intent = new IntentFilter();
        intent.addAction(AuthManager.BROADCAST_PASSWORD_RECOVERY_CHANGE_PASSWORD);
        addBroadcastListener(receiver, intent);

        m_et_password = (EditText) rootView.findViewById(R.id.et_password_forgot);
        met_confirmPassword = (EditText) rootView.findViewById(R.id.et_confirm_pass_forgot);
        btn_confirm = rootView.findViewById(R.id.btn_forgot_password);
    }

    private void onClickListners() {
        btn_confirm.setOnClickListener(this);
    }

    private boolean isValidConfirmPassword() {
        if ((!TextUtils.isEmpty(met_confirmPassword.getText().toString()) && met_confirmPassword.getText().toString().length() >= 6)) {
            return true;
        } else {
            met_confirmPassword.setError(Html.fromHtml(getString(R.string.invalid_password)));
            return false;
        }
    }

    private boolean isValidPassword() {
        if ((!TextUtils.isEmpty(m_et_password.getText().toString()) && m_et_password.getText().toString().length() >= 6)) {
            return true;
        } else {
            m_et_password.setError(Html.fromHtml(getString(R.string.password_should_contain_6char)));
            return false;
        }
    }

    private void navigateToLoginFragment() {
        getActivity().getSupportFragmentManager().popBackStack(0, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        Fragment fragment = new RegisterHomeFragment();
        if (getActivity() instanceof Login_page_one) {
            ((Login_page_one) getActivity()).addFragment(fragment);
        }
    }

}
