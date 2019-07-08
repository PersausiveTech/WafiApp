package com.mobtecnica.wafiapps.fragments.laundryHome;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.activity.LaundryActivity;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.model.LaundryModel.laundryHome.LaundryHomeImageRequest;
import com.mobtecnica.wafiapps.utils.BaseFragment;
import com.mobtecnica.wafiapps.utils.Constants;
import com.mobtecnica.wafiapps.utils.Utils;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by SIby on 03-04-2017.
 */

public class LaundrySelectServiceFragment extends BaseFragment implements View.OnClickListener {
    View view;
    private ImageView iv_home_background;
    private RelativeLayout ll_premium_service;
    private RelativeLayout ll_standard_service;
    private AppCompatButton btn_pricelist;
    private AppCompatButton btn_premium;
    private AppCompatButton btn_standard;
    private ImageView back_button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_select_laundry, container, false);
        intializeViews();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
//        apiCall();
        // setImage();
        onClickListner();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    private void onClickListner() {
        ll_standard_service.setOnClickListener(this);
//        ll_premium_service.setOnClickListener(this);
        btn_pricelist.setOnClickListener(this);
    }

/*
    private void setImage() {
        String imageUrl = ObjectFactory.getInstance().getAppPreference(getActivity()).getLaundryImage();
        if (!imageUrl.isEmpty() && imageUrl.contains("images"))
            Glide.with(getActivity())
                    .load(imageUrl).
                    into(iv_home_background);
    }
*/

 /*   private void apiCall() {
        LaundryHomeImageRequest request = new LaundryHomeImageRequest();
        request.setApiToken(Constants.API_TOKEN_LAUNDRY);
        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(getActivity()).getLaundryApiService().getLaundryHomeImage(request);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
                                     @Override
                                     public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                         ProgressbarDismiss();
                                         if (response.body() != null) {
                                             try {
                                                 String responseString = new String(response.body().bytes());
                                                 if (responseString != null) {
                                                     JSONObject jsonObject = new JSONObject(responseString);
                                                     if (jsonObject != null) {
                                                         if (jsonObject.getBoolean("success")) {
                                                             JSONObject data = jsonObject.getJSONObject("data");
                                                             if (data != null) {
                                                                 String image_url = data.getString("backgroundImage");
                                                                 ObjectFactory.getInstance().getAppPreference(getActivity()).setLaundryImage(image_url);
//                                                                 setImage();
                                                             }
                                                         } else {
//                                                             Snackbar.make(layout, "Please select an option", Snackbar.LENGTH_LONG).show();
                                                             Utils.makeSnackBar(view,"" + jsonObject.getString("errorMessage"),Snackbar.LENGTH_LONG);
                                                         }
                                                     }
                                                 }

                                             } catch (IOException | JSONException e) {
                                                 e.printStackTrace();
                                             }
                                         }
                                     }

                                     @Override
                                     public void onFailure(Call<ResponseBody> call, Throwable t) {

                                     }
                                 }
        );
    }
*/
/*    public void ProgressbarDismiss() {
        Intent intent = new Intent(Constants.Login.BROADCAST_PROGRESS_WHEEL);
        intent.putExtra(Constants.Login.BROADCAST_ISDISPLAY_PROGRESS_WHEEL, false);
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
    }*/

    private void intializeViews() {
        iv_home_background = (ImageView) view.findViewById(R.id.iv_home_background);
        ll_premium_service = (RelativeLayout) view.findViewById(R.id.ll_premium_service);
        ll_standard_service = (RelativeLayout) view.findViewById(R.id.ll_standard_service);
        btn_pricelist = (AppCompatButton) view.findViewById(R.id.btn_pricelist);
        btn_premium = (AppCompatButton) view.findViewById(R.id.btn_premium);
        btn_standard = (AppCompatButton) view.findViewById(R.id.btn_standard);
        back_button = (ImageView) view.findViewById(R.id.back_button);
        btn_standard.setOnClickListener(this);
        back_button.setOnClickListener(this);
//        btn_premium.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_standard_service:
                ObjectFactory.getInstance().getAppPreference(getActivity()).setServices(1);
                navigateToLaundryItemListFragment();
                break;
//            case R.id.ll_premium_service:
//                ObjectFactory.getInstance().getAppPreference(getActivity()).setServices(2);
//                navigateToLaundryItemListFragment();
//                break;
            case R.id.btn_pricelist:
                navigateToPriceList();
                break;
//            case R.id.btn_premium:
//                ll_premium_service.performClick();
//                break;
            case R.id.btn_standard:
                ll_standard_service.performClick();
                break;
            case R.id.back_button:
                getActivity().onBackPressed();
                break;
            default:
                break;
        }
    }

    private void navigateToPriceList() {
        Fragment fragment = new LaundryPriceListFragment();
//        getFragmentManager().beginTransaction()
//                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
//                .replace(R.id.sb_root_product, fragment, Utils.getTagForFragment(fragment))
//                .addToBackStack(fragment.getClass().getName())
//                .commit();
        if (getActivity() instanceof LaundryActivity){
            ((LaundryActivity) getActivity()).addFragment(fragment);
        }
    }

    private void navigateToLaundryItemListFragment() {
        Fragment fragment = new LaundryItemListFragment();
//        getFragmentManager().beginTransaction()
//                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
//                .replace(R.id.sb_root_product, fragment, Utils.getTagForFragment(fragment))
//                .addToBackStack(fragment.getClass().getName())
//                .commit();
        if (getActivity() instanceof LaundryActivity){
            ((LaundryActivity) getActivity()).addFragment(fragment);
        }
    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
//
//    }
}
