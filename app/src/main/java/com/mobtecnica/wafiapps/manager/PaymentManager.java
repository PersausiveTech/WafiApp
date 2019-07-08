package com.mobtecnica.wafiapps.manager;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mobtecnica.wafiapps.model.checkout.onePageCheckout.OnePageCheckoutResponse;
import com.mobtecnica.wafiapps.model.checkout.opcBillingForm.OpcBillingCheckoutResponse;
import com.mobtecnica.wafiapps.model.checkout.savePaymentInfo.SavePaymentInfoResponse;
import com.mobtecnica.wafiapps.model.checkout.startCheckout.ItemQuantityCheckout;
import com.mobtecnica.wafiapps.utils.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by SIby on 20-04-2017.
 */

public class PaymentManager {
    public static final String JSON_RESPONSE_STATUS = "success";
    //getUserAddress
    /*one start checkout*/
    public static final String BROADCAST_START_CHECKOUT_RESPONSE = "BROADCAST_START_CHECKOUT_RESPONSE";
    public static final String BROADCAST_START_CHECKOUT_RESPONSE_STATUS = "BROADCAST_START_CHECKOUT_RESPONSE_STATUS";

    /*one page checkout*/
    public static final String BROADCAST_START_ONE_PAGE_CHECKOUT_RESPONSE = "BROADCAST_START_ONE_PAGE_CHECKOUT_RESPONSE";
    public static final String BROADCAST_START_ONE_PAGE_CHECKOUT_RESPONSE_STATUS = "BROADCAST_START_ONE_PAGE_CHECKOUT_RESPONSE_STATUS";

    /*opc billing form*/
    public static final String BROADCAST_OPC_BILLING_FORM_CHECKOUT_RESPONSE = "BROADCAST_OPC_BILLING_FORM_CHECKOUT_RESPONSE";
    public static final String BROADCAST_OPC_BILLING_FORM_CHECKOUT_RESPONSE_STATUS = "BROADCAST_OPC_BILLING_FORM_CHECKOUT_RESPONSE_STATUS";

    /*opc SAVE billing form*/
    public static final String BROADCAST_OPC_SAVE_BILLING_FORM_CHECKOUT_RESPONSE = "BROADCAST_OPC_SAVE_BILLING_FORM_CHECKOUT_RESPONSE";
    public static final String BROADCAST_OPC_SAVE_BILLING_FORM_CHECKOUT_RESPONSE_STATUS = "BROADCAST_OPC_SAVE_BILLING_FORM_CHECKOUT_RESPONSE_STATUS";

    /*opc save shipping*/
    public static final String BROADCAST_OPC_SAVE_SHIPPING_RESPONSE = "BROADCAST_OPC_SAVE_SHIPPING_RESPONSE";
    public static final String BROADCAST_OPC_SAVE_SHIPPING_RESPONSE_STATUS = "BROADCAST_OPC_SAVE_SHIPPING_RESPONSE_STATUS";


    /*opc save shipping METHOD*/
    public static final String BROADCAST_OPC_SAVE_SHIPPING_METHOD_RESPONSE = "BROADCAST_OPC_SAVE_SHIPPING_METHOD_RESPONSE";
    public static final String BROADCAST_OPC_SAVE_SHIPPING_METHOD_RESPONSE_STATUS = "BROADCAST_OPC_SAVE_SHIPPING_METHOD_RESPONSE_STATUS";

    /*opc savePayemt method*/
    public static final String BROADCAST_OPC_SAVE_PAYMENT_METHOD_RESPONSE = "BROADCAST_OPC_SAVE_PAYMENT_METHOD_RESPONSE";
    public static final String BROADCAST_OPC_SAVE_PAYMENT_METHOD_RESPONSE_STATUS = "BROADCAST_OPC_SAVE_PAYMENT_METHOD_RESPONSE_STATUS";
    public static final String BROADCAST_OPC_SAVE_PAYMENT_METHOD_RESPONSE_MESSAGE = "BROADCAST_OPC_SAVE_PAYMENT_METHOD_RESPONSE_MESSAGE";

    /*Save payment info*/
    public static final String BROADCAST_OPC_SAVE_PAYMENT_INFO_RESPONSE = "BROADCAST_OPC_SAVE_PAYMENT_INFO_RESPONSE";
    public static final String BROADCAST_OPC_SAVE_PAYMENT_INFO_RESPONSE_STATUS = "BROADCAST_OPC_SAVE_PAYMENT_INFO_RESPONSE_STATUS";
    public static final String BROADCAST_OPC_SAVE_PAYMENT_INFO_RESPONSE_MESSAGE = "BROADCAST_OPC_SAVE_PAYMENT_INFO_RESPONSE_MESSAGE";


    /*CONFIRM ORDER info*/
    public static final String BROADCAST_OPC_CONFIRM_ORDER_RESPONSE = "BROADCAST_OPC_CONFIRM_ORDER_RESPONSE";
    public static final String BROADCAST_OPC_CONFIRM_ORDER_RESPONSE_STATUS = "BROADCAST_OPC_CONFIRM_ORDER_RESPONSE_STATUS";
    public static final String BROADCAST_OPC_CONFIRM_ORDER_RESPONSE_STATUS_MESSAGE = "BROADCAST_OPC_CONFIRM_ORDER_RESPONSE_STATUS_MESSAGE";
    public static final String BROADCAST_OPC_CONFIRM_ORDER_NUMBER = "BROADCAST_OPC_CONFIRM_ORDER_NUMBER";

    //Credimax
    private static final String BROADCAST_GET_CREDIMAX_PAYMENT_SESSION = "BROADCAST_GET_CREDIMAX_PAYMENT_SESSION";
    public static final String BROADCAST_OPC_CREDIMAX_SESSION_STATUS = "BROADCAST_OPC_CREDIMAX_SESSION_STATUS";
    public static final String BROADCAST_OPC_CREDIMAX_SESSION_ID = "BROADCAST_OPC_CREDIMAX_SESSION_ID";
    public static final String BROADCAST_OPC_CREDIMAX_SESSION_VERSION = "BROADCAST_OPC_CREDIMAX_SESSION_VERSION";
    public static final String BROADCAST_OPC_CREDIMAX_API_VERSION = "BROADCAST_OPC_CREDIMAX_API_VERSION";
    public static final String BROADCAST_OPC_CREDIMAX_MERCHANT_ID = "BROADCAST_OPC_CREDIMAX_MERCHANT_ID";
    public static final String BROADCAST_OPC_COMPLETE_TRANSACTION_RESPONSE = "BROADCAST_OPC_COMPLETE_TRANSACTION_RESPONSE";
    public static final String BROADCAST_OPC_CREDIMAX_COMPLETE_TRANSACTION_STATUS = "BROADCAST_OPC_CREDIMAX_COMPLETE_TRANSACTION_STATUS";

    OnePageCheckoutResponse onePageCheckoutResponse = null;
    OpcBillingCheckoutResponse opcBillingCheckoutResponse = null;
    SavePaymentInfoResponse savePaymentInfoResponse = null;

    private Context mcontext;
    private List<ItemQuantityCheckout> itemQuantityList;
    private String totalAmount;

    public PaymentManager(Context mcontext) {
        this.mcontext = mcontext;
    }

    public void updateContext(Context mcontext) {
        if (mcontext != null)
            this.mcontext = mcontext.getApplicationContext();
    }

    //on checkout page
    public void startCheckout(String request) {
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), request);
        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(mcontext).getApiService().startCheckout(body);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                ProgressbarDismiss();
                if (response.body() != null) {
                    try {
                        String responseStr = new String(response.body().bytes());
                        if (!TextUtils.isEmpty(responseStr)) {
                            JSONObject jsonObject = new JSONObject(responseStr);
                            if (jsonObject != null && jsonObject.getBoolean(JSON_RESPONSE_STATUS)) {
                                Intent intentRes = new Intent(BROADCAST_START_CHECKOUT_RESPONSE);
                                intentRes.putExtra(BROADCAST_START_CHECKOUT_RESPONSE_STATUS, true);
                                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);                                    //complete
                            } else {
                                Intent intentRes = new Intent(BROADCAST_START_CHECKOUT_RESPONSE);
                                intentRes.putExtra(BROADCAST_START_CHECKOUT_RESPONSE_STATUS, false);
                                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                            }
                        }
                    } catch (IOException e) {
                        Intent intentRes = new Intent(BROADCAST_START_CHECKOUT_RESPONSE);
                        intentRes.putExtra(BROADCAST_START_CHECKOUT_RESPONSE_STATUS, false);
                        LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                        e.printStackTrace();
                    } catch (JSONException e) {
                        Intent intentRes = new Intent(BROADCAST_START_CHECKOUT_RESPONSE);
                        intentRes.putExtra(BROADCAST_START_CHECKOUT_RESPONSE_STATUS, false);
                        LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                        e.printStackTrace();
                    }
                } else {
                    Intent intentRes = new Intent(BROADCAST_START_CHECKOUT_RESPONSE);
                    intentRes.putExtra(BROADCAST_START_CHECKOUT_RESPONSE_STATUS, false);
                    LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                ProgressbarDismiss();
                Intent intentRes = new Intent(BROADCAST_START_CHECKOUT_RESPONSE);
                intentRes.putExtra(BROADCAST_START_CHECKOUT_RESPONSE_STATUS, false);
                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
            }
        });

    }//ok

    public void onePageCheckout(String request) {
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), request);
        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(mcontext).getApiService().onePageCheckout(body);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                ProgressbarDismiss();
                if (response.body() != null) {
                    try {
                        String responseStr = new String(response.body().bytes());
                        if (!TextUtils.isEmpty(responseStr)) {
                            JSONObject jsonObject = new JSONObject(responseStr);
                            if (jsonObject != null && jsonObject.getBoolean(JSON_RESPONSE_STATUS)) {
                                onePageCheckoutResponse = new Gson().fromJson(responseStr, OnePageCheckoutResponse.class);
                                Intent intentRes = new Intent(BROADCAST_START_ONE_PAGE_CHECKOUT_RESPONSE);
                                intentRes.putExtra(BROADCAST_START_ONE_PAGE_CHECKOUT_RESPONSE_STATUS, true);
                                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);                                    //complete

                            } else {
                                Intent intentRes = new Intent(BROADCAST_START_ONE_PAGE_CHECKOUT_RESPONSE);
                                intentRes.putExtra(BROADCAST_START_ONE_PAGE_CHECKOUT_RESPONSE_STATUS, false);
                                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                            }
                        }
                    } catch (IOException e) {
                        Intent intentRes = new Intent(BROADCAST_START_ONE_PAGE_CHECKOUT_RESPONSE);
                        intentRes.putExtra(BROADCAST_START_ONE_PAGE_CHECKOUT_RESPONSE_STATUS, false);
                        LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                        e.printStackTrace();
                    } catch (JSONException e) {
                        Intent intentRes = new Intent(BROADCAST_START_ONE_PAGE_CHECKOUT_RESPONSE);
                        intentRes.putExtra(BROADCAST_START_ONE_PAGE_CHECKOUT_RESPONSE_STATUS, false);
                        LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                        e.printStackTrace();
                    }
                } else {
                    Intent intentRes = new Intent(BROADCAST_START_ONE_PAGE_CHECKOUT_RESPONSE);
                    intentRes.putExtra(BROADCAST_START_ONE_PAGE_CHECKOUT_RESPONSE_STATUS, false);
                    LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Intent intentRes = new Intent(BROADCAST_START_ONE_PAGE_CHECKOUT_RESPONSE);
                intentRes.putExtra(BROADCAST_START_ONE_PAGE_CHECKOUT_RESPONSE_STATUS, false);
                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
            }
        });

    }//ok

    public void opcBillingForm(String request) {
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), request);
        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(mcontext).getApiService().opcBillingForm(body);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                ProgressbarDismiss();
                if (response.body() != null) {
                    try {
                        String responseStr = new String(response.body().bytes());
                        if (!TextUtils.isEmpty(responseStr)) {
                            JSONObject jsonObject = new JSONObject(responseStr);
                            if (jsonObject != null && jsonObject.getBoolean(JSON_RESPONSE_STATUS)) {
                                opcBillingCheckoutResponse = new Gson().fromJson(responseStr, OpcBillingCheckoutResponse.class);
                                Intent intentRes = new Intent(BROADCAST_OPC_BILLING_FORM_CHECKOUT_RESPONSE);
                                intentRes.putExtra(BROADCAST_OPC_BILLING_FORM_CHECKOUT_RESPONSE_STATUS, true);
                                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);                                    //complete

                            } else {
                                Intent intentRes = new Intent(BROADCAST_OPC_BILLING_FORM_CHECKOUT_RESPONSE);
                                intentRes.putExtra(BROADCAST_OPC_BILLING_FORM_CHECKOUT_RESPONSE_STATUS, false);
                                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                            }
                        }
                    } catch (IOException e) {
                        Intent intentRes = new Intent(BROADCAST_OPC_BILLING_FORM_CHECKOUT_RESPONSE);
                        intentRes.putExtra(BROADCAST_OPC_BILLING_FORM_CHECKOUT_RESPONSE_STATUS, false);
                        LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                        e.printStackTrace();
                    } catch (JSONException e) {
                        Intent intentRes = new Intent(BROADCAST_OPC_BILLING_FORM_CHECKOUT_RESPONSE);
                        intentRes.putExtra(BROADCAST_OPC_BILLING_FORM_CHECKOUT_RESPONSE_STATUS, false);
                        LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                        e.printStackTrace();
                    }
                } else {
                    Intent intentRes = new Intent(BROADCAST_OPC_BILLING_FORM_CHECKOUT_RESPONSE);
                    intentRes.putExtra(BROADCAST_OPC_BILLING_FORM_CHECKOUT_RESPONSE_STATUS, false);
                    LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Intent intentRes = new Intent(BROADCAST_OPC_BILLING_FORM_CHECKOUT_RESPONSE);
                intentRes.putExtra(BROADCAST_OPC_BILLING_FORM_CHECKOUT_RESPONSE_STATUS, false);
                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
            }
        });

    }//ok

    public void opcSaveBilling(String request) {
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), request);
        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(mcontext).getApiService().opcSaveBilling(body);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                ProgressbarDismiss();
                if (response.body() != null) {
                    try {
                        String responseStr = new String(response.body().bytes());
                        if (!TextUtils.isEmpty(responseStr)) {
                            JSONObject jsonObject = new JSONObject(responseStr);
                            if (jsonObject != null && jsonObject.getBoolean(JSON_RESPONSE_STATUS)) {
                                   /*
                                * TODO: very important dont forget...*/
                                JSONObject object = jsonObject.getJSONObject("data");
//                                Toast.makeText(mcontext, "" + object.getString("Message"), Toast.LENGTH_SHORT).show();
                                Intent intentRes = new Intent(BROADCAST_OPC_SAVE_BILLING_FORM_CHECKOUT_RESPONSE);
                                intentRes.putExtra(BROADCAST_OPC_SAVE_BILLING_FORM_CHECKOUT_RESPONSE_STATUS, true);
                                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);                                    //complete

                            } else {
                                Toast.makeText(mcontext, "" + jsonObject.getString("errorMessage"), Toast.LENGTH_SHORT).show();
                                Intent intentRes = new Intent(BROADCAST_OPC_SAVE_BILLING_FORM_CHECKOUT_RESPONSE);
                                intentRes.putExtra(BROADCAST_OPC_SAVE_BILLING_FORM_CHECKOUT_RESPONSE_STATUS, false);
                                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                            }
                        }
                    } catch (IOException e) {
                        Intent intentRes = new Intent(BROADCAST_OPC_SAVE_BILLING_FORM_CHECKOUT_RESPONSE);
                        intentRes.putExtra(BROADCAST_OPC_SAVE_BILLING_FORM_CHECKOUT_RESPONSE_STATUS, false);
                        LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                        e.printStackTrace();
                    } catch (JSONException e) {
                        Intent intentRes = new Intent(BROADCAST_OPC_SAVE_BILLING_FORM_CHECKOUT_RESPONSE);
                        intentRes.putExtra(BROADCAST_OPC_SAVE_BILLING_FORM_CHECKOUT_RESPONSE_STATUS, false);
                        LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                        e.printStackTrace();
                    }
                } else {
                    Intent intentRes = new Intent(BROADCAST_OPC_SAVE_BILLING_FORM_CHECKOUT_RESPONSE);
                    intentRes.putExtra(BROADCAST_OPC_SAVE_BILLING_FORM_CHECKOUT_RESPONSE_STATUS, false);
                    LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Intent intentRes = new Intent(BROADCAST_OPC_SAVE_BILLING_FORM_CHECKOUT_RESPONSE);
                intentRes.putExtra(BROADCAST_OPC_SAVE_BILLING_FORM_CHECKOUT_RESPONSE_STATUS, false);
                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
            }
        });

    } //ok

    public void opcSaveShipping(String request) {
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), request);
        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(mcontext).getApiService().opcSaveShipping(body);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                ProgressbarDismiss();
                if (response.body() != null) {
                    try {
                        String responseStr = new String(response.body().bytes());
                        if (!TextUtils.isEmpty(responseStr)) {
                            JSONObject jsonObject = new JSONObject(responseStr);
                            if (jsonObject != null && jsonObject.getBoolean(JSON_RESPONSE_STATUS)) {
                                JSONObject object = jsonObject.getJSONObject("data");
//                                Toast.makeText(mcontext, "" + object.getString("Message"), Toast.LENGTH_SHORT).show();
                                Intent intentRes = new Intent(BROADCAST_OPC_SAVE_SHIPPING_RESPONSE);
                                intentRes.putExtra(BROADCAST_OPC_SAVE_SHIPPING_RESPONSE_STATUS, true);
                                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);                                    //complete

                            } else {
                                Toast.makeText(mcontext, "" + jsonObject.getString("errorMessage"), Toast.LENGTH_SHORT).show();
                                Intent intentRes = new Intent(BROADCAST_OPC_SAVE_SHIPPING_RESPONSE);
                                intentRes.putExtra(BROADCAST_OPC_SAVE_SHIPPING_RESPONSE_STATUS, false);
                                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                            }
                        }
                    } catch (IOException e) {
                        Intent intentRes = new Intent(BROADCAST_OPC_SAVE_SHIPPING_RESPONSE);
                        intentRes.putExtra(BROADCAST_OPC_SAVE_SHIPPING_RESPONSE_STATUS, false);
                        LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                        e.printStackTrace();
                    } catch (JSONException e) {
                        Intent intentRes = new Intent(BROADCAST_OPC_SAVE_SHIPPING_RESPONSE);
                        intentRes.putExtra(BROADCAST_OPC_SAVE_SHIPPING_RESPONSE_STATUS, false);
                        LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                        e.printStackTrace();
                    }
                } else {
                    Intent intentRes = new Intent(BROADCAST_OPC_SAVE_SHIPPING_RESPONSE);
                    intentRes.putExtra(BROADCAST_OPC_SAVE_SHIPPING_RESPONSE_STATUS, false);
                    LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Intent intentRes = new Intent(BROADCAST_OPC_BILLING_FORM_CHECKOUT_RESPONSE);
                intentRes.putExtra(BROADCAST_OPC_SAVE_SHIPPING_RESPONSE_STATUS, false);
                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
            }
        });

    }

    //on payment gateway selection page
    public void opcSaveShippingMethod(String request) {
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), request);
        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(mcontext).getApiService().opcSaveShippingMethod(body);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                ProgressbarDismiss();
                if (response.body() != null) {
                    try {
                        String responseStr = new String(response.body().bytes());
                        if (!TextUtils.isEmpty(responseStr)) {
                            JSONObject jsonObject = new JSONObject(responseStr);
                            if (jsonObject != null && jsonObject.getBoolean(JSON_RESPONSE_STATUS)) {
                                JSONObject object = jsonObject.getJSONObject("data");
//                                Toast.makeText(mcontext, "" + object.getString("Message"), Toast.LENGTH_SHORT).show();
                                Intent intentRes = new Intent(BROADCAST_OPC_SAVE_SHIPPING_METHOD_RESPONSE);
                                intentRes.putExtra(BROADCAST_OPC_SAVE_SHIPPING_METHOD_RESPONSE_STATUS, true);
                                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);                                    //complete

                            } else {
                                Toast.makeText(mcontext, "" + jsonObject.getString("errorMessage"), Toast.LENGTH_SHORT).show();
                                Intent intentRes = new Intent(BROADCAST_OPC_SAVE_SHIPPING_METHOD_RESPONSE);
                                intentRes.putExtra(BROADCAST_OPC_SAVE_SHIPPING_METHOD_RESPONSE_STATUS, false);
                                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                            }
                        }
                    } catch (IOException e) {
                        Intent intentRes = new Intent(BROADCAST_OPC_SAVE_SHIPPING_METHOD_RESPONSE);
                        intentRes.putExtra(BROADCAST_OPC_SAVE_SHIPPING_METHOD_RESPONSE_STATUS, false);
                        LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                        e.printStackTrace();
                    } catch (JSONException e) {
                        Intent intentRes = new Intent(BROADCAST_OPC_SAVE_SHIPPING_METHOD_RESPONSE);
                        intentRes.putExtra(BROADCAST_OPC_SAVE_SHIPPING_METHOD_RESPONSE_STATUS, false);
                        LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                        e.printStackTrace();
                    }
                } else {
                    Intent intentRes = new Intent(BROADCAST_OPC_SAVE_SHIPPING_METHOD_RESPONSE);
                    intentRes.putExtra(BROADCAST_OPC_SAVE_SHIPPING_METHOD_RESPONSE_STATUS, false);
                    LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Intent intentRes = new Intent(BROADCAST_OPC_SAVE_SHIPPING_METHOD_RESPONSE);
                intentRes.putExtra(BROADCAST_OPC_SAVE_SHIPPING_METHOD_RESPONSE_STATUS, false);
                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
            }
        });

    }

    public void opcSavePaymentMethod(String request) {
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), request);
        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(mcontext).getApiService().opcSavePaymentMethod(body);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                ProgressbarDismiss();
                if (response.body() != null) {
                    try {
                        String responseStr = new String(response.body().bytes());
                        if (!TextUtils.isEmpty(responseStr)) {
                            JSONObject jsonObject = new JSONObject(responseStr);
                            if (jsonObject != null && jsonObject.getBoolean(JSON_RESPONSE_STATUS)) {
                                JSONObject object = jsonObject.getJSONObject("data");
//                                Toast.makeText(mcontext, "" + object.getString("Message"), Toast.LENGTH_SHORT).show();
                                Intent intentRes = new Intent(BROADCAST_OPC_SAVE_PAYMENT_METHOD_RESPONSE);
                                intentRes.putExtra(BROADCAST_OPC_SAVE_PAYMENT_METHOD_RESPONSE_STATUS, true);

                                if (object.has("paymentInfo")) {
                                    intentRes.putExtra(BROADCAST_OPC_SAVE_PAYMENT_METHOD_RESPONSE_MESSAGE, object.getString("paymentInfo"));
                                } else if (object.has("Message")) {
                                    intentRes.putExtra(BROADCAST_OPC_SAVE_PAYMENT_METHOD_RESPONSE_MESSAGE, object.getString("Message"));
                                }

                                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                                //complete
                                return;
                            } else {
                                Toast.makeText(mcontext, "" + jsonObject.getString("errorMessage"), Toast.LENGTH_SHORT).show();
                                Intent intentRes = new Intent(BROADCAST_OPC_SAVE_PAYMENT_METHOD_RESPONSE);
                                intentRes.putExtra(BROADCAST_OPC_SAVE_PAYMENT_METHOD_RESPONSE_STATUS, false);
                                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                            }
                        }
                    } catch (IOException e) {
                        Intent intentRes = new Intent(BROADCAST_OPC_SAVE_PAYMENT_METHOD_RESPONSE);
                        intentRes.putExtra(BROADCAST_OPC_SAVE_PAYMENT_METHOD_RESPONSE_STATUS, false);
                        LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                        e.printStackTrace();
                    } catch (JSONException e) {
                        Intent intentRes = new Intent(BROADCAST_OPC_SAVE_PAYMENT_METHOD_RESPONSE);
                        intentRes.putExtra(BROADCAST_OPC_SAVE_PAYMENT_METHOD_RESPONSE_STATUS, false);
                        LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                        e.printStackTrace();
                    }
                } else {
                    Intent intentRes = new Intent(BROADCAST_OPC_SAVE_PAYMENT_METHOD_RESPONSE);
                    intentRes.putExtra(BROADCAST_OPC_SAVE_PAYMENT_METHOD_RESPONSE_STATUS, false);
                    LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                ProgressbarDismiss();
                Intent intentRes = new Intent(BROADCAST_OPC_SAVE_PAYMENT_METHOD_RESPONSE);
                intentRes.putExtra(BROADCAST_OPC_SAVE_PAYMENT_METHOD_RESPONSE_STATUS, false);
                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
            }
        });

    }

    public void opcSavePaymentInfo(String request) {
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), request);
        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(mcontext).getApiService().opcSavePaymentInfo(body);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                ProgressbarDismiss();
                if (response.body() != null) {
                    try {
                        String responseStr = new String(response.body().bytes());
                        if (!TextUtils.isEmpty(responseStr)) {
                            JSONObject jsonObject = new JSONObject(responseStr);
                            if (jsonObject != null && jsonObject.getBoolean(JSON_RESPONSE_STATUS)) {
                                JSONObject object = jsonObject.getJSONObject("data");
//                                Toast.makeText(mcontext, "" + object.getString("Message"), Toast.LENGTH_SHORT).show();

                                savePaymentInfoResponse = new Gson().fromJson(responseStr, SavePaymentInfoResponse.class);
                                Intent intentRes = new Intent(BROADCAST_OPC_SAVE_PAYMENT_INFO_RESPONSE);
                                intentRes.putExtra(BROADCAST_OPC_SAVE_PAYMENT_INFO_RESPONSE_STATUS, true);
                                intentRes.putExtra(BROADCAST_OPC_SAVE_PAYMENT_INFO_RESPONSE_MESSAGE, savePaymentInfoResponse.getData().getMessage());
                                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                                //complete

                            } else {
                                Toast.makeText(mcontext, "" + jsonObject.getString("errorMessage"), Toast.LENGTH_SHORT).show();
                                Intent intentRes = new Intent(BROADCAST_OPC_SAVE_PAYMENT_INFO_RESPONSE);
                                intentRes.putExtra(BROADCAST_OPC_SAVE_PAYMENT_INFO_RESPONSE_STATUS, false);
                                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                            }
                        }
                    } catch (IOException e) {
                        Intent intentRes = new Intent(BROADCAST_OPC_SAVE_PAYMENT_INFO_RESPONSE);
                        intentRes.putExtra(BROADCAST_OPC_SAVE_PAYMENT_INFO_RESPONSE_STATUS, false);
                        LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                        e.printStackTrace();
                    } catch (JSONException e) {
                        Intent intentRes = new Intent(BROADCAST_OPC_SAVE_PAYMENT_INFO_RESPONSE);
                        intentRes.putExtra(BROADCAST_OPC_SAVE_PAYMENT_INFO_RESPONSE_STATUS, false);
                        LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                        e.printStackTrace();
                    }
                } else {
                    Intent intentRes = new Intent(BROADCAST_OPC_SAVE_PAYMENT_INFO_RESPONSE);
                    intentRes.putExtra(BROADCAST_OPC_SAVE_PAYMENT_INFO_RESPONSE_STATUS, false);
                    LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Intent intentRes = new Intent(BROADCAST_OPC_SAVE_PAYMENT_INFO_RESPONSE);
                intentRes.putExtra(BROADCAST_OPC_SAVE_PAYMENT_INFO_RESPONSE_STATUS, false);
                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
            }
        });

    }

    public void opcConfirmOrder(String request) {
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), request);
        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(mcontext).getApiService().opcConfirmOrder(body);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                ProgressbarDismiss();
                if (response.body() != null) {
                    try {
                        String responseStr = new String(response.body().bytes());
                        if (!TextUtils.isEmpty(responseStr)) {
                            JSONObject jsonObject = new JSONObject(responseStr);
                            if (jsonObject != null && jsonObject.getBoolean(JSON_RESPONSE_STATUS)) {
                                JSONObject object = jsonObject.getJSONObject("data");
//                                Toast.makeText(mcontext, "" + object.getString("Message"), Toast.LENGTH_SHORT).show();
                                Intent intentRes = new Intent(BROADCAST_OPC_CONFIRM_ORDER_RESPONSE);
                                intentRes.putExtra(BROADCAST_OPC_CONFIRM_ORDER_RESPONSE_STATUS, true);
                                intentRes.putExtra(BROADCAST_OPC_CONFIRM_ORDER_RESPONSE_STATUS_MESSAGE, object.getString("Message"));
                                intentRes.putExtra(BROADCAST_OPC_CONFIRM_ORDER_NUMBER, object.getString("OrderNo"));
                                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                                //complete

                            } else {
                                Toast.makeText(mcontext, "" + jsonObject.getString("errorMessage"), Toast.LENGTH_SHORT).show();
                                Intent intentRes = new Intent(BROADCAST_OPC_CONFIRM_ORDER_RESPONSE);
                                intentRes.putExtra(BROADCAST_OPC_CONFIRM_ORDER_RESPONSE_STATUS, false);
                                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                            }
                        }
                    } catch (IOException e) {
                        Intent intentRes = new Intent(BROADCAST_OPC_CONFIRM_ORDER_RESPONSE);
                        intentRes.putExtra(BROADCAST_OPC_CONFIRM_ORDER_RESPONSE_STATUS, false);
                        LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                        e.printStackTrace();
                    } catch (JSONException e) {
                        Intent intentRes = new Intent(BROADCAST_OPC_CONFIRM_ORDER_RESPONSE);
                        intentRes.putExtra(BROADCAST_OPC_CONFIRM_ORDER_RESPONSE_STATUS, false);
                        LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                        e.printStackTrace();
                    }
                } else {
                    Intent intentRes = new Intent(BROADCAST_OPC_CONFIRM_ORDER_RESPONSE);
                    intentRes.putExtra(BROADCAST_OPC_CONFIRM_ORDER_RESPONSE_STATUS, false);
                    LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Intent intentRes = new Intent(BROADCAST_OPC_CONFIRM_ORDER_RESPONSE);
                intentRes.putExtra(BROADCAST_OPC_CONFIRM_ORDER_RESPONSE_STATUS, false);
                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
            }
        });

    }

    public void opcGetCredimaxSession(String request) {
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), request);
        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(mcontext).getCrediMaxApiService().opcGetCredimaxSession(body);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                ProgressbarDismiss();
                if (response.body() != null) {
                    try {
                        String responseStr = new String(response.body().bytes());
                        if (!TextUtils.isEmpty(responseStr)) {
                            JSONObject jsonObject = new JSONObject(responseStr);
                            if (jsonObject != null && jsonObject.getBoolean(JSON_RESPONSE_STATUS)) {
                                JSONObject object = jsonObject.getJSONObject("data");
                                Intent intentRes = new Intent(BROADCAST_GET_CREDIMAX_PAYMENT_SESSION);
                                intentRes.putExtra(BROADCAST_OPC_CREDIMAX_SESSION_STATUS, true);
                                intentRes.putExtra(BROADCAST_OPC_CREDIMAX_SESSION_ID, object.getString("sessionId"));
                                intentRes.putExtra(BROADCAST_OPC_CREDIMAX_SESSION_VERSION, object.getString("sessionVersion"));
                                intentRes.putExtra(BROADCAST_OPC_CREDIMAX_MERCHANT_ID, object.getString("merchant"));
                                intentRes.putExtra(BROADCAST_OPC_CREDIMAX_API_VERSION, object.getString("apiVersion"));
                                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                                //complete
                            } else {
                                Toast.makeText(mcontext, "" + jsonObject.getString("errorMessage"), Toast.LENGTH_SHORT).show();
                                Intent intentRes = new Intent(BROADCAST_GET_CREDIMAX_PAYMENT_SESSION);
                                intentRes.putExtra(BROADCAST_OPC_CREDIMAX_SESSION_STATUS, false);
                                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                            }
                        }
                    } catch (IOException e) {
                        Intent intentRes = new Intent(BROADCAST_GET_CREDIMAX_PAYMENT_SESSION);
                        intentRes.putExtra(BROADCAST_OPC_CREDIMAX_SESSION_STATUS, false);
                        LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                        e.printStackTrace();
                    } catch (JSONException e) {
                        Intent intentRes = new Intent(BROADCAST_GET_CREDIMAX_PAYMENT_SESSION);
                        intentRes.putExtra(BROADCAST_OPC_CREDIMAX_SESSION_STATUS, false);
                        LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                        e.printStackTrace();
                    }
                } else {
                    Intent intentRes = new Intent(BROADCAST_GET_CREDIMAX_PAYMENT_SESSION);
                    intentRes.putExtra(BROADCAST_OPC_CREDIMAX_SESSION_STATUS, false);
                    LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Intent intentRes = new Intent(BROADCAST_GET_CREDIMAX_PAYMENT_SESSION);
                intentRes.putExtra(BROADCAST_OPC_CREDIMAX_SESSION_STATUS, false);
                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
            }
        });

    }

    public void opcCompleteTransaction(String request) {
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), request);
        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(mcontext).getCrediMaxApiService().opcCompleteTransaction(body);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                ProgressbarDismiss();
                if (response.body() != null) {
                    try {
                        String responseStr = new String(response.body().bytes());
                        if (!TextUtils.isEmpty(responseStr)) {
                            JSONObject jsonObject = new JSONObject(responseStr);
                            if (jsonObject != null && jsonObject.getBoolean(JSON_RESPONSE_STATUS)) {
                                JSONObject object = jsonObject.getJSONObject("data");
                                Intent intentRes = new Intent(BROADCAST_OPC_COMPLETE_TRANSACTION_RESPONSE);
                                intentRes.putExtra(BROADCAST_OPC_CREDIMAX_COMPLETE_TRANSACTION_STATUS, true);
                                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                                //complete
                            } else {
                                Toast.makeText(mcontext, "" + jsonObject.getString("errorMessage"), Toast.LENGTH_SHORT).show();
                                Intent intentRes = new Intent(BROADCAST_OPC_COMPLETE_TRANSACTION_RESPONSE);
                                intentRes.putExtra(BROADCAST_OPC_CREDIMAX_COMPLETE_TRANSACTION_STATUS, false);
                                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                            }
                        }
                    } catch (IOException e) {
                        Intent intentRes = new Intent(BROADCAST_OPC_COMPLETE_TRANSACTION_RESPONSE);
                        intentRes.putExtra(BROADCAST_OPC_CREDIMAX_COMPLETE_TRANSACTION_STATUS, false);
                        LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                        e.printStackTrace();
                    } catch (JSONException e) {
                        Intent intentRes = new Intent(BROADCAST_OPC_COMPLETE_TRANSACTION_RESPONSE);
                        intentRes.putExtra(BROADCAST_OPC_CREDIMAX_COMPLETE_TRANSACTION_STATUS, false);
                        LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                        e.printStackTrace();
                    }
                } else {
                    Intent intentRes = new Intent(BROADCAST_OPC_COMPLETE_TRANSACTION_RESPONSE);
                    intentRes.putExtra(BROADCAST_OPC_CREDIMAX_COMPLETE_TRANSACTION_STATUS, false);
                    LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Intent intentRes = new Intent(BROADCAST_OPC_COMPLETE_TRANSACTION_RESPONSE);
                intentRes.putExtra(BROADCAST_OPC_CREDIMAX_COMPLETE_TRANSACTION_STATUS, false);
                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
            }
        });

    }

    public void ProgressbarDismiss() {
        Intent intent = new Intent(Constants.Login.BROADCAST_PROGRESS_WHEEL);
        intent.putExtra(Constants.Login.BROADCAST_ISDISPLAY_PROGRESS_WHEEL, false);
        LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intent);
    }

    public void setItemQuantityList(List<ItemQuantityCheckout> itemQuantityList) {
        this.itemQuantityList = itemQuantityList;
    }

    public List<ItemQuantityCheckout> getItemQuantityList() {
        return itemQuantityList;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }
}
