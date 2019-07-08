package com.mobtecnica.wafiapps.fragments.wafi_main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.activity.MainActivity;
import com.mobtecnica.wafiapps.listeners.OnWebserviceCallback;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.manager.WebserviceRequestManager;
import com.mobtecnica.wafiapps.model.forgotPassword.password_change.ChangePasswordRequest;
import com.mobtecnica.wafiapps.model.forgotPassword.password_change.ChangePasswordResponse;
import com.mobtecnica.wafiapps.utils.BaseFragment;
import com.mobtecnica.wafiapps.utils.Utils;

public class ChangePasswordFragment extends BaseFragment implements View.OnClickListener, OnWebserviceCallback {

    private EditText et_user_password, et_user_new_password, et_user_confirm_password;
    private View btn_update_password;

    public ChangePasswordFragment() {
        setButtonType(Utils.BUTTON_TYPE.EMPTY);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.change_password, container, false);
        initializeViews(view);
        buttonClickListners();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitle(getString(R.string.change_password));
    }

    private void buttonClickListners() {
        btn_update_password.setOnClickListener(this);
    }

    private void initializeViews(View view) {
        et_user_password = view.findViewById(R.id.et_user_password);
        et_user_new_password = view.findViewById(R.id.et_user_new_password);
        et_user_confirm_password = view.findViewById(R.id.et_user_confirm_password);
        btn_update_password = view.findViewById(R.id.btn_update_password);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update_password:
//                String mPassword = getValue(et_user_password);
                String mPassword = getValue(et_user_password);
                String mNewPassword = getValue(et_user_new_password);
                String mConfirmPassword = getValue(et_user_confirm_password);
                if (!TextUtils.isEmpty(mPassword) && !TextUtils.isEmpty(mNewPassword) && !TextUtils.isEmpty(mConfirmPassword)) {
                    if (checkPasswordStrength(mNewPassword)) {
                        if(!mPassword.equals(mNewPassword)){
                            if (mNewPassword.equals(mConfirmPassword)) {
                                showLoadingDialog();
                                ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest(ObjectFactory.getInstance().getAppPreference(getActivity()).getGuid(), mPassword, mNewPassword, mConfirmPassword);
                                WebserviceRequestManager.getInstance().enqueueRequest(ObjectFactory.getInstance().getRestClient(getContext()).getApiService().changePassword(changePasswordRequest), this, WebserviceRequestManager.RequestType.CHANGE_PASSWORD);
                            } else {
                                et_user_new_password.setText("");
                                et_user_confirm_password.setText("");
                                et_user_new_password.requestFocus();
                                et_user_new_password.setError(Html.fromHtml(getString(R.string.passwords_do_not_match)));
                            }
                        }
                        else{
                            et_user_new_password.setText("");
                            et_user_confirm_password.setText("");
                            et_user_password.setText("");
                            et_user_password.requestFocus();
                            Utils.makeSnackBar(et_user_confirm_password,"New Password should not be same as Old Password",Snackbar.LENGTH_SHORT);
                        }

                    } else {
                        et_user_new_password.setError(Html.fromHtml(getString(R.string.invalid_password)));
                        return;
                    }

                }

                break;
            default:
                break;
        }
    }


    private boolean checkPasswordStrength(String mPassword) {
        if ((!TextUtils.isEmpty(mPassword) && mPassword.length() >= 6)) {
            return true;
        }
        return false;
    }

    private String getValue(EditText mEditText) {

        if (mEditText != null) {
            String mText = mEditText.getText().toString().trim();
            if (!TextUtils.isEmpty(mText)) {
                return mText;
            } else {
                mEditText.setError(Html.fromHtml(getString(R.string.password_should_not_be_empty)));
            }
        }
        return null;
    }

    @Override
    public void onSuccess(Object result, WebserviceRequestManager.RequestType requestType) {
        if (getActivity() instanceof MainActivity) {
            dismissLoadingDialog();
            switch (requestType) {
                case CHANGE_PASSWORD:
                    if (result instanceof ChangePasswordResponse) {
                        if (((ChangePasswordResponse) result).getSuccess()) {
                            ObjectFactory.getInstance().getAppPreference(getContext()).setIsLoggedIn("1");
                            ObjectFactory.getInstance().getAppPreference(getContext()).setGuid(((ChangePasswordResponse) result).getData().getGuid());
                            ObjectFactory.getInstance().getAppPreference(getContext()).setEmailId(((ChangePasswordResponse) result).getData().getEmail());
//                    ObjectFactory.getInstance().getAppPreference(getContext()).saveUserType(responseStr);
                            ObjectFactory.getInstance().getAppPreference(getContext()).setCustomerID(((ChangePasswordResponse) result).getData().getCustomerid());
                            Utils.makeSnackBar(et_user_password, R.string.password_changed, Snackbar.LENGTH_LONG);
                            getActivity().onBackPressed();
                        } else {
                            if (!TextUtils.isEmpty(((ChangePasswordResponse) result).getErrorMessage())) {
                                Utils.makeSnackBar(et_user_password, ((ChangePasswordResponse) result).getErrorMessage(), Snackbar.LENGTH_LONG);
                            } else {
                                Utils.makeSnackBar(et_user_password, R.string.error_occured, Snackbar.LENGTH_LONG);
                            }
                            et_user_new_password.setText("");
                            et_user_confirm_password.setText("");
                            et_user_password.setText("");
                            et_user_new_password.requestFocus();
                        }
                    }
            }
        }
    }

    @Override
    public void onFailure(String message) {
        if (getActivity() == null) {
            dismissLoadingDialog();
            return;
        }
        dismissLoadingDialog();
        Utils.changeSnackBarFont(Snackbar.make(et_user_password, getResources().getString(R.string.error_occured), Snackbar.LENGTH_LONG));
    }

    @Override
    public void onCancel(Object result) {
        dismissLoadingDialog();
    }

}
