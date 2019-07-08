package com.mobtecnica.wafiapps.fragments.wafi_main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.activity.MainActivity;
import com.mobtecnica.wafiapps.adapters.CartListingRecyclerViewAdapter;
import com.mobtecnica.wafiapps.listeners.OnWebserviceCallback;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.manager.WebserviceRequestManager;
import com.mobtecnica.wafiapps.model.cart.getCart.GetCart;
import com.mobtecnica.wafiapps.model.cart.getCart.getCartResponse.CheckoutAttributes;
import com.mobtecnica.wafiapps.model.cart.getCart.getCartResponse.GetCartResponse;
import com.mobtecnica.wafiapps.model.cart.getCart.getCartResponse.Items;
import com.mobtecnica.wafiapps.model.cart.getCart.getCartResponse.Values;
import com.mobtecnica.wafiapps.model.cart.removeFromCart.RemoveFromCartRequest;
import com.mobtecnica.wafiapps.model.cart.updateCart.changeQuantity.ChangeQuantityRequest;
import com.mobtecnica.wafiapps.utils.BaseFragment;
import com.mobtecnica.wafiapps.utils.Utils;


/**
 * Created by SIby on 15-02-2017.
 */

/**
 * manages cart , delete product from cart etc
 */

public class CartFragment extends BaseFragment implements View.OnClickListener, OnWebserviceCallback, CartListingRecyclerViewAdapter.CartItemClickListener {
    private View rootView;
    private CartListingRecyclerViewAdapter adapter;
    private RecyclerView rv_cart_items;
    private TextView tv_total_price;
    private TextView check_out;
    private View empty_view;
    private LinearLayout card_layout;
    private View ll_bottom;

    public CartFragment() {
        setButtonType(Utils.BUTTON_TYPE.EMPTY);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_cart, container, false);
        initializeFields();
        onClickListners();
        return rootView;
    }

    private void onClickListners() {
        check_out.setOnClickListener(this);
        empty_view.findViewById(R.id.btn_start_shopping).setOnClickListener(this);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        apiCall();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebserviceRequestManager.getInstance().setGetCartResponse(null);
    }

    private void apiCall() {
        GetCart getCartRequest = new GetCart(ObjectFactory.getInstance().getAppPreference(getActivity()).getGuid());
        showLoadingDialog();
        WebserviceRequestManager.getInstance().enqueueRequest(ObjectFactory.getInstance().getRestClient(getActivity()).getApiService().getCart(getCartRequest), this, WebserviceRequestManager.RequestType.CART_LIST);
    }

    private void initializeFields() {
        setTitle(getResources().getString(R.string.cart));
        rv_cart_items = rootView.findViewById(R.id.rv_cart);
        check_out = rootView.findViewById(R.id.check_out);
        tv_total_price = rootView.findViewById(R.id.tv_total_price);
        empty_view = rootView.findViewById(R.id.empty_view);
        card_layout = rootView.findViewById(R.id.card_layout);
        ll_bottom = rootView.findViewById(R.id.ll_bottom);

        LinearLayoutManager verticalLayoutmanager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_cart_items.setLayoutManager(verticalLayoutmanager);
        adapter = new CartListingRecyclerViewAdapter(getActivity());
        rv_cart_items.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.check_out:
                if (validateSelection(WebserviceRequestManager.getInstance().getGetCartResponse())) {
                    navigateToCheckoutFragment();
                } else {
                    Utils.makeSnackBar(rootView, R.string.select_a_field, Snackbar.LENGTH_LONG);
                    if (card_layout != null) {
                        card_layout.getParent().requestChildFocus(card_layout, card_layout);
                    }
                }
                break;
            case R.id.btn_start_shopping:
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).navigateToViewPagerFragment();
                }
                break;
            default:
                break;
        }
    }

    private void navigateToCheckoutFragment() {
        Fragment fragment = new CheckOutFragment();
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).addFragment(fragment);
        }

    }


    @Override
    public void onSuccess(Object result, WebserviceRequestManager.RequestType requestType) {
        if (getActivity() == null) {
            dismissLoadingDialog();
            return;
        }
        switch (requestType) {
            case CART_LIST:
                if (result instanceof GetCartResponse) {
                    adapter.updateData(((GetCartResponse) result).getData().getCart().getItems());
                    updateViews((GetCartResponse) result);
                }
                break;
            case UPDATE_ITEM_QUANTITY:
                if (result instanceof GetCartResponse) {
                    updateCartCount(((GetCartResponse) result).getData().getCartItemsCount());
                    adapter.clearData();
                    adapter.updateData(((GetCartResponse) result).getData().getCart().getItems());
                    updateViews((GetCartResponse) result);
                }
                break;
            case DELETE_ITEM:
                if (result instanceof GetCartResponse) {
                    updateCartCount(((GetCartResponse) result).getData().getCartItemsCount());
                    adapter.clearData();
                    adapter.updateData(((GetCartResponse) result).getData().getCart().getItems());
                    updateViews((GetCartResponse) result);
                }
                break;
        }
        dismissLoadingDialog();
    }

    private void updateCartCount(int cartItemsCount) {
        ((MainActivity) getActivity()).setCartCount(cartItemsCount);
    }

    private void updateViews(GetCartResponse cartResponse) {
        if (cartResponse != null) {
            ObjectFactory.getInstance().getHomeManager(getActivity()).setCartResponse(cartResponse); //TODO change this
            showSubTotal(Utils.getCheckoutPrice(cartResponse));
            try {
                if (cartResponse.getData().getCart().getItems().size() == 0 && empty_view != null) {
                    empty_view.setVisibility(View.VISIBLE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (cartResponse.getData().getCart().getItems().size() > 0 && ll_bottom != null) {
                    ll_bottom.setVisibility(View.VISIBLE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                displayCheckOutAttribute(cartResponse);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void updateViewsTemp() {
        updateViews(WebserviceRequestManager.getInstance().getGetCartResponse());
    }

    private void showSubTotal(String sub_total) {
        if (tv_total_price != null) {
            tv_total_price.setText(sub_total);
        }
    }

    private void changeQuantity(Items item, int quantity) {
        showLoadingDialog();
        ChangeQuantityRequest request = new ChangeQuantityRequest(ObjectFactory.getInstance().getAppPreference(getContext()).getGuid(), item.getId(), quantity);
        WebserviceRequestManager.getInstance().enqueueRequest(ObjectFactory.getInstance().getRestClient(getActivity()).getApiService().changeQuantity(request), this, WebserviceRequestManager.RequestType.UPDATE_ITEM_QUANTITY);
    }

    private void deleteItem(Items item) {
        showLoadingDialog();
        RemoveFromCartRequest request = new RemoveFromCartRequest(ObjectFactory.getInstance().getAppPreference(getContext()).getGuid(), item.getId());
        WebserviceRequestManager.getInstance().enqueueRequest(ObjectFactory.getInstance().getRestClient(getActivity()).getApiService().deleteProducts(request), this, WebserviceRequestManager.RequestType.DELETE_ITEM);

    }

    @Override
    public void onFailure(String message) {
        if (getActivity() == null) {
            return;
        }
        dismissLoadingDialog();
        Utils.makeSnackBar(rootView, R.string.server_error, Snackbar.LENGTH_LONG);
    }

    @Override
    public void onCancel(Object result) {
        dismissLoadingDialog();
    }

    @Override
    public void onDeleteItem(Items item) {
        deleteItem(item);
    }

    @Override
    public void onItemClick(Items item) {
//        String id = item.getProductId();
//        ObjectFactory.getInstance().getAppPreference(getContext()).setProductId(id);
//        navigateToProductDetailFragment(item.getProductName());
    }

    private void navigateToProductDetailFragment(String productName) {
        ProductDetailedViewFragment fragment = new ProductDetailedViewFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean("isFromCart", true);
        fragment.setArguments(bundle);
        fragment.setTitle(productName);
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).addFragment(fragment);
        }
    }

    @Override
    public void onAddItemQuantity(Items item) {
        changeQuantity(item, item.getQuantity() + 1);
    }

    @Override
    public void onReduceItemQuantity(Items item) {
        changeQuantity(item, item.getQuantity() - 1);
    }


    private void displayCheckOutAttribute(GetCartResponse cartResponse) {

        CheckoutAttributes[] checkoutAttributes = cartResponse.getData().getCart().getCheckoutAttributes();
        int size = checkoutAttributes.length;
        String shipping = cartResponse.getData().getOrderTotals().getShipping();
        if (card_layout != null && card_layout.getChildCount() > 0) {
            card_layout.removeAllViewsInLayout();
        }
        ((View) card_layout.getParent()).setVisibility(View.VISIBLE);
        for (int i = 0; i < size; i++) {
            String isRequired = checkoutAttributes[i].getIsRequired();
            if (isRequired.equals("true")) {
                View v = getLayoutInflater().inflate(R.layout.special_charges_layout, null);
                TextView text_special_charges = v.findViewById(R.id.text_special_charges);
                RadioGroup rg = v.findViewById(R.id.rg_special_charges);
                rg.setTag(i);
//                rg.setOnCheckedChangeListener(onCheckedChangeListener);
                rg.setPadding(0, 10, 0, 0);
                text_special_charges.setText(checkoutAttributes[i].getName());
                card_layout.addView(v);
                Values[] values = checkoutAttributes[i].getValues();
                for (int j = 0; j < values.length; j++) {
                    String name = values[j].getName();
                    RadioButton rd_btn = new RadioButton(getContext());
                    rd_btn.setTag(values[j]);
                    rd_btn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
                    rd_btn.setTypeface(Utils.getTypefaceLatoRegular(getContext()));
                    if ((values[j].getPriceAdjustment()) != null) {
                        rd_btn.setText(name + " (" + values[j].getPriceAdjustment() + ")");
                    } else {
                        rd_btn.setText(name);
                    }
                    rd_btn.setTextColor(getResources().getColor(R.color.textColor));
                    rd_btn.setPadding(0, 0, 80, 0);
                    rg.addView(rd_btn);
                    rd_btn.setOnCheckedChangeListener(onCheckedChangeListener);
                    if (values[j].getIsPreSelected()) {
                        rg.check(rd_btn.getId());
                    }
                }
            }
        }
        if (!shipping.equals("BD 0.000")) {
            View v = getLayoutInflater().inflate(R.layout.special_charges_layout, null);
            TextView text_special_charges = v.findViewById(R.id.text_special_charges);
            RadioGroup rg = v.findViewById(R.id.rg_special_charges);
            rg.setTag(size);
            text_special_charges.setText(getResources().getString(R.string.shipping_charges));
            card_layout.addView(v);
            RadioButton rd_btn = new RadioButton(getContext());
            rd_btn.setTag(shipping);
            rd_btn.setTypeface(Utils.getTypefaceLatoRegular(getContext()));
            rd_btn.setText(shipping);
            rd_btn.setTextColor(getResources().getColor(R.color.textColor));
            rg.addView(rd_btn);
            rd_btn.setChecked(true);
            rd_btn.setOnCheckedChangeListener(onCheckedChangeListener);
        }
    }

    RadioButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            RadioButton rb = (RadioButton) buttonView;
            if (rb.getTag() instanceof Values) {
                Values values = (Values) rb.getTag();
                values.setIsPreSelected(rb.isChecked());
                showSubTotal(Utils.getCheckoutPrice(WebserviceRequestManager.getInstance().getGetCartResponse()));
            } else if (rb.getTag() instanceof String) {
                showSubTotal(Utils.getCheckoutPrice(WebserviceRequestManager.getInstance().getGetCartResponse()));

            }
        }
    };


    private boolean validateSelection(GetCartResponse cartResponse) {
        if (cartResponse != null) {
            if (cartResponse.getSuccess().equals("true")) {
                CheckoutAttributes[] checkoutAttributes = cartResponse.getData().getCart().getCheckoutAttributes();
                for (CheckoutAttributes attributes : checkoutAttributes) {
                    if (attributes.getIsRequired().equalsIgnoreCase("true")) {
                        boolean isSelected = false;
                        for (Values values : attributes.getValues()) {
                            if (values.getIsPreSelected()) {
                                isSelected = values.getIsPreSelected();
                                break;
                            }
                        }
                        if (!isSelected) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }
}
