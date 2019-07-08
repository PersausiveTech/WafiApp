package com.mobtecnica.wafiapps.fragments.wafi_main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.adapters.SelectAddressRvAdapter;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.manager.ProfileManager;
import com.mobtecnica.wafiapps.model.profile.get_address.Addresses;
import com.mobtecnica.wafiapps.utils.BaseFragment;
import com.mobtecnica.wafiapps.utils.Constants;
import com.mobtecnica.wafiapps.utils.Utils;

import java.util.ArrayList;

import static com.mobtecnica.wafiapps.manager.ProfileManager.BROADCAST_GET_ADDRESS_RESPONSE;
import static com.mobtecnica.wafiapps.manager.ProfileManager.BROADCAST_GET_ADDRESS_RESPONSE_STATUS;
import static com.mobtecnica.wafiapps.manager.ProfileManager.BROADCAST_GET_ADDRESS_RESPONSE_STATUS_CART;

/**
 * Created by SIby on 22-03-2017.
 */

public class SelectAddressFragment extends BaseFragment{
    SelectAddressRvAdapter adapter;
    private View rootView;
    private RecyclerView rv_address;
    private LinearLayoutManager mLayoutManager;
    String addressType = "";

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().matches(BROADCAST_GET_ADDRESS_RESPONSE)) {
                if (intent.getBooleanExtra(BROADCAST_GET_ADDRESS_RESPONSE_STATUS, false)) {
                    setAdapter();
                }
            }
        }
    };
    public SelectAddressFragment() {
        setButtonType(Utils.BUTTON_TYPE.ADD);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragement_select_address, container, false);
        initialization();
        fetchData();
        setAdapter();
        return rootView;
    }

    private void fetchData() {
        if(getArguments()!=null) {
            if(getArguments().containsKey("AddressType")) {
                addressType = getArguments().getString("AddressType");
            }
        }
    }

    private void initialization() {
        rv_address = (RecyclerView) rootView.findViewById(R.id.rv_address);
        mLayoutManager = new LinearLayoutManager(getActivity());
        rv_address.setLayoutManager(mLayoutManager);

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        rv_address.setItemAnimator(itemAnimator);
        setTitle(getString(R.string.select_address));
        IntentFilter intent = new IntentFilter();
        intent.addAction(BROADCAST_GET_ADDRESS_RESPONSE);
        addBroadcastListener(receiver, intent);
    }

    private void setAdapter() {
        ArrayList<Addresses> arrayList = ObjectFactory.getInstance().getProfileManager(getActivity()).getAddressList();
        if (arrayList.size()>0){
            adapter = new SelectAddressRvAdapter(getActivity(), arrayList);
            rv_address.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            if (adapter != null) {
                adapter.setOnItemClickListener(new SelectAddressRvAdapter.MyClickListener() {
                    @Override
                    public void onItemClick(int position, View v) {
                        switch (v.getId()) {
                            case R.id.cb_address_add:
                                navigateToCheckoutFragment(position);
                                break;
                            default:
                                break;
                        }
                    }
                });
            }
        }

    }

    private void navigateToCheckoutFragment(int position) {
        if(addressType.equalsIgnoreCase(Constants.SHIPPING)) {
            ObjectFactory.getInstance().getAppPreference(getActivity()).setAddressPosition(position);
        } else {
            ObjectFactory.getInstance().getAppPreference(getActivity()).setBillingAddressPosition(position);
        }
        Intent intentRes = new Intent(ProfileManager.BROADCAST_GET_ADDRESS_RESPONSE_CART);
        intentRes.putExtra(BROADCAST_GET_ADDRESS_RESPONSE_STATUS_CART, true);
        LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intentRes);
//        Fragment fragment = new CheckOutFragment();
//        if (getActivity() instanceof MainActivity) {
//            ((MainActivity) getActivity()).addFragment(fragment);
//        }
        getActivity().onBackPressed();
    }
}
