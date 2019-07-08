package com.mobtecnica.wafiapps.fragments.laundryHome;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.manager.LaundryManager;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.model.LaundryModel.priceLIst.DataLaundry;
import com.mobtecnica.wafiapps.model.LaundryModel.priceLIst.LstMensWear;
import com.mobtecnica.wafiapps.model.LaundryModel.priceLIst.PriceListRequest;
import com.mobtecnica.wafiapps.utils.BaseFragment;
import com.mobtecnica.wafiapps.utils.Constants;
import com.mobtecnica.wafiapps.utils.Utils;

/**
 * Created by SIby on 03-04-2017.
 */

public class LaundryPriceListFragment extends BaseFragment implements View.OnClickListener {
    private static int SERVICE_TYPE = 1;
    ArrayList<LstMensWear> dataLaundries = null;
    private View view;
    private AppCompatButton btn_mens_wear;
    private AppCompatButton btn_womens_wear;
    private AppCompatButton btn_house_hold;

    private LinearLayout ll_layout;
    private DataLaundry dataLaundries_ob;
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().matches(LaundryManager.BROADCAST_LAUNDRY_ITEMS)) {
                if (intent.getBooleanExtra(LaundryManager.BROADCAST_LAUNDRY_ITEMS_RESPONSE, false)) {
                    setAdapter();
                } else {

                }
            }
        }
    };
    private RadioGroup radioGroup;
    private RadioButton rb_mens_wear;
    private RadioButton rb_womes_wear;
    private RadioButton rb_house_hold;
    private RadioGroup radio_group_servicetype;
    private RadioButton tv_standard_service;
//    private RadioButton tv_premium_service;

    private void setAdapter() {
//        dataLaundries = ObjectFactory.getInstance().getLaundryManager(getActivity()).getLaundryPriceList();

        if (ObjectFactory.getInstance().getLaundryManager(getActivity()).getLaundryPriceList() != null) {
            dataLaundries_ob = ObjectFactory.getInstance().getLaundryManager(getActivity()).getLaundryPriceList();
            setPriceList(dataLaundries_ob.getLstMensWear(), SERVICE_TYPE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_price_listing_laundry, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        initializeViews();
        onClickLIstners();
        return view;
    }

    private void onClickLIstners() {
        rb_mens_wear.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    rb_mens_wear.setTextColor(Color.WHITE);
                } else {
                    rb_mens_wear.setTextColor(Color.BLACK);
                }
            }
        });
        rb_womes_wear.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    rb_womes_wear.setTextColor(Color.WHITE);
                } else {
                    rb_womes_wear.setTextColor(Color.BLACK);
                }
            }
        });
        rb_house_hold.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    rb_house_hold.setTextColor(Color.WHITE);
                } else {
                    rb_house_hold.setTextColor(Color.BLACK);
                }
            }
        });
        tv_standard_service.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    tv_standard_service.setTextColor(Color.BLACK);
                } else {
                    tv_standard_service.setTextColor(Color.WHITE);
                }
            }
        });
//        tv_premium_service.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (b) {
//                    tv_premium_service.setTextColor(Color.BLACK);
//                } else {
//                    tv_premium_service.setTextColor(Color.WHITE);
//                }
//            }
//        });


        btn_house_hold.setOnClickListener(this);
        btn_mens_wear.setOnClickListener(this);
        btn_womens_wear.setOnClickListener(this);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {
                    case R.id.rb_mens_wear:

                        setPriceList(dataLaundries_ob.getLstMensWear(), SERVICE_TYPE);
                        dataLaundries = new ArrayList<>();
                        dataLaundries = dataLaundries_ob.getLstMensWear();
                        break;
                    case R.id.rb_womes_wear:
                        setPriceList(dataLaundries_ob.getLstWomensWear(), SERVICE_TYPE);
                        dataLaundries = new ArrayList<>();
                        dataLaundries = dataLaundries_ob.getLstWomensWear();
                        break;
                    case R.id.rb_house_hold:
                        setPriceList(dataLaundries_ob.getLstHousehold(), SERVICE_TYPE);
                        dataLaundries = new ArrayList<>();
                        dataLaundries = dataLaundries_ob.getLstHousehold();
                        break;
                    default:
                        break;
                }
            }
        });

        radio_group_servicetype.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {
                    case R.id.tv_standard_service:
                        SERVICE_TYPE = 1;

                        setPriceList(dataLaundries, SERVICE_TYPE);


                        break;
//                    case R.id.tv_premium_service:
//                        SERVICE_TYPE = 2;
//
//                        setPriceList(dataLaundries, SERVICE_TYPE);
//
//                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void initializeViews() {
        IntentFilter intent = new IntentFilter();
        intent.addAction(LaundryManager.BROADCAST_LAUNDRY_ITEMS);
        addBroadcastListener(receiver, intent);
        radioGroup = (RadioGroup) view.findViewById(R.id.radio_group_price);
        radio_group_servicetype = (RadioGroup) view.findViewById(R.id.radio_group_servicetype);

        btn_mens_wear = (AppCompatButton) view.findViewById(R.id.btn_mens_wear);
        btn_womens_wear = (AppCompatButton) view.findViewById(R.id.btn_womens_wear);
        btn_house_hold = (AppCompatButton) view.findViewById(R.id.btn_house_hold);

        ll_layout = (LinearLayout) view.findViewById(R.id.price_list_items);
        dataLaundries_ob = new DataLaundry();
        rb_mens_wear = (RadioButton) view.findViewById(R.id.rb_mens_wear);
        rb_womes_wear = (RadioButton) view.findViewById(R.id.rb_womes_wear);
        rb_house_hold = (RadioButton) view.findViewById(R.id.rb_house_hold);

        tv_standard_service = (RadioButton) view.findViewById(R.id.tv_standard_service);
//        tv_premium_service = (RadioButton) view.findViewById(R.id.tv_premium_service);


        rb_mens_wear.setChecked(true);
        rb_mens_wear.setTextColor(Color.WHITE);

        tv_standard_service.setChecked(true);
        tv_standard_service.setTextColor(Color.BLACK);
        rb_mens_wear.setTypeface(Utils.getTypefaceLatoRegular(getContext()));
        rb_womes_wear.setTypeface(Utils.getTypefaceLatoRegular(getContext()));
        rb_house_hold.setTypeface(Utils.getTypefaceLatoRegular(getContext()));
        tv_standard_service.setTypeface(Utils.getTypefaceLatoRegular(getContext()));
//        tv_premium_service.setTypeface(Utils.getTypefaceLatoRegular(getContext()));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressbarShowing();
        apiCall();
    }

    private void progressbarShowing() {
        Intent intent = new Intent(Constants.Login.BROADCAST_PROGRESS_WHEEL);
        intent.putExtra(Constants.Login.BROADCAST_ISDISPLAY_PROGRESS_WHEEL, true);
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
    }

    private void apiCall() {
        PriceListRequest priceListRequest = new PriceListRequest();
        priceListRequest.setApiToken(Constants.API_TOKEN_LAUNDRY);
        ObjectFactory.getInstance().getLaundryManager(getActivity()).getPriceLIst(priceListRequest);
    }

    @Override
    public void onClick(View view) {
        switch ((view.getId())) {
            case R.id.btn_mens_wear:

                break;
            case R.id.btn_womens_wear:

                break;
            case R.id.btn_house_hold:

                break;
            case R.id.tv_standard_service:

                break;
//            case R.id.tv_premium_service:
//
//                break;
            default:
                break;
        }
    }

    private void setPriceList(ArrayList<LstMensWear> dataLaundries, int serviceType) {
        ll_layout.removeAllViews();
        if (dataLaundries != null && dataLaundries.size() > 0) {
            ArrayList<String> strings = new ArrayList<>();
            strings.add("siby");
            for (int i = 0; i < dataLaundries.size(); i++) {
                View view_deals = getActivity().getLayoutInflater().inflate(R.layout.ll_price_list, null);
                TextView laundry_type = (TextView) view_deals.findViewById(R.id.laundry_type);
                TextView tv_cloth_name = (TextView) view_deals.findViewById(R.id.tv_cloth_name);
                TextView tv_cloth_price = (TextView) view_deals.findViewById(R.id.tv_cloth_price);
                if (strings.contains(dataLaundries.get(i).getServiceType())) {
                    laundry_type.setVisibility(View.GONE);

                } else {
                    strings.add(dataLaundries.get(i).getServiceType());
                    laundry_type.setVisibility(View.VISIBLE);
                    laundry_type.setText(dataLaundries.get(i).getServiceType());
                }
                if (serviceType == 1) {
                    tv_cloth_name.setText(dataLaundries.get(i).getTitle());
                    tv_cloth_price.setText(dataLaundries.get(i).getStdPrice());
                } else {
                    tv_cloth_name.setText(dataLaundries.get(i).getTitle());
                    tv_cloth_price.setText(dataLaundries.get(i).getPremPrice());
                }
                ll_layout.addView(view_deals);
            }

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

    }
}
