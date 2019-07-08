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
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.adapters.ResturentMenuOptionsExpandableList;
import com.mobtecnica.wafiapps.customViews.NonScrollExpandableListView;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.manager.WafiEatsManager;
import com.mobtecnica.wafiapps.model.wafiEats.addToCart.AddToEatsCartRequest;
import com.mobtecnica.wafiapps.model.wafiEats.addToCart.Model;
import com.mobtecnica.wafiapps.model.wafiEats.getMenuOptions.CustomModelForMenuExpandableList;
import com.mobtecnica.wafiapps.model.wafiEats.getMenuOptions.GetMenuOptionsRequest;
import com.mobtecnica.wafiapps.model.wafiEats.getMenuOptions.GetMenuOptionsResponse;
import com.mobtecnica.wafiapps.model.wafiEats.getMenuOptions.MenuDetail;
import com.mobtecnica.wafiapps.model.wafiEats.getMenuOptions.MenuOption;
import com.mobtecnica.wafiapps.model.wafiEats.getRestaurantsMenu.CustomModelForExpandableLIst;
import com.mobtecnica.wafiapps.utils.BaseFragment;
import com.mobtecnica.wafiapps.utils.Constants;

/**
 * Created by SIby on 08-06-2017.
 */

public class GetMenuOptionsFragment extends BaseFragment implements View.OnClickListener {
    View rootView;
    String menu_id;
    String shop_id;
    String loc_id;
    MenuDetail menuDetail = null;
    int group_pos;
    int child_pos;
    ImageView iv_plus;
    ImageView iv_minus;
    TextView quantity;
    TextView price;
    AppCompatButton btn_add_toCart;
    int number_of_items = 1;
    ArrayList<String> checked_items = new ArrayList<>();
    GetMenuOptionsResponse menuOptionsResponse = null;
    private List<MenuOption> menuList = null;
    private NonScrollExpandableListView expandableListView_eats;
    private LinearLayout llcart;
    private boolean value_visible = true;
    private RelativeLayout rl_menu_frag;
    private ImageView iv_item;
    private TextView tv_name;
    private TextView tv_item_description;
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().matches(WafiEatsManager.BROADCAST_EATS_GET_MENU_OPTIONS_RESPONSE)) {
                if (intent.getBooleanExtra(WafiEatsManager.BROADCAST_EATS_GET_MENU_OPTIONS_RESPONSE_STATUS, false)) {
                    setMenuOptions();
                }
            } else if (intent.getAction().matches(WafiEatsManager.BROADCAST_ADD_TO_CART_EATS)) {
                if (intent.getBooleanExtra(WafiEatsManager.BROADCAST_ADD_TO_CART_EATS_STATUS, false)) {
                    Snackbar.make(rl_menu_frag, intent.getStringExtra(WafiEatsManager.BROADCAST_ADD_TO_CART_EATS_MESSAGE), Snackbar.LENGTH_LONG).show();
                }
            }
        }


    };

    public GetMenuOptionsFragment() {
    }

    @SuppressLint("ValidFragment")
    public GetMenuOptionsFragment(String menu_id, String shop_id, String loc_id, int group_pos, int child_pos) {
        this.menu_id = menu_id;
        this.shop_id = shop_id;
        this.loc_id = loc_id;
        this.group_pos = group_pos;
        this.child_pos = child_pos;
    }

    private void setMenuOptions() {
        ArrayList<CustomModelForExpandableLIst> customModelForExpandableLIsts = ObjectFactory.getInstance().getWafiEatsManager(getActivity()).getMenu();

        Glide.with(getActivity())
                .load(customModelForExpandableLIsts.get(group_pos).getList().get(child_pos).getMenuPic())
                .thumbnail(Glide.with(getActivity()).load(R.drawable.spinningwheel))
                .into(iv_item);
        tv_name.setText(customModelForExpandableLIsts.get(group_pos).getList().get(child_pos).getMenu());
        tv_item_description.setText(customModelForExpandableLIsts.get(group_pos).getList().get(child_pos).getDescription());

        menuOptionsResponse = ObjectFactory.getInstance().getWafiEatsManager(getActivity()).getMenuOptionsList();
        menuDetail = menuOptionsResponse.getData().getMenuDetail();
        menuList = new ArrayList<>();
        menuList = menuOptionsResponse.getData().getMenuOptions();
        if (menuList.size() > 0) {
            //list and split
            List<String> group_name = new ArrayList<>();
            for (int i = 0; i < menuList.size(); i++) {
                if (group_name.size() == 0) {
                    group_name.add(menuList.get(i).getMenuSubItemGroupID());
                } else {
                    if (!group_name.contains(menuList.get(i).getMenuSubItemGroupID())) {
                        group_name.add(menuList.get(i).getMenuSubItemGroupID());
                    }
                }
            }
            ArrayList<CustomModelForMenuExpandableList> expandableLists = new ArrayList<>();
            for (int i = 0; i < group_name.size(); i++) {
                CustomModelForMenuExpandableList customModelForMenuExpandableList = new CustomModelForMenuExpandableList();
                ArrayList<MenuOption> menuOptions = new ArrayList<>();
                for (int j = 0; j < menuList.size(); j++) {
                    if (group_name.get(i).matches(menuList.get(j).getMenuSubItemGroupID())) {
                        menuOptions.add(menuList.get(j));
                    }
                }
                customModelForMenuExpandableList.setGroup_name(group_name.get(i));
                customModelForMenuExpandableList.setList(menuOptions);
                customModelForMenuExpandableList.setMenuSubItemGroupID(Integer.parseInt(menuOptions.get(0).getMenuSubItemGroupID()));
                customModelForMenuExpandableList.setMaxCount(Integer.parseInt(menuOptions.get(0).getMaxValue()));
                customModelForMenuExpandableList.setMinCount(Integer.parseInt(menuOptions.get(0).getMinValue()));
                expandableLists.add(i, customModelForMenuExpandableList);
            }
            ResturentMenuOptionsExpandableList expandableList = new ResturentMenuOptionsExpandableList(getActivity(), expandableLists);
            expandableListView_eats.setAdapter(expandableList);

        } else {
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_get_menu_options, container, false);
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
        GetMenuOptionsRequest optionsRequest = new GetMenuOptionsRequest();
        optionsRequest.setApiToken(Constants.API_TOKEN_EATS);
        optionsRequest.setLocID(loc_id);
        optionsRequest.setMenuID(menu_id);
        optionsRequest.setShopID(shop_id);
        ObjectFactory.getInstance().getWafiEatsManager(getActivity()).getMenuOptions(optionsRequest);
    }

    private void initViews() {
        IntentFilter intent = new IntentFilter();
        intent.addAction(WafiEatsManager.BROADCAST_EATS_GET_MENU_OPTIONS_RESPONSE);
        intent.addAction(WafiEatsManager.BROADCAST_ADD_TO_CART_EATS);
        addBroadcastListener(receiver, intent);

        expandableListView_eats = (NonScrollExpandableListView) rootView.findViewById(R.id.expandableListView_eats);
        price = (TextView) rootView.findViewById(R.id.tv_price);
        quantity = (TextView) rootView.findViewById(R.id.tv_quantity);
        rl_menu_frag = (RelativeLayout) rootView.findViewById(R.id.rl_menu_frag);

        iv_plus = (ImageView) rootView.findViewById(R.id.iv_plus);
        iv_minus = (ImageView) rootView.findViewById(R.id.iv_minus);
        btn_add_toCart = (AppCompatButton) rootView.findViewById(R.id.btn_add_to_cart);
        iv_item = (ImageView) rootView.findViewById(R.id.iv_item);
        tv_name = (TextView) rootView.findViewById(R.id.tv_name);
        tv_item_description = (TextView) rootView.findViewById(R.id.tv_item_description);

        btn_add_toCart.setOnClickListener(this);
        iv_minus.setOnClickListener(this);
        iv_plus.setOnClickListener(this);
        ObjectFactory.getInstance().getWafiEatsManager(getActivity()).setCheckedItems(checked_items);

        llcart = (LinearLayout) rootView.findViewById(R.id.llcart);
   /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            expandableListView_eats.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View view, int x, int y, int oldx, int oldy) {
                    if (value_visible) {
                        llcart.setVisibility(View.GONE);
                        value_visible = false;
                    } else {
                        llcart.setVisibility(View.VISIBLE);
                        value_visible = true;
                    }
                }
            });
        }*/

    }

    private void progressbarShowing() {
        Intent intent = new Intent(Constants.Login.BROADCAST_PROGRESS_WHEEL);
        intent.putExtra(Constants.Login.BROADCAST_ISDISPLAY_PROGRESS_WHEEL, true);
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_to_cart:
                progressbarShowing();
                addToCartApi();
                break;
            case R.id.iv_plus:
                number_of_items = number_of_items + 1;
                quantity.setText(String.valueOf(number_of_items));
                break;
            case R.id.iv_minus:
                if (number_of_items == 1) {
                    quantity.setText(String.valueOf(number_of_items));
                } else if (number_of_items > 1) {
                    number_of_items = number_of_items - 1;
                    quantity.setText(String.valueOf(number_of_items));
                }
                break;
            default:
                break;
        }
    }

    private void addToCartApi() {
        AddToEatsCartRequest request = new AddToEatsCartRequest();
        request.setApiToken(Constants.API_TOKEN_EATS);
        request.setEmailID(ObjectFactory.getInstance().getAppPreference(getActivity()).getEmailId());
        Model model = new Model();
        model.setMenuID(menu_id);
        model.setQuantity(String.valueOf(number_of_items));
        model.setRate(menuOptionsResponse.getData().getMenuDetail().getRate());
        model.setShopID(shop_id);
        model.setShopLocationID(loc_id);
        checked_items = ObjectFactory.getInstance().getWafiEatsManager(getActivity()).getCheckedItems();
        String checkedItem = "";
        for (int i = 0; i < checked_items.size(); i++) {
            if (i == 0) {
                checkedItem = checked_items.get(i);
            } else checkedItem = checkedItem + "," + checked_items.get(i);
        }
        model.setCheckedItems(checkedItem);
        request.setModel(model);
        String json = new Gson().toJson(request);
        ObjectFactory.getInstance().getWafiEatsManager(getActivity()).addToCartWafieats(request);
    }
}
