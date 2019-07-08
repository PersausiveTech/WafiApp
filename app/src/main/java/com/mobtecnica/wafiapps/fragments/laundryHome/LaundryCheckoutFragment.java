package com.mobtecnica.wafiapps.fragments.laundryHome;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRadioButton;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.activity.LaundryActivity;
import com.mobtecnica.wafiapps.fragments.wafi_main.AddNewAddressFragment;
import com.mobtecnica.wafiapps.manager.LaundryManager;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.manager.ProfileManager;
import com.mobtecnica.wafiapps.model.LaundryModel.LaundryCart.CartsItem;
import com.mobtecnica.wafiapps.model.LaundryModel.laundry_checkout.AreaList;
import com.mobtecnica.wafiapps.model.LaundryModel.laundry_checkout.LaundryCheckoutRequest;
import com.mobtecnica.wafiapps.model.LaundryModel.laundry_checkout.LaundryCheckoutResponse;
import com.mobtecnica.wafiapps.model.LaundryModel.laundry_checkout.ListArea;
import com.mobtecnica.wafiapps.model.LaundryModel.laundry_checkout.ListTimeSlot;
import com.mobtecnica.wafiapps.model.LaundryModel.placeorder.CartItem;
import com.mobtecnica.wafiapps.model.LaundryModel.placeorder.CustDetails;
import com.mobtecnica.wafiapps.model.LaundryModel.placeorder.LaundryPlaceorderRequest;
import com.mobtecnica.wafiapps.model.LaundryModel.placeorder.Model;
import com.mobtecnica.wafiapps.model.checkout.startCheckout.ItemQuantityCheckout;
import com.mobtecnica.wafiapps.model.profile.get_address.Addresses;
import com.mobtecnica.wafiapps.model.profile.get_address.CustomAddressAttributes;
import com.mobtecnica.wafiapps.model.profile.get_address.GetUserAddressRequest;
import com.mobtecnica.wafiapps.utils.BaseFragment;
import com.mobtecnica.wafiapps.utils.Constants;
import com.mobtecnica.wafiapps.utils.Utils;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import paytabs.project.PayTabActivity;

/**
 * Created by siby on 16-Jun-17.
 */

public class LaundryCheckoutFragment extends BaseFragment implements View.OnClickListener {
    int selected_position = 0;
    List<String> available_address = new ArrayList<>();
    List<String> areas = new ArrayList<>();
    Calendar myCalendar = Calendar.getInstance();
    private View rootView;
    private RadioGroup ll_pickup_time;
    private RadioGroup ll_deliver_time;
    private LaundryCheckoutResponse laundryCheckoutData;
    private ImageView iv_back_checkout;
    private List<ListArea> listAres = new ArrayList<>();
    private List<ListTimeSlot> timeSlotes;
    private EditText tv_name;
    private EditText tv_email;
    private EditText tv_mobile;
    private EditText tv_flat_num;
    private EditText tv_road_num;
    private EditText tv_building_num;
    private EditText tv_block;
    private EditText land_mark;
    private RadioButton cash_on_delivery;
    private SearchableSpinner spinner_change_address;
    private SearchableSpinner spinner_change_area;
    private FloatingActionButton datepicker_laundry_deliver;
    private ArrayList<CartsItem> data = new ArrayList<>();
    private LinearLayout ll_order_summery;
    private TextView tv_subtotal;
    private TextView tv_discount;
    private TextView tv_delivery_charge;
    private TextView tv_amount_payable;
    private TextView tv_pickup_day;
    private TextView tv_pickup_date;
    private TextView tv_deliver_day;
    private TextView tv_deliver_date;
    private String mLastName = "";
    private String mCountryId = "";
    private String mSelectedAddressId = "";
    private String mAmountPayable = "";
    private String mDeliveryArea = "";
    private String mDeliveryDate = "";
    private String isExpressCheckout = "";
    private String mDeliveryTimeId = "";
    private String mPickupTimeId = "";
    private String mDiscountPercent = "";
    private Integer mLaundryTypeId = 0;
    private String mPickupDate = "";
    private Button btn_place_order;
    private ScrollView scrollView;

    DatePickerDialog.OnDateSetListener date_pickup = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            String myFormat = "MM/dd/yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            String dt = sdf.format(myCalendar.getTime());  // Start date

/*
date validation
*/
            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            String formattedDate = df.format(c.getTime());
            try {
                Date currentdate = df.parse(formattedDate);
                Date date2 = df.parse(dt);
                if (currentdate.compareTo(date2) < 0) {
//if success setting all dates
// set pick up
                    try {
                        Calendar c_pick = Calendar.getInstance();
                        c_pick.setTime(sdf.parse(dt));
                        dt = sdf.format(c_pick.getTime());
                        mPickupDate = dt;
                        tv_pickup_date.setText(dt);
                        Date date = sdf.parse(dt);
                        tv_pickup_day.setText(android.text.format.DateFormat.format("EEEE", date));

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    //set delivery date
                    try {
                        Calendar c_deli = Calendar.getInstance();
                        c_deli.setTime(sdf.parse(dt));
                        c_deli.add(Calendar.DATE, 2);
                        dt = sdf.format(c_deli.getTime());
                        mDeliveryDate = dt;
                        tv_deliver_date.setText(dt);
                        Date date = sdf.parse(dt);
                        tv_deliver_day.setText(android.text.format.DateFormat.format("EEEE", date));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    Utils.makeSnackBar(tv_subtotal, Constants.DATE_NOT_VALID, Snackbar.LENGTH_LONG);
                }
            } catch (Exception e) {

            }
        }

    };
    DatePickerDialog.OnDateSetListener date_delivery = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            String myFormat = "MM/dd/yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            String dt = sdf.format(myCalendar.getTime());
            //just time validation
            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            String formattedDate = df.format(c.getTime());
            try {
                Date currentdate = df.parse(formattedDate);
                Date date2 = df.parse(dt);
                if (currentdate.compareTo(date2) < 0) {
                    mDeliveryDate = dt;
                    try {
                        Calendar c_pick = Calendar.getInstance();
                        c_pick.setTime(sdf.parse(dt));
                        dt = sdf.format(c_pick.getTime());
                        tv_deliver_date.setText(dt);
                        Date date = sdf.parse(dt);
                        tv_deliver_day.setText(android.text.format.DateFormat.format("EEEE", date));

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    Utils.makeSnackBar(tv_subtotal, R.string.date_not_valid, Snackbar.LENGTH_LONG);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    };
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().matches(LaundryManager.BROADCAST_LAUNDRY_CHECKOUT)) {
                if (intent.getBooleanExtra(LaundryManager.BROADCAST_LAUNDRY_CHECKOUT_STATUS, false)) {
                    setDatas();
                    setOrderSummery();

                } else if (intent.getBooleanExtra(LaundryManager.BROADCAST_GET_ADDRESS_RESPONSE_AREA_LIST, false)) {
                    setAreaList();

                }
            } else if (intent.getAction().matches(ProfileManager.BROADCAST_GET_ADDRESS_RESPONSE_LAUNDRY_CART)) {
                if (intent.getBooleanExtra(ProfileManager.BROADCAST_GET_ADDRESS_RESPONSE_LAUNDRY_STATUS_CART, false)) {
//                    selectAddressAdapter();
                    setSpinner();
                }
            } else if (intent.getAction().matches(LaundryManager.BROADCAST_LAUNDRY_PLACE_ORDER)) {
                if (intent.getBooleanExtra(LaundryManager.BROADCAST_LAUNDRY_PLACE_ORDER_STATUS, false)) {

                    if (payment_method.equalsIgnoreCase(getString(R.string.cash_delivery))) {
                        Fragment fragment = new LaundrySuccessPageFragment();
//                        getFragmentManager().beginTransaction()
//                                .replace(R.id.sb_root_product, fragment, Utils.getTagForFragment(fragment))
//                                .commit();
                        ((LaundryActivity) getActivity()).addFragment(fragment);
                        Utils.makeSnackBar(tv_subtotal, getString(R.string.order_placed), Snackbar.LENGTH_LONG);
                    } else if (payment_method.equalsIgnoreCase(getString(R.string.paytab))) {
                        payTabSDKCall("1234");
                    } else {
                        Fragment fragment = new LaundrySuccessPageFragment();
//                        getFragmentManager().beginTransaction()
//                                .replace(R.id.sb_root_product, fragment, Utils.getTagForFragment(fragment))
//                                .commit();
                        ((LaundryActivity) getActivity()).addFragment(fragment);
                        Utils.makeSnackBar(tv_subtotal, getString(R.string.order_placed), Snackbar.LENGTH_LONG);
                    }


                } else {
                    Utils.makeSnackBar(tv_subtotal, Constants.SOME_ERROR_OCCURED, Snackbar.LENGTH_LONG);
                }
            }
        }


    };
    private LinearLayout ll_order_summery_hide;
    private FloatingActionButton datepicker_laundry_pickup;
    private String payment_method = "";
    private RadioGroup radioGroup;
    private Integer selectedAreaId = 0;

    @SuppressLint("ValidFragment")
    public LaundryCheckoutFragment(String s) {
        this.isExpressCheckout = s;
    }

    public LaundryCheckoutFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_checkout_laundry, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        initViews();
        onClickListners();
        setInitialDates();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        apiCall();
        apiCallTogetAllAddress();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.datepicker_laundry_pickup:
                new DatePickerDialog(getActivity(), date_pickup
                        , myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.datepicker_laundry_deliver:
                new DatePickerDialog(getActivity(), date_delivery
                        , myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.btn_place_order:
                if (isExpressCheckout.matches("no")) {
                    if (valid()) {
                        progressbarShowing();
                        placeOrderApiCall();
                    }
                } else {
                    if (valid()) {
                        progressbarShowing();
                        placeOrderApiCall();
                    }
                }
                break;
            case R.id.iv_back_checkout:
                getActivity().onBackPressed();
                break;
            default:
                break;
        }
    }

    private void setSpinner() {
        ArrayList<Addresses> arrayList = ObjectFactory.getInstance().getProfileManager(getActivity()).getAddressList();
        for (int i = 0; i < arrayList.size(); i++) {
            available_address.add(arrayList.get(i).getFirstName() + " " + arrayList.get(i).getLastName());
        }
        available_address.add(getString(R.string.add_new_address));
        if (arrayList.size() > 0) {
            ArrayAdapter<String> adp1 = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1, available_address);
            adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_change_address.setAdapter(adp1);
        }
    }

    private void selectAddressAdapter() {
        ArrayList<Addresses> arrayList = ObjectFactory.getInstance().getProfileManager(getActivity()).getAddressList();

        if (arrayList != null && arrayList.size() > 0) {
            if (selected_position == arrayList.size()) {
                Fragment fragment = new AddNewAddressFragment();
//            getFragmentManager().beginTransaction()
//                    .replace(R.id.sb_root_product, fragment, Utils.getTagForFragment(fragment))
//                    .addToBackStack(fragment.getTag())
//                    .commit();
                ((LaundryActivity) getActivity()).addFragment(fragment);
            } else {
                mLastName = arrayList.get(selected_position).getLastName();
                mCountryId = arrayList.get(selected_position).getCountryId();
                mSelectedAddressId = arrayList.get(selected_position).getId();
                tv_name.setText(arrayList.get(selected_position).getFirstName());
                tv_email.setText(arrayList.get(selected_position).getEmail());
                tv_mobile.setText(arrayList.get(selected_position).getPhoneNumber());

                CustomAddressAttributes[] items = arrayList.get(selected_position).getCustomAddressAttributes();
                try {
                    ArrayList<CustomAddressAttributes> address = new ArrayList<CustomAddressAttributes>(Arrays.asList(items));
                    tv_flat_num.setText(address.get(0).getDefaultValue());
                    tv_building_num.setText(address.get(1).getDefaultValue());
                    tv_road_num.setText(address.get(2).getDefaultValue());
                    tv_block.setText(address.get(3).getDefaultValue());
                    land_mark.setText(address.get(5).getDefaultValue());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            Utils.makeSnackBar(tv_subtotal, Constants.NO_EXISTING_ADDRESS, Snackbar.LENGTH_LONG);
          /*  Fragment fragment = new AddNewAddressFragment();
            getFragmentManager().beginTransaction()
                    .replace(R.id.sb_root_product, fragment, Utils.getTagForFragment(fragment))
                    .addToBackStack(fragment.getTag())
                    .commit();*/

        }
    }

    private void setAreaList() {
        listAres = new ArrayList<>();
        laundryCheckoutData = ObjectFactory.getInstance().getLaundryManager(getActivity()).getLaundryCheckoutDatas();
        listAres = laundryCheckoutData.getData().getListArea();
        timeSlotes = laundryCheckoutData.getData().getListTimeSlots();

        mLaundryTypeId = laundryCheckoutData.getData().getLaundryTypeID();
        if (selectedAreaId == 0) {
            if (listAres != null) {
                for (int i = 0; i < listAres.size(); i++) {
                    areas.add(listAres.get(i).getAreaName());
                }
            }
            if (areas.size() > 0) {
                ArrayAdapter<String> adp1 = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_list_item_1, areas);
                adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_change_area.setAdapter(adp1);
//                spinner_change_area.performClick();

//                // Spawn a thread that triggers the Spinner to open after 5 seconds...
//                new Thread(new Runnable() {
//                    public void run() {
//                        // DO NOT ATTEMPT TO DIRECTLY UPDATE THE UI HERE, IT WON'T WORK!
//                        // YOU MUST POST THE WORK TO THE UI THREAD'S HANDLER
//                        spinner_change_area.postDelayed(new Runnable() {
//                            public void run() {
//                                // Open the Spinner...
//                                spinner_change_area.performClick();
//                            }
//                        }, 500);
//                    }
//                }).start();
            }
            setTimeslotes(timeSlotes);
        }
    }

//

    private void setDatas() {

//        laundryCheckoutData = new LaundryCheckoutResponse();
        laundryCheckoutData = ObjectFactory.getInstance().getLaundryManager(getActivity()).getLaundryCheckoutDatas();

        timeSlotes = laundryCheckoutData.getData().getListTimeSlots();
        setTimeslotes(timeSlotes);
        mLaundryTypeId = laundryCheckoutData.getData().getLaundryTypeID();

    }

    private void setTimeslotes(List<ListTimeSlot> timeSlotes) {

        ll_pickup_time.setOrientation(LinearLayout.VERTICAL);
        ll_pickup_time.removeAllViews();
        if (timeSlotes != null) {
            for (int i = 0; i < timeSlotes.size(); i++) {

                final AppCompatRadioButton rbn = (AppCompatRadioButton) getActivity().getLayoutInflater().inflate(R.layout.custom_radiobutton, null);//initialize and set content

                rbn.setText(timeSlotes.get(i).getTimeSlot());
                rbn.setTag(timeSlotes.get(i).getTimeSlotValue());
                rbn.setTypeface(Utils.getTypefaceLatoRegular(getContext()));
                ll_pickup_time.addView(rbn);

                rbn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b) {
                            mPickupTimeId = String.valueOf(rbn.getTag());
                        } else {
                        }
                    }
                });
            }
        }


        ll_deliver_time.setOrientation(LinearLayout.VERTICAL);
        ll_deliver_time.removeAllViews();
        if (timeSlotes != null) {
            for (int i = 0; i < timeSlotes.size(); i++) {
//            final RadioButton rbn_ = new RadioButton(getActivity());
                final AppCompatRadioButton rbn_ = (AppCompatRadioButton) getActivity().getLayoutInflater().inflate(R.layout.custom_radiobutton, null);//initialize and set content

                rbn_.setText(timeSlotes.get(i).getTimeSlot());
                rbn_.setTag(timeSlotes.get(i).getTimeSlotValue());
                rbn_.setTypeface(Utils.getTypefaceLatoRegular(getContext()));
                ll_deliver_time.addView(rbn_);
                rbn_.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b) {
//                        Toast.makeText(getActivity(), "" + rbn_.getTag(), Toast.LENGTH_SHORT).show();
                            mDeliveryTimeId = String.valueOf(rbn_.getTag());

                        } else {

                        }
                    }
                });
            }
        }
    }

    private void setInitialDates() {
        String myFormat = "MM/dd/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        String dt = sdf.format(myCalendar.getTime());

        try {
            Calendar c_pick = Calendar.getInstance();
            c_pick.setTime(sdf.parse(dt));
            c_pick.add(Calendar.DATE, 1);
            dt = sdf.format(c_pick.getTime());
            mPickupDate = dt;

            tv_pickup_date.setText(dt);
            Date date = sdf.parse(dt);
            tv_pickup_day.setText(android.text.format.DateFormat.format("EEEE", date));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        //set delivery date
        try {
            Calendar c_deli = Calendar.getInstance();
            c_deli.setTime(sdf.parse(dt));
            c_deli.add(Calendar.DATE, 3);
            dt = sdf.format(c_deli.getTime());
            mDeliveryDate = dt;
            tv_deliver_date.setText(dt);
            Date date = sdf.parse(dt);
            tv_deliver_day.setText(android.text.format.DateFormat.format("EEEE", date));


        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private void onClickListners() {
        datepicker_laundry_deliver.setOnClickListener(this);
        btn_place_order.setOnClickListener(this);
        datepicker_laundry_pickup.setOnClickListener(this);
        iv_back_checkout.setOnClickListener(this);
        spinner_change_address.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selected_position = i;
                selectAddressAdapter();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_change_area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mDeliveryArea = areas.get(i);
                if (listAres != null) {
                    selectedAreaId = listAres.get(i).getAreaID();
                }
                apiCall();
                /*asdfghjkl*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    private void updateLabelPickup() {

        String myFormat = "MM/dd/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
//        tv_pickup_date.setText(sdf.format(myFormat.getTime()));

//        Toast.makeText(getActivity(), "" + sdf.format(myCalendar.getTime()), Toast.LENGTH_SHORT).show();
    }

    private void updateLabelDelivery() {

        String myFormat = "MM/dd/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
//        Toast.makeText(getActivity(), "" + sdf.format(myCalendar.getTime()), Toast.LENGTH_SHORT).show();
    }

    private void initViews() {
        IntentFilter intent = new IntentFilter();
        intent.addAction(LaundryManager.BROADCAST_LAUNDRY_CHECKOUT);
        intent.addAction(ProfileManager.BROADCAST_GET_ADDRESS_RESPONSE_LAUNDRY_CART);
        intent.addAction(LaundryManager.BROADCAST_LAUNDRY_PLACE_ORDER);
        addBroadcastListener(receiver, intent);

        datepicker_laundry_pickup = (FloatingActionButton) rootView.findViewById(R.id.datepicker_laundry_pickup);
        datepicker_laundry_deliver = (FloatingActionButton) rootView.findViewById(R.id.datepicker_laundry_deliver);
        /*views*/

        ll_pickup_time = (RadioGroup) rootView.findViewById(R.id.ll_pickup_time);
        ll_deliver_time = (RadioGroup) rootView.findViewById(R.id.ll_deliver_time);

        /*address*/
        tv_name = (EditText) rootView.findViewById(R.id.tv_name);
        tv_email = (EditText) rootView.findViewById(R.id.tv_email);
        tv_mobile = (EditText) rootView.findViewById(R.id.tv_mobile);
        tv_flat_num = (EditText) rootView.findViewById(R.id.tv_flat_num);
        tv_building_num = (EditText) rootView.findViewById(R.id.tv_building_num);
        tv_road_num = (EditText) rootView.findViewById(R.id.tv_road_num);
        tv_block = (EditText) rootView.findViewById(R.id.tv_block);
        land_mark = (EditText) rootView.findViewById(R.id.land_mark);
        iv_back_checkout = (ImageView) rootView.findViewById(R.id.iv_back_checkout);


        ll_order_summery = (LinearLayout) rootView.findViewById(R.id.ll_order_summery);
        spinner_change_address = (SearchableSpinner) rootView.findViewById(R.id.spinner_change_address);
        spinner_change_address.setTitle(getString(R.string.select_address));
        spinner_change_address.setPositiveButton(getString(R.string.ok));
        spinner_change_area = (SearchableSpinner) rootView.findViewById(R.id.spinner_select_area);
        spinner_change_area.setTitle(getString(R.string.select_area));
        spinner_change_area.setPositiveButton(getString(R.string.ok));

        /*order summery*/
        tv_subtotal = (TextView) rootView.findViewById(R.id.tv_subtotal);
        tv_discount = (TextView) rootView.findViewById(R.id.tv_discount);
        tv_delivery_charge = (TextView) rootView.findViewById(R.id.tv_delivery_charge);
        tv_amount_payable = (TextView) rootView.findViewById(R.id.tv_amount_payable);


        tv_pickup_day = (TextView) rootView.findViewById(R.id.tv_pickup_day);
        tv_pickup_date = (TextView) rootView.findViewById(R.id.tv_pickup_date);

        tv_deliver_day = (TextView) rootView.findViewById(R.id.tv_deliver_day);
        tv_deliver_date = (TextView) rootView.findViewById(R.id.tv_deliver_date);
        btn_place_order = (Button) rootView.findViewById(R.id.btn_place_order);
        cash_on_delivery = (RadioButton) rootView.findViewById(R.id.cash_on_delivery);
        cash_on_delivery.setChecked(true);
        cash_on_delivery.setTypeface(Utils.getTypefaceLatoRegular(getContext()));

        scrollView = (ScrollView) rootView.findViewById(R.id.scrollView);
        ll_order_summery_hide = (LinearLayout) rootView.findViewById(R.id.ll_order_summery_hide);
        if (isExpressCheckout.matches("yes")) {
            ll_order_summery_hide.setVisibility(View.GONE);
        } else if (isExpressCheckout.matches("no")) {
            ll_order_summery_hide.setVisibility(View.VISIBLE);
        }

        radioGroup = (RadioGroup) rootView.findViewById(R.id.radio_group_payment_selection);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.cash_on_delivery:
                        payment_method = "CashOnDelivery";
                        break;
                    case R.id.credit_or_debit:
                        payment_method = "PayTab";
                        break;
                    default:
                        break;
                }
            }
        });

    }

    private void setOrderSummery() {

        if (isExpressCheckout.matches("no")) {
            data = ObjectFactory.getInstance().getLaundryManager(getActivity()).getLaundryCartList();
            String total = ObjectFactory.getInstance().getLaundryManager(getActivity()).getAmountCart();
            ll_order_summery.removeAllViews();
            if (data.size() > 0) {
                for (int i = 0; i < data.size(); i++) {
                    View order_view = getActivity().getLayoutInflater().inflate(R.layout.ll_order_summery_laundry, null);
                    TextView tv_item = (TextView) order_view.findViewById(R.id.tv_item);
                    TextView tv_qty = (TextView) order_view.findViewById(R.id.tv_qty);
                    TextView tv_service = (TextView) order_view.findViewById(R.id.tv_service);
                    TextView tv_amount = (TextView) order_view.findViewById(R.id.tv_amount);

                    tv_item.setText(data.get(i).getTitle());
                    tv_qty.setText(data.get(i).getQuantity());
                    tv_service.setText(data.get(i).getServiceType());
                    tv_amount.setText(data.get(i).getAmount());
                    ll_order_summery.addView(order_view);
                }
            }

            tv_subtotal.setText("Subtotal : BD " + String.format("%.3f", Float.parseFloat(total)));
            tv_discount.setText("Discount (" + laundryCheckoutData.getData().getDiscountPercent() + "%)");
            tv_delivery_charge.setText("Delivery Charge(BD): 0.00");
            if (laundryCheckoutData != null && laundryCheckoutData.getSuccess() != null && !TextUtils.isEmpty(laundryCheckoutData.getData().getAmoutPayable())) {
                tv_amount_payable.setText("Amount Payable: BD " + String.format("%.3f", Float.parseFloat(laundryCheckoutData.getData().getAmoutPayable())));
            }
            mDiscountPercent = laundryCheckoutData.getData().getDiscountPercent();
            mAmountPayable = laundryCheckoutData.getData().getAmoutPayable();
        }

    }

    private void apiCallTogetAllAddress() {
        progressbarShowing();
        GetUserAddressRequest addressRequest = new GetUserAddressRequest();
        addressRequest.setGuid(ObjectFactory.getInstance().getAppPreference(getActivity()).getGuid());
        addressRequest.setApiToken(Constants.API_TOKEN);
        ObjectFactory.getInstance().getProfileManager(getActivity()).getUserAddress(addressRequest, 3);
    }

    private void apiCall() {
        progressbarShowing();
        LaundryCheckoutRequest laundryCheckoutRequest = new LaundryCheckoutRequest();
        laundryCheckoutRequest.setApiToken(Constants.API_TOKEN_LAUNDRY);
        laundryCheckoutRequest.setCustGuid(ObjectFactory.getInstance().getAppPreference(getActivity()).getGuid());
        laundryCheckoutRequest.setIsExpressCheckout(isExpressCheckout);
        laundryCheckoutRequest.setPromoCode("");
        laundryCheckoutRequest.setLaundryTypeID(mLaundryTypeId);
        laundryCheckoutRequest.setAreaID(selectedAreaId);
        String json = new Gson().toJson(laundryCheckoutRequest);
        ObjectFactory.getInstance().getLaundryManager(getActivity()).laundryCheckout(json);

        AreaList areaList = new AreaList();
        areaList.setApiToken(Constants.API_TOKEN_LAUNDRY);
        areaList.setCustGuid(ObjectFactory.getInstance().getAppPreference(getActivity()).getGuid());
        ObjectFactory.getInstance().getLaundryManager(getActivity()).getAreaListLaundry(areaList);
    }

    private void progressbarShowing() {
        Intent intent = new Intent(Constants.Login.BROADCAST_PROGRESS_WHEEL);
        intent.putExtra(Constants.Login.BROADCAST_ISDISPLAY_PROGRESS_WHEEL, true);
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

    private boolean valid() {
        boolean status = true;
        if (TextUtils.isEmpty(mDeliveryArea)) {
            scrollView.smoothScrollTo(0,spinner_change_area.getBottom());
//            spinner_change_area.requestFocus();
            Utils.makeSnackBar(tv_subtotal, Constants.SELECT_AREA, Snackbar.LENGTH_LONG);
            status = false;
        } else if (TextUtils.isEmpty(mCountryId)) {
            status = false;
            scrollView.smoothScrollTo(0,spinner_change_address.getBottom());
//            spinner_change_address.requestFocus();
            Utils.makeSnackBar(tv_subtotal, Constants.SELECT_DELIVERY_ADDRESS, Snackbar.LENGTH_LONG);
        } else if (TextUtils.isEmpty(mPickupTimeId)) {
            status = false;
            scrollView.smoothScrollTo(0,((View)ll_pickup_time.getParent().getParent().getParent()).getBottom());
//            ll_pickup_time.requestFocus();
            Utils.makeSnackBar(tv_subtotal, Constants.SELECT_PICKUP_TIME, Snackbar.LENGTH_LONG);
        } else if (TextUtils.isEmpty(mDeliveryTimeId)) {
            status = false;
            scrollView.smoothScrollTo(0,((View)ll_deliver_time.getParent().getParent().getParent()).getBottom());
//            ll_deliver_time.requestFocus();
            Utils.makeSnackBar(tv_subtotal, Constants.SELECT_DELIVERY_TIME, Snackbar.LENGTH_LONG);
        } else if (TextUtils.isEmpty(tv_name.getText().toString())) {
            tv_name.setError(getString(R.string.enter_name));
            Utils.makeSnackBar(tv_subtotal, R.string.please_fill_the_address_fields, Snackbar.LENGTH_LONG);
            status = false;
        } else if (TextUtils.isEmpty(tv_email.getText().toString())) {
            tv_email.setError(getString(R.string.enter_your_email));
            Utils.makeSnackBar(tv_subtotal, R.string.please_fill_the_address_fields, Snackbar.LENGTH_LONG);

            status = false;
        } else if (TextUtils.isEmpty(tv_mobile.getText().toString())) {
            tv_mobile.setError(getString(R.string.enter_mobile_number));
            Utils.makeSnackBar(tv_subtotal, R.string.please_fill_the_address_fields, Snackbar.LENGTH_LONG);

            status = false;
        } else if (TextUtils.isEmpty(tv_flat_num.getText().toString())) {
            tv_flat_num.setError(getString(R.string.enter_flat_number));
            Utils.makeSnackBar(tv_subtotal, R.string.please_fill_the_address_fields, Snackbar.LENGTH_LONG);

            status = false;
        } else if (TextUtils.isEmpty(tv_building_num.getText().toString())) {
            tv_building_num.setError(getString(R.string.enter_building_number));
            Utils.makeSnackBar(tv_subtotal, R.string.please_fill_the_address_fields, Snackbar.LENGTH_LONG);
            status = false;
        } else if (TextUtils.isEmpty(tv_road_num.getText().toString())) {
            tv_road_num.setError(getString(R.string.enter_road_number));
            Utils.makeSnackBar(tv_subtotal, R.string.please_fill_the_address_fields, Snackbar.LENGTH_LONG);
            status = false;
        } else if (TextUtils.isEmpty(tv_block.getText().toString())) {
            tv_block.setError(getString(R.string.enter_block_number));
            Utils.makeSnackBar(tv_subtotal, R.string.please_fill_the_address_fields, Snackbar.LENGTH_LONG);
            status = false;
        }
        return status;
    }

    private void placeOrderApiCall() {
        LaundryPlaceorderRequest laundryPlaceorderRequest = new LaundryPlaceorderRequest();
        laundryPlaceorderRequest.setApiToken(Constants.API_TOKEN_LAUNDRY);
        laundryPlaceorderRequest.setCustGuid(ObjectFactory.getInstance().getAppPreference(getActivity()).getGuid());
        laundryPlaceorderRequest.setEmailID(ObjectFactory.getInstance().getAppPreference(getActivity()).getEmailId());
        laundryPlaceorderRequest.setIsExpressCheckout(isExpressCheckout);

        Model model = new Model();
//        model.setAmoutPayable(mAmountPayable);        //no value available
        model.setAmoutPayable(tv_subtotal.getText().toString());//

        model.setDeliveryArea(mDeliveryArea);
        model.setDeliveryDate(mDeliveryDate);
        model.setDeliveryTimeID(mDeliveryTimeId);


//        model.setDiscountPercent(mDiscountPercent);   //no value available
        model.setDiscountPercent(mDiscountPercent);
//        model.setLaundryTypeID(mLaundryTypeId);      //no value
        String laundryTypeId = ObjectFactory.getInstance().getLaundryManager(getActivity()).getSelectedLaundryTypeId();
        model.setLaundryTypeID(laundryTypeId);
        model.setPickupDate(mPickupDate);
        model.setPickupTimeID(mPickupTimeId);
        model.setSpecialRequest("");

        CustDetails custDetails = new CustDetails();
        custDetails.setAddress1("");
        custDetails.setName(tv_name.getText().toString());
        custDetails.setLastName(mLastName);
        custDetails.setCountryId(mCountryId);
        custDetails.setEmail(ObjectFactory.getInstance().getAppPreference(getActivity()).getEmailId());
        custDetails.setMobile(tv_mobile.getText().toString().trim());
        custDetails.setFlatVilla(tv_flat_num.getText().toString().trim());
        custDetails.setBuilding(tv_building_num.getText().toString().trim());
        custDetails.setRoad(tv_road_num.getText().toString().trim());
        custDetails.setBlock(tv_block.getText().toString().trim());
        custDetails.setLandmark(land_mark.getText().toString().trim());
        custDetails.setSelectedAddressId(mSelectedAddressId);
        custDetails.setCustomerID(ObjectFactory.getInstance().getAppPreference(getActivity()).getCustomerId());
//        custDetails.setDeliveryCharge(laundryCheckoutData.getData());
        custDetails.setDeliveryCharge("0");
        custDetails.setId(mSelectedAddressId);

        model.setCustDetails(custDetails);

        laundryPlaceorderRequest.setModel(model);
        if (isExpressCheckout.matches("no")) {
            List<CartItem> cartItems = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                CartItem cartItem = new CartItem();
                cartItem.setItemId(data.get(i).getItemId());
                cartItem.setMenuID(data.get(i).getMenuID());
                cartItem.setPrice(data.get(i).getPrice());
                cartItem.setQuantity(data.get(i).getQuantity());
                cartItem.setServiceID(data.get(i).getServiceID());
                cartItems.add(cartItem);
            }
            laundryPlaceorderRequest.setCartItems(cartItems);
        }

        String json = new Gson().toJson(laundryPlaceorderRequest);
        ObjectFactory.getInstance().getLaundryManager(getActivity()).laundryPlaceoeder(json);
    }

    private void payTabSDKCall(String orderNo) {
        Intent in = new Intent(getActivity(), PayTabActivity.class);
//        in.putExtra("pt_merchant_email", "shaijutom123@gmail.com"); //this a demo account for testing the sdk
//        in.putExtra("pt_secret_key", "NG5JVXjaSEXD9CpYUthbevNh4xm6u8qiPv2KslVl0SYaun8pRpA5VomZVJ6qSwIH1Ksu6oP6gOhLbpb3PL8K9vK4fQv7mdPoYhT5");//Add your Secret Key Here
        in.putExtra("pt_merchant_email", Constants.PT_MERCHANT_EMAIL); //this a demo account for testing the sdk
        in.putExtra("pt_secret_key", Constants.PT_SECRET_KEY);//Add your Secret Key Here
        ArrayList<Addresses> arrayList = ObjectFactory.getInstance().getProfileManager(getActivity()).getAddressList();
        int position = ObjectFactory.getInstance().getAppPreference(getActivity()).getBillingAddressPosition();
        Addresses addresses = arrayList.get(position);
        in.putExtra("pt_transaction_title", addresses.getFirstName() + " " + addresses.getLastName());
        String amount[] = ObjectFactory.getInstance().getPaymentManager(getActivity()).getTotalAmount().split(" ");
        in.putExtra("pt_amount", amount[1]);
        in.putExtra("pt_currency_code", "BHD"); //Use Standard 3 character ISO
        in.putExtra("pt_shared_prefs_name", Constants.PAYTABS_PREFNAME);
        in.putExtra("pt_customer_email", addresses.getEmail());

        in.putExtra("pt_customer_phone_number", addresses.getPhoneNumber());
        in.putExtra("pt_order_id", orderNo);
        List<ItemQuantityCheckout> quantityCheckouts = ObjectFactory.getInstance().getPaymentManager(getActivity()).getItemQuantityList();
        String itemNames = "";
        for (int i = 0; i < quantityCheckouts.size(); i++) {
            itemNames = itemNames + quantityCheckouts.get(i).getName();
            if (i != quantityCheckouts.size() - 1) {
                itemNames = itemNames + " ,";
            }
        }
        in.putExtra("pt_product_name", itemNames);//Optional //Billing Address
        in.putExtra("pt_address_billing", addresses.getAddress1());
        in.putExtra("pt_city_billing", addresses.getCountryName());
        in.putExtra("pt_state_billing", addresses.getCountryName());
        in.putExtra("pt_country_billing", addresses.getCountryName());
        int shipping_position = ObjectFactory.getInstance().getAppPreference(getActivity()).getBillingAddressPosition();
        Addresses shipping_addresses = arrayList.get(shipping_position);
        in.putExtra("pt_postal_code_billing", "00973"); //Put Country Phone code if Postal code not available '00973' //Shipping Address
        in.putExtra("pt_address_shipping", shipping_addresses.getAddress1());
        in.putExtra("pt_city_shipping", shipping_addresses.getCountryName());
        in.putExtra("pt_state_shipping", shipping_addresses.getCountryName());
        in.putExtra("pt_country_shipping", shipping_addresses.getCountryName());
        in.putExtra("pt_postal_code_shipping", "00973"); //Put Country Phone code if Postal code not available '00973'
        int requestCode = 111;
        startActivityForResult(in, requestCode);
    }
}
