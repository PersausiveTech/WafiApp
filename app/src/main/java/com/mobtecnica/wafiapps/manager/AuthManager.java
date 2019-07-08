package com.mobtecnica.wafiapps.manager;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import com.mobtecnica.wafiapps.model.fbLogin.FBLoginRequest;
import com.mobtecnica.wafiapps.model.fbLogin.FbParameters;
import com.mobtecnica.wafiapps.model.forgotPassword.ForgotPasswordRequest;
import com.mobtecnica.wafiapps.model.forgotPassword.ForgotPasswordResponse;
import com.mobtecnica.wafiapps.model.forgotPassword.password_recovery_change_password.ForgotPasswordChangeRequest;
import com.mobtecnica.wafiapps.model.forgotPassword.password_recovery_change_password.ForgotPasswordChangeResponse;
import com.mobtecnica.wafiapps.model.forgotPassword.password_recovery_confirmation_token.ForgotPasswordTokenRequest;
import com.mobtecnica.wafiapps.model.forgotPassword.password_recovery_confirmation_token.PasswordRecoveryTokenResponse;
import com.mobtecnica.wafiapps.model.login.Login;
import com.mobtecnica.wafiapps.model.login.LoginFail;
import com.mobtecnica.wafiapps.model.login.LoginRequest;
import com.mobtecnica.wafiapps.model.signup.SignUpFailed;
import com.mobtecnica.wafiapps.model.signup.SignUpRequest;
import com.mobtecnica.wafiapps.model.signup.Signup;
import com.mobtecnica.wafiapps.utils.Constants;
import com.mobtecnica.wafiapps.utils.Logger;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by siby on 29-Dec-16.
 * <p>
 * contains login ,signup, forgotpassword api calls
 */

public class AuthManager {
    private Context mcontext;
    //login
    public static final String BROADCAST_LOGIN_RESPONSE = "BROADCAST_LOGIN_RESPONSE";
    public static final String JSON_RESPONSE_STATUS = "success";
    public static final String BROADCAST_LOGIN_RESPONSE_STATUS = "BROADCAST_LOGIN_RESPONSE_STATUS";
    public static final String BROADCAST_LOGIN_RESPONSE_MESSAGE = "BROADCAST_LOGIN_RESPONSE_MESSAFE";
    //sign_up
    public static final String BROADCAST_SIGN_UP_RESPONSE = "BROADCAST_LOGIN_RESPONSE";
    public static final String BROADCAST_SIGN_UP_RESPONSE_STATUS = "BROADCAST_SIGN_UP_RESPONSE_STATUS";
    public static final String BROADCAST_SIGN_UP_RESPONSE_MESSAGE = "BROADCAST_SIGN_UP_RESPONSE_MESSAFE";


    //forgot password
    public static final String FORGOTPASSWORD_RESPONSE = "FORGOTPASSWORD_RESPONSE";
    public static final String FORGOTPASSWORD_RESPONSE_STATUS = "FORGOTPASSWORD_RESPONSE_STATUS";
    public static final String FORGOTPASSWORD_RESPONSE_MESSAGE = "FORGOTPASSWORD_RESPONSE_MESSAGE";

    //forgot password token
    public static final String BROADCAST_FORGOTPASSWORD_TOKEN = "BROADCAST_FORGOTPASSWORD_TOKEN";
    public static final String BROADCAST_FORGOTPASSWORD_TOKEN_STATUS = "BROADCAST_FORGOTPASSWORD_TOKEN_STATUS";

    //PasswordRecoveryChangePassword
    public static final String BROADCAST_PASSWORD_RECOVERY_CHANGE_PASSWORD = "BROADCAST_PASSWORD_RECOVERY_CHANGE_PASSWORD";
    public static final String BROADCAST_PASSWORD_RECOVERY_CHANGE_PASSWORD_STATUS = "BROADCAST_PASSWORD_RECOVERY_CHANGE_PASSWORD_STATUS";
    private SignUpRequest signupRequestData;


    public AuthManager(Context mcontext) {
        this.mcontext = mcontext;
    }

    public void updateContext(Context mcontext) {
        if (mcontext != null)
            this.mcontext = mcontext.getApplicationContext();
    }

    public void loginApi(LoginRequest request) {
        Call<ResponseBody> responsecall = ObjectFactory.getInstance().getRestClient(mcontext).getApiService().userLogin(request);
        responsecall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                progressDialogDismiss();


                if (response.body() != null) {
                    try {
                        String responseStr = new String(response.body().bytes());
                        if (!TextUtils.isEmpty(responseStr)) {
                            JSONObject jsonObject = new JSONObject(responseStr);
                            if (jsonObject != null && (jsonObject.getBoolean(JSON_RESPONSE_STATUS))) {
                                Login loginRes = new Gson().fromJson(responseStr, Login.class);
                                if (loginRes != null) {
                                    ObjectFactory.getInstance().getAppPreference(mcontext).setIsLoggedIn("1");
                                    ObjectFactory.getInstance().getAppPreference(mcontext).setGuid(loginRes.getData().getGuid());
                                    ObjectFactory.getInstance().getAppPreference(mcontext).setEmailId(loginRes.getData().getEmail());
                                    ObjectFactory.getInstance().getAppPreference(mcontext).saveUserType(responseStr);
                                    ObjectFactory.getInstance().getAppPreference(mcontext).setCustomerID(loginRes.getData().getCustomerid());
                                    Intent intentRes = new Intent(BROADCAST_LOGIN_RESPONSE);
                                    intentRes.putExtra(BROADCAST_LOGIN_RESPONSE_STATUS, true);
                                    LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                                    return;
                                }

                            } else {
                                LoginFail loginFail = new Gson().fromJson(responseStr, LoginFail.class);
                                Intent intentRes = new Intent(BROADCAST_LOGIN_RESPONSE);
                                intentRes.putExtra(BROADCAST_LOGIN_RESPONSE_STATUS, false);
                                intentRes.putExtra(BROADCAST_LOGIN_RESPONSE_MESSAGE, loginFail.getErrorMessage());
                                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                                return;
                            }
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Intent intentRes = new Intent(BROADCAST_LOGIN_RESPONSE);
                    intentRes.putExtra(BROADCAST_LOGIN_RESPONSE_STATUS, false);
                    intentRes.putExtra(BROADCAST_LOGIN_RESPONSE_MESSAGE, "Login failed");
                    LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                    return;
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Intent intentRes = new Intent(BROADCAST_LOGIN_RESPONSE);
                intentRes.putExtra(BROADCAST_LOGIN_RESPONSE_STATUS, false);
                intentRes.putExtra(BROADCAST_LOGIN_RESPONSE_MESSAGE, "Login failed");
                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                return;
            }
        });
    }

    public void signUpApi(SignUpRequest request) {
        Call<ResponseBody> responsecall = ObjectFactory.getInstance().getRestClient(mcontext).getApiService().userRegister(request);
        responsecall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.body() != null) {
                    try {
                        String responseStr = new String(response.body().bytes());
                        if (!TextUtils.isEmpty(responseStr)) {
                            JSONObject jsonObject = new JSONObject(responseStr);
                            if (jsonObject != null && (jsonObject.getBoolean(JSON_RESPONSE_STATUS) == true)) {
                                Signup signup = new Gson().fromJson(responseStr, Signup.class);
                                if (signup != null) {
                                    ObjectFactory.getInstance().getAppPreference(mcontext).setIsLoggedIn("1");
                                    ObjectFactory.getInstance().getAppPreference(mcontext).setGuid(signup.getData().getGuid());
                                    ObjectFactory.getInstance().getAppPreference(mcontext).setEmailId(signup.getData().getEmail());
                                    ObjectFactory.getInstance().getAppPreference(mcontext).setCustomerID(signup.getData().getCustomerid());

                                    ObjectFactory.getInstance().getAppPreference(mcontext).saveSignUpDetails(responseStr);
                                    Intent intentRes = new Intent(BROADCAST_SIGN_UP_RESPONSE);
                                    intentRes.putExtra(BROADCAST_SIGN_UP_RESPONSE_STATUS, true);
                                    LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                                    return;
                                }
                            } else if (jsonObject != null && (jsonObject.getBoolean(JSON_RESPONSE_STATUS) == false)) {
                                SignUpFailed signUpFailed = new Gson().fromJson(responseStr, SignUpFailed.class);
                                Intent intentRes = new Intent(BROADCAST_SIGN_UP_RESPONSE);
                                intentRes.putExtra(BROADCAST_SIGN_UP_RESPONSE_STATUS, false);
                                intentRes.putExtra(BROADCAST_SIGN_UP_RESPONSE_MESSAGE, signUpFailed.getErrorMessage());
                                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                                return;
                            }
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Intent intentRes = new Intent(BROADCAST_SIGN_UP_RESPONSE);
                    intentRes.putExtra(BROADCAST_SIGN_UP_RESPONSE_STATUS, false);
                    intentRes.putExtra(BROADCAST_SIGN_UP_RESPONSE_MESSAGE,  Constants.SERVER_ERROR);
                    LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Intent intentRes = new Intent(BROADCAST_SIGN_UP_RESPONSE);
                intentRes.putExtra(BROADCAST_SIGN_UP_RESPONSE_STATUS, false);
                intentRes.putExtra(BROADCAST_SIGN_UP_RESPONSE_MESSAGE,  Constants.SERVER_ERROR);
                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
            }
        });
    }


    public void passwordRecoverySend(ForgotPasswordRequest request) {
        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(mcontext).getApiService().passwordRecoverySend(request);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ProgressbarDismiss();
                if (response.body() != null) {
                    String responseStr = null;
                    try {
                        responseStr = new String(response.body().bytes());
                        Logger.d(responseStr);
                        if (!TextUtils.isEmpty(responseStr)) {
                            JSONObject jsonObject = new JSONObject(responseStr);
                            if (jsonObject != null && (jsonObject.getBoolean(JSON_RESPONSE_STATUS))) {
                                ForgotPasswordResponse forgotPasswordResponse = new Gson().fromJson(responseStr, ForgotPasswordResponse.class);
                                if (forgotPasswordResponse != null) {
                                    ObjectFactory.getInstance().getAppPreference(mcontext).setPasswordRecoveryToken(forgotPasswordResponse.getData().getResetCode());
                                    ObjectFactory.getInstance().getAppPreference(mcontext).setRecoveryEmail(forgotPasswordResponse.getData().getEmail());

                                    Intent intentRes = new Intent(FORGOTPASSWORD_RESPONSE);
                                    intentRes.putExtra(FORGOTPASSWORD_RESPONSE_STATUS, true);
                                    LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                                    return;
                                }
                            } else if (jsonObject != null && (jsonObject.getBoolean(JSON_RESPONSE_STATUS) == false)) {
                                Intent intentRes = new Intent(FORGOTPASSWORD_RESPONSE);
                                intentRes.putExtra(FORGOTPASSWORD_RESPONSE_STATUS, false);
                                intentRes.putExtra(FORGOTPASSWORD_RESPONSE_MESSAGE, ""+jsonObject.getString("errorMessage"));
                                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                                return;
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Intent intentRes = new Intent(FORGOTPASSWORD_RESPONSE);
                    intentRes.putExtra(FORGOTPASSWORD_RESPONSE_STATUS, false);
                    intentRes.putExtra(FORGOTPASSWORD_RESPONSE_MESSAGE, Constants.SERVER_ERROR);
                    LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ProgressbarDismiss();
                Intent intentRes = new Intent(FORGOTPASSWORD_RESPONSE);
                intentRes.putExtra(FORGOTPASSWORD_RESPONSE_STATUS, false);
                intentRes.putExtra(FORGOTPASSWORD_RESPONSE_MESSAGE, Constants.SERVER_ERROR);
                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
            }
        });
    }

    public void passwordRecoveryConfirmationToken(ForgotPasswordTokenRequest forgotPasswordTokenRequest) {
        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(mcontext).getApiService().passwordRecoveryToken(forgotPasswordTokenRequest);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ProgressbarDismiss();
                if (response.body() != null) {
                    String responseStr = null;
                    try {
                        responseStr = new String(response.body().bytes());
                        if (!TextUtils.isEmpty(responseStr)) {
                            JSONObject jsonObject = new JSONObject(responseStr);
                            if (jsonObject != null && (jsonObject.getBoolean(JSON_RESPONSE_STATUS))) {
                                PasswordRecoveryTokenResponse tokenResponse = new Gson().fromJson(responseStr, PasswordRecoveryTokenResponse.class);
                                if (tokenResponse != null) {
                                    ObjectFactory.getInstance().getAppPreference(mcontext).setPasswordRecoveryTokenFinal(tokenResponse.getData().getResetCode());
                                    ObjectFactory.getInstance().getAppPreference(mcontext).setRecoveryEmail(tokenResponse.getData().getEmail());

                                    Intent intentRes = new Intent(BROADCAST_FORGOTPASSWORD_TOKEN);
                                    intentRes.putExtra(BROADCAST_FORGOTPASSWORD_TOKEN_STATUS, true);
                                    LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                                    return;
                                }
                            } else if (jsonObject != null && (!jsonObject.getBoolean(JSON_RESPONSE_STATUS))) {
                                Intent intentRes = new Intent(BROADCAST_FORGOTPASSWORD_TOKEN);
                                intentRes.putExtra(BROADCAST_FORGOTPASSWORD_TOKEN_STATUS, false);
                                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                                return;
                            }


                        }
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                    Intent intentRes = new Intent(BROADCAST_FORGOTPASSWORD_TOKEN);
                    intentRes.putExtra(BROADCAST_FORGOTPASSWORD_TOKEN_STATUS, false);
                    LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                ProgressbarDismiss();
                Intent intentRes = new Intent(BROADCAST_FORGOTPASSWORD_TOKEN);
                intentRes.putExtra(BROADCAST_FORGOTPASSWORD_TOKEN_STATUS, false);
                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
            }
        });
    }

    public void passwordRecoveryChangePassword(final ForgotPasswordChangeRequest request) {
        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(mcontext).getApiService().passwordRecoveryChangePassword(request);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ProgressbarDismiss();
                if (response.body() != null) {
                    String responseStr = null;
                    try {
                        responseStr = new String(response.body().bytes());
                        if (!TextUtils.isEmpty(responseStr)) {
                            JSONObject jsonObject = new JSONObject(responseStr);
                            if (jsonObject != null && (jsonObject.getBoolean(JSON_RESPONSE_STATUS))) {
                                ForgotPasswordChangeResponse changeResponse = new Gson().fromJson(responseStr, ForgotPasswordChangeResponse.class);
                                if (changeResponse != null) {
                                    Intent intentRes = new Intent(BROADCAST_PASSWORD_RECOVERY_CHANGE_PASSWORD);
                                    intentRes.putExtra(BROADCAST_PASSWORD_RECOVERY_CHANGE_PASSWORD_STATUS, true);
                                    LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                                    return;
                                }
                            } else if (jsonObject != null && (!jsonObject.getBoolean(JSON_RESPONSE_STATUS))) {
                                Intent intentRes = new Intent(BROADCAST_PASSWORD_RECOVERY_CHANGE_PASSWORD);
                                intentRes.putExtra(BROADCAST_PASSWORD_RECOVERY_CHANGE_PASSWORD_STATUS, false);
                                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                                return;
                            }
                        }
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }

                }

                Intent intentRes = new Intent(BROADCAST_PASSWORD_RECOVERY_CHANGE_PASSWORD);
                intentRes.putExtra(BROADCAST_PASSWORD_RECOVERY_CHANGE_PASSWORD_STATUS, false);
                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                return;
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ProgressbarDismiss();
                Intent intentRes = new Intent(BROADCAST_PASSWORD_RECOVERY_CHANGE_PASSWORD);
                intentRes.putExtra(BROADCAST_PASSWORD_RECOVERY_CHANGE_PASSWORD_STATUS, false);
                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
            }
        });

    }
    public void ProgressbarDismiss() {
        Intent intent = new Intent(Constants.Login.BROADCAST_PROGRESS_WHEEL);
        intent.putExtra(Constants.Login.BROADCAST_ISDISPLAY_PROGRESS_WHEEL, false);
        LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intent);
    }

    public void setSignupRequestData(SignUpRequest signupRequestData) {
        this.signupRequestData = signupRequestData;
    }

    public SignUpRequest getSignupRequestData() {
        return signupRequestData;
    }

    public void apiFBLogin(FbParameters fbLoginRequest) {
        Gson gson = new Gson();
        String json = gson.toJson(fbLoginRequest);
        Log.d("fblogin params",json.toString());
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(mcontext).getApiService().fbLogin(body);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ProgressbarDismiss();
                if (response.body() != null) {
                    String responseStr = null;
                    try {
                        responseStr = new String(response.body().bytes());
                        if (!TextUtils.isEmpty(responseStr)) {
                            JSONObject jsonObject = new JSONObject(responseStr);
                            if (jsonObject != null && (jsonObject.getBoolean(JSON_RESPONSE_STATUS))) {
                                Login loginRes = new Gson().fromJson(responseStr, Login.class);
                                if (loginRes != null) {
                                    ObjectFactory.getInstance().getAppPreference(mcontext).setIsLoggedIn("1");
                                    ObjectFactory.getInstance().getAppPreference(mcontext).setGuid(loginRes.getData().getGuid());
                                    ObjectFactory.getInstance().getAppPreference(mcontext).setEmailId(loginRes.getData().getEmail());
                                    ObjectFactory.getInstance().getAppPreference(mcontext).saveUserType(responseStr);
                                    ObjectFactory.getInstance().getAppPreference(mcontext).setCustomerID(loginRes.getData().getCustomerid());
                                    Intent intentRes = new Intent(BROADCAST_LOGIN_RESPONSE);
                                    intentRes.putExtra(BROADCAST_LOGIN_RESPONSE_STATUS, true);
                                    LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                                    return;
                                }

                            } else {
                                LoginFail loginFail = new Gson().fromJson(responseStr, LoginFail.class);
                                Intent intentRes = new Intent(BROADCAST_LOGIN_RESPONSE);
                                intentRes.putExtra(BROADCAST_LOGIN_RESPONSE_STATUS, false);
                                intentRes.putExtra(BROADCAST_LOGIN_RESPONSE_MESSAGE, loginFail.getErrorMessage());
                                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                                return;
                            }
                        }
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                        Intent intentRes = new Intent(BROADCAST_LOGIN_RESPONSE);
                        intentRes.putExtra(BROADCAST_LOGIN_RESPONSE_STATUS, false);
                        intentRes.putExtra(BROADCAST_LOGIN_RESPONSE_MESSAGE, "Login failed");
                        LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                        return;
                    }

                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ProgressbarDismiss();
                Intent intentRes = new Intent(BROADCAST_LOGIN_RESPONSE);
                intentRes.putExtra(BROADCAST_LOGIN_RESPONSE_STATUS, false);
                intentRes.putExtra(BROADCAST_LOGIN_RESPONSE_MESSAGE, "Login failed");
                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                return;
            }
        });

    }
}
