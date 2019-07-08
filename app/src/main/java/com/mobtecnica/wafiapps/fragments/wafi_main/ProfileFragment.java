package com.mobtecnica.wafiapps.fragments.wafi_main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.kcode.bottomlib.BottomDialog;
import com.mobtecnica.wafiapps.BuildConfig;
import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.activity.Login_page_one;
import com.mobtecnica.wafiapps.activity.MainActivity;
import com.mobtecnica.wafiapps.activity.Terms_and_Conditions;
import com.mobtecnica.wafiapps.listeners.OnWebserviceCallback;
import com.mobtecnica.wafiapps.manager.HomeManager;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.manager.ProfileManager;
import com.mobtecnica.wafiapps.manager.WebserviceRequestManager;
import com.mobtecnica.wafiapps.model.profile.cusomerDetails.CustomerPersonalDetailsRequest;
import com.mobtecnica.wafiapps.model.profile.cusomerDetails.customerDetails.CustomerDetailsResponse;
import com.mobtecnica.wafiapps.model.profile.get_address.Addresses;
import com.mobtecnica.wafiapps.model.profile.get_address.CustomAddressAttributes;
import com.mobtecnica.wafiapps.model.profile.get_address.CustomerAddressResponse;
import com.mobtecnica.wafiapps.model.profile.get_address.GetUserAddressRequest;
import com.mobtecnica.wafiapps.utils.BaseFragment;
import com.mobtecnica.wafiapps.utils.Constants;
import com.mobtecnica.wafiapps.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by siby on 3/1/17.
 */

public class ProfileFragment extends BaseFragment implements View.OnClickListener, OnWebserviceCallback {
    LinearLayout ll_address;
    private TextView mName,
            mEmail,
            mAddress,
            mAddNewAddress,
            mMyOrders,
            mFeedBack,
            mRateApp,
            mContactUs,
            mTermsAndConditions,
            mNotifications,
            mLogout;
    private View mAddresaLabel;
    private TextView mUpdatePassword;
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().matches(HomeManager.BROADCAST_PROFILE_DETAILS)) {
                if (intent.getBooleanExtra(HomeManager.BROADCAST_PROFILE_DETAILS_STATUS, false)) {
//                    setData();
                }
            } else if (intent.getAction().matches(ProfileManager.BROADCAST_GET_ADDRESS_RESPONSE)) {
                if (intent.getBooleanExtra(ProfileManager.BROADCAST_GET_ADDRESS_RESPONSE_STATUS, false)) {
                    setAddress(ObjectFactory.getInstance().getProfileManager(getActivity()).getSelectedAddress());
                }
            }
        }
    };


/*    private void setAddress() {
        ArrayList<Addresses> addresses = ObjectFactory.getInstance().getProfileManager(getActivity()).getAddressList();
        if (addresses.size() > 0) {
            ArrayList<CustomAddressAttributes> addressAttributes = new ArrayList<CustomAddressAttributes>(Arrays.asList(addresses.get(0).getCustomAddressAttributes()));
            ll_address.removeAllViews();
            for (int i = 0; i < addressAttributes.size(); i++) {
                View view_deals = getActivity().getLayoutInflater().inflate(R.layout.ll_address_profile, null);
                TextView address = (TextView) view_deals.findViewById(R.id.tv_address);
                address.setText(addressAttributes.get(i).getName() + ": " + addressAttributes.get(i).getDefaultValue());
                ll_address.addView(view_deals);
            }
            ll_address.setOnClickListener(this);
        }
    }*/

    private void setAddress(CustomerAddressResponse addressResponse) {
        ArrayList<Addresses> addresses = new ArrayList<>(Arrays.asList(addressResponse.getData().getAddresses()));
        if (addresses.size() > 0) {
            setAddress(addresses.get(0));
        }
        else{
            setEmptyAddress();
        }
    }

    private void setAddress(Addresses addresses) {
        if (addresses != null) {
            ArrayList<CustomAddressAttributes> addressAttributes = new ArrayList(Arrays.asList(addresses.getCustomAddressAttributes()));
            ll_address.removeAllViews();
            if (addressAttributes != null && addressAttributes.size() > 0) {
                for (int i = 0; i < addressAttributes.size(); i++) {
                    View view_deals = getActivity().getLayoutInflater().inflate(R.layout.ll_address_profile, null);
                    TextView address = view_deals.findViewById(R.id.tv_address);
                    address.setText(addressAttributes.get(i).getName() + ": " + addressAttributes.get(i).getDefaultValue());
                    ll_address.addView(view_deals);
                }
            } else {
                setEmptyAddress();
            }
            ll_address.setOnClickListener(this);
        } else {
            setEmptyAddress();
        }
    }

    private void setEmptyAddress() {
        if (ll_address != null) {
            ll_address.removeAllViews();
            View view_deals = getActivity().getLayoutInflater().inflate(R.layout.ll_address_profile, null);
            TextView address = view_deals.findViewById(R.id.tv_address);
            address.setText(R.string.empty);
            ll_address.addView(view_deals);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_profile, container, false);
        initializeViews(rootview);
        clickListners();
        return rootview;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    private void clickListners() {
        mLogout.setOnClickListener(this);
        mAddNewAddress.setOnClickListener(this);
        mRateApp.setOnClickListener(this);
        mTermsAndConditions.setOnClickListener(this);
        mMyOrders.setOnClickListener(this);
        mUpdatePassword.setOnClickListener(this);
        mAddresaLabel.setOnClickListener(this);
    }

    private void initializeViews(View rootview) {
        IntentFilter intent = new IntentFilter();
        intent.addAction(HomeManager.BROADCAST_PROFILE_DETAILS);
        intent.addAction(ProfileManager.BROADCAST_GET_ADDRESS_RESPONSE);
        addBroadcastListener(receiver, intent);

        ll_address = (LinearLayout) rootview.findViewById(R.id.ll_address);
        mName = (TextView) rootview.findViewById(R.id.tv_name_profile);
        mEmail = (TextView) rootview.findViewById(R.id.tv_email_profile);
        mAddress = (TextView) rootview.findViewById(R.id.tv_address_profile);
        mAddNewAddress = (TextView) rootview.findViewById(R.id.tv_addnewaddress_profile);
        mMyOrders = (TextView) rootview.findViewById(R.id.tv_my_orders_profile);
        mUpdatePassword = (TextView) rootview.findViewById(R.id.tv_update_password);
        mFeedBack = (TextView) rootview.findViewById(R.id.tv_feedback_profile);
        mRateApp = (TextView) rootview.findViewById(R.id.tv_rate_app_profile);
        mContactUs = (TextView) rootview.findViewById(R.id.tv_contactus_profile);
        mTermsAndConditions = (TextView) rootview.findViewById(R.id.tv_termsandconditions_profile);
        mNotifications = (TextView) rootview.findViewById(R.id.tv_notifications_profile);
        mLogout = (TextView) rootview.findViewById(R.id.tv_logout_profile);
        mAddresaLabel =rootview.findViewById(R.id.textView_address_label);
        setTitle(getString(R.string.profile));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_logout_profile:
                confirmationDialog();
                break;
            case R.id.tv_addnewaddress_profile:
                navigateToAddNewAddressFragment();
                break;
            case R.id.tv_rate_app_profile:
                rateApp();
                break;
            case R.id.tv_termsandconditions_profile:
                navigateTotv_termsandconditions_profile();
                break;
            case R.id.tv_my_orders_profile:
                navigateToMyOrdersFragment();
                break;
            case R.id.textView_address_label:
            case R.id.ll_address:
                navigatetoAddressListFragment();
                break;
            case R.id.tv_update_password:
                navigatetoUpdatePasswordFragment();
                break;
            default:
                break;
        }
    }

    private void navigatetoUpdatePasswordFragment() {
        Fragment fragment = new ChangePasswordFragment();
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).addFragment(fragment);
        }
    }

    private void navigatetoAddressListFragment() {
        Fragment fragment = new AddressListFragment();
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).addFragment(fragment);
        }
    }

    private void navigateTotv_termsandconditions_profile() {
        Intent i = new Intent(getActivity(), Terms_and_Conditions.class);
        getActivity().startActivity(i);
    }

    private void confirmationDialog() {
        BottomDialog dialog = BottomDialog.newInstance(getString(R.string.sure_to_logout), getString(R.string.cancel), new String[]{getString(R.string.ok)});
        dialog.show(getChildFragmentManager(), "dialog");
        //add item click listener
        dialog.setListener(new BottomDialog.OnClickListener() {
            @Override
            public void click(int position) {
                switch (position) {
                    case 0:
                        navigateToLogin();
                        break;

                }

            }
        });


    }


    private void rateApp() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(Constants.PLAYSTORE_URL + BuildConfig.APPLICATION_ID));
        startActivity(browserIntent);
    }

    private void navigateToAddNewAddressFragment() {
        Fragment fragment = new AddNewAddressFragment();
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).addFragment(fragment);
        }
    }

    private void navigateToMyOrdersFragment() {
        Fragment fragment = new MyOrdersFragment();
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).addFragment(fragment);
        }
    }

    private void navigateToLogin() {
        try {
            LoginManager.getInstance().logOut();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //clearing all the user details
        ObjectFactory.getInstance().getAppPreference(getContext()).setGuid("");
        ObjectFactory.getInstance().getAppPreference(getContext()).setEmailId("");
        ObjectFactory.getInstance().getAppPreference(getContext()).setRecoveryEmail("");
        ObjectFactory.getInstance().getAppPreference(getContext()).setPasswordRecoveryTokenFinal("");
        ObjectFactory.getInstance().getAppPreference(getContext()).setPasswordRecoveryToken("");
        ObjectFactory.getInstance().getAppPreference(getContext()).setIsLoggedIn("0");
        ObjectFactory.getInstance().getAppPreference(getContext()).setCustomerID("");
        ObjectFactory.getInstance().getAppPreference(getContext()).saveProfileData("");

        getFragmentManager().popBackStack();
        Intent intent = new Intent(getActivity(), Login_page_one.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        getActivity().finish();

    }

    /*private void setData() {

        ArrayList<CustomerDetailsResponse> arrayList = null;
        arrayList = ObjectFactory.getInstance().getHomeManager(getContext()).getProfileDetailsList();
        try {
            if (arrayList.get(0).getData().getCustomerInfo().getFirstName() == null || arrayList.get(0).getData().getCustomerInfo().getLastName() == null) {
                mName.setVisibility(View.GONE);
            } else {
                mName.setText(arrayList.get(0).getData().getCustomerInfo().getFirstName() + " " + arrayList.get(0).getData().getCustomerInfo().getLastName());

            }
            mEmail.setText(arrayList.get(0).getData().getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
*/
    private void setData(CustomerDetailsResponse customerDetailsResponse) {

        ArrayList<CustomerDetailsResponse> arrayList = new ArrayList(Arrays.asList(customerDetailsResponse));

//        arrayList = ObjectFactory.getInstance().getHomeManager(getContext()).getProfileDetailsList();
        try {
            if (arrayList.get(0).getData().getCustomerInfo().getFirstName() == null || arrayList.get(0).getData().getCustomerInfo().getLastName() == null) {
                mName.setVisibility(View.GONE);
            } else {
                mName.setText(arrayList.get(0).getData().getCustomerInfo().getFirstName() + " " + arrayList.get(0).getData().getCustomerInfo().getLastName());

            }
            mEmail.setText(arrayList.get(0).getData().getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {

            showLoadingDialog();
            CustomerPersonalDetailsRequest customerPersonalDetailsRequest = new CustomerPersonalDetailsRequest(ObjectFactory.getInstance().getAppPreference(getActivity()).getGuid());
            WebserviceRequestManager.getInstance().enqueueRequest(ObjectFactory.getInstance().getRestClient(getActivity()).getApiService().getCustomer(customerPersonalDetailsRequest), this, WebserviceRequestManager.RequestType.CUSTOMER_PERSONAL_DETAILS);
            GetUserAddressRequest getUserAddressRequest = new GetUserAddressRequest(ObjectFactory.getInstance().getAppPreference(getActivity()).getGuid());
            WebserviceRequestManager.getInstance().enqueueRequest(ObjectFactory.getInstance().getRestClient(getActivity()).getApiService().getCustomerAddresses(getUserAddressRequest), this, WebserviceRequestManager.RequestType.USER_ADDRESS_REQUEST);

        }
    }

    @Override
    public void onSuccess(Object result, WebserviceRequestManager.RequestType requestType) {
        if (getActivity() == null) {
            dismissLoadingDialog();
            return;
        }
        switch (requestType) {
            case CUSTOMER_PERSONAL_DETAILS:
                if (result instanceof CustomerDetailsResponse) {
                    ObjectFactory.getInstance().getAppPreference(getContext()).saveProfileData((CustomerDetailsResponse) result);
                    setData((CustomerDetailsResponse) result);
                }
                break;
            case USER_ADDRESS_REQUEST:
                if (result instanceof CustomerAddressResponse) {
                    ObjectFactory.getInstance().getProfileManager(getContext()).setAddressResponse((CustomerAddressResponse) result);
                    setAddress((CustomerAddressResponse) result);
                }
                break;
        }
        dismissLoadingDialog();
    }

    @Override
    public void onFailure(String message) {
        if (getActivity() == null) {
            dismissLoadingDialog();
            return;
        }
        dismissLoadingDialog();
        Utils.makeSnackBar(ll_address, R.string.error_occured, Snackbar.LENGTH_LONG);
    }

    @Override
    public void onCancel(Object result) {
        dismissLoadingDialog();
    }
}
