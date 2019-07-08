package com.mobtecnica.wafiapps.fragments.foodHome;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.model.wafiEats.checkout.CartItem;
import com.mobtecnica.wafiapps.model.wafiEats.checkout.CustDetails;
import com.mobtecnica.wafiapps.model.wafiEats.checkout.Model;
import com.mobtecnica.wafiapps.model.wafiEats.checkout.PlaceOrderWafieatsRequest;
import com.mobtecnica.wafiapps.utils.BaseFragment;
import com.mobtecnica.wafiapps.utils.Constants;

/**
 * Created by siby on 23-Jun-17.
 */

public class WafieatsCheckoutFragment extends BaseFragment implements View.OnClickListener {
    private View rootView;
    private EditText et_name;
    private EditText et_email;
    private EditText et_mobile_number;
    private EditText et_road;
    private EditText et_building_num;
    private EditText et_flat_number;
    private EditText et_block_num;
    private EditText et_land_mark;
    private TextView tv_subtotal;
    private TextView tv_amount_payable;
    private AppCompatButton btn_place_order;
    private TextView tv_delivery_charge;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_checkout_wafieats, container, false);
        initViews();
        btnClickListners();
        return rootView;
    }

    private void btnClickListners() {
        btn_place_order.setOnClickListener(this);
    }

    private void initViews() {
        et_name = (EditText) rootView.findViewById(R.id.et_name);
        et_email = (EditText) rootView.findViewById(R.id.et_email);
        et_mobile_number = (EditText) rootView.findViewById(R.id.et_mobile_number);
        et_road = (EditText) rootView.findViewById(R.id.et_road);
        et_building_num = (EditText) rootView.findViewById(R.id.et_building_num);
        et_flat_number = (EditText) rootView.findViewById(R.id.et_flat_number);
        et_block_num = (EditText) rootView.findViewById(R.id.et_block_num);
        et_land_mark = (EditText) rootView.findViewById(R.id.et_land_mark);
        tv_subtotal = (TextView) rootView.findViewById(R.id.tv_subtotal);
        tv_delivery_charge = (TextView) rootView.findViewById(R.id.tv_delivery_charge);
        tv_amount_payable = (TextView) rootView.findViewById(R.id.tv_amount_payable);
        btn_place_order = (AppCompatButton) rootView.findViewById(R.id.btn_place_order);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_place_order:
                placeOrderCheckout();
                break;
            default:
                break;
        }
    }

    private void placeOrderCheckout() {
        PlaceOrderWafieatsRequest request = new PlaceOrderWafieatsRequest();
        request.setApiToken(Constants.API_TOKEN_EATS);
        request.setCustGuid(ObjectFactory.getInstance().getAppPreference(getActivity()).getGuid());
        request.setEmailID(ObjectFactory.getInstance().getAppPreference(getActivity()).getEmailId());

        CustDetails custDetails = new CustDetails();
        custDetails.setAddress1("");
        custDetails.setBlock(et_block_num.getText().toString().trim());
        custDetails.setBuilding(et_building_num.getText().toString());
        custDetails.setCountryId("Country id");
        custDetails.setFirstName(et_name.getText().toString().trim());
        custDetails.setFlatVilla(et_flat_number.getText().toString().trim());
        custDetails.setEmail(et_email.getText().toString().trim());
        custDetails.setRoad(et_road.getText().toString().trim());
        custDetails.setId("id");
        custDetails.setSelectedAddressId("selected address id");
        custDetails.setLandmark("land mark");

        request.setCustDetails(custDetails);

        Model model = new Model();
        model.setDeliveryCharge("");
        model.setDiscount("");
        model.setLocationID("");
        model.setPromoDiscount("");
        model.setShopID("");
        model.setSpecialrequest("");
        model.setTotal("");
        request.setModel(model);


        List<CartItem> cartItems = new ArrayList<>();

        CartItem cartItem = new CartItem();
        request.setCartItems(cartItems);


    }
}
