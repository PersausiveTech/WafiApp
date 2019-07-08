package com.mobtecnica.wafiapps.manager;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.mobtecnica.wafiapps.model.profile.add_address.AddAddressRequest;
import com.mobtecnica.wafiapps.model.profile.add_address.add_address_response.AddAddressResponse;
import com.mobtecnica.wafiapps.model.profile.deleteAddress.DeleteUserAddressRequest;
import com.mobtecnica.wafiapps.model.profile.get_address.Addresses;
import com.mobtecnica.wafiapps.model.profile.get_address.CustomerAddressResponse;
import com.mobtecnica.wafiapps.model.profile.get_address.GetUserAddressRequest;
import com.mobtecnica.wafiapps.model.profile.update_address.UpdateUserAddressRequest;
import com.mobtecnica.wafiapps.utils.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by SIby on 10-01-2017.
 */

public class ProfileManager {
    public static final String BROADCAST_DELETE_ADDRESS_RESPONSE = "BROADCAST_DELETE_ADDRESS_RESPONSE";
    public static final String BROADCAST_DELETE_ADDRESS_RESPONSE_STATUS = "BROADCAST_DELETE_ADDRESS_RESPONSE_STATUS";
    private Context mcontext;
    public static final String JSON_RESPONSE_STATUS = "success";

    //getUserAddress
    public static final String BROADCAST_GET_ADDRESS_RESPONSE = "BROADCAST_GET_ADDRESS_RESPONSE";
    public static final String BROADCAST_GET_ADDRESS_RESPONSE_STATUS = "BROADCAST_GET_ADDRESS_RESPONSE_STATUS";
    //getUserAddress
    public static final String BROADCAST_GET_ADDRESS_RESPONSE_CART = "BROADCAST_GET_ADDRESS_RESPONSE_CART";
    public static final String BROADCAST_GET_ADDRESS_RESPONSE_STATUS_CART = "BROADCAST_GET_ADDRESS_RESPONSE_STATUS_CART";
    //getUserAddress
    public static final String BROADCAST_GET_ADDRESS_RESPONSE_LAUNDRY_CART = "BROADCAST_GET_ADDRESS_RESPONSE_LAUNDRY_CART";
    public static final String BROADCAST_GET_ADDRESS_RESPONSE_LAUNDRY_STATUS_CART = "BROADCAST_GET_ADDRESS_RESPONSE_LAUNDRY_STATUS_CART";

    //customer details
    public static final String BROADCAST_GET_DETAILS_RESPONSE = "BROADCAST_GET_DETAILS_RESPONSE";
    public static final String BROADCAST_GET_DETAILS_RESPONSE_STATUS = "BROADCAST_GET_DETAILS_RESPONSE_STATUS";

    //UPDATE ADDRESS
    public static final String BROADCAST_UPDATE_ADDRESS_RESPONSE = "BROADCAST_UPDATE_ADDRESS_RESPONSE";
    public static final String BROADCAST_UPDATE_ADDRESS_RESPONSE_STATUS = "BROADCAST_UPDATE_ADDRESS_RESPONSE_STATUS";

    //ADD USER ADDRESS
    public static final String BROADCAST_ADD_ADDRESS_RESPONSE = "BROADCAST_ADD_ADDRESS_RESPONSE";
    public static final String BROADCAST_ADD_ADDRESS_RESPONSE_STATUS = "BROADCAST_ADD_ADDRESS_RESPONSE_STATUS";
    private Addresses selectedAddress;
    private int selectedAddressPosition = 0;


    public ProfileManager(Context mcontext) {
        this.mcontext = mcontext;
    }

    public void updateContext(Context mcontext) {
        if (mcontext != null)
            this.mcontext = mcontext.getApplicationContext();
    }

    CustomerAddressResponse addressResponse = null;
    AddAddressResponse addAddressResponse = null;

    public void setAddressResponse(CustomerAddressResponse addressResponse) {
        this.addressResponse = addressResponse;
    }

//    public void setAddAddressResponse(AddAddressResponse addAddressResponse) {
//        this.addAddressResponse = addAddressResponse;
//    }

    //  for getting Customer details
   /* public void getUserDetails(CustomerDetailsRequest request) {

        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(mcontext).getApiService().getCustomerDetails(request);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ProgressbarDismiss();
                if (response.body() != null) {
                    try {
                        String responseString = new String(response.body().bytes());
                        if (!TextUtils.isEmpty(responseString)) {
                            JSONObject jsonObject = new JSONObject(responseString);
                            if (jsonObject != null && jsonObject.getBoolean(JSON_RESPONSE_STATUS) == true) {
                                CustomerResponse customerResponse = new Gson().fromJson(responseString, CustomerResponse.class);
                                if (customerResponse != null) {
                                }
                            }
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
*/
    public void addUserAddress(AddAddressRequest request) {
        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(mcontext).getApiService().addCustomerAddress(request);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ProgressbarDismiss();
                if (response.body() != null) {
                    try {
                        String responseStr = new String(response.body().bytes());
                        if (!TextUtils.isEmpty(responseStr)) {
                            JSONObject jsonObject = new JSONObject(responseStr);
                            if (jsonObject != null && jsonObject.getBoolean(JSON_RESPONSE_STATUS)) {
                                addAddressResponse = new Gson().fromJson(responseStr, AddAddressResponse.class);
                                if (addAddressResponse != null) {
                                    Intent intentRes = new Intent(BROADCAST_ADD_ADDRESS_RESPONSE);
                                    intentRes.putExtra(BROADCAST_ADD_ADDRESS_RESPONSE_STATUS, true);
                                    LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);                                    //complete

                                }
                            }
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
                Intent intentRes = new Intent(BROADCAST_ADD_ADDRESS_RESPONSE);
                intentRes.putExtra(BROADCAST_ADD_ADDRESS_RESPONSE_STATUS, false);
                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);                                    //complete
            }
        });
    }

    public void getUserAddress(GetUserAddressRequest request, final int i) {
        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(mcontext).getApiService().getCustomerAddresses(request);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ProgressbarDismiss();
                if (response.body() != null) {
                    try {
                        String responseStr = new String(response.body().bytes());
                        if (!TextUtils.isEmpty(responseStr)) {
                            JSONObject jsonObject = new JSONObject(responseStr);
                            if (jsonObject != null && jsonObject.getBoolean(JSON_RESPONSE_STATUS)) {
                                addressResponse = new Gson().fromJson(responseStr, CustomerAddressResponse.class);
                                if (addressResponse != null) {
                                    if (i == 1) {
                                        Intent intentRes = new Intent(BROADCAST_GET_ADDRESS_RESPONSE);
                                        intentRes.putExtra(BROADCAST_GET_ADDRESS_RESPONSE_STATUS, true);
                                        LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                                    } else if (i == 2) {
                                        Intent intentRes = new Intent(BROADCAST_GET_ADDRESS_RESPONSE_CART);
                                        intentRes.putExtra(BROADCAST_GET_ADDRESS_RESPONSE_STATUS_CART, true);
                                        LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                                    } else if (i == 3) {

                                        Intent intentRes = new Intent(BROADCAST_GET_ADDRESS_RESPONSE_LAUNDRY_CART);
                                        intentRes.putExtra(BROADCAST_GET_ADDRESS_RESPONSE_LAUNDRY_STATUS_CART, true);
                                        LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                                    }
                                    //complete

                                }
                            }
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
                Intent intentRes = new Intent(BROADCAST_GET_ADDRESS_RESPONSE);
                intentRes.putExtra(BROADCAST_GET_ADDRESS_RESPONSE_STATUS, false);
                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
            }
        });
    }

    public void updateUserAddress(UpdateUserAddressRequest request) {
        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(mcontext).getApiService().updateCustomerAddress(request);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ProgressbarDismiss();
                if (response.body() != null) {
                    try {
                        String responseStr = new String(response.body().bytes());
                        if (!TextUtils.isEmpty(responseStr)) {
                            JSONObject jsonObject = new JSONObject(responseStr);
                            if (jsonObject != null && jsonObject.getBoolean(JSON_RESPONSE_STATUS)) {
                                addressResponse = new Gson().fromJson(responseStr, CustomerAddressResponse.class);
                                if (addressResponse != null) {
                                    Intent intentRes = new Intent(BROADCAST_UPDATE_ADDRESS_RESPONSE);
                                    intentRes.putExtra(BROADCAST_UPDATE_ADDRESS_RESPONSE_STATUS, true);
                                    LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                                    //cmplete
                                }
                            }
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
                Intent intentRes = new Intent(BROADCAST_UPDATE_ADDRESS_RESPONSE);
                intentRes.putExtra(BROADCAST_UPDATE_ADDRESS_RESPONSE_STATUS, false);
                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
            }
        });
    }

    public void ProgressbarDismiss() {
        Intent intent = new Intent(Constants.Login.BROADCAST_PROGRESS_WHEEL);
        intent.putExtra(Constants.Login.BROADCAST_ISDISPLAY_PROGRESS_WHEEL, false);
        LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intent);
    }

    public ArrayList<Addresses> getAddressList() {
        ArrayList<Addresses> arrayList = new ArrayList<>();
        if (addressResponse != null) {
            arrayList = new ArrayList<Addresses>(Arrays.asList(addressResponse.getData().getAddresses()));
        }
        return arrayList;
    }

    public void setSelectedAddress(Addresses selectedAddress,int selectedAddressPosition) {
        this.selectedAddress = selectedAddress;
        this.selectedAddressPosition = selectedAddressPosition;
    }
/*
    public void setSelectedAddressPosition(int selectedAddressPosition) {
        this.selectedAddressPosition = selectedAddressPosition;
    }*/

    public Addresses getSelectedAddress() {
//        if (selectedAddress==null){
        ArrayList<Addresses> addressList = getAddressList();
        if (addressList != null && addressList.size() > 0 && selectedAddressPosition < addressList.size()) {
            selectedAddress = addressList.get(selectedAddressPosition);
        } else {
            selectedAddress = null;
        }
//        }
        return selectedAddress;
    }

    public void deleteUserAddress(DeleteUserAddressRequest request) {
        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(mcontext).getApiService().deleteCustomerAddress(request);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ProgressbarDismiss();
                if (response.body() != null) {
                    try {
                        String responseStr = new String(response.body().bytes());
                        if (!TextUtils.isEmpty(responseStr)) {
                            JSONObject jsonObject = new JSONObject(responseStr);
                            if (jsonObject != null && jsonObject.getBoolean(JSON_RESPONSE_STATUS)) {
                                addressResponse = new Gson().fromJson(responseStr, CustomerAddressResponse.class);
                                if (addressResponse != null) {
                                    Intent intentRes = new Intent(BROADCAST_DELETE_ADDRESS_RESPONSE);
                                    intentRes.putExtra(BROADCAST_DELETE_ADDRESS_RESPONSE_STATUS, true);
                                    LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                                    //cmplete
                                    selectedAddressPosition =0;
                                }
                            }
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
                Intent intentRes = new Intent(BROADCAST_DELETE_ADDRESS_RESPONSE);
                intentRes.putExtra(BROADCAST_DELETE_ADDRESS_RESPONSE_STATUS, false);
                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
            }
        });
    }

}
