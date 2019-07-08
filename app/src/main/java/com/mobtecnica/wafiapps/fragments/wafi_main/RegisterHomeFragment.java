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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.activity.Login_page_one;
import com.mobtecnica.wafiapps.activity.MainActivity;
import com.mobtecnica.wafiapps.activity.Terms_and_Conditions;
import com.mobtecnica.wafiapps.manager.AuthManager;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.model.fbLogin.FBLoginRequest;
import com.mobtecnica.wafiapps.model.fbLogin.FbParameters;
import com.mobtecnica.wafiapps.model.fbLogin.UserClaims;
import com.mobtecnica.wafiapps.utils.BaseFragment;
import com.mobtecnica.wafiapps.utils.Constants;
import com.mobtecnica.wafiapps.utils.Utils;

import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterHomeFragment extends BaseFragment implements View.OnClickListener, FacebookCallback<LoginResult> {


    private CallbackManager callbackManager;
    private Button Fblog;
    private int FB_SIGN_IN = 101;
    private TextView textView101, textView102, textView103;
    private LoginButton fb_loginButton_edit_one;

    private String first_name = "";
    private String email = "";
    private String last_name = "";
    private String ProviderSystemName;
    private String ExternalIdentifier;
    private String OAuthToken;
    private String OAuthAccessToken;


    Button Createaccount, Loginme;
    ImageButton Close;

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().matches(AuthManager.BROADCAST_LOGIN_RESPONSE)) {
                if (intent.getBooleanExtra(AuthManager.BROADCAST_LOGIN_RESPONSE_STATUS, false)) {
                    navigatetoMainActivity();
                } else {
                    try {
                        String msg = intent.getExtras().get(AuthManager.BROADCAST_LOGIN_RESPONSE_MESSAGE).toString();
                        Utils.makeSnackBar(Fblog, msg, Snackbar.LENGTH_LONG);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Utils.makeSnackBar(Fblog, "Login Failed", Snackbar.LENGTH_LONG);
                    }
                }

            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_one, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        viewClickListeners();
    }


    private void initViews(View v) {
        FacebookSdk.sdkInitialize(getActivity());
        IntentFilter intent = new IntentFilter();
        intent.addAction(AuthManager.BROADCAST_LOGIN_RESPONSE);
        addBroadcastListener(receiver, intent);

        Fblog = (Button) v.findViewById(R.id.fb_login_button);
        textView101 = (TextView) v.findViewById(R.id.textView101);
        textView102 = (TextView) v.findViewById(R.id.textView102);
        textView103 = (TextView) v.findViewById(R.id.textView103);
        fb_loginButton_edit_one = (LoginButton) v.findViewById(R.id.login_button_fb_edit_one);
        Createaccount = (Button) v.findViewById(R.id.Createaccnt);
        Loginme = (Button) v.findViewById(R.id.buttonsmalllogin);
        Close = (ImageButton) v.findViewById(R.id.close);
        LoginManager.getInstance().logOut();
//        AssetManager am = getActivity().getApplicationContext().getAssets();
//        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Lato-Regular.ttf");
//
//        Fblog.setTypeface(custom_font);
        callbackManager = CallbackManager.Factory.create();
//        fb_loginButton_edit_one.setTypeface(custom_font);
        fb_loginButton_edit_one.setFragment(this);
        fb_loginButton_edit_one.setReadPermissions("email");
        fb_loginButton_edit_one.registerCallback(callbackManager,this);
//
//        Createaccount.setTypeface(custom_font);
//        Loginme.setTypeface(custom_font);
//        textView102.setTypeface(custom_font);
//        textView101.setTypeface(custom_font);
//        textView103.setTypeface(custom_font);

    }

    private void viewClickListeners() {
        Fblog.setOnClickListener(this);
        Createaccount.setOnClickListener(this);
        Loginme.setOnClickListener(this);
        Close.setOnClickListener(this);
        textView103.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Createaccnt:
                navigateToEdit_Two_Fragment();
                break;

            case R.id.buttonsmalllogin:
                navigateToLogInFragment();
                break;
            case R.id.close:
                getActivity().onBackPressed();
                break;
            case R.id.fb_login_button:
                LoginManager.getInstance().logOut();
                fb_loginButton_edit_one.performClick();
                break;
            case R.id.textView103:
                navigateTotv_termsandconditions_profile();
                break;
            default:
                break;
        }
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//    private void  navigateToMainFragment() {
//        startActivity(new Intent(getActivity(),MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
//        getActivity().finish();
//    }

    private void navigateTotv_termsandconditions_profile() {
        Intent i = new Intent(getActivity(), Terms_and_Conditions.class);
        getActivity().startActivity(i);
    }

    private void navigateToLogInFragment() {
        Fragment fragment = new LoginFragment();
        if (getActivity() instanceof Login_page_one) {
            ((Login_page_one) getActivity()).addFragment(fragment);
        }

    }

    private void navigateToEdit_Two_Fragment() {
        Fragment fragment = new SignupFormFragment();
        if (getActivity() instanceof Login_page_one) {
            ((Login_page_one) getActivity()).addFragment(fragment);
        }

    }

    private void navigatetoMainActivity() {
        getFragmentManager().popBackStack();
        Intent intent = new Intent(getActivity(), MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        if(getActivity().getIntent().getExtras()!=null)
            intent.putExtras(getActivity().getIntent().getExtras());
        intent.setData(getActivity().getIntent().getData());
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        if(loginResult!=null) {
            OAuthAccessToken = loginResult.getAccessToken().getUserId();
            OAuthToken = loginResult.getAccessToken().getToken();
            ExternalIdentifier = loginResult.getAccessToken().getUserId();
            GraphRequest request = GraphRequest.newMeRequest(
                    loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject me, GraphResponse response) {
                            if (response.getError() != null) {
                                // handle error
                            } else {
                                if (me.has("email")) {
                                    email = me.optString("email");
                                    String[] name = me.optString("name").split(" ");
                                    first_name = name[0];
                                    int i=1;
                                    while (i<name.length) {
                                        last_name = last_name + name[i] + " ";
                                        i++;
                                    }

                                    UserClaims userClaims = new UserClaims();
                                    userClaims.setEmail(email);
                                    userClaims.setFirst(first_name);
                                    userClaims.setLast(last_name);
                                    FbParameters fbParameters  = new FbParameters();
                                    fbParameters.setApitoken(Constants.API_TOKEN);
                                    fbParameters.setExternalIdentifier(ExternalIdentifier);
                                    fbParameters.setOAuthToken(OAuthToken);
                                    fbParameters.setOAuthAccessToken(OAuthAccessToken);
                                    fbParameters.setProviderSystemName("ExternalAuth.Facebook");
                                    fbParameters.setUserClaims(userClaims);
                                   /* FBLoginRequest fbLoginRequest = new FBLoginRequest();
                                    fbLoginRequest.setApitoken(Constants.API_TOKEN);
                                    fbLoginRequest.setFbparameters(fbParameters);*/
                                    showLoadingDialog();
                                    ObjectFactory.getInstance().getAuthManager(getActivity()).apiFBLogin(fbParameters);
//                                    loginApiRequest(email, "token", me.optString("name"), "", "", "", "", "", "", "");
                                } else {
                                    Utils.makeSnackBar(Fblog,   R.string.try_with_another_account, Snackbar.LENGTH_LONG);
                                }
                            }
                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,email");
            request.setParameters(parameters);
            request.executeAsync();
        }
    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onError(FacebookException error) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (callbackManager != null) {
            try {
                callbackManager.onActivityResult(requestCode, resultCode, data);
            } catch (Exception e) {
                e.printStackTrace();
                Utils.makeSnackBar(Fblog, R.string.error_try_again, Snackbar.LENGTH_LONG);
            }
        }
    }
}


