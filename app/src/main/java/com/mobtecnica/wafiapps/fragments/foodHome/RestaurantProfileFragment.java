package com.mobtecnica.wafiapps.fragments.foodHome;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.List;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.manager.WafiEatsManager;
import com.mobtecnica.wafiapps.model.wafiEats.getAllrestaruntsDetails.EatsRestrauntsDetailsResponse;
import com.mobtecnica.wafiapps.model.wafiEats.getAllrestaruntsDetails.GetRestaurantsDetailsRequest;
import com.mobtecnica.wafiapps.model.wafiEats.getAllrestaruntsDetails.ListShopLocation;
import com.mobtecnica.wafiapps.model.wafiEats.getAllrestaruntsDetails.Restaurant;
import com.mobtecnica.wafiapps.utils.BaseFragment;
import com.mobtecnica.wafiapps.utils.Constants;
import com.mobtecnica.wafiapps.utils.Utils;

/**
 * Created by siby on 09-Jun-17.
 */

public class RestaurantProfileFragment extends BaseFragment implements View.OnClickListener {
    EatsRestrauntsDetailsResponse detailsResponse = null;
    String loc_id = "";
    private List<ListShopLocation> shop_locations;
    private View rootView;
    private TextView tv_status;
    private TextView tv_min_order;
    private TextView tv_delivery_charge;
    private TextView tv_delivery_time;
    private TextView tv_timing;
    private TextView tv_cuisines;
    private SearchableSpinner searchableSpinner;
    private String shop_id;
    private TextView btn_find_restaurants;
    private TextView tv_resturant_name;
    private Restaurant restaurantDetails;
    private ImageView iv_restaurant_name;
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().matches(WafiEatsManager.BROADCAST_EATS_RESTAURANTS_DETAILS_RESPONSE)) {
                if (intent.getBooleanExtra(WafiEatsManager.BROADCAST_EATS_RESTAURANTS_DETAILS_RESPONSE_STATUS, false)) {
                    setShopDetails();
                }
            }
        }
    };

    public RestaurantProfileFragment() {
    }

    @SuppressLint("ValidFragment")
    public RestaurantProfileFragment(String shop_id) {
        this.shop_id = shop_id;
    }

    private void setShopDetails() {
        detailsResponse = ObjectFactory.getInstance().getWafiEatsManager(getActivity()).getResturantDetails();
        shop_locations = new ArrayList<ListShopLocation>();
        shop_locations = detailsResponse.getData().getListShopLocations();
        restaurantDetails = new Restaurant();
        restaurantDetails = detailsResponse.getData().getRestaurant();
        List<String> locations = new ArrayList<>();
        for (int i = 0; i < shop_locations.size(); i++) {
            locations.add(shop_locations.get(i).getLocation());
        }
        if (locations.size() > 0) {
            ArrayAdapter<String> adp2 = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1, locations);
            adp2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            searchableSpinner.setAdapter(adp2);
        }
        tv_resturant_name.setText(restaurantDetails.getShopName());
        Glide.with(getActivity())
                .load(restaurantDetails.getHeaderPic())
                .into(iv_restaurant_name);
        tv_status.setText(restaurantDetails.getActive());
        tv_min_order.setText(restaurantDetails.getMinOrder());
        tv_delivery_charge.setText(restaurantDetails.getDeliveryCharge());
        tv_timing.setText(restaurantDetails.getTimings());
        String cuisines = "";
        for (int i = 0; i < detailsResponse.getData().getListShopCuisines().size(); i++) {
            cuisines = cuisines + ", " + detailsResponse.getData().getListShopCuisines().get(i).getCuisine();
        }
        tv_cuisines.setText(cuisines);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_resturant_profile, container, false);
        initViews();
        btnClickListners();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        apiCall();
    }

    private void apiCall() {
        GetRestaurantsDetailsRequest request = new GetRestaurantsDetailsRequest();
        request.setApiToken(Constants.API_TOKEN_EATS);
        request.setShopID(shop_id);
        ObjectFactory.getInstance().getWafiEatsManager(getActivity()).eatsGetAllRestaruntsDetails(request);
    }

    private void btnClickListners() {
        searchableSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                loc_id = String.valueOf(shop_locations.get(i).getLocationID());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btn_find_restaurants.setOnClickListener(this);
    }

    private void initViews() {
        IntentFilter intent = new IntentFilter();
        intent.addAction(WafiEatsManager.BROADCAST_EATS_RESTAURANTS_DETAILS_RESPONSE);
        addBroadcastListener(receiver, intent);
        tv_resturant_name = (TextView) rootView.findViewById(R.id.tv_resturant_name);
        tv_status = (TextView) rootView.findViewById(R.id.tv_status);
        tv_min_order = (TextView) rootView.findViewById(R.id.tv_min_order);
        tv_delivery_charge = (TextView) rootView.findViewById(R.id.tv_delivery_charge);
        tv_delivery_time = (TextView) rootView.findViewById(R.id.tv_delivery_time);
        tv_timing = (TextView) rootView.findViewById(R.id.tv_timing);
        tv_cuisines = (TextView) rootView.findViewById(R.id.tv_cuisines);
        searchableSpinner = (SearchableSpinner) rootView.findViewById(R.id.spinner_all_countries);
        btn_find_restaurants = (TextView) rootView.findViewById(R.id.btn_find_restaurants);
        iv_restaurant_name = (ImageView) rootView.findViewById(R.id.iv_restaurant_name);
        searchableSpinner.setTitle("Select your area in Bahrain");
        searchableSpinner.setPositiveButton("OK");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_find_restaurants:
                Fragment fragment = new ResturantItemsFragment(loc_id, shop_id);
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                        .replace(R.id.sb_root_product, fragment, Utils.getTagForFragment(fragment))
                        .addToBackStack(fragment.getClass().getName())
                        .commit();
                break;
            default:
                break;
        }
    }
}
