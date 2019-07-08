package com.mobtecnica.wafiapps.fragments.wafi_main;


import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.AppCompatEditText;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.activity.Login_page_one;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.model.signup.SignUpRequest;
import com.mobtecnica.wafiapps.utils.BaseFragment;
import com.mobtecnica.wafiapps.utils.Constants;
import com.mobtecnica.wafiapps.utils.Utils;

import static com.mobtecnica.wafiapps.R.id.back2;
import static com.mobtecnica.wafiapps.R.id.editText1edit2;
import static com.mobtecnica.wafiapps.R.id.editText2edit2;
import static com.mobtecnica.wafiapps.R.id.editText3edit2;
import static com.mobtecnica.wafiapps.R.id.editText4edit2;
import static com.mobtecnica.wafiapps.R.id.next_page2;
import static com.mobtecnica.wafiapps.R.id.textview104;
import static com.mobtecnica.wafiapps.R.id.textview1050;


public class SignupFormFragment extends BaseFragment implements  View.OnClickListener {

    ImageButton Back2, Next_page2;
    AppCompatEditText First_Name, Last_Name, E_mail, Mobile_number;
    TextView textView104,textView105;

    public SignupFormFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit__two_, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        viewClickListeners();
    }


    private void initViews(View view) {

        Back2 = (ImageButton) view.findViewById(back2);
        Next_page2 = (ImageButton) view.findViewById(next_page2);
        First_Name =  view.findViewById(editText1edit2);
        Last_Name = view.findViewById(editText2edit2);
        E_mail = view.findViewById(editText3edit2);
        Mobile_number =view.findViewById(editText4edit2);
        textView104 = (TextView) view.findViewById(textview104);
        textView105 = (TextView) view.findViewById(textview1050);

        First_Name.setTypeface(Utils.getTypefaceLatoRegular(getContext()));
        Last_Name.setTypeface(Utils.getTypefaceLatoRegular(getContext()));
        E_mail.setTypeface(Utils.getTypefaceLatoRegular(getContext()));
        Mobile_number.setTypeface(Utils.getTypefaceLatoRegular(getContext()));



//        AssetManager am = getActivity().getApplicationContext().getAssets();
//        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Lato-Regular.ttf");
//
//        textView104.setTypeface(custom_font);
//        textView105.setTypeface(custom_font);
//        First_Name.setTypeface(custom_font);
//        Last_Name.setTypeface(custom_font);
//        E_mail.setTypeface(custom_font);
//        Mobile_number.setTypeface(custom_font);

    }

    private void viewClickListeners() {
        Back2.setOnClickListener(this);
        Next_page2.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case back2:
                navigateToLoginPage1Fragment();
                break;

            case R.id.next_page2:
                if (isValidFields()) {
                    try {
                        SignUpApi();
                        navigateToEdit_Three_Fragment();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                break;

            default:
                break;

        }
    }

    private void navigateToEdit_Three_Fragment() {
        Fragment fragment = new SignupPasswordFragment();
        if (getActivity() instanceof Login_page_one) {
            ((Login_page_one) getActivity()).addFragment(fragment);
        }
    }


    private void navigateToLoginPage1Fragment() {
        getActivity().onBackPressed();
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private boolean isValidFields() {
        if (TextUtils.isEmpty(First_Name.getText().toString().trim())) {
            First_Name.setError(Html.fromHtml(getString(R.string.error_message_create_account_first_name)));
            First_Name.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(Last_Name.getText().toString().trim())) {
            Last_Name.setError(Html.fromHtml(getString(R.string.error_message_create_account_last_name)));
            Last_Name.requestFocus();
            return false;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(E_mail.getText().toString()).matches()) {
            E_mail.setError(Html.fromHtml(getString(R.string.error_message_create_account_email)));
            E_mail.requestFocus();
            return false;
        }
        if (Mobile_number.getText().toString().trim().length() < 8 && Utils.isNumeric(Mobile_number.getText().toString().trim())) {// Bahrain has 8 digits mobile number
            Mobile_number.setError(Html.fromHtml(getString(R.string.error_message_create_account_number)));
            Mobile_number.requestFocus();
            return false;
        }
        return true;
    }

    private void SignUpApi() {
//        progressbarShowing();
        SignUpRequest request = new SignUpRequest();
        request.setApiToken(Constants.API_TOKEN);
        request.setFirstName(First_Name.getText().toString().trim());
        request.setLastName(Last_Name.getText().toString().trim());
        request.setEmail(E_mail.getText().toString().trim());
        request.setUsername(First_Name.getText().toString().trim());
        request.setPhone(Mobile_number.getText().toString().trim());
        //  request.setCountryID();
        request.setGender("");
        //request.setDateOfBirthDay("");
//        ObjectFactory.getInstance().geLoginManager(getActivity()).signUpApi(request);
        ObjectFactory.getInstance().getAuthManager(getActivity()).setSignupRequestData(request);
    }


}