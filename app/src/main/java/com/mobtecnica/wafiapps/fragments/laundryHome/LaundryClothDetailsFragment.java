package com.mobtecnica.wafiapps.fragments.laundryHome;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.activity.LaundryActivity;
import com.mobtecnica.wafiapps.manager.LaundryManager;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.model.LaundryModel.getlaundryItems.ListLaundryMenu;
import com.mobtecnica.wafiapps.model.LaundryModel.laundryAddToCart.LaundryAddToCartModel;
import com.mobtecnica.wafiapps.model.LaundryModel.laundryAddToCart.LaundryAddtoCartRequest;
import com.mobtecnica.wafiapps.utils.BaseFragment;
import com.mobtecnica.wafiapps.utils.Constants;
import com.mobtecnica.wafiapps.utils.Utils;

/**
 * Created by SIby on 04-04-2017.
 */

public class LaundryClothDetailsFragment extends BaseFragment implements View.OnClickListener {
    ListLaundryMenu listLaundryMenu = null;
    String id = "";
    String price = "";
    public Dialog dialog;
    List<RadioButton> buttons;
    LinearLayout layout;
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().matches(LaundryManager.BROADCAST_LAUNDRY_ADD_TO_CART)) {
                if (intent.getBooleanExtra(LaundryManager.BROADCAST_LAUNDRY_ADD_TO_CART_STATUS, false)) {
                    Utils.makeSnackBar(layout, intent.getStringExtra(LaundryManager.BROADCAST_LAUNDRY_ADD_TO_CART_MESSAGE), Snackbar.LENGTH_LONG);
                    progressDialogDismiss();
                    if(getActivity() instanceof LaundryActivity){
                        ((LaundryActivity)getActivity()).getLaundryCart();
                    }
                    getActivity().onBackPressed();
                } else {
                    progressDialogDismiss();
                    Utils.makeSnackBar(layout, R.string.error_occured, Snackbar.LENGTH_LONG);
                }
            }
        }
    };
    private View rootView;
    private ImageView iv_cloth_image;
    private LinearLayout ll_washing_type;
    private TextView tv_cloth_name;
    private AppCompatButton btn_add_to_cart_laundry;
    private TextView et_quantity;
    private ImageView iv_plus;
    private ImageView iv_minus;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_cloth_details, container, false);
        Bundle bundle = this.getArguments();
        listLaundryMenu = bundle.getParcelable("DETAILS");
        initializeViews();
        setValues();
        return rootView;
    }

    private void setValues() {
        Glide.with(getActivity())
                .load(listLaundryMenu.getImageUrl()).into(iv_cloth_image);
        tv_cloth_name.setText(listLaundryMenu.getDescription());
        et_quantity.setText("1");
        for (int i = 0; i < listLaundryMenu.getListItemServices().length; i++) {
            View view_deals = getActivity().getLayoutInflater().inflate(R.layout.ll_washing_type, null);
            TextView tv_cleaning_type = (TextView) view_deals.findViewById(R.id.tv_cleaning_type);
            final RadioButton radioButton = (RadioButton) view_deals.findViewById(R.id.dry_clean);
            tv_cleaning_type.setText(listLaundryMenu.getListItemServices()[i].getServiceType());
            radioButton.setText(listLaundryMenu.getListItemServices()[i].getPrice());
            radioButton.setTag(i);
            radioButton.setTypeface(Utils.getTypefaceLatoRegular(getContext()));
            radioButton.setChecked(false);
            final int pos = i;
            buttons.add(radioButton);

            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    id = listLaundryMenu.getListItemServices()[(int) radioButton.getTag()].getServiceID();
                    price = listLaundryMenu.getListItemServices()[(int) radioButton.getTag()].getPrice();
                    for (int j = 0; j < buttons.size(); j++) {
                        if (j == (int) radioButton.getTag()) {

                        } else {
                            buttons.get(j).setChecked(false);
                        }
                    }

                }
            });
            ll_washing_type.addView(view_deals);
        }
    }

    private void initializeViews() {

        IntentFilter intent = new IntentFilter();
        intent.addAction(LaundryManager.BROADCAST_LAUNDRY_ADD_TO_CART);
        addBroadcastListener(receiver, intent);

        iv_cloth_image = (ImageView) rootView.findViewById(R.id.iv_cloth_image);
        tv_cloth_name = (TextView) rootView.findViewById(R.id.tv_cloth_name);
        btn_add_to_cart_laundry = (AppCompatButton) rootView.findViewById(R.id.btn_add_to_cart_laundry);
        ll_washing_type = (LinearLayout) rootView.findViewById(R.id.ll_washing_type);
        et_quantity = (TextView) rootView.findViewById(R.id.et_quantity);
        iv_plus = (ImageView) rootView.findViewById(R.id.iv_plus);
        iv_minus = (ImageView) rootView.findViewById(R.id.iv_minus);
        layout = (LinearLayout) rootView.findViewById(R.id.ll_cloth_details);
        iv_plus.setOnClickListener(this);
        iv_minus.setOnClickListener(this);
        btn_add_to_cart_laundry.setOnClickListener(this);
        buttons = new ArrayList<>();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_to_cart_laundry:
                progressDialogshow();
                addToCartApiCall();
                break;
            case R.id.iv_plus:
                addQuantity();
                break;
            case R.id.iv_minus:
                reduceQuantity();
                break;
            default:
                break;
        }
    }

    private void reduceQuantity() {
        String st = et_quantity.getText().toString().trim();
        int num = Integer.parseInt(st);
        if (num > 1)
            num = num - 1;
        et_quantity.setText(String.valueOf(num));
    }

    private void addQuantity() {
        String st = et_quantity.getText().toString().trim();
        int num = Integer.parseInt(st);
        if (num >= 0)
            num = num + 1;
        et_quantity.setText(String.valueOf(num));
    }

    private void addToCartApiCall() {
        if (!TextUtils.isEmpty(id)) {
            progressDialogshow();
            LaundryAddtoCartRequest laundryAddtoCartRequest = new LaundryAddtoCartRequest();
            laundryAddtoCartRequest.setApiToken(Constants.API_TOKEN_LAUNDRY);
            laundryAddtoCartRequest.setEmailID(ObjectFactory.getInstance().getAppPreference(getContext()).getEmailId());
            LaundryAddToCartModel laundryAddToCartModel = new LaundryAddToCartModel();
            laundryAddToCartModel.setItemId(listLaundryMenu.getItemID());
            laundryAddToCartModel.setLaundryTypeID(listLaundryMenu.getLaundryTypeID());
            laundryAddToCartModel.setMenuID(listLaundryMenu.getMenuID());
            laundryAddToCartModel.setPrice(price);
            laundryAddToCartModel.setQuantity(et_quantity.getText().toString().trim());
            laundryAddToCartModel.setServiceID(id);
            laundryAddtoCartRequest.setModel(laundryAddToCartModel);
            ObjectFactory.getInstance().getLaundryManager(getActivity()).addToLaundryCart(laundryAddtoCartRequest);
        } else {
            progressDialogDismiss();
            Utils.makeSnackBar(layout, R.string.select_option, Snackbar.LENGTH_LONG);
        }
    }

    private void progressbarShowing() {
        Intent intent = new Intent(Constants.Login.BROADCAST_PROGRESS_WHEEL);
        intent.putExtra(Constants.Login.BROADCAST_ISDISPLAY_PROGRESS_WHEEL, true);
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
    }

    public void progressDialogshow() {
        if (dialog == null) {
            dialog = new Dialog(getContext());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_progressbar);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(false);
        }
        dialog.show();
    }

    public void progressDialogDismiss() {
        if (dialog != null)
            dialog.dismiss();
    }
}
