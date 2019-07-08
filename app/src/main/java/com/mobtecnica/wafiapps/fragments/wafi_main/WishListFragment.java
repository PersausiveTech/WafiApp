package com.mobtecnica.wafiapps.fragments.wafi_main;

import android.app.DialogFragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.activity.MainActivity;
import com.mobtecnica.wafiapps.adapters.WishListItemsListingRvAdapter;
import com.mobtecnica.wafiapps.customViews.CustomAlertDialog;
import com.mobtecnica.wafiapps.manager.HomeManager;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.model.wishlist.addToCart.AddToCartFromWishListRequest;
import com.mobtecnica.wafiapps.model.wishlist.addToCart.ItemQuantity;
import com.mobtecnica.wafiapps.model.wishlist.addToCart.UpdateModel;
import com.mobtecnica.wafiapps.model.wishlist.newWishList.GetWishListItems;
import com.mobtecnica.wafiapps.model.wishlist.newWishList.GetWishListRequest;
import com.mobtecnica.wafiapps.model.wishlist.removeWishList.Item_quantity;
import com.mobtecnica.wafiapps.model.wishlist.removeWishList.RemoveWishListRequest;
import com.mobtecnica.wafiapps.model.wishlist.updateWishlist.Item_quantityUpdate;
import com.mobtecnica.wafiapps.model.wishlist.updateWishlist.UpdateWishListRequest;
import com.mobtecnica.wafiapps.utils.BaseFragment;
import com.mobtecnica.wafiapps.utils.Constants;
import com.mobtecnica.wafiapps.utils.Utils;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Siby on 04-01-2017.
 */

public class WishListFragment extends BaseFragment {
    View rootView;
    WishListItemsListingRvAdapter wishListItemsListingRvAdapter;
    List<Integer> list;
    ArrayList<GetWishListItems> wishList = null;
    private RecyclerView rv_wishList;
    private LinearLayout ll_no_data;
    private AppCompatButton btn_start_shopping;
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().matches(HomeManager.BROADCAST_GET_WISHLIST_RESPONSE)) {
                if (intent.getBooleanExtra(HomeManager.BROADCAST_GET_WISHLIST_RESPONSE_STATUS, false)) {
                    dismissLoadingDialog();
                    setWishListData();
                }
            }
        }
    };

    private void setWishListData() {
        wishList = ObjectFactory.getInstance().getHomeManager(getActivity()).getWishListItems();

        if (wishList != null && wishList.size() > 0) {
            rv_wishList.setVisibility(View.VISIBLE);
            ll_no_data.setVisibility(View.GONE);
            wishListItemsListingRvAdapter.setDataset(wishList);
            wishListItemsListingRvAdapter.setOnItemClickListener(new WishListItemsListingRvAdapter.MyClickListener() {
                @Override
                public void onItemClick(int position, View v) {
                    switch (v.getId()) {
                        case R.id.btn_Add_to_cart:
                            progressbarShowing();
                            addProductToWishList(position);
                            break;
                        case R.id.iv_plus_:
                            int count = Integer.parseInt(wishList.get(position).getQuantity()) + 1;
                            progressbarShowing();
                            updateWishList(position, count);
                            break;
                        case R.id.iv_minus_:
                            int count_min = Integer.parseInt(wishList.get(position).getQuantity()) - 1;
                            progressbarShowing();
                            updateWishList(position, count_min);
                            break;
                        case R.id.iv_delete:
                            setAlert(position);
                            break;
                        default:
                            break;
                    }
                }

                @Override
                public void onViewClick(GetWishListItems getWishListItems) {
                    String id=getWishListItems.getProductId();
                    ObjectFactory.getInstance().getAppPreference(getContext()).setProductId(id);
                    navigateToProductDetailFragment(getWishListItems.getProductName());
                }
            });

        } else {
            try {
                rv_wishList.setVisibility(View.GONE);
                ll_no_data.setVisibility(View.VISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void setAlert(final int position) {

        CustomAlertDialog customAlertDialog = new CustomAlertDialog(getString(R.string.sure_to_continue), getString(R.string.confirm_delete),
                getString(R.string.cancel), getString(R.string.delete));
        customAlertDialog.setCustomAlertClickListener(new CustomAlertDialog.CustomAlertClickListener() {

            @Override
            public void onPositive(DialogFragment dialogFragment) {
                dialogFragment.dismiss();
                progressbarShowing();
                deleteItem(position);
            }

            @Override
            public void onNegative(DialogFragment dialogFragment) {
                dialogFragment.dismiss();
            }
        });
        customAlertDialog.show(getActivity().getFragmentManager(), "Alert");


    }
    private void navigateToProductDetailFragment(String productName) {
        ProductDetailedViewFragment fragment = new ProductDetailedViewFragment();
        fragment.setTitle(productName);
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).addFragment(fragment);
        }
    }

    private void updateWishList(int position, int count) {

        UpdateWishListRequest updateWishListRequest = new UpdateWishListRequest();
        updateWishListRequest.setApiToken(Constants.API_TOKEN);
        updateWishListRequest.setGuid(ObjectFactory.getInstance().getAppPreference(getActivity()).getGuid());
        Item_quantityUpdate item_quantityUpdate = new Item_quantityUpdate();
        item_quantityUpdate.setName(getString(R.string.item_quantity) + wishList.get(position).getId());
        item_quantityUpdate.setValue(String.valueOf(count));
        List<Item_quantityUpdate> updates = new ArrayList<>();
        updates.add(item_quantityUpdate);
        updateWishListRequest.setItem_quantity(updates);

        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(getActivity()).getApiService().updateWishList(updateWishListRequest);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ProgressbarDismiss();
                if (response.body() != null) {
                    try {
                        String responseString = new String(response.body().bytes());
                        if (responseString != null) {
                            JSONObject jsonObject = new JSONObject(responseString);
                            refreshFragment();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Utils.makeSnackBar(rootView, R.string.network_error, Snackbar.LENGTH_LONG);
            }
        });


    }





    private void refreshFragment() {
        GetWishListRequest getWishListRequest = new GetWishListRequest();
        getWishListRequest.setGuid(ObjectFactory.getInstance().getAppPreference(getActivity()).getGuid());
        getWishListRequest.setApiToken(Constants.API_TOKEN);
        ObjectFactory.getInstance().getHomeManager(getActivity()).getWishList(getWishListRequest);
    }

    private void deleteItem(int position) {
        if(wishList.size()>position) {
            RemoveWishListRequest removeWishListRequest = new RemoveWishListRequest();
            removeWishListRequest.setGuid(ObjectFactory.getInstance().getAppPreference(getActivity()).getGuid());
            removeWishListRequest.setApiToken(Constants.API_TOKEN);

            List<String> removefromcart = new ArrayList<>();
            removefromcart.add(wishList.get(position).getId());
            removeWishListRequest.setRemovefromcart(removefromcart);

            Item_quantity item_quantity = new Item_quantity();
            item_quantity.setName("itemquantity" + wishList.get(position).getProductId());
            item_quantity.setName(wishList.get(position).getQuantity());
            List<Item_quantity> item_quantities = new ArrayList<>();
            item_quantities.add(item_quantity);
            removeWishListRequest.setItem_quantity(item_quantities);

            Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(getActivity()).getApiService().deleteFromWishList(removeWishListRequest);
            responseBodyCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    ProgressbarDismiss();
                    if (response.body() != null) {
                        try {
                            String responseString = new String(response.body().bytes());
                            if (responseString != null) {
                                JSONObject jsonObject = new JSONObject(responseString);

                                GetWishListRequest getWishListRequest = new GetWishListRequest();
                                getWishListRequest.setGuid(ObjectFactory.getInstance().getAppPreference(getActivity()).getGuid());
                                getWishListRequest.setApiToken(Constants.API_TOKEN);
                                ObjectFactory.getInstance().getHomeManager(getActivity()).getWishList(getWishListRequest);

                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                }
            });

        }
    }

    private void addProductToWishList(int position) {
        AddToCartFromWishListRequest request = new AddToCartFromWishListRequest();
        request.setApiToken(Constants.API_TOKEN);
        request.setGuid(ObjectFactory.getInstance().getAppPreference(getActivity()).getGuid());

        UpdateModel updateModel = new UpdateModel();
        List<Integer> list = new ArrayList<>();
        list.add(Integer.valueOf(wishList.get(position).getId()));
        updateModel.setAddtocart(list);

        ItemQuantity quantity = new ItemQuantity();
        quantity.setName("itemquantity" + wishList.get(position).getProductId());
        quantity.setValue(Integer.valueOf(wishList.get(position).getQuantity()));
        List<ItemQuantity> quantities = new ArrayList<>();
        quantities.add(quantity);
        updateModel.setItemQuantity(quantities);
        request.setUpdateModel(updateModel);

        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(getActivity()).getApiService().addItemsToWishList(request);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ProgressbarDismiss();
                if (response.body() != null) {
                    try {
                        String responseString = new String(response.body().bytes());
                        if (responseString != null) {
                            JSONObject jsonObject = new JSONObject(responseString);
                            boolean success = jsonObject.getBoolean("success");
                            try {
                                int cartItemCount = jsonObject.getJSONObject("data").getInt("CartItemsCount");
                                ((MainActivity) getActivity()).setCartCount(cartItemCount);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            Utils.makeSnackBar(rootView, success ? R.string.from_wishlist_to_cart_success : R.string.from_wishlist_to_cart_failed, Snackbar.LENGTH_LONG);
                            GetWishListRequest getWishListRequest = new GetWishListRequest();
                            getWishListRequest.setGuid(ObjectFactory.getInstance().getAppPreference(getActivity()).getGuid());
                            getWishListRequest.setApiToken(Constants.API_TOKEN);
                            ObjectFactory.getInstance().getHomeManager(getActivity()).getWishList(getWishListRequest);

                            showLoadingDialog();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (Exception e) {

                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_wishlist, container, false);
        initilize();
        return rootView;
    }

    private void initilize() {
        IntentFilter intent = new IntentFilter();
        intent.addAction(HomeManager.BROADCAST_GET_WISHLIST_RESPONSE);
        addBroadcastListener(receiver, intent);
        rv_wishList = (RecyclerView) rootView.findViewById(R.id.rv_wishlist);
        ll_no_data = (LinearLayout) rootView.findViewById(R.id.ll_no_data);
        ll_no_data.setVisibility(View.GONE);
        btn_start_shopping = (AppCompatButton) rootView.findViewById(R.id.btn_start_shopping);
        LinearLayoutManager verticalLayoutmanager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_wishList.setLayoutManager(verticalLayoutmanager);
        wishListItemsListingRvAdapter = new WishListItemsListingRvAdapter(getActivity());
        rv_wishList.setAdapter(wishListItemsListingRvAdapter);
        btn_start_shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).navigateToViewPagerFragment();
                }
            }
        });
        setTitle(getString(R.string.wishlist));
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            progressbarShowing();
            if (ll_no_data != null) {
                ll_no_data.setVisibility(View.GONE);
            }
            if (rv_wishList != null) {
                rv_wishList.setVisibility(View.GONE);
            }
            GetWishListRequest getWishListRequest = new GetWishListRequest();
            getWishListRequest.setGuid(ObjectFactory.getInstance().getAppPreference(getActivity()).getGuid());
            getWishListRequest.setApiToken(Constants.API_TOKEN);
            ObjectFactory.getInstance().getHomeManager(getActivity()).getWishList(getWishListRequest);
        }
    }

    private void progressbarShowing() {
//        Intent intent = new Intent(Constants.Login.BROADCAST_PROGRESS_WHEEL);
//        intent.putExtra(Constants.Login.BROADCAST_ISDISPLAY_PROGRESS_WHEEL, true);
//        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);

        showLoadingDialog();
    }

    public void ProgressbarDismiss() {
//        Intent intent = new Intent(Constants.Login.BROADCAST_PROGRESS_WHEEL);
//        intent.putExtra(Constants.Login.BROADCAST_ISDISPLAY_PROGRESS_WHEEL, false);
//        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
        dismissLoadingDialog();
    }
}
