package com.mobtecnica.wafiapps.fragments.foodHome;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.List;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.fragments.laundryHome.LaundryPriceListFragment;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.manager.WafiEatsManager;
import com.mobtecnica.wafiapps.model.wafiEats.getRestaurants.GetResturantsRequest;
import com.mobtecnica.wafiapps.model.wafiEats.home.HomeRequest;
import com.mobtecnica.wafiapps.model.wafiEats.home.LstCuisine;
import com.mobtecnica.wafiapps.model.wafiEats.home.LstLocation;
import com.mobtecnica.wafiapps.utils.BaseFragment;
import com.mobtecnica.wafiapps.utils.Constants;
import com.mobtecnica.wafiapps.utils.Utils;

/**
 * Created by SIby on 06-06-2017.
 */

public class FoodHomeFragment extends BaseFragment implements View.OnClickListener {
    List<LstLocation> location_array;
    List<LstCuisine> cuision_array;
    String selected_loc = "";
    String selected_cuision = "";
    private View rootView;
    private SearchableSpinner spinner_all_countries;
    private SearchableSpinner spinner_area;
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().matches(WafiEatsManager.BROADCAST_EATS_HOME_RESPONSE)) {
                if (intent.getBooleanExtra(WafiEatsManager.BROADCAST_EATS_HOME_RESPONSE_STATUS, false)) {
                    setSpinners();
                } else {

                }
            }else if (intent.getAction().matches(WafiEatsManager.BROADCAST_EATS_GET_RESTAURANTS_RESPONSE)){
                if (intent.getBooleanExtra(WafiEatsManager.BROADCAST_EATS_GET_RESTAURANTS_RESPONSE_STATUS,false)){
                    navigateToResturantsPage(1);
                }
            }else if (intent.getAction().matches(WafiEatsManager.BROADCAST_EATS_RESTAURANTS_RESPONSE)){
                if (intent.getBooleanExtra(WafiEatsManager.BROADCAST_EATS_RESTAURANTS_RESPONSE_STATUS,false)){
                    navigateToResturantsPage(2);
                }
            }
        }
    };

    private void navigateToResturantsPage( int i) {
        Fragment fragment = new ResturantsListFragment(i);
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                .replace(R.id.sb_root_product, fragment, Utils.getTagForFragment(fragment))
                .addToBackStack(fragment.getClass().getName())
                .commit();
    }

    private Button btn_find_restaurants;
    private Button btn_all_restaurants;
    private Button btn_offers;

    private void setSpinners() {
        List<String> countries = ObjectFactory.getInstance().getWafiEatsManager(getActivity()).getAllCountries();
        if (countries.size() > 0) {
            ArrayAdapter<String> adp1 = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1, countries);
            adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_all_countries.setAdapter(adp1);
        }
        List<String> cuisines = ObjectFactory.getInstance().getWafiEatsManager(getActivity()).getAllCusines();
        if (cuisines.size() > 0) {
            ArrayAdapter<String> adp2 = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1, cuisines);
            adp2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_area.setAdapter(adp2);
        }
        location_array = new ArrayList<>();
        cuision_array = new ArrayList<>();
        location_array = ObjectFactory.getInstance().getWafiEatsManager(getActivity()).getCountryDetails();
        cuision_array = ObjectFactory.getInstance().getWafiEatsManager(getActivity()).getCuisions();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_food_home, container, false);
        initializeViews();
        btnClickListners();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressbarShowing();
        apiCall();
    }

    private void apiCall() {
        HomeRequest request = new HomeRequest();
        request.setApiToken(Constants.API_TOKEN_EATS);
        ObjectFactory.getInstance().getWafiEatsManager(getActivity()).eatsHome(request);
    }

    private void btnClickListners() {
        spinner_all_countries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selected_loc = String.valueOf(location_array.get(i).getLocationID());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner_area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selected_cuision = String.valueOf(cuision_array.get(i).getCuisineID());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btn_find_restaurants.setOnClickListener(this);
        btn_all_restaurants.setOnClickListener(this);
        btn_offers.setOnClickListener(this);
    }

    private void initializeViews() {
        IntentFilter intent = new IntentFilter();
        intent.addAction(WafiEatsManager.BROADCAST_EATS_HOME_RESPONSE);
        intent.addAction(WafiEatsManager.BROADCAST_EATS_GET_RESTAURANTS_RESPONSE);
        intent.addAction(WafiEatsManager.BROADCAST_EATS_RESTAURANTS_RESPONSE);
        addBroadcastListener(receiver, intent);

        spinner_all_countries = (SearchableSpinner) rootView.findViewById(R.id.spinner_all_countries);
        spinner_area = (SearchableSpinner) rootView.findViewById(R.id.spinner_area);
        btn_find_restaurants = (Button) rootView.findViewById(R.id.btn_find_restaurants);
        btn_all_restaurants = (Button) rootView.findViewById(R.id.btn_all_restaurants);
        btn_offers = (Button) rootView.findViewById(R.id.btn_offers);
        spinner_all_countries.setTitle("Select your area in Bahrain");
        spinner_all_countries.setPositiveButton("OK");
        spinner_area.setTitle("All Cuisines");
        spinner_area.setPositiveButton("OK");
    }

    private void progressbarShowing() {
        Intent intent = new Intent(Constants.Login.BROADCAST_PROGRESS_WHEEL);
        intent.putExtra(Constants.Login.BROADCAST_ISDISPLAY_PROGRESS_WHEEL, true);
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_find_restaurants:
                progressbarShowing();
                apiFindRestaurants();
                break;
            case R.id.btn_all_restaurants:
                progressbarShowing();
                apiGetAllResturants();
                break;
            case R.id.btn_offers:
                navigateToOffersPage();
                break;
            default:
                break;
        }
    }

    private void navigateToOffersPage() {
        Fragment fragment = new FoodOfferFragment();
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                .replace(R.id.sb_root_product, fragment, Utils.getTagForFragment(fragment))
                .addToBackStack(fragment.getClass().getName())
                .commit();
    }

    private void apiGetAllResturants() {
        HomeRequest request=new HomeRequest();
        request.setApiToken(Constants.API_TOKEN_EATS);
        ObjectFactory.getInstance().getWafiEatsManager(getActivity()).eatsGetAllRestarunts(request);
    }

    private void apiFindRestaurants() {
        GetResturantsRequest getResturantsRequest = new GetResturantsRequest();
        getResturantsRequest.setApiToken(Constants.API_TOKEN_EATS);
        getResturantsRequest.setLocationID(selected_loc);
        getResturantsRequest.setCuisineID(selected_cuision);
        ObjectFactory.getInstance().getWafiEatsManager(getActivity()).eatsRestaurants(getResturantsRequest);
    }
}
