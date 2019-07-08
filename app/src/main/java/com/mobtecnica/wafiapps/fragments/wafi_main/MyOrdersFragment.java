package com.mobtecnica.wafiapps.fragments.wafi_main;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.activity.MainActivity;
import com.mobtecnica.wafiapps.adapters.MyOrdersAdapter;
import com.mobtecnica.wafiapps.manager.HomeManager;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.model.orderHistory.Order;
import com.mobtecnica.wafiapps.utils.BaseFragment;
import com.mobtecnica.wafiapps.utils.Constants;
import com.mobtecnica.wafiapps.utils.Utils;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyOrdersFragment extends BaseFragment implements MyOrdersAdapter.MyOrdersClickListener, View.OnClickListener {

    MyOrdersAdapter myOrdersAdapter;
    ArrayList<Order> orderList = new ArrayList<>();
    RecyclerView recyclerView;
    LinearLayout llno_data;
    Button btnStartShopping;


    public MyOrdersFragment() {
        // Required empty public constructor
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().matches(HomeManager.BROADCAST_MY_ORDER)) {
                dismissLoadingDialog();
                if (intent.getBooleanExtra(HomeManager.BROADCAST_MY_ORDER_STATUS, false)) {
                    orderList = ObjectFactory.getInstance().getHomeManager(getActivity()).getOrderHistory();
                    if (orderList != null) {
                        myOrdersAdapter.updateMyOrdersData(orderList);
                        if(orderList.size()>0) {
                            hideNoData();
                        } else {
                            showNoData();
                        }
                    } else {
                        Utils.makeSnackBar(btnStartShopping,  R.string.no_orders, Snackbar.LENGTH_LONG);
                        showNoData();
                    }
                } else {
                    Utils.makeSnackBar(btnStartShopping,  R.string.error_try_again, Snackbar.LENGTH_LONG);
                }
            }
        }
    };

    private void showNoData() {
        llno_data.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
    }

    private void hideNoData() {
        llno_data.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_orders, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        IntentFilter intent = new IntentFilter();
        intent.addAction(HomeManager.BROADCAST_MY_ORDER);
        addBroadcastListener(receiver, intent);
        initViews(view);
        fetchData();
        viewClickListeners();
    }

    private void initViews(View view) {
        setTitle(getString(R.string.my_orders));
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_my_orders);
        llno_data = (LinearLayout) view.findViewById(R.id.ll_no_data);
        btnStartShopping = (Button) view.findViewById(R.id.btn_start_shopping);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        myOrdersAdapter = new MyOrdersAdapter(getActivity(), orderList);
        recyclerView.setAdapter(myOrdersAdapter);
        btnStartShopping.setOnClickListener(this);
    }

    private void viewClickListeners() {
        if (myOrdersAdapter != null) {
            myOrdersAdapter.setOnItemClickListener(this);
        }

    }

    private void fetchData() {
        showLoadingDialog();
        String guId = ObjectFactory.getInstance().getAppPreference(getActivity()).getGuid();
        ObjectFactory.getInstance().getHomeManager(getActivity()).apiGetOrderHistory(Constants.API_TOKEN, guId);
    }

    @Override
    public void onItemClick(int position, View v) {
        switch (v.getId()) {
            case R.id.view_my_orders:
                navigateToOrderHistoryDetails(position);
                break;
            default:
        }
    }

    private void navigateToOrderHistoryDetails(int position) {
        Fragment fragment = new OrderHistoryDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ORDER_ID, orderList.get(position).getId().toString());
        fragment.setArguments(bundle);
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).addFragment(fragment);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start_shopping :
//                startActivity(new Intent(getActivity(), MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                ((MainActivity)getActivity()).navigateToViewPagerFragment(0);
        }
    }
}
