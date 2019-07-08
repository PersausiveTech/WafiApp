package com.mobtecnica.wafiapps.fragments.foodHome;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.adapters.ResturentMenuExpandableList;
import com.mobtecnica.wafiapps.customViews.NonScrollExpandableListView;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.manager.WafiEatsManager;
import com.mobtecnica.wafiapps.model.wafiEats.getRestaurantsMenu.CustomModelForExpandableLIst;
import com.mobtecnica.wafiapps.model.wafiEats.getRestaurantsMenu.GetRestaurantMenuRequest;
import com.mobtecnica.wafiapps.model.wafiEats.getRestaurantsMenu.MenuData;
import com.mobtecnica.wafiapps.model.wafiEats.getRestaurantsMenu.MenuList;
import com.mobtecnica.wafiapps.model.wafiEats.getRestaurantsMenu.ShopDetail;
import com.mobtecnica.wafiapps.utils.BaseFragment;
import com.mobtecnica.wafiapps.utils.Constants;

/**
 * Created by siby on 07-Jun-17.
 */

public class ResturantItemsFragment extends BaseFragment {
    View rootView;
    String loc_id = "";
    String shop_id = "";
    MenuData menuData = null;
    ShopDetail shopDetail = null;
    List<MenuList> menuLists = null;
    private NonScrollExpandableListView expandableListView;
    private ImageView iv_resturant_img;
    private TextView tv_name;
    private RatingBar ratingbar;
    private TextView tv_min_order;
    private TextView tv_delivery_time;
    private TextView tv_delivery_charge;
    private ScrollView sv_restaurant_items;
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().matches(WafiEatsManager.BROADCAST_EATS_GET_RESTAURANT_MENU_RESPONSE)) {
                if (intent.getBooleanExtra(WafiEatsManager.BROADCAST_EATS_GET_RESTAURANT_MENU_RESPONSE_STATUS, false)) {
                    setExpandableList();
                }
            }
        }
    };

    public ResturantItemsFragment() {

    }

    @SuppressLint("ValidFragment")
    public ResturantItemsFragment(String loc_id, String shop_id) {
        this.loc_id = loc_id;
        this.shop_id = shop_id;
    }

    private void setExpandableList() {
        menuData = new MenuData();
        menuData = ObjectFactory.getInstance().getWafiEatsManager(getActivity()).getMenuDataOfRest();
        shopDetail = new ShopDetail();
        shopDetail = menuData.getShopDetail();
        serRestaurantDetails();

        menuLists = menuData.getMenuList();
        List<String> group_name = new ArrayList<>();
        if (menuLists.size() <= 0) {
            Snackbar.make(sv_restaurant_items, "No data available", Snackbar.LENGTH_LONG).show();
            getActivity().onBackPressed();
        } else {
            for (int i = 0; i < menuLists.size(); i++) {
                if (group_name.size() == 0) {
                    group_name.add(menuLists.get(i).getMenuTypeID());
                } else {
                    if (!group_name.contains(menuLists.get(i).getMenuTypeID())) {
                        group_name.add(menuLists.get(i).getMenuTypeID());
                    }
                }
            }
            ArrayList<CustomModelForExpandableLIst> customModelForExpandableLIsts = new ArrayList<>();
            for (int i = 0; i < group_name.size(); i++) {
                CustomModelForExpandableLIst expandableLIst = new CustomModelForExpandableLIst();
                ArrayList<MenuList> menu = new ArrayList<>();
                for (int j = 0; j < menuLists.size(); j++) {
                    if (group_name.get(i).matches(menuLists.get(j).getMenuTypeID())) {
                        menu.add(menuLists.get(j));
                    }

                }

                expandableLIst.setGroup_name(group_name.get(i));
                expandableLIst.setList(menu);
                customModelForExpandableLIsts.add(i, expandableLIst);

            }
            ResturentMenuExpandableList resturentMenuExpandableList = new ResturentMenuExpandableList(getActivity(), customModelForExpandableLIsts, loc_id);
            expandableListView.setAdapter(resturentMenuExpandableList);

            ObjectFactory.getInstance().getWafiEatsManager(getActivity()).setMenu(customModelForExpandableLIsts);
        }
    }

    private void serRestaurantDetails() {
        Glide.with(getActivity()).load(shopDetail.getHeaderPic()).into(iv_resturant_img);
        tv_delivery_charge.setText("Delivery Charge:" + shopDetail.getDeliveryCharge());
          tv_delivery_time.setText("Delivery time  :" + shopDetail.getDeliveryCharge());
              tv_min_order.setText("Min Order      :" + shopDetail.getMinOrder());
        tv_name.setText(shopDetail.getShopName());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_resturant_items, container, false);
        initViews();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressbarShowing();
        apiCall();
    }

    private void apiCall() {
        progressbarShowing();
        GetRestaurantMenuRequest getRestaurantMenuRequest = new GetRestaurantMenuRequest();
        getRestaurantMenuRequest.setApiToken(Constants.API_TOKEN_EATS);
        getRestaurantMenuRequest.setLocationID(loc_id);
        getRestaurantMenuRequest.setShopID(shop_id);
        ObjectFactory.getInstance().getWafiEatsManager(getActivity()).eatsRestaurantsMenu(getRestaurantMenuRequest);
    }

    private void initViews() {
        IntentFilter intent = new IntentFilter();
        intent.addAction(WafiEatsManager.BROADCAST_EATS_GET_RESTAURANT_MENU_RESPONSE);
        addBroadcastListener(receiver, intent);
        expandableListView = (NonScrollExpandableListView) rootView.findViewById(R.id.expandableListView_eats);
        iv_resturant_img = (ImageView) rootView.findViewById(R.id.iv_resturant_img);
        tv_name = (TextView) rootView.findViewById(R.id.tv_name);
        ratingbar = (RatingBar) rootView.findViewById(R.id.ratingbar);
        tv_min_order = (TextView) rootView.findViewById(R.id.tv_min_order);
        tv_delivery_time = (TextView) rootView.findViewById(R.id.tv_delivery_time);
        tv_delivery_charge = (TextView) rootView.findViewById(R.id.tv_delivery_charge);
        sv_restaurant_items = (ScrollView) rootView.findViewById(R.id.sv_restaurant_items);
    }

    private void progressbarShowing() {
        Intent intent = new Intent(Constants.Login.BROADCAST_PROGRESS_WHEEL);
        intent.putExtra(Constants.Login.BROADCAST_ISDISPLAY_PROGRESS_WHEEL, true);
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
    }
}
