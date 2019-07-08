package com.mobtecnica.wafiapps.fragments.laundryHome;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.activity.LaundryActivity;
import com.mobtecnica.wafiapps.adapters.LaundryCartListingRecyclerViewAdapter;
import com.mobtecnica.wafiapps.customViews.CustomAlertDialog;
import com.mobtecnica.wafiapps.manager.LaundryManager;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.model.LaundryModel.LaundryCart.CartsItem;
import com.mobtecnica.wafiapps.model.LaundryModel.LaundryCart.LaundryCartRequest;
import com.mobtecnica.wafiapps.model.LaundryModel.removeFromCart.RemoveFromCartRequestLaundry;
import com.mobtecnica.wafiapps.model.LaundryModel.removeFromCart.UpdateModel;
import com.mobtecnica.wafiapps.utils.BaseFragment;
import com.mobtecnica.wafiapps.utils.Constants;
import com.mobtecnica.wafiapps.utils.Utils;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by SIby on 12-04-2017.
 */

public class LaundryCartFragment extends BaseFragment implements View.OnClickListener {
    public ArrayList<String> deleteIds;
    ArrayList<CartsItem> data;
    List<String> delete_ids;
    private RelativeLayout rl_cart_layout;
    private View rootView;
    private ImageView iv_back;
    private ImageView iv_delete;
    private String cartItemId = "";
    private String itemId = "";
    private TextView tv_total_price;
    LaundryCartListingRecyclerViewAdapter adapter;
    private RecyclerView rv_cart_laundry;
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().matches(LaundryManager.BROADCAST_LAUNDRY_CART)) {
                if (intent.getBooleanExtra(LaundryManager.BROADCAST_LAUNDRY_CART_STATUS, false)) {
                    setData();
                } else {
                }
            }
        }
    };
    private TextView check_out;

    private void setData() {
        deleteIds = new ArrayList<>();
        data = ObjectFactory.getInstance().getLaundryManager(getActivity()).getLaundryCartList();
        String st = ObjectFactory.getInstance().getLaundryManager(getActivity()).getAmountCart();
        if(adapter!=null) {
            adapter.updateList(data);
        }
        if (data.size() > 0) {
            tv_total_price.setText(getResources().getString(R.string.bd)+": "+ String.format("%.3f",Double.parseDouble(st)));
            adapter.updateList(data);
            rv_cart_laundry.setAdapter(adapter);
            if (adapter != null) {
                adapter.setOnItemClickListener(new LaundryCartListingRecyclerViewAdapter.MyClickListener() {
                    @Override
                    public void onItemClick(int position, View v) {
                        switch (v.getId()) {
                            case R.id.remove_from_cart:
                                v.setSelected(!v.isSelected());
                                cartItemId = data.get(position).getCartItemId().toString();
                                itemId = data.get(position).getItemId().toString();
                                if (delete_ids.size() == 0) {
                                    delete_ids.add(cartItemId);
                                } else {
                                    if (delete_ids.contains(cartItemId)) {
                                        delete_ids.remove(cartItemId);
                                    } else delete_ids.add(cartItemId);
                                }
                                break;
                            default:
                                break;
                        }
                    }
                });
            }

        } else {
            initializeViews();
            tv_total_price.setText("BD 00.00");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_cart_laundry, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        initializeViews();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

    private void initializeViews() {

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(LaundryManager.BROADCAST_LAUNDRY_CART);
        addBroadcastListener(receiver, intentFilter);
//        toolbar = (android.support.v7.widget.Toolbar) getActivity().findViewById(R.id.toolbar_main);

        iv_back = (ImageView) rootView.findViewById(R.id.iv_back);
        tv_total_price = (TextView) rootView.findViewById(R.id.tv_total_price);
        iv_delete = (ImageView) rootView.findViewById(R.id.iv_delete);
        rv_cart_laundry = (RecyclerView) rootView.findViewById(R.id.rv_cart_laundry);
        LinearLayoutManager verticalLayoutmanager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_cart_laundry.setLayoutManager(verticalLayoutmanager);
        rv_cart_laundry.setHasFixedSize(true);
        adapter = new LaundryCartListingRecyclerViewAdapter(getActivity(), new ArrayList<CartsItem>());
        rv_cart_laundry.setAdapter(adapter);
        rl_cart_layout = (RelativeLayout) rootView.findViewById(R.id.rl_cart_layout);
        iv_back.setOnClickListener(this);
        iv_delete.setOnClickListener(this);
        delete_ids = new ArrayList<>();
        check_out = (TextView) rootView.findViewById(R.id.check_out);
        check_out.setOnClickListener(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressbarShowing();
        apiCallListItems();
    }

    private void apiCallListItems() {
        LaundryCartRequest request = new LaundryCartRequest();
        request.setApiToken(Constants.API_TOKEN_LAUNDRY);
        request.setEmailID(ObjectFactory.getInstance().getAppPreference(getActivity()).getEmailId());
        ObjectFactory.getInstance().getLaundryManager(getActivity()).getLaundryCart(request);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                getActivity().onBackPressed();
                break;
            case R.id.iv_delete:
                deleteItemsFromCartApi();
                break;
            case R.id.check_out:
                navigateToCheckout();
                break;
            default:
                break;
        }
    }

    private void navigateToCheckout() {
        if(adapter.getNoOfItems() > 0) {
            Fragment fragment = new LaundryCheckoutFragment("no");
//            getFragmentManager().beginTransaction().
//                    replace(R.id.sb_root_product, fragment, Utils.getTagForFragment(fragment))
//                    .commit();
            ((LaundryActivity)getActivity()).addFragment(fragment);
        } else {
            Utils.makeSnackBar(rl_cart_layout, R.string.no_items_to_checkout, Snackbar.LENGTH_LONG);
        }
    }

    private void deleteItemsFromCartApi() {
        if (delete_ids.size() > 0) {
            RemoveFromCartRequestLaundry removeFromCartRequestLaundry = new RemoveFromCartRequestLaundry();
            removeFromCartRequestLaundry.setApiToken(Constants.API_TOKEN_LAUNDRY);
            removeFromCartRequestLaundry.setEmailID(ObjectFactory.getInstance().getAppPreference(getActivity()).getEmailId());
            UpdateModel updateModel = new UpdateModel();
            updateModel.setRemovefromcart(delete_ids);
            removeFromCartRequestLaundry.setUpdateModel(updateModel);
            progressbarShowing();
            Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(getActivity()).getLaundryApiService().removeFromCart(removeFromCartRequestLaundry);
            responseBodyCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    ProgressbarDismiss();
                    if (response.body() != null) {
                        try {
                            String responseString = new String(response.body().bytes());
                            if (responseString != null) {
                                JSONObject jsonObject = new JSONObject(responseString);
                                if (jsonObject != null && jsonObject.getBoolean(Constants.SUCCESS)) {
                                    delete_ids = new ArrayList<String>();
                                    Utils.makeSnackBar(rl_cart_layout, jsonObject.getString(Constants.DATA), Snackbar.LENGTH_LONG).show();
                                    apiCallListItems();
                                    if(getActivity() instanceof LaundryActivity){
                                        ((LaundryActivity)getActivity()).getLaundryCart();
                                    }

                                } else {
                                    Utils.makeSnackBar(rl_cart_layout, jsonObject.getString(Constants.ERROR_MISTAKE), Snackbar.LENGTH_LONG).show();
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
            });

        } else {
            Utils.makeSnackBar(rl_cart_layout,Constants.NO_ITEMS_TO_DELETE, Snackbar.LENGTH_LONG).show();
        }

    }

    public void setAlert(String message, String head) {
        CustomAlertDialog customAlertDialog=   new CustomAlertDialog(message,head, null,null);
        customAlertDialog.show(getActivity().getFragmentManager(),"Alert");
    }

    public void ProgressbarDismiss() {
        Intent intent = new Intent(Constants.Login.BROADCAST_PROGRESS_WHEEL);
        intent.putExtra(Constants.Login.BROADCAST_ISDISPLAY_PROGRESS_WHEEL, false);
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
    }

    private void progressbarShowing() {
        Intent intent = new Intent(Constants.Login.BROADCAST_PROGRESS_WHEEL);
        intent.putExtra(Constants.Login.BROADCAST_ISDISPLAY_PROGRESS_WHEEL, true);
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
    }
}
