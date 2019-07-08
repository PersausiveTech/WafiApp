package com.mobtecnica.wafiapps.fragments.wafi_main;


import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.activity.MainActivity;
import com.mobtecnica.wafiapps.manager.AuthManager;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.model.signup.SignUpRequest;
import com.mobtecnica.wafiapps.utils.BaseFragment;
import com.mobtecnica.wafiapps.utils.Constants;
import com.mobtecnica.wafiapps.utils.Utils;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupDOBFragment extends BaseFragment implements View.OnClickListener {

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().matches(AuthManager.BROADCAST_SIGN_UP_RESPONSE)) {
                dismissLoadingDialog();
                if (intent.getBooleanExtra(AuthManager.BROADCAST_SIGN_UP_RESPONSE_STATUS, false)) {
//                    navigateToSignInFragment();
                        Intent intent1 = new Intent(getActivity(), MainActivity.class);
                    if(getActivity().getIntent().getExtras()!=null)
                        intent.putExtras(getActivity().getIntent().getExtras());
                    if(getActivity().getIntent().getData()!=null)
                        intent1.setData(getActivity().getIntent().getData());

                    startActivity(intent1);
                    getActivity().finish();
                } else {
                    String message = Constants.SERVER_ERROR;
                    try {
                        message = intent.getStringExtra(AuthManager.BROADCAST_SIGN_UP_RESPONSE_MESSAGE);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Utils.makeSnackBar(Start_Shopping, message, Snackbar.LENGTH_LONG);
                }
            }
        }
    };

    Button Start_Shopping;
    ImageButton Back4;
    TextView Day, Month, Year;


    public SignupDOBFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit__four_, container, false);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        viewClickListners();
    }

    private void initViews(View view) {
        IntentFilter intent = new IntentFilter();
        intent.addAction(AuthManager.BROADCAST_SIGN_UP_RESPONSE);
        addBroadcastListener(receiver, intent);


        Start_Shopping = (Button) view.findViewById(R.id.startshoppee);
        Back4 = (ImageButton) view.findViewById(R.id.back4);
        Day = (TextView) view.findViewById(R.id.edit_four_day);
        Month = (TextView) view.findViewById(R.id.edit_four_month);
        Year = (TextView) view.findViewById(R.id.edit_four_year);
    }

    private void viewClickListners() {
        Back4.setOnClickListener(this);
        Start_Shopping.setOnClickListener(this);
        Day.setOnClickListener(this);
        Month.setOnClickListener(this);
        Year.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.startshoppee:
                SignUpApi();
                break;
            case R.id.back4:
                navigateToEdit_Three_Fragment();
                break;
            case R.id.edit_four_day:
                showDatePicker();
                break;
            case R.id.edit_four_month:
                showDatePicker();
                break;

            case R.id.edit_four_year:
                showDatePicker();
                break;


        }
    }

    private void showDatePicker() {
        DatePickerFragment date = new DatePickerFragment();
        /**
         * Set Up Current Date Into dialog
         */
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        date.setCallBack(ondate);
        date.show(getFragmentManager(), "Date Picker");
    }

    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            Day.setText(String.valueOf(dayOfMonth));
            Month.setText(String.valueOf(monthOfYear + 1));
            Year.setText(String.valueOf(year));
        }
    };


    private void SignUpApi() {
        showLoadingDialog();
        SignUpRequest request = ObjectFactory.getInstance().getAuthManager(getActivity()).getSignupRequestData();
        request.setDateOfBirthDay(Day.getText().toString().trim());
        request.setDateOfBirthMonth(Month.getText().toString().trim());
        request.setDateOfBirthYear(Year.getText().toString().trim());
        ObjectFactory.getInstance().geLoginManager(getActivity()).signUpApi(request);

    }

    private void navigateToEdit_Three_Fragment() {
        getActivity().onBackPressed();
    }
}

