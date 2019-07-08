package com.mobtecnica.wafiapps.fragments.wafi_main;

import android.app.DialogFragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.activity.MainActivity;
import com.mobtecnica.wafiapps.adapters.AddressListRvAdapter;
import com.mobtecnica.wafiapps.customViews.CustomAlertDialog;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.manager.ProfileManager;
import com.mobtecnica.wafiapps.model.profile.deleteAddress.DeleteUserAddressRequest;
import com.mobtecnica.wafiapps.model.profile.get_address.Addresses;
import com.mobtecnica.wafiapps.model.profile.get_address.GetUserAddressRequest;
import com.mobtecnica.wafiapps.utils.BaseFragment;
import com.mobtecnica.wafiapps.utils.Constants;
import com.mobtecnica.wafiapps.utils.Utils;

import java.util.ArrayList;

import static com.mobtecnica.wafiapps.manager.ProfileManager.BROADCAST_GET_ADDRESS_RESPONSE;
import static com.mobtecnica.wafiapps.manager.ProfileManager.BROADCAST_GET_ADDRESS_RESPONSE_STATUS;

/**
 * Created by SIby on 22-03-2017.
 */

public class AddressListFragment extends BaseFragment {
    AddressListRvAdapter adapter;
    private View rootView;
    private RecyclerView rv_address;
    private ArrayList<Addresses> addressesArrayList;
    private LinearLayout nodata;
    private LinearLayoutManager mLayoutManager;
    private Button address_add;

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().matches(ProfileManager.BROADCAST_DELETE_ADDRESS_RESPONSE)) {
                if (intent.getBooleanExtra(ProfileManager.BROADCAST_DELETE_ADDRESS_RESPONSE_STATUS, false)) {
                    GetUserAddressRequest getUserAddressRequest = new GetUserAddressRequest();
                    getUserAddressRequest.setGuid(ObjectFactory.getInstance().getAppPreference(getActivity()).getGuid());
                    getUserAddressRequest.setApiToken(Constants.API_TOKEN);
                    progressbarShowing();
                    ObjectFactory.getInstance().getProfileManager(getActivity()).getUserAddress(getUserAddressRequest, 1);
                }
            }
            if (intent.getAction().matches(BROADCAST_GET_ADDRESS_RESPONSE)) {
                if (intent.getBooleanExtra(BROADCAST_GET_ADDRESS_RESPONSE_STATUS, false)) {
                    setAdapter();
                }
            }
        }
    };

    public AddressListFragment() {
        setButtonType(Utils.BUTTON_TYPE.ADD);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_address_list, container, false);
        initialization();
        setAdapter();
        return rootView;
    }


    private void initialization() {
        address_add = rootView.findViewById(R.id.address_add);
        IntentFilter intent = new IntentFilter();
        intent.addAction(ProfileManager.BROADCAST_DELETE_ADDRESS_RESPONSE);
        intent.addAction(BROADCAST_GET_ADDRESS_RESPONSE);
        intent.addAction(ProfileManager.BROADCAST_UPDATE_ADDRESS_RESPONSE);
        addBroadcastListener(receiver, intent);

        rv_address = (RecyclerView) rootView.findViewById(R.id.rv_address);
        nodata = (LinearLayout) rootView.findViewById(R.id.nodata);
        address_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToAddAddressFragment();
            }
        });

        addressesArrayList = ObjectFactory.getInstance().getProfileManager(getActivity()).getAddressList();

        mLayoutManager = new LinearLayoutManager(getActivity());
        rv_address.setLayoutManager(mLayoutManager);
        adapter = new AddressListRvAdapter(getActivity());
        adapter.setOnItemClickListener(new AddressListRvAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {

            }

            @Override
            public void onDelete(final Addresses address, int position) {
                CustomAlertDialog customAlertDialog = new CustomAlertDialog(getString(R.string.confirm_delete), getString(R.string.sure_to_continue),
                        getString(R.string.cancel), getString(R.string.ok));
                customAlertDialog.setCustomAlertClickListener(new CustomAlertDialog.CustomAlertClickListener() {

                    @Override
                    public void onPositive(DialogFragment dialogFragment) {
                        dialogFragment.dismiss();
                        deleteAddressAPICall(address);
                    }

                    @Override
                    public void onNegative(DialogFragment dialogFragment) {
                        dialogFragment.dismiss();
                    }
                });
                customAlertDialog.show(getActivity().getFragmentManager(), "Alert");

            }

            @Override
            public void onEdit(Addresses address, int position) {
                ObjectFactory.getInstance().getProfileManager(getActivity()).setSelectedAddress(address, position);
                navigateToUpdateAddress();
            }

            @Override
            public void onSelect(Addresses address, int position) {
                if (address != null) {
                    ObjectFactory.getInstance().getProfileManager(getActivity()).setSelectedAddress(address, position);
                    Intent intentRes = new Intent(BROADCAST_GET_ADDRESS_RESPONSE);
                    intentRes.putExtra(BROADCAST_GET_ADDRESS_RESPONSE_STATUS, true);
                    LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intentRes);
                    getActivity().onBackPressed();
                }
            }
        });
        rv_address.setAdapter(adapter);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        rv_address.setItemAnimator(itemAnimator);
        setTitle(getString(R.string.address));
        adapter.updateData(ObjectFactory.getInstance().getProfileManager(getActivity()).getAddressList());

    }


    private void setAdapter() {
        addressesArrayList = ObjectFactory.getInstance().getProfileManager(getActivity()).getAddressList();
        if (addressesArrayList != null && addressesArrayList.size() > 0) {
            nodata.setVisibility(View.GONE);
            rv_address.setVisibility(View.VISIBLE);
            if (adapter != null) {
                adapter.updateData(addressesArrayList);
            }
        } else {
            nodata.setVisibility(View.VISIBLE);
            rv_address.setVisibility(View.GONE);
        }

    }
    private void deleteAddressAPICall(Addresses addresses) {
        DeleteUserAddressRequest deleteUserAddressRequest = new DeleteUserAddressRequest();
        if (addresses != null) {
            deleteUserAddressRequest.setAddressId(addresses.getId());
            deleteUserAddressRequest.setApiToken(Constants.API_TOKEN);
        }
        String guid = ObjectFactory.getInstance().getAppPreference(getActivity()).getGuid();
        deleteUserAddressRequest.setGuid(guid);
        progressbarShowing();
        ObjectFactory.getInstance().getProfileManager(getActivity()).deleteUserAddress(deleteUserAddressRequest);

    }

    private void progressbarShowing() {
        Intent intent = new Intent(Constants.Login.BROADCAST_PROGRESS_WHEEL);
        intent.putExtra(Constants.Login.BROADCAST_ISDISPLAY_PROGRESS_WHEEL, true);
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
    }

    private void navigateToUpdateAddress() {
        Fragment fragment = new UpdateAddressFragment();
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).addFragment(fragment);
        }
    }

    public void navigateToAddAddressFragment() {
        Fragment fragment = new AddNewAddressFragment();
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).addFragment(fragment);
        }
    }
}
