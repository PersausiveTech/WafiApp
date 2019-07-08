package com.mobtecnica.wafiapps.fragments.wafi_main;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.activity.MainActivity;
import com.mobtecnica.wafiapps.adapters.MyOrderDetailsItemsAdapter;
import com.mobtecnica.wafiapps.listeners.OnWebserviceCallback;
import com.mobtecnica.wafiapps.manager.HomeManager;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.manager.WebserviceRequestManager;
import com.mobtecnica.wafiapps.model.orderDetails.Item;
import com.mobtecnica.wafiapps.model.orderDetails.OrderDetailsResponse;
import com.mobtecnica.wafiapps.model.orderHistory.ReOrderRequest;
import com.mobtecnica.wafiapps.model.orderHistory.ReOrderResponse;
import com.mobtecnica.wafiapps.utils.BaseFragment;
import com.mobtecnica.wafiapps.utils.Constants;
import com.mobtecnica.wafiapps.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderHistoryDetailsFragment extends BaseFragment implements View.OnClickListener, OnWebserviceCallback {

    View rootView;

    RecyclerView rv_items_my_order_details;
    MyOrderDetailsItemsAdapter myOrderDetailsItemsAdapter;

    TextView tv_order_id;
    TextView tv_order_date;
    TextView tv_order_total;

    AppCompatImageView iv_order_status;
    AppCompatImageView iv_payment_status;
    AppCompatImageView iv_shipment_status;

    TextView tv_order_status;
    TextView tv_payment_status;
    TextView tv_shipping_status;

    AppCompatButton btn_re_order;

    TextView tv_shipment_status;
    TextView tv_expected_delivery;
    TextView tv_shipment_method;
    TextView tv_shipment_charges;
    TextView tv_tracking_no;

    TextView tv_shipping_details_expandable;
    TextView tv_shipping_details_name;
    TextView tv_shipping_details_address;

    TextView tv_billing_details_expandable;
    TextView tv_billing_details_name;
    TextView tv_billing_details_address;

    TextView tv_items_expandable;

    TextView tv_coupon_code;
    TextView tv_gift_card;
    TextView tv_coupon_amount;
    TextView tv_gift_card_amount;

    CardView cv_shipping_details;
    CardView cv_billing_details;
    CardView cv_items;

    private boolean state_shipping_details_expanded = false;
    private boolean state_billing_details_expanded = false;
    private boolean state_items_expanded = false;

    String orderId = "";
    String apiToken = "";
    String guid = "";
    OrderDetailsResponse orderDetailsResponse;
    List<Item> items = new ArrayList<>();
    private View button_re_order;


    public OrderHistoryDetailsFragment() {
        // Required empty public constructor
        setButtonType(Utils.BUTTON_TYPE.EMPTY);
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().matches(HomeManager.BROADCAST_ORDER_DETAILS)) {
                dismissLoadingDialog();
                if (intent.getBooleanExtra(HomeManager.BROADCAST_ORDER_DETAILS_STATUS, false)) {
                    orderDetailsResponse = ObjectFactory.getInstance().getHomeManager(getActivity()).getOrderDetails();
                    if (orderDetailsResponse != null)
                        setData();
                    if (orderDetailsResponse.getData().getItems() != null)
                        items = orderDetailsResponse.getData().getItems();
                    myOrderDetailsItemsAdapter.updateItemsData(items);
                } else {
                    Utils.makeSnackBar(rootView,  R.string.error_try_again, Snackbar.LENGTH_LONG);
                }
            }
        }
    };

    private void setData() {
        tv_order_id.setText(orderDetailsResponse.getData().getId().toString());
        Calendar c = Calendar.getInstance();
        if (orderDetailsResponse.getData().getCreatedOn() != null)
            if (!orderDetailsResponse.getData().getCreatedOn().equalsIgnoreCase("")) {
                String time = orderDetailsResponse.getData().getCreatedOn().replace("/", "").replace("Date(", "").replace(")", "");
                c.setTimeInMillis(Long.parseLong(time));
            }
        tv_order_date.setText(new SimpleDateFormat("dd MMM yyyy").format(c.getTimeInMillis()));
        tv_order_total.setText(orderDetailsResponse.getData().getOrderTotal());

        tv_order_status.setText(orderDetailsResponse.getData().getOrderStatus());
        tv_payment_status.setText(orderDetailsResponse.getData().getPaymentMethodStatus());
        tv_shipping_status.setText(orderDetailsResponse.getData().getShippingStatus());


        if (orderDetailsResponse.getData().getOrderStatus().equalsIgnoreCase(Constants.ORDER_STATUS_CANCELLED)) {
            iv_order_status.setImageResource(R.drawable.ic_cancel_red);
        } else if (orderDetailsResponse.getData().getOrderStatus().equalsIgnoreCase(Constants.ORDER_STATUS_PROCESSING_COMPLETE)) {
            iv_order_status.setImageResource(R.drawable.ic_check_circle_green);
        } else {
            iv_order_status.setImageResource(R.drawable.ic_watch_later_yellow);
        }

        if (orderDetailsResponse.getData().getPaymentMethodStatus().equalsIgnoreCase(Constants.PAYMENT_STATUS_PAID)) {
            iv_payment_status.setImageResource(R.drawable.ic_check_circle_green);
        } else if (orderDetailsResponse.getData().getPaymentMethodStatus().equalsIgnoreCase(Constants.PAYMENT_STATUS_PARTIALLY_REFUNDED)) {
            iv_payment_status.setImageResource(R.drawable.ic_watch_later_yellow);
        } else if (orderDetailsResponse.getData().getPaymentMethodStatus().equalsIgnoreCase(Constants.PAYMENT_STATUS_REFUNDED)) {
            iv_payment_status.setImageResource(R.drawable.ic_check_circle_green);
        } else if (orderDetailsResponse.getData().getPaymentMethodStatus().equalsIgnoreCase(Constants.PAYMENT_STATUS_VOIDED)) {
            iv_payment_status.setImageResource(R.drawable.ic_cancel_red);
        } else {
            iv_payment_status.setImageResource(R.drawable.ic_watch_later_yellow);
        }

        if (orderDetailsResponse.getData().getShippingStatus().equalsIgnoreCase(Constants.SHIPPING_STATUS_SHIPPING_NOT_REQUIRED)) {
            iv_shipment_status.setImageResource(R.drawable.ic_cancel_red);
        } else if (orderDetailsResponse.getData().getShippingStatus().equalsIgnoreCase(Constants.SHIPPING_STATUS_PARTIALLY_SHIPPED)) {
            iv_shipment_status.setImageResource(R.drawable.ic_watch_later_yellow);
        } else if (orderDetailsResponse.getData().getShippingStatus().equalsIgnoreCase(Constants.SHIPPING_STATUS_DELIVERED)) {
            iv_shipment_status.setImageResource(R.drawable.ic_check_circle_green);
        } else {
            iv_shipment_status.setImageResource(R.drawable.ic_watch_later_yellow);
        }

        tv_shipment_status.setText(orderDetailsResponse.getData().getShippingStatus().toString());
//        tv_expected_delivery.setText(orderDetailsResponse.getData().getId().toString());
        tv_shipment_method.setText(orderDetailsResponse.getData().getShippingMethod().toString());
//        tv_shipment_charges.setText(orderDetailsResponse.getData().getId().toString());
//        tv_tracking_no.setText(orderDetailsResponse.getData().getId().toString());

        tv_shipping_details_name.setText(orderDetailsResponse.getData().getShippingAddress().getFirstName()+" "+orderDetailsResponse.getData().getShippingAddress().getLastName());
        tv_shipping_details_address.setText(Html.fromHtml(orderDetailsResponse.getData().getShippingAddress().getFormattedCustomAddressAttributes()));

        tv_billing_details_name.setText(orderDetailsResponse.getData().getBillingAddress().getFirstName()+" "+orderDetailsResponse.getData().getBillingAddress().getLastName());
        tv_billing_details_address.setText(Html.fromHtml(orderDetailsResponse.getData().getBillingAddress().getFormattedCustomAddressAttributes()));

//        tv_coupon_code.setText(orderDetailsResponse.getData().getId().toString());
//        tv_gift_card.setText(orderDetailsResponse.getData().getId().toString());
//        tv_coupon_amount.setText(orderDetailsResponse.getData().getOrderTotalDiscount().toString());
//        tv_gift_card_amount.setText(orderDetailsResponse.getData().getId().toString());

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_order_history_details, container, false);
        IntentFilter intent = new IntentFilter();
        intent.addAction(HomeManager.BROADCAST_ORDER_DETAILS);
        addBroadcastListener(receiver, intent);
        initViews();
        fetchData();
        viewClickListeners();

        return rootView;
    }

    private void initViews() {
        setTitle(getString(R.string.order_details));
        apiToken = Constants.API_TOKEN;
        guid = ObjectFactory.getInstance().getAppPreference(getActivity()).getGuid();
        orderId = this.getArguments().getString(Constants.ORDER_ID);

        tv_order_id = (TextView) rootView.findViewById(R.id.tv_order_id);
        tv_order_date = (TextView) rootView.findViewById(R.id.tv_order_date);
        tv_order_total = (TextView) rootView.findViewById(R.id.tv_order_total);

        iv_order_status = (AppCompatImageView) rootView.findViewById(R.id.iv_order_status);
        iv_payment_status = (AppCompatImageView) rootView.findViewById(R.id.iv_payment_status);
        iv_shipment_status = (AppCompatImageView) rootView.findViewById(R.id.iv_shipment_status);

        tv_order_status = (TextView) rootView.findViewById(R.id.tv_order_status);
        tv_payment_status = (TextView) rootView.findViewById(R.id.tv_payment_status);
        tv_shipping_status = (TextView) rootView.findViewById(R.id.tv_shipping_status);

        btn_re_order = (AppCompatButton) rootView.findViewById(R.id.btn_re_order);

        tv_shipment_status = (TextView) rootView.findViewById(R.id.tv_shipment_status);
        tv_expected_delivery = (TextView) rootView.findViewById(R.id.tv_expected_delivery);
        tv_shipment_method = (TextView) rootView.findViewById(R.id.tv_shipment_method);
        tv_shipment_charges = (TextView) rootView.findViewById(R.id.tv_shipment_charges);
        tv_tracking_no = (TextView) rootView.findViewById(R.id.tv_tracking_no);

        tv_shipping_details_expandable = (TextView) rootView.findViewById(R.id.tv_shipping_details_expandable);
        tv_shipping_details_name = (TextView) rootView.findViewById(R.id.tv_shipping_details_name);
        tv_shipping_details_address = (TextView) rootView.findViewById(R.id.tv_shipping_details_address);

        tv_billing_details_expandable = (TextView) rootView.findViewById(R.id.tv_billing_details_expandable);
        tv_billing_details_name = (TextView) rootView.findViewById(R.id.tv_billing_details_name);
        tv_billing_details_address = (TextView) rootView.findViewById(R.id.tv_billing_details_address);

        tv_items_expandable = (TextView) rootView.findViewById(R.id.tv_items_expandable);

        tv_coupon_code = (TextView) rootView.findViewById(R.id.tv_coupon_code);
        tv_gift_card = (TextView) rootView.findViewById(R.id.tv_gift_card);
        tv_coupon_amount = (TextView) rootView.findViewById(R.id.tv_coupon_amount);
        tv_gift_card_amount = (TextView) rootView.findViewById(R.id.tv_gift_card_amount);

        cv_shipping_details = (CardView) rootView.findViewById(R.id.cv_shipping_details);
        cv_billing_details = (CardView) rootView.findViewById(R.id.cv_billing_details);
        cv_items = (CardView) rootView.findViewById(R.id.cv_items);

        button_re_order = rootView.findViewById(R.id.button_re_order);


        rv_items_my_order_details = (RecyclerView) rootView.findViewById(R.id.rv_items_my_order_details);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_items_my_order_details.setLayoutManager(linearLayoutManager);
        myOrderDetailsItemsAdapter = new MyOrderDetailsItemsAdapter(getActivity(), items);
        rv_items_my_order_details.setAdapter(myOrderDetailsItemsAdapter);

    }

    private void viewClickListeners() {
        btn_re_order.setOnClickListener(this);
        tv_shipping_details_expandable.setOnClickListener(this);
        tv_billing_details_expandable.setOnClickListener(this);
        tv_items_expandable.setOnClickListener(this);
        button_re_order.setOnClickListener(this);

    }

    private void fetchData() {
        showLoadingDialog();
        ObjectFactory.getInstance().getHomeManager(getActivity()).apiGetOrderHistoryDetails(apiToken, guid, orderId);
    }

    private void reOrder() {
        showLoadingDialog();
        ReOrderRequest reOrderRequest =new ReOrderRequest(ObjectFactory.getInstance().getAppPreference(getActivity()).getGuid(),orderId);
        WebserviceRequestManager.getInstance().enqueueRequest(ObjectFactory.getInstance().getRestClient(getActivity()).getApiService().reOrder(reOrderRequest), this, WebserviceRequestManager.RequestType.RE_ORDER);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_re_order:
                reOrder();
                break;
            case R.id.tv_shipping_details_expandable:
                if (!state_shipping_details_expanded) {
                    cv_shipping_details.setVisibility(View.VISIBLE);
                    tv_shipping_details_expandable.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_up_red, 0);
                    state_shipping_details_expanded = true;
                } else {
                    cv_shipping_details.setVisibility(View.GONE);
                    tv_shipping_details_expandable.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_down_red, 0);
                    state_shipping_details_expanded = false;
                }

                break;
            case R.id.tv_billing_details_expandable:
                if (!state_billing_details_expanded) {
                    cv_billing_details.setVisibility(View.VISIBLE);
                    tv_billing_details_expandable.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_up_red, 0);
                    state_billing_details_expanded = true;
                } else {
                    cv_billing_details.setVisibility(View.GONE);
                    tv_billing_details_expandable.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_down_red, 0);
                    state_billing_details_expanded = false;
                }
                break;
            case R.id.tv_items_expandable:
                if (!state_items_expanded) {
                    cv_items.setVisibility(View.VISIBLE);
                    tv_items_expandable.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_up_red, 0);
                    state_items_expanded = true;
                } else {
                    cv_items.setVisibility(View.GONE);
                    tv_items_expandable.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_down_red, 0);
                    state_items_expanded = false;
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onSuccess(Object result, WebserviceRequestManager.RequestType requestType) {
        if (getActivity() instanceof MainActivity) {
            dismissLoadingDialog();
            switch (requestType) {
                case RE_ORDER:
                    if (result instanceof ReOrderResponse) {
                        if (((ReOrderResponse) result).isSuccess()) {
                            if (((ReOrderResponse) result).getData() != null && ((ReOrderResponse) result).getData().equals("Items successfully added to cart.")) {
                                navigateToCart();
                            } else {
                                Utils.changeSnackBarFont(Snackbar.make(button_re_order, getResources().getString(R.string.error_occured), Snackbar.LENGTH_LONG));
                            }
                        } else {
                            Utils.changeSnackBarFont(Snackbar.make(button_re_order, getResources().getString(R.string.error_occured), Snackbar.LENGTH_LONG));
                        }
                    }
                    break;
            }
        }
    }
    private void navigateToCart() {
        Fragment fragment = new CartFragment();
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).addFragment(fragment);
        }
    }
    @Override
    public void onFailure(String message) {
        if (getActivity() == null) {
            dismissLoadingDialog();
            return;
        }
        dismissLoadingDialog();
        Utils.changeSnackBarFont(Snackbar.make(button_re_order, getResources().getString(R.string.error_occured), Snackbar.LENGTH_LONG));
    }

    @Override
    public void onCancel(Object result) {
        dismissLoadingDialog();
    }
}
