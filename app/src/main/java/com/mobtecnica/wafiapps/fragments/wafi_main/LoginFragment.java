package com.mobtecnica.wafiapps.fragments.wafi_main;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.text.Html;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.activity.Login_page_one;
import com.mobtecnica.wafiapps.activity.MainActivity;
import com.mobtecnica.wafiapps.manager.AuthManager;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.model.login.LoginRequest;
import com.mobtecnica.wafiapps.utils.BaseFragment;
import com.mobtecnica.wafiapps.utils.Constants;
import com.mobtecnica.wafiapps.utils.Utils;

/**
 * Created by siby on 28-Dec-16.
 */

public class LoginFragment extends BaseFragment implements View.OnClickListener {
    View rootview;
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().matches(AuthManager.BROADCAST_LOGIN_RESPONSE)) {
                dismissLoadingDialog();
                if (intent.getBooleanExtra(AuthManager.BROADCAST_LOGIN_RESPONSE_STATUS, false)) {
                    dismissLoadingDialog();
                    navigatetoMainActivity();
                } else {
                    String message = "Login failed";
                    try {
                        message = intent.getStringExtra(AuthManager.BROADCAST_LOGIN_RESPONSE_MESSAGE);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Utils.makeSnackBar(btn_login,message , Snackbar.LENGTH_LONG);
                }

            }
        }
    };


    private AppCompatEditText et_userEmail, et_userPassword;
    private TextView tv_forgotPassword;
    private AppCompatButton btn_login;
    private AppCompatImageView btn_back_register;
    public Dialog dialog=null;
    TextView tvPasswordToggle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_login, container, false);
        initializeViews();
        clickListners();
        return rootview;
    }

    private void clickListners() {
        btn_back_register.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        tv_forgotPassword.setOnClickListener(this);
    }

    private void initializeViews() {
        IntentFilter intent = new IntentFilter();
        intent.addAction(AuthManager.BROADCAST_LOGIN_RESPONSE);
        addBroadcastListener(receiver, intent);
        tvPasswordToggle = (TextView)rootview.findViewById(R.id.tv_togglepassword);
        et_userEmail =  rootview.findViewById(R.id.et_email_login);
        et_userPassword = rootview.findViewById(R.id.et_password_login);
        tv_forgotPassword = (TextView) rootview.findViewById(R.id.tv_forgot_pass_login);
        btn_back_register = (AppCompatImageView) rootview.findViewById(R.id.btn_back_register);
        btn_login = (AppCompatButton) rootview.findViewById(R.id.btn_login);
        tvPasswordToggle.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                if (getStatus()) {
                    try {
                        userLogin();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //api calling
                }

                break;
            case R.id.btn_back_register:
                navigateToEdit_One_Fragment();
                break;
            case R.id.tv_forgot_pass_login:
                navigateToForgotPasswordFragment();
                break;
            case R.id.tv_togglepassword:
                showHidePassword();
            default:
                break;
        }

    }

    private void showHidePassword() {
        if(tvPasswordToggle.getText().toString().equalsIgnoreCase("Hide")) {
            et_userPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            tvPasswordToggle.setText(R.string.show);
        } else {
            et_userPassword.setInputType(InputType.TYPE_CLASS_TEXT);
            tvPasswordToggle.setText(R.string.hide);
        }
    }

    private void userLogin() {
        showLoadingDialog();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setApiToken(Constants.API_TOKEN);
        loginRequest.setEmail(et_userEmail.getText().toString().trim());
        loginRequest.setPassword(et_userPassword.getText().toString().trim());
        ObjectFactory.getInstance().geLoginManager(getActivity()).loginApi(loginRequest);
    }

    private void navigateToForgotPasswordFragment() {
        Fragment fragment = new ForgotPasswordFragment();
        if (getActivity() instanceof Login_page_one) {
            ((Login_page_one) getActivity()).addFragment(fragment);
        }
    }

    private void navigateToEdit_One_Fragment() {
        getActivity().onBackPressed();
    }


    private void navigatetoMainActivity() {

//        getFragmentManager().popBackStack();
        Intent intent = new Intent(getActivity(), MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        if(getActivity().getIntent().getExtras()!=null)
            intent.putExtras(getActivity().getIntent().getExtras());
        intent.setData(getActivity().getIntent().getData());
        startActivity(intent);
        getActivity().finish();
    }

    private boolean getStatus() {
        return isEmailValid() && isValidPassword();
    }

    private boolean isValidPassword() {
        if ((!TextUtils.isEmpty(et_userPassword.getText().toString()) && et_userPassword.getText().toString().length() >= 6)) {
            return true;
        } else {
            et_userPassword.requestFocus();
            et_userPassword.setError(Html.fromHtml(getString(R.string.invalid_password)));
            return false;
        }

    }

    private boolean isEmailValid() {
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(et_userEmail.getText().toString()).matches()) {
            return true;
        } else {
            et_userEmail.requestFocus();
            et_userEmail.setError(Html.fromHtml(getString(R.string.invalid_email)));
            return false;
        }


    }

}
