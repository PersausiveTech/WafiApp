package com.mobtecnica.wafiapps.fragments.wafi_main;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.activity.Login_page_one;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.model.signup.SignUpRequest;
import com.mobtecnica.wafiapps.utils.BaseFragment;
import com.mobtecnica.wafiapps.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupPasswordFragment extends BaseFragment implements View.OnClickListener {
    ImageButton Back3, Next_page3;
    EditText Password;


    public SignupPasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit__three_, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        viewClickListners();

    }

    private void initViews(View view) {
        Back3 = (ImageButton) view.findViewById(R.id.back3);
        Next_page3 = (ImageButton) view.findViewById(R.id.next_page3);
        Password = (EditText) view.findViewById(R.id.editText1edit3);

    }

    private void viewClickListners() {
        Back3.setOnClickListener(this);
        Next_page3.setOnClickListener(this);
//        Show_Password.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back3:
                navigateToEdit_Two_Fragment();
                break;
            case R.id.next_page3:
                if (isValidPassword()) {
                    try {
                        SignUpApi();
                        navigateToEdit_Four_Fragment();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

//            case R.id.password_show:
//            {
//                int flag=0;
//                if (flag==0) {
//                    Password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
//                    flag++;
//                }
//                else if(flag==1) {
//                    Password.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                    flag--;
//                }
//
//
//            }
//                break;
//        }
//
//    }

    private void navigateToEdit_Four_Fragment() {
        Fragment fragment = new SignupDOBFragment();
        if (getActivity() instanceof Login_page_one) {
            ((Login_page_one) getActivity()).addFragment(fragment);
        }
    }

    private void navigateToEdit_Two_Fragment() {
        getActivity().onBackPressed();
    }

    private boolean isValidPassword() {
        if ((!TextUtils.isEmpty(Password.getText().toString()) && Password.getText().toString().length() >= 6)) {
            return true;
        } else {
            if (TextUtils.isEmpty(Password.getText().toString())) {
                Password.setError(Html.fromHtml(getString(R.string.password_should_not_be_empty)));
                return false;
            } else if (Password.getText().toString().length() < 6) {
                Password.setError(Html.fromHtml(getString(R.string.minimum_six_characters_required)));
                Password.requestFocus();
                return false;
            } else
                return false;
        }
    }




    private void SignUpApi() {
        SignUpRequest request = ObjectFactory.getInstance().getAuthManager(getActivity()).getSignupRequestData();
        request.setPassword(Password.getText().toString().trim());
        ObjectFactory.getInstance().getAuthManager(getActivity()).setSignupRequestData(request);
//        ObjectFactory.getInstance().geLoginManager(getActivity()).signUpApi(request);
    }




}