package com.mobtecnica.wafiapps.manager;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.model.LaundryModel.LaundryCart.CartsItem;
import com.mobtecnica.wafiapps.model.LaundryModel.LaundryCart.LaundryCartRequest;
import com.mobtecnica.wafiapps.model.LaundryModel.LaundryCart.LaundryCartResponse;
import com.mobtecnica.wafiapps.model.LaundryModel.getlaundryItems.CustomClassForExpandableView;
import com.mobtecnica.wafiapps.model.LaundryModel.getlaundryItems.DataLaundryItems;
import com.mobtecnica.wafiapps.model.LaundryModel.getlaundryItems.LaundryItemsRequest;
import com.mobtecnica.wafiapps.model.LaundryModel.getlaundryItems.LaundryItemsResponse;
import com.mobtecnica.wafiapps.model.LaundryModel.laundryAddToCart.LaundryAddtoCartRequest;
import com.mobtecnica.wafiapps.model.LaundryModel.laundry_checkout.AreaList;
import com.mobtecnica.wafiapps.model.LaundryModel.laundry_checkout.LaundryCheckoutResponse;
import com.mobtecnica.wafiapps.model.LaundryModel.priceLIst.DataLaundry;
import com.mobtecnica.wafiapps.model.LaundryModel.priceLIst.PriceListRequest;
import com.mobtecnica.wafiapps.model.LaundryModel.priceLIst.PriceListResponse;
import com.mobtecnica.wafiapps.utils.Constants;
import com.mobtecnica.wafiapps.utils.Utils;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by SIby on 03-04-2017.
 */

public class LaundryManager {
    public static final String JSON_RESPONSE_STATUS = "success";
    public static final String BROADCAST_LAUNDRY_ITEMS = "BROADCAST_LAUNDRY_ITEMS";
    public static final String BROADCAST_LAUNDRY_ITEMS_RESPONSE = "BROADCAST_LAUNDRY_ITEMS_RESPONSE";

    public static final String BROADCAST_LAUNDRY_RESPONSE = "BROADCAST_LAUNDRY_RESPONSE";
    public static final String BROADCAST_LAUNDRY_RESPONSE_STATUS = "BROADCAST_LAUNDRY_RESPONSE_STATUS";
    public static final String BROADCAST_GET_ADDRESS_RESPONSE_AREA_LIST = "BROADCAST_GET_ADDRESS_RESPONSE_AREA_LIST";


    public static final String BROADCAST_LAUNDRY_CART = "BROADCAST_LAUNDRY_CART";
    public static final String BROADCAST_LAUNDRY_CART_STATUS = "BROADCAST_LAUNDRY_CART_STATUS";

    public static final String BROADCAST_LAUNDRY_ADD_TO_CART = "BROADCAST_LAUNDRY_ADD_TO_CART";
    public static final String BROADCAST_LAUNDRY_ADD_TO_CART_STATUS = "BROADCAST_LAUNDRY_ADD_TO_CART_STATUS";
    public static final String BROADCAST_LAUNDRY_ADD_TO_CART_MESSAGE = "BROADCAST_LAUNDRY_ADD_TO_CART_MESSAGE";

    public static final String BROADCAST_LAUNDRY_CHECKOUT = "BROADCAST_LAUNDRY_CHECKOUT";
    public static final String BROADCAST_LAUNDRY_CHECKOUT_STATUS = "BROADCAST_LAUNDRY_CHECKOUT_STATUS";
    public static final String BROADCAST_LAUNDRY_CHECKOUT_CART_STATUS = "BROADCAST_LAUNDRY_CHECKOUT_CART_STATUS";

    public static final String BROADCAST_LAUNDRY_PLACE_ORDER = "BROADCAST_LAUNDRY_PLACE_ORDER";
    public static final String BROADCAST_LAUNDRY_PLACE_ORDER_STATUS = "BROADCAST_LAUNDRY_PLACE_ORDER_STATUS";

    PriceListResponse priceListResponse = null;
    LaundryItemsResponse itemsResponse = null;
    LaundryCartResponse laundryCartResponse = null;
    ArrayList<CustomClassForExpandableView> homeViewProductses = new ArrayList<>();
    private Context mcontext;
    private LaundryCheckoutResponse laundryCheckoutResponse = null;
    private String selectedLaundryTypeId;

    public LaundryManager(Context mcontext) {
        this.mcontext = mcontext;
    }

    public void updateContext(Context mcontext) {
        if (mcontext != null)
            this.mcontext = mcontext.getApplicationContext();
    }

    public void getPriceLIst(PriceListRequest request) {
        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(mcontext).getLaundryApiService().getPriceLIst(request);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ProgressbarDismiss();
                if (response.body() != null) {
                    try {
                        String responseString = new String(response.body().bytes());
                        if (responseString != null) {
                            JSONObject jsonObject = new JSONObject(responseString);
                            if (jsonObject != null && jsonObject.getBoolean(JSON_RESPONSE_STATUS)) {
                                priceListResponse = new Gson().fromJson(responseString, PriceListResponse.class);
                                if (priceListResponse != null && priceListResponse.getData() != null) {
                                    Intent intentRes = new Intent(BROADCAST_LAUNDRY_ITEMS);
                                    intentRes.putExtra(BROADCAST_LAUNDRY_ITEMS_RESPONSE, true);
                                    LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                                }
                            }
                        }
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                        Intent intentRes = new Intent(BROADCAST_LAUNDRY_ITEMS);
                        intentRes.putExtra(BROADCAST_LAUNDRY_ITEMS_RESPONSE, false);
                        LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Intent intentRes = new Intent(BROADCAST_LAUNDRY_ITEMS);
                intentRes.putExtra(BROADCAST_LAUNDRY_ITEMS_RESPONSE, false);
                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);

            }
        });

    }

    public void getLaundryCart(LaundryCartRequest request) {
        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(mcontext).getLaundryApiService().getlaundryCart(request);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ProgressbarDismiss();
                if (response.body() != null) {
                    try {
                        String responseString = new String(response.body().bytes());
                        if (responseString != null) {
                            JSONObject jsonObject = new JSONObject(responseString);
                            if (jsonObject != null && jsonObject.getBoolean(JSON_RESPONSE_STATUS)) {
                                laundryCartResponse = new Gson().fromJson(responseString, LaundryCartResponse.class);
                                if (laundryCartResponse != null && laundryCartResponse.getData() != null) {
                                    Intent intentRes = new Intent(BROADCAST_LAUNDRY_CART);
                                    intentRes.putExtra(BROADCAST_LAUNDRY_CART_STATUS, true);
                                    LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                                } else {
                                    Intent intentRes = new Intent(BROADCAST_LAUNDRY_CART);
                                    intentRes.putExtra(BROADCAST_LAUNDRY_CART_STATUS, false);
                                    LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                                }
                            } else {
                                Intent intentRes = new Intent(BROADCAST_LAUNDRY_CART);
                                intentRes.putExtra(BROADCAST_LAUNDRY_CART_STATUS, false);
                                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                            }
                        } else {
                            Intent intentRes = new Intent(BROADCAST_LAUNDRY_CART);
                            intentRes.putExtra(BROADCAST_LAUNDRY_CART_STATUS, false);
                            LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                        }
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                        Intent intentRes = new Intent(BROADCAST_LAUNDRY_CART);
                        intentRes.putExtra(BROADCAST_LAUNDRY_CART_STATUS, false);
                        LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Intent intentRes = new Intent(BROADCAST_LAUNDRY_CART);
                intentRes.putExtra(BROADCAST_LAUNDRY_CART_STATUS, false);
                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);

            }
        });

    }
    public void getCartCount(LaundryCartRequest laundryCartRequest) {

        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(mcontext).getLaundryApiService().getLaundryCartCount(laundryCartRequest);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.body() != null) {
                    try {
                        String responseString = new String(response.body().bytes());
                        if (responseString != null) {
                            JSONObject jsonObject = new JSONObject(responseString);
                            if (jsonObject != null && jsonObject.getBoolean("success")) {
                                String count=jsonObject.getJSONObject("data").getString("LaundryBagCount");
                                Intent intentRes = new Intent(BROADCAST_LAUNDRY_RESPONSE);
                                intentRes.putExtra(BROADCAST_LAUNDRY_CHECKOUT_CART_STATUS, true);
                                intentRes.putExtra("cart_count",count);
                                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                            }
                        }
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                        Intent intentRes = new Intent(BROADCAST_LAUNDRY_RESPONSE);
                        intentRes.putExtra(BROADCAST_LAUNDRY_CHECKOUT_CART_STATUS, false);
                        LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Intent intentRes = new Intent(BROADCAST_LAUNDRY_RESPONSE);
                intentRes.putExtra(BROADCAST_LAUNDRY_CHECKOUT_CART_STATUS, false);
                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);

            }
        });
    }

    public void getLaundryItems(LaundryItemsRequest request) {
        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(mcontext).getLaundryApiService().getLaundryItems(request);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ProgressbarDismiss();
                if (response.body() != null) {
                    try {
                        String responseString = new String(response.body().bytes());
                        if (responseString != null) {
                            JSONObject jsonObject = new JSONObject(responseString);
                            if (jsonObject != null && jsonObject.getBoolean(JSON_RESPONSE_STATUS)) {
                                itemsResponse = new Gson().fromJson(responseString, LaundryItemsResponse.class);
                                if (itemsResponse != null && itemsResponse.getData() != null) {
                                    Intent intentRes = new Intent(BROADCAST_LAUNDRY_RESPONSE);
                                    intentRes.putExtra(BROADCAST_LAUNDRY_RESPONSE_STATUS, true);
                                    LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                                }
                            }
                        }
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                        Intent intentRes = new Intent(BROADCAST_LAUNDRY_RESPONSE);
                        intentRes.putExtra(BROADCAST_LAUNDRY_RESPONSE_STATUS, false);
                        LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ProgressbarDismiss();
                Intent intentRes = new Intent(BROADCAST_LAUNDRY_RESPONSE);
                intentRes.putExtra(BROADCAST_LAUNDRY_RESPONSE_STATUS, false);
                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);

            }
        });

    }
    public void getAreaListLaundry(AreaList request){
        Call<ResponseBody> responseBodyCall= ObjectFactory.getInstance().getRestClient(mcontext).getLaundryApiService().getCustomerAddressesAndAreaList(request);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                ProgressbarDismiss();
                if (response.body() != null) {
                    try {
                        String responseString = new String(response.body().bytes());
                        if (responseString != null) {
                            JSONObject jsonObject = new JSONObject(responseString);
                            if (jsonObject != null && jsonObject.getBoolean(JSON_RESPONSE_STATUS)) {
                                laundryCheckoutResponse = new Gson().fromJson(responseString, LaundryCheckoutResponse.class);
                                if (laundryCheckoutResponse != null && laundryCheckoutResponse.getData() != null) {
                                    Intent intentRes = new Intent(BROADCAST_LAUNDRY_CHECKOUT);
                                    intentRes.putExtra(BROADCAST_GET_ADDRESS_RESPONSE_AREA_LIST, true);
                                    LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                                }
                            }
                        }
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                        Intent intentRes = new Intent(BROADCAST_LAUNDRY_RESPONSE);
                        intentRes.putExtra(BROADCAST_LAUNDRY_RESPONSE_STATUS, false);
                        LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);

                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void addToLaundryCart(LaundryAddtoCartRequest request) {
        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(mcontext).getLaundryApiService().addToLaundryCart(request);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ProgressbarDismiss();
                if (response.body() != null) {
                    try {
                        String responseString = new String(response.body().bytes());
                        if (responseString != null) {
                            JSONObject jsonObject = new JSONObject(responseString);
                            if (jsonObject != null && jsonObject.getBoolean(JSON_RESPONSE_STATUS)) {
                                Intent intentRes = new Intent(BROADCAST_LAUNDRY_ADD_TO_CART);
                                intentRes.putExtra(BROADCAST_LAUNDRY_ADD_TO_CART_STATUS, true);
                                intentRes.putExtra(BROADCAST_LAUNDRY_ADD_TO_CART_MESSAGE, jsonObject.getString("data"));
                                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);

                            }
                        }
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                        Intent intentRes = new Intent(BROADCAST_LAUNDRY_ADD_TO_CART);
                        intentRes.putExtra(BROADCAST_LAUNDRY_ADD_TO_CART_STATUS, false);
                        LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Intent intentRes = new Intent(BROADCAST_LAUNDRY_ADD_TO_CART);
                intentRes.putExtra(BROADCAST_LAUNDRY_ADD_TO_CART_STATUS, false);
                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);

            }
        });

    }

    /*LAUNDRY CHECKOUT.*/
    public void laundryCheckout(String request) {
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), request);
        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(mcontext).getLaundryApiService().laundryCheckout(body);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ProgressbarDismiss();
                if (response.body() != null) {
                    try {
                        String responseString = new String(response.body().bytes());
                        if (responseString != null) {
                            JSONObject jsonObject = new JSONObject(responseString);
                            if (jsonObject != null && jsonObject.getBoolean(JSON_RESPONSE_STATUS)) {
                                laundryCheckoutResponse = new Gson().fromJson(responseString, LaundryCheckoutResponse.class);
                                Intent intentRes = new Intent(BROADCAST_LAUNDRY_CHECKOUT);
                                intentRes.putExtra(BROADCAST_LAUNDRY_CHECKOUT_STATUS, true);
                                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);

                            } else {
                                Intent intentRes = new Intent(BROADCAST_LAUNDRY_CHECKOUT);
                                intentRes.putExtra(BROADCAST_LAUNDRY_CHECKOUT_STATUS, false);
                                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                            }
                        }
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                        Intent intentRes = new Intent(BROADCAST_LAUNDRY_CHECKOUT);
                        intentRes.putExtra(BROADCAST_LAUNDRY_CHECKOUT_STATUS, false);
                        LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Intent intentRes = new Intent(BROADCAST_LAUNDRY_CHECKOUT);
                intentRes.putExtra(BROADCAST_LAUNDRY_CHECKOUT_STATUS, false);
                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);

            }
        });

    }

    /*laundry placeorder*/
    public void laundryPlaceoeder(String request) {
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), request);
        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(mcontext).getLaundryApiService().laundryPlaceorder(body);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ProgressbarDismiss();
                if (response.body() != null) {
                    try {
                        String responseString = new String(response.body().bytes());
                        if (responseString != null) {
                            JSONObject jsonObject = new JSONObject(responseString);
                            if (jsonObject != null && jsonObject.getBoolean(JSON_RESPONSE_STATUS)) {
                                Intent intentRes = new Intent(BROADCAST_LAUNDRY_PLACE_ORDER);
                                intentRes.putExtra(BROADCAST_LAUNDRY_PLACE_ORDER_STATUS, true);
                                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                            } else {
                                Intent intentRes = new Intent(BROADCAST_LAUNDRY_PLACE_ORDER);
                                intentRes.putExtra(BROADCAST_LAUNDRY_PLACE_ORDER_STATUS, false);
                                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                            }
                        }
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                        Intent intentRes = new Intent(BROADCAST_LAUNDRY_PLACE_ORDER);
                        intentRes.putExtra(BROADCAST_LAUNDRY_PLACE_ORDER_STATUS, false);
                        LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Intent intentRes = new Intent(BROADCAST_LAUNDRY_PLACE_ORDER);
                intentRes.putExtra(BROADCAST_LAUNDRY_PLACE_ORDER_STATUS, false);
                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);

            }
        });

    }

    public DataLaundry getLaundryPriceList() {
//        ArrayList<DataLaundry> laundries = new ArrayList<>();
        return priceListResponse.getData();
    }

    public ArrayList<DataLaundryItems> getLaundryItemsList() {
        ArrayList<DataLaundryItems> laundries = new ArrayList<>();
        if (itemsResponse != null) {
            return laundries = new ArrayList<DataLaundryItems>(Arrays.asList(itemsResponse.getData()));
        } else {
            return laundries;
        }
    }

    public void ProgressbarDismiss() {
        Intent intent = new Intent(Constants.Login.BROADCAST_PROGRESS_WHEEL);
        intent.putExtra(Constants.Login.BROADCAST_ISDISPLAY_PROGRESS_WHEEL, false);
        LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intent);
    }

    public ArrayList<CartsItem> getLaundryCartList() {
        return laundryCartResponse.getData().getCartsItems();
    }

    public String getAmountCart() {
        return laundryCartResponse.getData().getTotalAmount().toString();
    }

    public LaundryCheckoutResponse getLaundryCheckoutDatas() {
        if (laundryCheckoutResponse != null) {
            return laundryCheckoutResponse;
        } else {
            return new LaundryCheckoutResponse();
        }

    }

    public void setLaundryItemsExpandableList(ArrayList<CustomClassForExpandableView> homeViewProductses) {
        this.homeViewProductses = homeViewProductses;
    }


    public void setSelectedLaundryTypeId(String selectedLaundryTypeId) {
        this.selectedLaundryTypeId = selectedLaundryTypeId;
    }

    public String getSelectedLaundryTypeId() {
        return selectedLaundryTypeId;
    }
}
