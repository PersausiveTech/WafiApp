package com.mobtecnica.wafiapps.fragments.wafi_main;


import android.app.DialogFragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mastercard.gateway.android.sdk.Gateway;
import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.activity.MainActivity;
import com.mobtecnica.wafiapps.adapters.PaymentMethodsRecyclerViewAdapter;
import com.mobtecnica.wafiapps.customViews.CustomAlertDialog;
import com.mobtecnica.wafiapps.listeners.OnWebserviceCallback;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.manager.PaymentManager;
import com.mobtecnica.wafiapps.manager.WebserviceRequestManager;
import com.mobtecnica.wafiapps.model.cart.getCart.getCartResponse.GetCartResponse;
import com.mobtecnica.wafiapps.model.checkout.BasicRequest;
import com.mobtecnica.wafiapps.model.checkout.credimaxsession.CredimaxSessionRequest;
import com.mobtecnica.wafiapps.model.checkout.opcSavePaymentMethodRequest.OpcSavePaymentMethodRequest;
import com.mobtecnica.wafiapps.model.checkout.startCheckout.ItemQuantityCheckout;
import com.mobtecnica.wafiapps.model.paymentmethod.PaymentMethod;
import com.mobtecnica.wafiapps.model.paymentmethod.PaymentMethodRequest;
import com.mobtecnica.wafiapps.model.paymentmethod.PaymentMethodResponse;
import com.mobtecnica.wafiapps.model.profile.get_address.Addresses;
import com.mobtecnica.wafiapps.utils.BaseFragment;
import com.mobtecnica.wafiapps.utils.Constants;
import com.mobtecnica.wafiapps.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import paytabs.project.PayTabActivity;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by siby on 28-Dec-16.
 */
public class PaymentSelectionFragment extends BaseFragment implements View.OnClickListener, OnWebserviceCallback {
    private static final String BROADCAST_SHOW_SUCCESS_FRAGMENT = "BROADCAST_SHOW_SUCCESS_FRAGMENT";
    private static final String BROADCAST_SHOW_SUCCESS_FRAGMENT_STATUS = "BROADCAST_SHOW_SUCCESS_FRAGMENT_STATUS";
    private static final String BROADCAST_SHOW_SUCCESS_FRAGMENT_ORDER_NO = "BROADCAST_SHOW_SUCCESS_FRAGMENT_ORDER_NO";
    private static final String BROADCAST_GET_CREDIMAX_PAYMENT_SESSION = "BROADCAST_GET_CREDIMAX_PAYMENT_SESSION";
    //    View rootView;
    ArrayList<GetCartResponse> cartResponses;
    private String orderNo = "";
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().matches(PaymentManager.BROADCAST_OPC_SAVE_PAYMENT_METHOD_RESPONSE)) {
                if (intent.getBooleanExtra(PaymentManager.BROADCAST_OPC_SAVE_PAYMENT_METHOD_RESPONSE_STATUS, false)) {
                    dismissLoadingDialog();
                    try {
                        final String mess = intent.getStringExtra(PaymentManager.BROADCAST_OPC_SAVE_PAYMENT_METHOD_RESPONSE_MESSAGE);
                        setAlert(mess, "");
                    }catch (Exception e){
                        e.printStackTrace();
                        Utils.makeSnackBar(confirm_order, R.string.error_occured, Snackbar.LENGTH_LONG);
                    }
                }
            } else if (intent.getAction().matches(PaymentManager.BROADCAST_OPC_SAVE_PAYMENT_INFO_RESPONSE)) {
                if (intent.getBooleanExtra(PaymentManager.BROADCAST_OPC_SAVE_PAYMENT_INFO_RESPONSE_STATUS, false)) {
                    confirmOrderRequest();
                }
            } else if (intent.getAction().matches(PaymentManager.BROADCAST_OPC_CONFIRM_ORDER_RESPONSE)) {
                if (intent.getBooleanExtra(PaymentManager.BROADCAST_OPC_CONFIRM_ORDER_RESPONSE_STATUS, false)) {
                    dismissLoadingDialog();
                    if (payment_method.equalsIgnoreCase("PayTabs")) {
                        orderNo = intent.getStringExtra(PaymentManager.BROADCAST_OPC_CONFIRM_ORDER_NUMBER);
                        payTabSDKCall(intent.getStringExtra(PaymentManager.BROADCAST_OPC_CONFIRM_ORDER_NUMBER));
                    } else if(payment_method.equalsIgnoreCase("CrediMax")){
                        orderNo = intent.getStringExtra(PaymentManager.BROADCAST_OPC_CONFIRM_ORDER_NUMBER);
                        crediMaxSDKCall(intent.getStringExtra(PaymentManager.BROADCAST_OPC_CONFIRM_ORDER_NUMBER));
                    }else {
                        Fragment fragment = new OrderSucccessFragment(intent.getStringExtra(PaymentManager.BROADCAST_OPC_CONFIRM_ORDER_NUMBER));
                        if (getActivity() instanceof MainActivity) {
                            ((MainActivity) getActivity()).addFragment(fragment);
                        }
                    }
                }
            } else if (intent.getAction().matches(BROADCAST_SHOW_SUCCESS_FRAGMENT)) {
                if (intent.getBooleanExtra(BROADCAST_SHOW_SUCCESS_FRAGMENT_STATUS, false)) {
                    dismissLoadingDialog();
                    Fragment fragment = new OrderSucccessFragment(intent.getStringExtra(BROADCAST_SHOW_SUCCESS_FRAGMENT_ORDER_NO));
                    if (getActivity() instanceof MainActivity) {
                        ((MainActivity) getActivity()).addFragment(fragment);
                    }
                }
            }else if(intent.getAction().matches(BROADCAST_GET_CREDIMAX_PAYMENT_SESSION)){
                dismissLoadingDialog();
                if (intent.getBooleanExtra(PaymentManager.BROADCAST_OPC_CREDIMAX_SESSION_STATUS, false)) {
                    updateCredimaxSession(intent.getStringExtra(PaymentManager.BROADCAST_OPC_CREDIMAX_SESSION_ID),
                            intent.getStringExtra(PaymentManager.BROADCAST_OPC_CREDIMAX_SESSION_VERSION),
                            intent.getStringExtra(PaymentManager.BROADCAST_OPC_CREDIMAX_MERCHANT_ID),
                            intent.getStringExtra(PaymentManager.BROADCAST_OPC_CREDIMAX_API_VERSION));
                }
            }
        }
    };
    String payment_method = "";
    private TextView tv_total_price;
    //    private ImageButton ibCashOnDelivery, ibPaytabs;
    private TextView confirm_order;
    private RecyclerView paymentMethodRecyclerview;
    private PaymentMethodsRecyclerViewAdapter paymentMethodsRecyclerViewAdapter;
    private View containerNoPayment;

/*    private void setConfirmationAlert(String stringExtra, String s) {
        new AlertDialog.Builder(getActivity(), R.style.Theme_AppCompat_Light_Dialog)
                .setTitle(s)
                .setMessage(stringExtra)
                .setPositiveButton(Html.fromHtml(getString(R.string.confirm_order)), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        confirmOrderRequest();
                    }
                })
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }*/

    private void confirmOrderRequest() {
        progressbarShowing();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                BasicRequest basicRequest = new BasicRequest();
                basicRequest.setGuid(ObjectFactory.getInstance().getAppPreference(getActivity()).getGuid());
                basicRequest.setApiToken(Constants.API_TOKEN);
                String json = new Gson().toJson(basicRequest);
                ObjectFactory.getInstance().getPaymentManager(getActivity()).opcConfirmOrder(json);

            }
        }, 100);
    }

    public PaymentSelectionFragment() {
        setButtonType(Utils.BUTTON_TYPE.EMPTY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_payment_selection, container, false);
        initializeFields(rootView);
        setValues();
        initPaymentMethods();
        return rootView;
    }

    private void initPaymentMethods() {
        if (!Utils.getCheckoutPrice(WebserviceRequestManager.getInstance().getGetCartResponse()).equals("BD 0.000")) {
            containerNoPayment.setVisibility(View.GONE);
            PaymentMethodRequest paymentMethodRequest = new PaymentMethodRequest(ObjectFactory.getInstance().getAppPreference(getActivity()).getGuid());
            showLoadingDialog();
            WebserviceRequestManager.getInstance().enqueueRequest(ObjectFactory.getInstance().getRestClient(getActivity()).getApiService().getPaymentMethods(paymentMethodRequest), this, WebserviceRequestManager.RequestType.PAYMENT_METHODS);
        } else {
            containerNoPayment.setVisibility(View.VISIBLE);
        }
    }

    private void setValues() {
//        cartResponses = ObjectFactory.getInstance().getHomeManager(getActivity()).getCartProductsList();
        tv_total_price.setText(Utils.getCheckoutPrice(WebserviceRequestManager.getInstance().getGetCartResponse()));
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(getContext());
        paymentMethodRecyclerview.setLayoutManager(recyclerLayoutManager);
        paymentMethodsRecyclerViewAdapter = new PaymentMethodsRecyclerViewAdapter();
        paymentMethodRecyclerview.setAdapter(paymentMethodsRecyclerViewAdapter);
    }

    private void initializeFields(View rootView) {

        IntentFilter intent = new IntentFilter();
        intent.addAction(PaymentManager.BROADCAST_OPC_SAVE_PAYMENT_METHOD_RESPONSE);
        intent.addAction(PaymentManager.BROADCAST_OPC_SAVE_PAYMENT_INFO_RESPONSE);
        intent.addAction(PaymentManager.BROADCAST_OPC_CONFIRM_ORDER_RESPONSE);
        intent.addAction(BROADCAST_SHOW_SUCCESS_FRAGMENT);
        intent.addAction(BROADCAST_GET_CREDIMAX_PAYMENT_SESSION);
        addBroadcastListener(receiver, intent);
        setTitle(getString(R.string.payment_method));
        tv_total_price = rootView.findViewById(R.id.tv_total_price);
        confirm_order = rootView.findViewById(R.id.tv_confirm_order);
        paymentMethodRecyclerview = rootView.findViewById(R.id.rv_items);
        containerNoPayment = rootView.findViewById(R.id.container_no_payment);
//        ibCashOnDelivery = (ImageButton) rootView.findViewById(R.id.ib_cash_on_delivery);
//        ibPaytabs = (ImageButton) rootView.findViewById(R.id.ib_credit_or_debit);
//        radioGroup = (RadioGroup) rootView.findViewById(R.id.radio_group_payment_selection);
//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
//                switch (checkedId) {
//                    case R.id.cash_on_delivery:
//                        payment_method = "CashOnDelivery";
//                        break;
//                    case R.id.credit_or_debit:
//                        payment_method = "PayTab";
//                        break;
//                    case R.id.purchase_order:
//                        payment_method = "";
//                        break;
//                    default:
//                        break;
//                }
//            }
//        });
        confirm_order.setOnClickListener(this);
//        ibCashOnDelivery.setOnClickListener(this);
//        ibPaytabs.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_confirm_order:
//                apiCallForPaymentMethodSelection();
//                methodSelection(paymentMethodsRecyclerViewAdapter.getSelectedPaymentMethod());
                if (!Utils.getCheckoutPrice(WebserviceRequestManager.getInstance().getGetCartResponse()).equals("BD 0.000")) {
                    methodSelection(paymentMethodsRecyclerViewAdapter.getSelectedPaymentMethod());
                } else {
                    PaymentMethod paymentMethod = new PaymentMethod();
                    paymentMethod.setName("Cash On Delivery (COD)");
                    paymentMethod.setPaymentMethodSystemName("Payments.CashOnDelivery");
                    methodSelection(paymentMethod);
                }
                break;
            default:
                break;
        }
    }

    private void methodSelection(PaymentMethod paymentMethod) {
        if (paymentMethod == null){
            Utils.makeSnackBar(confirm_order, R.string.select_a_field, Snackbar.LENGTH_LONG);
            return;
        }
        showLoadingDialog();
        try {
            payment_method = paymentMethod.getPaymentMethodSystemName().replace("Payments.", "");
            OpcSavePaymentMethodRequest methodRequest = new OpcSavePaymentMethodRequest();
            methodRequest.setApiToken(Constants.API_TOKEN);
            methodRequest.setGuid(ObjectFactory.getInstance().getAppPreference(getActivity()).getGuid());
            if (paymentMethod.getName().equalsIgnoreCase("Credit Card") || paymentMethod.getName().equalsIgnoreCase("Debit Card")) {
                payment_method = "PayTabs";
                methodRequest.setPaymentmethod(payment_method);
            }
//            methodRequest.setPaymentmethod(payment_method);
            methodRequest.setPaymentmethod(payment_method);
            String json = new Gson().toJson(methodRequest);
            ObjectFactory.getInstance().getPaymentManager(getActivity()).opcSavePaymentMethod(json);
        }catch (Exception e){
            Utils.makeSnackBar(confirm_order, R.string.error_occured, Snackbar.LENGTH_LONG);
        }
    }

    private void payTabSDKCall(String orderNo) {
        Intent in = new Intent(getActivity(), PayTabActivity.class);
        in.putExtra("pt_merchant_email", Constants.PT_MERCHANT_EMAIL); //this a demo account for testing the sdk
        in.putExtra("pt_secret_key", Constants.PT_SECRET_KEY);//Add your Secret Key Here
        ArrayList<Addresses> arrayList = ObjectFactory.getInstance().getProfileManager(getActivity()).getAddressList();
        int position = ObjectFactory.getInstance().getAppPreference(getActivity()).getBillingAddressPosition();
        Addresses addresses = arrayList.get(position);
        in.putExtra("pt_transaction_title", addresses.getFirstName() + " " + addresses.getLastName());
//        String amount[] = ObjectFactory.getInstance().getPaymentManager(getActivity()).getTotalAmount().split(" ");
//        in.putExtra("pt_amount", amount[1]);
        in.putExtra("pt_amount", Utils.getTotalAmount(WebserviceRequestManager.getInstance().getGetCartResponse()));
        in.putExtra("pt_currency_code", "BHD"); //Use Standard 3 character ISO
        in.putExtra("pt_shared_prefs_name", Constants.PAYTABS_PREFNAME);
        in.putExtra("pt_customer_email", addresses.getEmail());

        in.putExtra("pt_customer_phone_number", addresses.getPhoneNumber());
        in.putExtra("pt_order_id", orderNo);
        List<ItemQuantityCheckout> quantityCheckouts = ObjectFactory.getInstance().getPaymentManager(getActivity()).getItemQuantityList();
        String itemNames = "";
        for (int i = 0; i < quantityCheckouts.size(); i++) {
            itemNames = itemNames + quantityCheckouts.get(i).getName();
            if (i != quantityCheckouts.size() - 1) {
                itemNames = itemNames + " ,";
            }
        }
        in.putExtra("pt_product_name", itemNames);
//        in.putExtra("pt_timeout_in_seconds", "600"); //Optional//Optional //Billing Address
        in.putExtra("pt_address_billing", addresses.getFormattedCustomAddressAttributes());
        in.putExtra("pt_city_billing", addresses.getCountryName());
        in.putExtra("pt_state_billing", addresses.getCountryName());
        in.putExtra("pt_country_billing","BHR");
        int shipping_position = ObjectFactory.getInstance().getAppPreference(getActivity()).getBillingAddressPosition();
        Addresses shipping_addresses = arrayList.get(shipping_position);
        in.putExtra("pt_postal_code_billing", "00973"); //Put Country Phone code if Postal code not available '00973' //Shipping Address
        in.putExtra("pt_address_shipping", shipping_addresses.getFormattedCustomAddressAttributes());
        in.putExtra("pt_city_shipping", shipping_addresses.getCountryName());
        in.putExtra("pt_state_shipping", shipping_addresses.getCountryName());
        in.putExtra("pt_country_shipping", shipping_addresses.getCountryName());
        in.putExtra("pt_postal_code_shipping", "00973"); //Put Country Phone code if Postal code not available '00973'
        int requestCode = 111;
        startActivityForResult(in, requestCode);
    }

    private void crediMaxSDKCall(String orderNo){
        showLoadingDialog();
        try {
            CredimaxSessionRequest methodRequest = new CredimaxSessionRequest();
            methodRequest.setApiToken("fd2cdf71-3fc9-40b5-9638-a281e39d6b2c");
            methodRequest.setGuid(ObjectFactory.getInstance().getAppPreference(getActivity()).getGuid());
            methodRequest.setOrderId(orderNo);
            String json = new Gson().toJson(methodRequest);
            ObjectFactory.getInstance().getPaymentManager(getActivity()).opcGetCredimaxSession(json);
        }catch (Exception e){
            Utils.makeSnackBar(confirm_order, R.string.error_occured, Snackbar.LENGTH_LONG);
        }
    }

    private void updateCredimaxSession(String sessionId,String sessionVersion,String merchantId,String apiVersion){
        Fragment fragment = new CredimaxCardDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("apiVersion",apiVersion);
        bundle.putString("sessionId",sessionId);
        bundle.putString("sessionVersion",sessionVersion);
        bundle.putString("merchantId",merchantId);
        bundle.putString("orderNo",orderNo);
        fragment.setArguments(bundle);
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).addFragment(fragment);
        }
    }

    public void setAlert(String message, String head) {

        CustomAlertDialog customAlertDialog = new CustomAlertDialog(message, head,
                (Html.fromHtml(getString(R.string.confirm_order))).toString(), getString(R.string.cancel));
        customAlertDialog.setCustomAlertClickListener(new CustomAlertDialog.CustomAlertClickListener() {

            @Override
            public void onPositive(DialogFragment dialogFragment) {
                dialogFragment.dismiss();
            }

            @Override
            public void onNegative(DialogFragment dialogFragment) {
                savePaymentInformation();
                dialogFragment.dismiss();
            }
        });
        customAlertDialog.show(getActivity().getFragmentManager(), "Alert");

    }

    private void savePaymentInformation() {
        progressbarShowing();
        BasicRequest basicRequest = new BasicRequest();
        basicRequest.setGuid(ObjectFactory.getInstance().getAppPreference(getActivity()).getGuid());
        basicRequest.setApiToken(Constants.API_TOKEN);
        String json = new Gson().toJson(basicRequest);
        progressbarShowing();
        ObjectFactory.getInstance().getPaymentManager(getActivity()).opcSavePaymentInfo(json);
    }

    private void progressbarShowing() {
        Intent intent = new Intent(Constants.Login.BROADCAST_PROGRESS_WHEEL);
        intent.putExtra(Constants.Login.BROADCAST_ISDISPLAY_PROGRESS_WHEEL, true);
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 111) {
            SharedPreferences shared_prefs = getActivity().getSharedPreferences(Constants.PAYTABS_PREFNAME, MODE_PRIVATE);
            String pt_response_code = shared_prefs.getString("pt_response_code", "");
            String pt_transaction_id = shared_prefs.getString("pt_transaction_id", "");
//            Toast.makeText(getActivity(), "PayTabs Response Code : " + pt_response_code, Toast.LENGTH_LONG).show();
//            Toast.makeText(getActivity(), "Paytabs transaction ID after payment : " + pt_transaction_id, Toast.LENGTH_LONG).show();
//            Intent intentRes = new Intent(BROADCAST_SHOW_SUCCESS_FRAGMENT);
//            intentRes.putExtra(BROADCAST_SHOW_SUCCESS_FRAGMENT_STATUS, true);
//            intentRes.putExtra(BROADCAST_SHOW_SUCCESS_FRAGMENT_ORDER_NO, orderNo);
//            LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intentRes);
            if (pt_response_code.equalsIgnoreCase("100")) {
                Fragment fragment = new OrderSucccessFragment(orderNo);
//                getFragmentManager().beginTransaction()
//                        .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
//                        .replace(R.id.sb_root, fragment, Utils.getTagForFragment(fragment))
//                        .commit();
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).addFragment(fragment);
                }

            } else {
                Fragment fragment = new OrderFailedFragment(orderNo);
//                getFragmentManager().beginTransaction()
//                        .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
//                        .replace(R.id.sb_root, fragment, Utils.getTagForFragment(fragment))
//                        .commit();
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).addFragment(fragment);
                }
            }

        }

    }

    @Override
    public void onSuccess(Object result, WebserviceRequestManager.RequestType requestType) {
        if (getActivity() == null) {
            dismissLoadingDialog();
            return;
        }
        switch (requestType) {
            case PAYMENT_METHODS:
                if (result instanceof PaymentMethodResponse) {
                    if (paymentMethodsRecyclerViewAdapter != null) {
                        try {
                            paymentMethodsRecyclerViewAdapter.updateData(((PaymentMethodResponse) result).getData().getPaymentMethods());
                        } catch (Exception e) {
                            e.printStackTrace();
                            Utils.makeSnackBar(tv_total_price, R.string.server_error, Snackbar.LENGTH_LONG);
                        }
                    }
                } else if (result instanceof String) {
                    Utils.makeSnackBar(tv_total_price, result.toString(), Snackbar.LENGTH_LONG);
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
        Utils.makeSnackBar(tv_total_price, R.string.server_error, Snackbar.LENGTH_LONG);
    }

    @Override
    public void onCancel(Object result) {
        dismissLoadingDialog();
    }
}
