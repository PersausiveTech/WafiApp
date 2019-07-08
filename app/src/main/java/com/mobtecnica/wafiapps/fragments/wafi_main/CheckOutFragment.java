package com.mobtecnica.wafiapps.fragments.wafi_main;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.activity.MainActivity;
import com.mobtecnica.wafiapps.activity.Terms_and_Conditions;
import com.mobtecnica.wafiapps.listeners.OnWebserviceCallback;
import com.mobtecnica.wafiapps.manager.HomeManager;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.manager.PaymentManager;
import com.mobtecnica.wafiapps.manager.ProfileManager;
import com.mobtecnica.wafiapps.manager.WebserviceRequestManager;
import com.mobtecnica.wafiapps.model.cart.getCart.getCartResponse.CheckoutAttributes;
import com.mobtecnica.wafiapps.model.cart.getCart.getCartResponse.GetCartResponse;
import com.mobtecnica.wafiapps.model.cart.getCart.getCartResponse.GiftCards;
import com.mobtecnica.wafiapps.model.cart.getCart.getCartResponse.Values;
import com.mobtecnica.wafiapps.model.checkout.BasicRequest;
import com.mobtecnica.wafiapps.model.checkout.opcSaveBilling.OpcSaveBillingRequest;
import com.mobtecnica.wafiapps.model.checkout.opcSaveShipping.OpcSaveShippingRequest;
import com.mobtecnica.wafiapps.model.checkout.opcSaveShippingMethod.OpcSaveShippingMethodRequest;
import com.mobtecnica.wafiapps.model.checkout.startCheckout.CheckoutAttributeCheckout;
import com.mobtecnica.wafiapps.model.checkout.startCheckout.ItemQuantityCheckout;
import com.mobtecnica.wafiapps.model.checkout.startCheckout.StartCheckoutRequest;
import com.mobtecnica.wafiapps.model.discountCoupon.ApplyDiscountCoupenRequest;
import com.mobtecnica.wafiapps.model.discountCoupon.RemoveDiscountCouponRequest;
import com.mobtecnica.wafiapps.model.giftCard.GiftCardRequest;
import com.mobtecnica.wafiapps.model.giftCard.RemoveGiftCardRequest;
import com.mobtecnica.wafiapps.model.profile.get_address.Addresses;
import com.mobtecnica.wafiapps.model.profile.get_address.GetUserAddressRequest;
import com.mobtecnica.wafiapps.utils.BaseFragment;
import com.mobtecnica.wafiapps.utils.Constants;
import com.mobtecnica.wafiapps.utils.PaymentDataHolder;
import com.mobtecnica.wafiapps.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static com.mobtecnica.wafiapps.manager.ProfileManager.BROADCAST_GET_ADDRESS_RESPONSE;
import static com.mobtecnica.wafiapps.manager.ProfileManager.BROADCAST_GET_ADDRESS_RESPONSE_STATUS;

/**
 * Created by SIby on 22-03-2017.
 */

public class CheckOutFragment extends BaseFragment implements View.OnClickListener, OnWebserviceCallback {
    private View rootView;
    private TextView tv_name;
    private TextView tv_billing_name;
    private TextView tv_phone_number;
    private TextView tv_billing_phone_number;
    private TextView tv_shipping_address;
    private TextView tv_billing_address;
    private TextView tv_change_address;
    private TextView tv_billing_change_address;
    private Button next_payment_gateway;
    private TextView tv_total_price;
    private ImageView btn_ok;
    private AlertDialog alertDialog;
    private Button tv_apply_coupon;
    private Button tv_apply_gift_card;
    private EditText et_gift_coupon;
    private EditText et_discount_coupon;
    private FrameLayout rl_checkout;
    private LinearLayout show_price;
    private LinearLayout card_layout;
    private CheckBox checkBox;
    private TextView text_checkbox;
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().matches(ProfileManager.BROADCAST_GET_ADDRESS_RESPONSE_CART)) {
                if (intent.getBooleanExtra(ProfileManager.BROADCAST_GET_ADDRESS_RESPONSE_STATUS_CART, false)) {
                    setAddress();
                } else {
                    dismissLoadingDialog();
                    Utils.makeSnackBar(rl_checkout, R.string.some_error_occurred, Snackbar.LENGTH_LONG);
                }
            } else if (intent.getAction().matches(HomeManager.BROADCAST_APPLY_GIFT_CARD)) {
                if (intent.getBooleanExtra(HomeManager.BROADCAST_APPLY_GIFT_CARD_STATUS, false)) {
                    Utils.makeSnackBar(rl_checkout, intent.getStringExtra(HomeManager.BROADCAST_MESSAGE), Snackbar.LENGTH_LONG);
                }
            } else if (intent.getAction().matches(HomeManager.BROADCAST_APPLY_DISCOUNT_COUPON)) {
                if (intent.getBooleanExtra(HomeManager.BROADCAST_APPLYBROADCAST_APPLY_DISCOUNT_COUPON_STATUS, false)) {
                    Utils.makeSnackBar(rl_checkout, intent.getStringExtra(HomeManager.BROADCAST_MESSAGE), Snackbar.LENGTH_LONG);
                }
            } else if (intent.getAction().matches(PaymentManager.BROADCAST_START_CHECKOUT_RESPONSE)) {
                if (intent.getBooleanExtra(PaymentManager.BROADCAST_START_CHECKOUT_RESPONSE_STATUS, false)) {
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            opcBillingFormApiRequest();

                        }
                    }, 200);
                } else {
                    dismissLoadingDialog();
                    Utils.makeSnackBar(rl_checkout, R.string.error_occured, Snackbar.LENGTH_LONG);
                }
            } /*else if (intent.getAction().matches(PaymentManager.BROADCAST_START_ONE_PAGE_CHECKOUT_RESPONSE)) {
                if (intent.getBooleanExtra(PaymentManager.BROADCAST_START_ONE_PAGE_CHECKOUT_RESPONSE_STATUS, false)) {
                    System.out.println("CheckOutFragment.onReceive BROADCAST_START_ONE_PAGE_CHECKOUT_RESPONSE");
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Do something after 100ms

                        }
                    }, 200);
                } else {
                    setAlert(getString(R.string.error_occured), getString(R.string.message));
                    refreshFragment();
                }
            }*/ else if (intent.getAction().matches(PaymentManager.BROADCAST_OPC_BILLING_FORM_CHECKOUT_RESPONSE)) {
                if (intent.getBooleanExtra(PaymentManager.BROADCAST_OPC_BILLING_FORM_CHECKOUT_RESPONSE_STATUS, false)) {
                    System.out.println("CheckOutFragment.onReceive BROADCAST_OPC_BILLING_FORM_CHECKOUT_RESPONSE");
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Do something after 100ms
                            opcSaveBillingRequest();

                        }
                    }, 200);
                } else {
                    dismissLoadingDialog();
                    Utils.makeSnackBar(rl_checkout, R.string.error_occured, Snackbar.LENGTH_LONG);
                }
            } else if (intent.getAction().matches(PaymentManager.BROADCAST_OPC_SAVE_BILLING_FORM_CHECKOUT_RESPONSE)) {
                if (intent.getBooleanExtra(PaymentManager.BROADCAST_OPC_SAVE_BILLING_FORM_CHECKOUT_RESPONSE_STATUS, false)) {
                    System.out.println("CheckOutFragment.onReceive BROADCAST_OPC_SAVE_BILLING_FORM_CHECKOUT_RESPONSE");
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Do something after 100ms
                            opcSaveShippingRequest();
                        }
                    }, 200);
                } else {
                    dismissLoadingDialog();
                    Utils.makeSnackBar(rl_checkout, R.string.error_occured, Snackbar.LENGTH_LONG);
                    refreshFragment();
                }
            } else if (intent.getAction().matches(PaymentManager.BROADCAST_OPC_SAVE_SHIPPING_RESPONSE)) {
                if (intent.getBooleanExtra(PaymentManager.BROADCAST_OPC_SAVE_SHIPPING_RESPONSE_STATUS, false)) {
                    System.out.println("CheckOutFragment.onReceive BROADCAST_OPC_SAVE_SHIPPING_RESPONSE");
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Do something after 100ms
                            opcSaveShippingMethod();

                        }
                    }, 200);

                } else {
                    dismissLoadingDialog();
                    Utils.makeSnackBar(rl_checkout, R.string.error_occured, Snackbar.LENGTH_LONG);
                }
            } else if (intent.getAction().matches(PaymentManager.BROADCAST_OPC_SAVE_SHIPPING_METHOD_RESPONSE)) {
                if (intent.getBooleanExtra(PaymentManager.BROADCAST_OPC_SAVE_SHIPPING_METHOD_RESPONSE_STATUS, false)) {
                    System.out.println("CheckOutFragment.onReceive BROADCAST_OPC_SAVE_SHIPPING_METHOD_RESPONSE");
                    dismissLoadingDialog();
                    navigateToGatewaySelectionFragment();
                } else {
                    dismissLoadingDialog();
                    Utils.makeSnackBar(rl_checkout, R.string.error_occured, Snackbar.LENGTH_LONG);
                }
            } else if (intent.getAction().matches(BROADCAST_GET_ADDRESS_RESPONSE)) {
                if (intent.getBooleanExtra(BROADCAST_GET_ADDRESS_RESPONSE_STATUS, false)) {
                    setAddress();
                }
            }

        }
    };
    private View container_discount_code_entry;
    private View container_gift_card_entry;
    private View btn_delete_gift_card;
    private View btn_delete_discount_code;
    private GetCartResponse tempCartResponse; // TODO remove this when correcting Cuopon and gift card APIs
    private ScrollView scrollViewItems;

    private void opcSaveShippingMethod() {
        progressbarShowing();
        OpcSaveShippingMethodRequest opcSaveShippingMethodRequest = new OpcSaveShippingMethodRequest();
        opcSaveShippingMethodRequest.setGuid(ObjectFactory.getInstance().getAppPreference(getActivity()).getGuid());
        opcSaveShippingMethodRequest.setApiToken(Constants.API_TOKEN);
        opcSaveShippingMethodRequest.setShippingoption("Express");
        String json = new Gson().toJson(opcSaveShippingMethodRequest);
        ObjectFactory.getInstance().getPaymentManager(getActivity()).opcSaveShippingMethod(json);
    }

    private void opcSaveBillingRequest() {
        progressbarShowing();
        OpcSaveBillingRequest request = new OpcSaveBillingRequest();
        request.setGuid(ObjectFactory.getInstance().getAppPreference(getActivity()).getGuid());
        request.setApiToken(Constants.API_TOKEN);
        int position = ObjectFactory.getInstance().getAppPreference(getActivity()).getBillingAddressPosition();
        ArrayList<Addresses> arrayList = ObjectFactory.getInstance().getProfileManager(getActivity()).getAddressList();
        request.setBillingAddressId(arrayList.get(position).getId());
        request.setShipToSameAddress("true");
        String json = new Gson().toJson(request);
        ObjectFactory.getInstance().getPaymentManager(getActivity()).opcSaveBilling(json);
    }

    private void opcSaveShippingRequest() {
        progressbarShowing();
        OpcSaveShippingRequest shippingRequest = new OpcSaveShippingRequest();
        shippingRequest.setApiToken(Constants.API_TOKEN);
        shippingRequest.setGuid(ObjectFactory.getInstance().getAppPreference(getActivity()).getGuid());
        int position = ObjectFactory.getInstance().getAppPreference(getActivity()).getCurrrentPosition();
        ArrayList<Addresses> arrayList = ObjectFactory.getInstance().getProfileManager(getActivity()).getAddressList();
        shippingRequest.setShippingAddressId(arrayList.get(position).getId());
        String json = new Gson().toJson(shippingRequest);

        ObjectFactory.getInstance().getPaymentManager(getActivity()).opcSaveShipping(json);
    }

    private void opcBillingFormApiRequest() {
        progressbarShowing();
        BasicRequest basicRequest = new BasicRequest();
        basicRequest.setGuid(ObjectFactory.getInstance().getAppPreference(getActivity()).getGuid());
        basicRequest.setApiToken(Constants.API_TOKEN);
        String json = new Gson().toJson(basicRequest);

        ObjectFactory.getInstance().getPaymentManager(getActivity()).opcBillingForm(json);
    }


    private void refreshFragment() {
//        Fragment fragment = new CheckOutFragment();
//        if (getActivity() instanceof MainActivity) {
//            ((MainActivity) getActivity()).addFragment(fragment);
//        }
    }

    private void setAddress() {
       ArrayList<Addresses> arrayList = ObjectFactory.getInstance().getProfileManager(getActivity()).getAddressList();
        if (arrayList.size() > 0) {
            int position = ObjectFactory.getInstance().getAppPreference(getActivity()).getCurrrentPosition();
            int billing_position = ObjectFactory.getInstance().getAppPreference(getActivity()).getBillingAddressPosition();

            tv_billing_change_address.setText(getString(R.string.change_address));
            tv_change_address.setText(getString(R.string.change_address));
            tv_name.setVisibility(View.VISIBLE);
            tv_name.setText(arrayList.get(position).getFirstName() + " " + arrayList.get(position).getLastName());
            try {
                tv_phone_number.setText(getString(R.string.mob) + "" + arrayList.get(position).getPhoneNumber());
                tv_phone_number.setVisibility(View.VISIBLE);
            } finally {

            }
            String s = arrayList.get(position).getFormattedCustomAddressAttributes();
            s = s.replaceAll("<br />", ",  ");
            tv_shipping_address.setText(s);
            tv_shipping_address.setVisibility(View.VISIBLE);
//            tv_shipping_address.setText(Html.fromHtml(arrayList.get(position).getFormattedCustomAddressAttributes()));

            tv_billing_name.setText(arrayList.get(billing_position).getFirstName() + " " + arrayList.get(billing_position).getLastName());
            tv_billing_name.setVisibility(View.VISIBLE);
            try {
                tv_billing_phone_number.setText(getString(R.string.mob) + "" + arrayList.get(billing_position).getPhoneNumber());
                tv_billing_phone_number.setVisibility(View.VISIBLE);
            } finally {

            }
            String address = arrayList.get(billing_position).getFormattedCustomAddressAttributes();
            address = address.replaceAll("<br />", ",  ");
            tv_billing_address.setText(address);
            tv_billing_address.setVisibility(View.VISIBLE);
//            tv_billing_address.setText(Html.fromHtml(arrayList.get(billing_position).getFormattedCustomAddressAttributes()));
        } else {
            tv_billing_change_address.setText(getString(R.string.add_new_address));
            tv_change_address.setText(getString(R.string.add_new_address));
        }
    }

    public CheckOutFragment() {
        setButtonType(Utils.BUTTON_TYPE.EMPTY);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_checkout, container, false);
        initializeViews();
        onClicklistners();
        updateViews();
        tempCartResponse = WebserviceRequestManager.getInstance().getGetCartResponse();
        return rootView;
    }

    private void updateViews() {
        if (tv_total_price != null) {
            tv_total_price.setText(Utils.getCheckoutPrice(WebserviceRequestManager.getInstance().getGetCartResponse()));
        }
        if (container_discount_code_entry != null) {
            container_discount_code_entry.setVisibility(Utils.isDiscountCouponApplied(WebserviceRequestManager.getInstance().getGetCartResponse()) ? View.GONE : View.VISIBLE);
            if (et_discount_coupon != null) {
                et_discount_coupon.setText("");
            }
        }
        if (container_gift_card_entry != null) {
            container_gift_card_entry.setVisibility(Utils.isGiftCardApplied(WebserviceRequestManager.getInstance().getGetCartResponse()) ? View.GONE : View.VISIBLE);
            if (et_gift_coupon != null) {
                et_gift_coupon.setText("");
            }
        }
    }

    private void onClicklistners() {
        tv_change_address.setOnClickListener(this);
        tv_billing_change_address.setOnClickListener(this);
        next_payment_gateway.setOnClickListener(this);
        tv_apply_coupon.setOnClickListener(this);
        show_price.setOnClickListener(this);
        tv_apply_gift_card.setOnClickListener(this);

        btn_delete_gift_card.setOnClickListener(this);
        btn_delete_discount_code.setOnClickListener(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GetUserAddressRequest addressRequest = new GetUserAddressRequest();
        addressRequest.setGuid(ObjectFactory.getInstance().getAppPreference(getActivity()).getGuid());
        addressRequest.setApiToken(Constants.API_TOKEN);
        ObjectFactory.getInstance().getProfileManager(getActivity()).getUserAddress(addressRequest, 2);
    }

    @SuppressLint("ResourceType")
    private void initializeViews() {

        IntentFilter intent = new IntentFilter();
        intent.addAction(ProfileManager.BROADCAST_GET_ADDRESS_RESPONSE_CART);
        intent.addAction(HomeManager.BROADCAST_APPLY_GIFT_CARD);
        intent.addAction(HomeManager.BROADCAST_APPLY_DISCOUNT_COUPON);
        intent.addAction(PaymentManager.BROADCAST_START_CHECKOUT_RESPONSE);
        intent.addAction(PaymentManager.BROADCAST_START_ONE_PAGE_CHECKOUT_RESPONSE);
        intent.addAction(PaymentManager.BROADCAST_OPC_BILLING_FORM_CHECKOUT_RESPONSE);
        intent.addAction(PaymentManager.BROADCAST_OPC_SAVE_BILLING_FORM_CHECKOUT_RESPONSE);
        intent.addAction(PaymentManager.BROADCAST_OPC_SAVE_SHIPPING_RESPONSE);
        intent.addAction(PaymentManager.BROADCAST_OPC_SAVE_SHIPPING_METHOD_RESPONSE);
        intent.addAction(BROADCAST_GET_ADDRESS_RESPONSE);
        addBroadcastListener(receiver, intent);
        setTitle(getString(R.string.check_out));

        tv_name = (TextView) rootView.findViewById(R.id.tv_name);
        tv_billing_name = (TextView) rootView.findViewById(R.id.tv_billing_name);
        tv_shipping_address = (TextView) rootView.findViewById(R.id.tv_shipping_address);
        tv_billing_address = (TextView) rootView.findViewById(R.id.tv_billing_address);
        tv_phone_number = (TextView) rootView.findViewById(R.id.tv_phone_number);
        tv_billing_phone_number = (TextView) rootView.findViewById(R.id.tv_billing_phone_number);
        tv_change_address = (TextView) rootView.findViewById(R.id.tv_change_address);
        tv_billing_change_address = (TextView) rootView.findViewById(R.id.tv_billing_change_address);
        next_payment_gateway = (Button) rootView.findViewById(R.id.next_payment_gateway);
        show_price=(LinearLayout)rootView.findViewById(R.id.show_price); 
        tv_total_price = (TextView) rootView.findViewById(R.id.tv_total_price);
        tv_apply_coupon = (Button) rootView.findViewById(R.id.tv_apply_coupon);
        tv_apply_gift_card = (Button) rootView.findViewById(R.id.tv_apply_gift_card);
        et_gift_coupon = (EditText) rootView.findViewById(R.id.et_gift_coupon);
        et_discount_coupon = (EditText) rootView.findViewById(R.id.et_discount_coupon);
        checkBox = (CheckBox) rootView.findViewById(R.id.disclaimer);
        text_checkbox = (TextView) rootView.findViewById(R.id.checkbox_text);
//
        rl_checkout = (FrameLayout) rootView.findViewById(R.id.rl_checkout);
        card_layout = (LinearLayout) rootView.findViewById(R.id.card_layout);
        scrollViewItems = rootView.findViewById(R.id.sv_items);

        container_discount_code_entry =  rootView.findViewById(R.id.container_discount_code_entry);
        container_gift_card_entry =  rootView.findViewById(R.id.container_gift_card_entry);
        btn_delete_gift_card =  rootView.findViewById(R.id.btn_delete_gift_card);
        btn_delete_discount_code =  rootView.findViewById(R.id.btn_delete_discount_code);


            setCheckBoxClick();
    }

    private void setCheckBoxClick() {
        SpannableString ss = new SpannableString(text_checkbox.getText().toString());
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                startActivity(new Intent(getActivity(), Terms_and_Conditions.class));
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(true);
            }
        };
//r
        ss.setSpan(clickableSpan, 17, 33, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        text_checkbox.setText(ss);
        text_checkbox.setMovementMethod(LinkMovementMethod.getInstance());
        text_checkbox.setHighlightColor(getResources().getColor(R.color.colorAccent));
        text_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBox.setChecked(!checkBox.isChecked());
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_change_address:
                if (tv_change_address.getText().toString().matches(getString(R.string.add_new_address))) {
                    navigateToAddAddressFragment();
                } else {
                    navigateToSelectAddressFragment(Constants.SHIPPING);
                }
                break;
            case R.id.next_payment_gateway:
                if(checkBox.isChecked()){
                    navigateToGatewaySelection();
                } else {
                    if (scrollViewItems != null) {
                        scrollViewItems.smoothScrollTo(0, ((View) checkBox.getParent().getParent()).getBottom());
                    }
                    Utils.makeSnackBar(rootView, R.string.accept_terms_of_service,Snackbar.LENGTH_LONG);
                }

                break;
            case R.id.tv_apply_coupon:
                applyCouponApi();
                break;
            case R.id.tv_apply_gift_card:
                giftCardApi();
                break;
            case R.id.tv_billing_change_address:
                if (tv_billing_change_address.getText().toString().matches(getString(R.string.add_new_address))) {
                    navigateToAddAddressFragment();
                } else {
                    navigateToSelectAddressFragment(Constants.BILLING);
                }
                break;
            case R.id.show_price:
                showPopUp();
                break;
            case R.id.price_ok:
                if (alertDialog != null)
                    alertDialog.dismiss();
                break;
            case R.id.btn_delete_gift_card:
                deleteGiftCard();
                break;
            case R.id.btn_delete_discount_code:
                deleteDiscountCode();
                break;
            default:
                break;
        }
    }

    private void deleteDiscountCode() {
        showLoadingDialog();
        RemoveDiscountCouponRequest removeDiscountCouponRequest =new RemoveDiscountCouponRequest(ObjectFactory.getInstance().getAppPreference(getActivity()).getGuid());
        WebserviceRequestManager.getInstance().enqueueRequest(ObjectFactory.getInstance().getRestClient(getActivity()).getApiService().removeDiscountCoupon(removeDiscountCouponRequest),this,WebserviceRequestManager.RequestType.DELETE_DISCOUNT_COUPON);

    }

    private void deleteGiftCard() {
        showLoadingDialog();
        RemoveGiftCardRequest removeGiftCardRequest = new RemoveGiftCardRequest(ObjectFactory.getInstance().getAppPreference(getActivity()).getGuid());
        String giftCardId = getGiftCardID();
        if (TextUtils.isEmpty(giftCardId)) {
            Utils.makeSnackBar(rl_checkout, R.string.error_occured, Snackbar.LENGTH_LONG);
        } else {
            removeGiftCardRequest.setRemoveGiftCardID(getGiftCardID());
            WebserviceRequestManager.getInstance().enqueueRequest(ObjectFactory.getInstance().getRestClient(getActivity()).getApiService().removeGiftCardCode(removeGiftCardRequest), this, WebserviceRequestManager.RequestType.DELETE_GIFT_CARD);
        }
    }

    private String getGiftCardID() {
        try {
            GiftCards[] giftCards = WebserviceRequestManager.getInstance().getGetCartResponse().getData().getOrderTotals().getGiftCards();
            if (giftCards.length > 0) {
                return giftCards[0].getId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void showPopUp() {
        PaymentDataHolder paymentDataHolder = Utils.getPaymentBreakdownList(WebserviceRequestManager.getInstance().getGetCartResponse());
        ArrayList<PaymentDataHolder.PriceBreakdown> list = paymentDataHolder.getPriceBreakdownArrayList();
        Log.d("list price",new Gson().toJson(list));
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.payment_holder, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(false);
        TextView card_price_value = dialogView.findViewById(R.id.card_price_value);
        TextView sub_total_value = dialogView.findViewById(R.id.sub_total_value);
        LinearLayout v = dialogView.findViewById(R.id.price_layout);
        btn_ok = dialogView.findViewById(R.id.price_ok);
        btn_ok.setOnClickListener(this);
        card_price_value.setText(paymentDataHolder.getCartTotal());
        sub_total_value.setText(paymentDataHolder.getSubTotal());
// ...Irrelevant code for customizing the buttons and title


        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                LayoutInflater inflater_holder = this.getLayoutInflater();
                View view = inflater.inflate(R.layout.payment_data_holder, null);
                TextView price_name = (TextView) view.findViewById(R.id.price_name);
                TextView price_value = (TextView) view.findViewById(R.id.price_value);
                price_name.setText(list.get(i).getName());
                price_value.setText(list.get(i).getValue());
                v.addView(view);
            }
        }

        alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

    private void giftCardApi() {
        if (!TextUtils.isEmpty(et_gift_coupon.getText().toString().trim())) {
            progressbarShowing();
            GiftCardRequest request = new GiftCardRequest();
            request.setApiToken(Constants.API_TOKEN);
            request.setGuid(ObjectFactory.getInstance().getAppPreference(getActivity()).getGuid());
            request.setGiftcardcouponcode(et_gift_coupon.getText().toString().trim());
//            ObjectFactory.getInstance().getHomeManager(getActivity()).applyGiftCard(request, 0);
            WebserviceRequestManager.getInstance().enqueueRequest(ObjectFactory.getInstance().getRestClient(getActivity()).getApiService().applyGiftCard(request),this,WebserviceRequestManager.RequestType.APPLY_GIFT_CARD);

        } else {

        }
    }


    private void applyCouponApi() {
        if (!TextUtils.isEmpty(et_discount_coupon.getText().toString().trim())) {
            progressbarShowing();
            ApplyDiscountCoupenRequest request = new ApplyDiscountCoupenRequest(ObjectFactory.getInstance().getAppPreference(getActivity()).getGuid());
            request.setDiscountcouponcode(et_discount_coupon.getText().toString().trim());
            /*Api call*/
//            ObjectFactory.getInstance().getHomeManager(getActivity()).applyDiscountCoupon(request);
            WebserviceRequestManager.getInstance().enqueueRequest(ObjectFactory.getInstance().getRestClient(getActivity()).getApiService().applyDiscountCoupon(request),this,WebserviceRequestManager.RequestType.APPLY_DISCOUNT_COUPON);
        }

    }

    /*TODO: payment process..*/

    public void startCheckoutApi() {

        if (WebserviceRequestManager.getInstance().getGetCartResponse()!=null) {
            progressbarShowing();
            StartCheckoutRequest request = new StartCheckoutRequest();
            request.setGuid(ObjectFactory.getInstance().getAppPreference(getActivity()).getGuid());
            request.setApiToken(Constants.API_TOKEN);

            List<CheckoutAttributeCheckout> checkouts = new ArrayList<>();
            CheckoutAttributeCheckout checkout = new CheckoutAttributeCheckout();
            checkout.setName(getString(R.string.checkout_attribute_1));
//            checkout.setValue(id.toString());
            checkout.setValue(getCheckoutAttribute1Id());
            checkouts.add(checkout);

//            CheckoutAttributeCheckout checkout2 = new CheckoutAttributeCheckout();
//            checkout2.setName("checkout_attribute_2");
//            checkout2.setValue("0.500");
//            checkouts.add(checkout2);

            CheckoutAttributeCheckout checkout3 = new CheckoutAttributeCheckout();
            checkout3.setName(getString(R.string.checkout_attribute_5));
            checkout3.setValue("5");
            checkouts.add(checkout3);

            CheckoutAttributeCheckout[] attributeCheckouts = checkouts.toArray(new CheckoutAttributeCheckout[checkouts.size()]);
            request.setCheckoutAttributes(attributeCheckouts);

            List<ItemQuantityCheckout> quantityCheckouts = new ArrayList<>();
            for (int i = 0; i < WebserviceRequestManager.getInstance().getGetCartResponse().getData().getCart().getItems().size(); i++) {
                ItemQuantityCheckout quantityCheckout = new ItemQuantityCheckout();
                quantityCheckout.setName(getString(R.string.item_quantity) + WebserviceRequestManager.getInstance().getGetCartResponse().getData().getCart().getItems().get(i).getId());
                quantityCheckout.setValue(WebserviceRequestManager.getInstance().getGetCartResponse().getData().getCart().getItems().get(i).getQuantity());

                quantityCheckouts.add(quantityCheckout);
            }
            ItemQuantityCheckout[] quantityCheckouts1 = quantityCheckouts.toArray(new ItemQuantityCheckout[quantityCheckouts.size()]);
            ObjectFactory.getInstance().getPaymentManager(getActivity()).setItemQuantityList(quantityCheckouts);
            request.setItemQuantity(quantityCheckouts1);
            String json = new Gson().toJson(request);
            ObjectFactory.getInstance().getPaymentManager(getActivity()).startCheckout(json);
        } else {
            Utils.makeSnackBar(rl_checkout, R.string.error_occured, Snackbar.LENGTH_LONG);
        }

    }

    private void navigateToGatewaySelectionFragment() {
        ObjectFactory.getInstance().getPaymentManager(getActivity()).setTotalAmount(WebserviceRequestManager.getInstance().getGetCartResponse().getData().getOrderTotals().getOrderTotal());
        Fragment fragment = new PaymentSelectionFragment();
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).addFragment(fragment);
        }
    }

    private void navigateToGatewaySelection() {
        ArrayList<Addresses> arrayList = ObjectFactory.getInstance().getProfileManager(getActivity()).getAddressList();
        if (arrayList.size() > 0) {
            startCheckoutApi();
        }
        else {
            Utils.makeSnackBar(rootView,getString(R.string.please_add_address),Snackbar.LENGTH_SHORT);
        }

    }

    private void navigateToSelectAddressFragment(String addressType) {
        Fragment fragment = new SelectAddressFragment();
        Bundle bundle = new Bundle();
        bundle.putString("AddressType", addressType);
        fragment.setArguments(bundle);
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).addFragment(fragment);
        }
    }

    private void progressbarShowing() {
        showLoadingDialog();
    }

    private void navigateToAddAddressFragment() {
        Fragment fragment = new AddNewAddressFragment();
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).addFragment(fragment);
        }
    }

    public String getCheckoutAttribute1Id() { // TODO change this to dynamic.
        CheckoutAttributes[] checkoutAttributes =tempCartResponse.getData().getCart().getCheckoutAttributes();
        for (CheckoutAttributes attributes :checkoutAttributes){
            if (attributes.getName().equalsIgnoreCase("Gift wrapping")){
               Values[] values= attributes.getValues();
               for (Values value:values){
                   if (value.getIsPreSelected()){
                       return value.getId();
                   }
               }
            }
        }
        return "0";
    }

    @Override
    public void onSuccess(Object result, WebserviceRequestManager.RequestType requestType) {
        if (getActivity() == null) {
            dismissLoadingDialog();
            return;
        }
        switch (requestType) {
            case APPLY_DISCOUNT_COUPON:
                if (result instanceof GetCartResponse) {
                    try{
                        Utils.makeSnackBar(rl_checkout, ((GetCartResponse) result).getData().getCart().getDiscountBox().getMessage(), Snackbar.LENGTH_LONG);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    updateViews();
                }
                break;
            case APPLY_GIFT_CARD:
                if (result instanceof GetCartResponse) {
                    try{
                        Utils.makeSnackBar(rl_checkout, ((GetCartResponse) result).getData().getCart().getGiftCardBox().getMessage(), Snackbar.LENGTH_LONG);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    updateViews();
                }
                break;
            case DELETE_DISCOUNT_COUPON:
                if (result instanceof GetCartResponse) {
                    updateViews();
                }
                break;
            case DELETE_GIFT_CARD:
                if (result instanceof GetCartResponse) {
                    updateViews();
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
        Utils.makeSnackBar(rootView, R.string.server_error, Snackbar.LENGTH_LONG);
    }

    @Override
    public void onCancel(Object result) {
        dismissLoadingDialog();
    }

}
