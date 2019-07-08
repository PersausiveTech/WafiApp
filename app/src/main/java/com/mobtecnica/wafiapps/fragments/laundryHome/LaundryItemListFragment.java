package com.mobtecnica.wafiapps.fragments.laundryHome;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.activity.LaundryActivity;
import com.mobtecnica.wafiapps.adapters.LaundryItemsExpandableList;
import com.mobtecnica.wafiapps.customViews.NonScrollExpandableListView;
import com.mobtecnica.wafiapps.manager.LaundryManager;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.model.LaundryModel.getlaundryItems.CustomClassForExpandableView;
import com.mobtecnica.wafiapps.model.LaundryModel.getlaundryItems.DataLaundryItems;
import com.mobtecnica.wafiapps.model.LaundryModel.getlaundryItems.LaundryItemsRequest;
import com.mobtecnica.wafiapps.model.LaundryModel.getlaundryItems.ListLaundryMenu;
import com.mobtecnica.wafiapps.utils.BaseFragment;
import com.mobtecnica.wafiapps.utils.Constants;

/**
 * Created by SIby on 03-04-2017.
 */

public class LaundryItemListFragment extends BaseFragment implements View.OnClickListener {
    private View rootView;
    private TextView tv_laundry_type;
    private AppCompatButton btn_express_checkout;
    private NonScrollExpandableListView expandableListView_laundry;
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().matches(LaundryManager.BROADCAST_LAUNDRY_RESPONSE)) {
                if (intent.getBooleanExtra(LaundryManager.BROADCAST_LAUNDRY_RESPONSE_STATUS, false)) {
                    setExpandableListView();
                } else {

                }
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_item_list_fragment, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        initializeViews();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    private void initializeViews() {

        IntentFilter intent = new IntentFilter();
        intent.addAction(LaundryManager.BROADCAST_LAUNDRY_RESPONSE);
        addBroadcastListener(receiver, intent);

        tv_laundry_type = rootView.findViewById(R.id.tv_laundry_type);
        btn_express_checkout = rootView.findViewById(R.id.btn_express_checkout);
        expandableListView_laundry = rootView.findViewById(R.id.expandableListView_laundry);
        btn_express_checkout.setOnClickListener(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        apiCall();
    }


    private void apiCall() {
        showLoadingDialog();
        int id = ObjectFactory.getInstance().getAppPreference(getActivity()).getServiceType();
        switch (id) {
            case 1:
                getLaundryItems("standard");
                tv_laundry_type.setText(R.string.l_standard);
                break;
            case 2:
                getLaundryItems("premium");
                tv_laundry_type.setText(R.string.l_premium);
                break;
            default:
                break;
        }
    }

    private void getLaundryItems(String type) {
        LaundryItemsRequest request = new LaundryItemsRequest();
        request.setApiToken(Constants.API_TOKEN_LAUNDRY);
        request.setLaundryType(type);
        ObjectFactory.getInstance().getLaundryManager(getActivity()).getLaundryItems(request);
    }

    private void setExpandableListView() {
        ArrayList<String> groupname = new ArrayList<>();
        ArrayList<DataLaundryItems> laundryItemses = ObjectFactory.getInstance().getLaundryManager(getActivity()).getLaundryItemsList();
        ArrayList<ListLaundryMenu> laundryMenus = laundryItemses.get(0).getListLaundryMenu();
        ObjectFactory.getInstance().getLaundryManager(getActivity()).setSelectedLaundryTypeId(laundryItemses.get(0).getLaundryTypeID());
        for (int i = 0; i < laundryMenus.size(); i++) {
            if (groupname.size() == 0) {
                groupname.add(laundryMenus.get(0).getMenu());
            } else {
                if (!groupname.contains(laundryMenus.get(i).getMenu())) {
                    groupname.add(laundryMenus.get(i).getMenu());
                }
            }
        }

        ArrayList<CustomClassForExpandableView> homeViewProductses = new ArrayList<CustomClassForExpandableView>();
        for (int i = 0; i < groupname.size(); i++) {                              //number of different group id
            CustomClassForExpandableView products = new CustomClassForExpandableView();
            ArrayList<ListLaundryMenu> customProductses = new ArrayList<ListLaundryMenu>();
            for (int j = 0; j < laundryMenus.size(); j++) {                            //number of total items
                if (groupname.get(i).matches(laundryMenus.get(j).getMenu())) {    //seperating w.r.t grop id
                    customProductses.add(laundryMenus.get(j));
                }
            }
            products.setGroup_name(groupname.get(i));
            products.setList(customProductses);
            homeViewProductses.add(i, products);
        }
        ObjectFactory.getInstance().getLaundryManager(getActivity()).setLaundryItemsExpandableList(homeViewProductses);
        LaundryItemsExpandableList expandableList = new LaundryItemsExpandableList(getContext(), homeViewProductses);
        expandableListView_laundry.setAdapter(expandableList);

        expandableListView_laundry.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousGroup) {
                    expandableListView_laundry.collapseGroup(previousGroup);
                }
                previousGroup = groupPosition;
            }
        });
        expandableList.setOnChildClick(new LaundryItemsExpandableList.OnChildClick() {
            @Override
            public void onChildClick(ListLaundryMenu listLaundryMenu) {
                Fragment fragment = new LaundryClothDetailsFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("DETAILS", listLaundryMenu);
                fragment.setArguments(bundle);
                if (getActivity() instanceof LaundryActivity) {
                    ((LaundryActivity) getActivity()).addFragment(fragment);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_express_checkout:
                LaundryCheckoutFragment fragment = new LaundryCheckoutFragment("yes");
                ((LaundryActivity)getActivity()).addFragment(fragment);
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }
}
