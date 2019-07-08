package com.mobtecnica.wafiapps.manager;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mobtecnica.wafiapps.model.wafiEats.Offers.AllOffer;
import com.mobtecnica.wafiapps.model.wafiEats.Offers.EatsGetAllOffersResponse;
import com.mobtecnica.wafiapps.model.wafiEats.addToCart.AddToEatsCartRequest;
import com.mobtecnica.wafiapps.model.wafiEats.cartRestaurant.CartItem;
import com.mobtecnica.wafiapps.model.wafiEats.cartRestaurant.CartListingEatsResponse;
import com.mobtecnica.wafiapps.model.wafiEats.cartRestaurant.CartRequest;
import com.mobtecnica.wafiapps.model.wafiEats.cartRestaurant.removeFromCart.RemoveFromEatsCartRequest;
import com.mobtecnica.wafiapps.model.wafiEats.getAllRestaurants.EatsRestrauntsResponse;
import com.mobtecnica.wafiapps.model.wafiEats.getAllrestaruntsDetails.EatsRestrauntsDetailsResponse;
import com.mobtecnica.wafiapps.model.wafiEats.getAllrestaruntsDetails.GetRestaurantsDetailsRequest;
import com.mobtecnica.wafiapps.model.wafiEats.getMenuOptions.GetMenuOptionsRequest;
import com.mobtecnica.wafiapps.model.wafiEats.getMenuOptions.GetMenuOptionsResponse;
import com.mobtecnica.wafiapps.model.wafiEats.getRestaurants.EatsGetResturantsResponse;
import com.mobtecnica.wafiapps.model.wafiEats.getRestaurants.GetResturantsRequest;
import com.mobtecnica.wafiapps.model.wafiEats.getRestaurants.RestaurantsInArea;
import com.mobtecnica.wafiapps.model.wafiEats.getRestaurantsMenu.CustomModelForExpandableLIst;
import com.mobtecnica.wafiapps.model.wafiEats.getRestaurantsMenu.GetRestaurantMenuRequest;
import com.mobtecnica.wafiapps.model.wafiEats.getRestaurantsMenu.GetRestaurantMenuResponse;
import com.mobtecnica.wafiapps.model.wafiEats.getRestaurantsMenu.MenuData;
import com.mobtecnica.wafiapps.model.wafiEats.home.EatsHomeResponse;
import com.mobtecnica.wafiapps.model.wafiEats.home.HomeRequest;
import com.mobtecnica.wafiapps.model.wafiEats.home.LstCuisine;
import com.mobtecnica.wafiapps.model.wafiEats.home.LstLocation;
import com.mobtecnica.wafiapps.utils.Constants;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by SIby on 05-05-2017.
 */

public class WafiEatsManager {

    public static final String JSON_RESPONSE_STATUS = "success";
    //HOME
    public static final String BROADCAST_EATS_HOME_RESPONSE = "BROADCAST_EATS_HOME_RESPONSE";
    public static final String BROADCAST_EATS_HOME_RESPONSE_STATUS = "BROADCAST_EATS_HOME_RESPONSE_STATUS";

    public static final String BROADCAST_EATS_RESTAURANTS_RESPONSE = "BROADCAST_EATS_RESTAURANTS_RESPONSE";
    public static final String BROADCAST_EATS_RESTAURANTS_RESPONSE_STATUS = "BROADCAST_EATS_RESTAURANTS_RESPONSE_STATUS";

    public static final String BROADCAST_EATS_RESTAURANTS_DETAILS_RESPONSE = "BROADCAST_EATS_RESTAURANTS_DETAILS_RESPONSE";
    public static final String BROADCAST_EATS_RESTAURANTS_DETAILS_RESPONSE_STATUS = "BROADCAST_EATS_RESTAURANTS_DETAILS_RESPONSE_STATUS";

    public static final String BROADCAST_EATS_OFFERS_RESPONSE = "BROADCAST_EATS_OFFERS_RESPONSE";
    public static final String BROADCAST_EATS_OFFERS_RESPONSE_STATUS = "BROADCAST_EATS_OFFERS_RESPONSE_STATUS";

    public static final String BROADCAST_EATS_GET_RESTAURANTS_RESPONSE = "BROADCAST_EATS_GET_RESTAURANTS_RESPONSE";
    public static final String BROADCAST_EATS_GET_RESTAURANTS_RESPONSE_STATUS = "BROADCAST_EATS_GET_RESTAURANTS_RESPONSE_STATUS";

    public static final String BROADCAST_EATS_GET_RESTAURANT_MENU_RESPONSE = "BROADCAST_EATS_GET_RESTAURANT_MENU_RESPONSE";
    public static final String BROADCAST_EATS_GET_RESTAURANT_MENU_RESPONSE_STATUS = "BROADCAST_EATS_GET_RESTAURANT_MENU_RESPONSE_STATUS";

    public static final String BROADCAST_EATS_GET_MENU_OPTIONS_RESPONSE = "BROADCAST_EATS_GET_MENU_OPTIONS_RESPONSE";
    public static final String BROADCAST_EATS_GET_MENU_OPTIONS_RESPONSE_STATUS = "BROADCAST_EATS_GET_MENU_OPTIONS_RESPONSE_STATUS";

    public static final String BROADCAST_EATS_CART_RESPONSE = "BROADCAST_EATS_CART_RESPONSE";
    public static final String BROADCAST_EATS_CART_RESPONSE_STATUS = "BROADCAST_EATS_CART_RESPONSE_STATUS";

    public static final String BROADCAST_REMOVE_EATS_CART_RESPONSE = "BROADCAST_REMOVE_EATS_CART_RESPONSE";
    public static final String BROADCAST_REMOVE_EATS_CART_RESPONSE_STATUS = "BROADCAST_REMOVE_EATS_CART_RESPONSE_STATUS";

    public static final String BROADCAST_ADD_TO_CART_EATS = "BROADCAST_ADD_TO_CART_EATS";
    public static final String BROADCAST_ADD_TO_CART_EATS_STATUS = "BROADCAST_ADD_TO_CART_EATS_STATUS";
    public static final String BROADCAST_ADD_TO_CART_EATS_MESSAGE = "BROADCAST_ADD_TO_CART_EATS_MESSAGE";

    public EatsHomeResponse eatsHomeResponse = null;
    public EatsRestrauntsResponse eatsRestrauntsResponse = null;
    public EatsRestrauntsDetailsResponse eatsRestrauntsDetailsResponse = null;
    public EatsGetAllOffersResponse eatsGetAllOffersResponse = null;
    public EatsGetResturantsResponse eatsGetResturantsResponse = null;
    public GetRestaurantMenuResponse getRestaurantMenuResponse = null;
    public GetMenuOptionsResponse menuOptionsResponse = null;
    CartListingEatsResponse cartListingEatsResponse = null;
    ArrayList<String> checked_items = new ArrayList<>();
    ArrayList<CustomModelForExpandableLIst> customModelForExpandableLIsts = new ArrayList<>();
    private Context context;

    public WafiEatsManager(Context context) {
        this.context = context.getApplicationContext();
    }

    public void updateContext(Context context) {
        if (context != null)
            this.context = context.getApplicationContext();
    }

    /*EATS HOME */
    public void eatsHome(HomeRequest request) {
        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(context).getEatsApiService().eatsHome(request);
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
                                eatsHomeResponse = new Gson().fromJson(responseStr, EatsHomeResponse.class);
                                Intent intentRes = new Intent(BROADCAST_EATS_HOME_RESPONSE);
                                intentRes.putExtra(BROADCAST_EATS_HOME_RESPONSE_STATUS, true);
                                LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);                                    //complete

                            } else {
                                Intent intentRes = new Intent(BROADCAST_EATS_HOME_RESPONSE);
                                intentRes.putExtra(BROADCAST_EATS_HOME_RESPONSE_STATUS, false);
                                LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
                            }
                        }
                    } catch (IOException e) {
                        Intent intentRes = new Intent(BROADCAST_EATS_HOME_RESPONSE);
                        intentRes.putExtra(BROADCAST_EATS_HOME_RESPONSE_STATUS, false);
                        LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
                        e.printStackTrace();
                    } catch (JSONException e) {
                        Intent intentRes = new Intent(BROADCAST_EATS_HOME_RESPONSE);
                        intentRes.putExtra(BROADCAST_EATS_HOME_RESPONSE_STATUS, false);
                        LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
                        e.printStackTrace();
                    }
                } else {
                    Intent intentRes = new Intent(BROADCAST_EATS_HOME_RESPONSE);
                    intentRes.putExtra(BROADCAST_EATS_HOME_RESPONSE_STATUS, false);
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ProgressbarDismiss();
                Intent intentRes = new Intent(BROADCAST_EATS_HOME_RESPONSE);
                intentRes.putExtra(BROADCAST_EATS_HOME_RESPONSE_STATUS, false);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
            }
        });

    }

    /*eATS RESTAURANTS */
    public void eatsGetAllRestarunts(HomeRequest request) {
        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(context).getEatsApiService().eatsGetAllRestaurants(request);
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
                                eatsRestrauntsResponse = new Gson().fromJson(responseStr, EatsRestrauntsResponse.class);
                                Intent intentRes = new Intent(BROADCAST_EATS_RESTAURANTS_RESPONSE);
                                intentRes.putExtra(BROADCAST_EATS_RESTAURANTS_RESPONSE_STATUS, true);
                                LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);                                    //complete

                            } else {
                                Intent intentRes = new Intent(BROADCAST_EATS_RESTAURANTS_RESPONSE);
                                intentRes.putExtra(BROADCAST_EATS_RESTAURANTS_RESPONSE_STATUS, false);
                                LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
                            }
                        }
                    } catch (IOException e) {
                        Intent intentRes = new Intent(BROADCAST_EATS_RESTAURANTS_RESPONSE);
                        intentRes.putExtra(BROADCAST_EATS_RESTAURANTS_RESPONSE_STATUS, false);
                        LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
                        e.printStackTrace();
                    } catch (JSONException e) {
                        Intent intentRes = new Intent(BROADCAST_EATS_RESTAURANTS_RESPONSE);
                        intentRes.putExtra(BROADCAST_EATS_RESTAURANTS_RESPONSE_STATUS, false);
                        LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
                        e.printStackTrace();
                    }
                } else {
                    Intent intentRes = new Intent(BROADCAST_EATS_RESTAURANTS_RESPONSE);
                    intentRes.putExtra(BROADCAST_EATS_RESTAURANTS_RESPONSE_STATUS, false);
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ProgressbarDismiss();
                Intent intentRes = new Intent(BROADCAST_EATS_RESTAURANTS_RESPONSE);
                intentRes.putExtra(BROADCAST_EATS_RESTAURANTS_RESPONSE_STATUS, false);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
            }
        });

    }

    /*eats resturants details*/
    public void eatsGetAllRestaruntsDetails(GetRestaurantsDetailsRequest request) {
        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(context).getEatsApiService().getRestaurantDetail(request);
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
                                eatsRestrauntsDetailsResponse = new Gson().fromJson(responseStr, EatsRestrauntsDetailsResponse.class);
                                Intent intentRes = new Intent(BROADCAST_EATS_RESTAURANTS_DETAILS_RESPONSE);
                                intentRes.putExtra(BROADCAST_EATS_RESTAURANTS_DETAILS_RESPONSE_STATUS, true);
                                LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);                                    //complete

                            } else {
                                Intent intentRes = new Intent(BROADCAST_EATS_RESTAURANTS_DETAILS_RESPONSE);
                                intentRes.putExtra(BROADCAST_EATS_RESTAURANTS_DETAILS_RESPONSE_STATUS, false);
                                LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
                            }
                        }
                    } catch (IOException e) {
                        Intent intentRes = new Intent(BROADCAST_EATS_RESTAURANTS_DETAILS_RESPONSE);
                        intentRes.putExtra(BROADCAST_EATS_RESTAURANTS_DETAILS_RESPONSE_STATUS, false);
                        LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
                        e.printStackTrace();
                    } catch (JSONException e) {
                        Intent intentRes = new Intent(BROADCAST_EATS_RESTAURANTS_DETAILS_RESPONSE);
                        intentRes.putExtra(BROADCAST_EATS_RESTAURANTS_DETAILS_RESPONSE_STATUS, false);
                        LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
                        e.printStackTrace();
                    }
                } else {
                    Intent intentRes = new Intent(BROADCAST_EATS_RESTAURANTS_DETAILS_RESPONSE);
                    intentRes.putExtra(BROADCAST_EATS_RESTAURANTS_DETAILS_RESPONSE_STATUS, false);
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ProgressbarDismiss();
                Intent intentRes = new Intent(BROADCAST_EATS_RESTAURANTS_DETAILS_RESPONSE);
                intentRes.putExtra(BROADCAST_EATS_RESTAURANTS_DETAILS_RESPONSE_STATUS, false);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
            }
        });

    }

    /*get all offers*/
    public void getAllOffers(HomeRequest request) {
        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(context).getEatsApiService().eatsGetAllOffers(request);
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
                                eatsGetAllOffersResponse = new Gson().fromJson(responseStr, EatsGetAllOffersResponse.class);
                                Intent intentRes = new Intent(BROADCAST_EATS_OFFERS_RESPONSE);
                                intentRes.putExtra(BROADCAST_EATS_OFFERS_RESPONSE_STATUS, true);
                                LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);                                    //complete

                            } else {
                                Intent intentRes = new Intent(BROADCAST_EATS_OFFERS_RESPONSE);
                                intentRes.putExtra(BROADCAST_EATS_OFFERS_RESPONSE_STATUS, false);
                                LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
                            }
                        }
                    } catch (IOException e) {
                        Intent intentRes = new Intent(BROADCAST_EATS_OFFERS_RESPONSE);
                        intentRes.putExtra(BROADCAST_EATS_OFFERS_RESPONSE_STATUS, false);
                        LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
                        e.printStackTrace();
                    } catch (JSONException e) {
                        Intent intentRes = new Intent(BROADCAST_EATS_OFFERS_RESPONSE);
                        intentRes.putExtra(BROADCAST_EATS_OFFERS_RESPONSE_STATUS, false);
                        LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
                        e.printStackTrace();
                    }
                } else {
                    Intent intentRes = new Intent(BROADCAST_EATS_OFFERS_RESPONSE);
                    intentRes.putExtra(BROADCAST_EATS_OFFERS_RESPONSE_STATUS, false);
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ProgressbarDismiss();
                Intent intentRes = new Intent(BROADCAST_EATS_HOME_RESPONSE);
                intentRes.putExtra(BROADCAST_EATS_OFFERS_RESPONSE_STATUS, false);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
            }
        });

    }

    public void getWafiEatsCart(CartRequest request) {
        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(context).getEatsApiService().eatsGetCart(request);
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
                                cartListingEatsResponse = new Gson().fromJson(responseStr, CartListingEatsResponse.class);
                                Intent intentRes = new Intent(BROADCAST_EATS_CART_RESPONSE);
                                intentRes.putExtra(BROADCAST_EATS_CART_RESPONSE_STATUS, true);
                                LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);                                    //complete

                            } else {
                                Intent intentRes = new Intent(BROADCAST_EATS_CART_RESPONSE);
                                intentRes.putExtra(BROADCAST_EATS_CART_RESPONSE_STATUS, false);
                                LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
                            }
                        }
                    } catch (IOException e) {
                        Intent intentRes = new Intent(BROADCAST_EATS_CART_RESPONSE);
                        intentRes.putExtra(BROADCAST_EATS_CART_RESPONSE_STATUS, false);
                        LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
                        e.printStackTrace();
                    } catch (JSONException e) {
                        Intent intentRes = new Intent(BROADCAST_EATS_CART_RESPONSE);
                        intentRes.putExtra(BROADCAST_EATS_CART_RESPONSE_STATUS, false);
                        LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
                        e.printStackTrace();
                    }
                } else {
                    Intent intentRes = new Intent(BROADCAST_EATS_CART_RESPONSE);
                    intentRes.putExtra(BROADCAST_EATS_CART_RESPONSE_STATUS, false);
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ProgressbarDismiss();
                Intent intentRes = new Intent(BROADCAST_EATS_CART_RESPONSE);
                intentRes.putExtra(BROADCAST_EATS_CART_RESPONSE_STATUS, false);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
            }
        });

    }

    public void removeFromCart(RemoveFromEatsCartRequest request) {
        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(context).getEatsApiService().removeFromEatsCart(request);
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
                                Intent intentRes = new Intent(BROADCAST_REMOVE_EATS_CART_RESPONSE);
                                intentRes.putExtra(BROADCAST_REMOVE_EATS_CART_RESPONSE_STATUS, true);
                                LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);                                    //complete

                            } else {
                                Intent intentRes = new Intent(BROADCAST_REMOVE_EATS_CART_RESPONSE);
                                intentRes.putExtra(BROADCAST_REMOVE_EATS_CART_RESPONSE_STATUS, false);
                                LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
                            }
                        }
                    } catch (IOException e) {
                        Intent intentRes = new Intent(BROADCAST_REMOVE_EATS_CART_RESPONSE);
                        intentRes.putExtra(BROADCAST_REMOVE_EATS_CART_RESPONSE_STATUS, false);
                        LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
                        e.printStackTrace();
                    } catch (JSONException e) {
                        Intent intentRes = new Intent(BROADCAST_REMOVE_EATS_CART_RESPONSE);
                        intentRes.putExtra(BROADCAST_REMOVE_EATS_CART_RESPONSE_STATUS, false);
                        LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
                        e.printStackTrace();
                    }
                } else {
                    Intent intentRes = new Intent(BROADCAST_REMOVE_EATS_CART_RESPONSE);
                    intentRes.putExtra(BROADCAST_REMOVE_EATS_CART_RESPONSE_STATUS, false);
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ProgressbarDismiss();
                Intent intentRes = new Intent(BROADCAST_REMOVE_EATS_CART_RESPONSE);
                intentRes.putExtra(BROADCAST_REMOVE_EATS_CART_RESPONSE_STATUS, false);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
            }
        });

    }

    /**/
    public void eatsRestaurants(GetResturantsRequest request) {
        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(context).getEatsApiService().eatsRestaurants(request);
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
                                eatsGetResturantsResponse = new Gson().fromJson(responseStr, EatsGetResturantsResponse.class);
                                Intent intentRes = new Intent(BROADCAST_EATS_GET_RESTAURANTS_RESPONSE);
                                intentRes.putExtra(BROADCAST_EATS_GET_RESTAURANTS_RESPONSE_STATUS, true);
                                LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
                                //complete

                            } else {
                                Intent intentRes = new Intent(BROADCAST_EATS_GET_RESTAURANTS_RESPONSE);
                                intentRes.putExtra(BROADCAST_EATS_GET_RESTAURANTS_RESPONSE_STATUS, false);
                                LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
                            }
                        }
                    } catch (IOException e) {
                        Intent intentRes = new Intent(BROADCAST_EATS_GET_RESTAURANTS_RESPONSE);
                        intentRes.putExtra(BROADCAST_EATS_GET_RESTAURANTS_RESPONSE_STATUS, false);
                        LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
                        e.printStackTrace();
                    } catch (JSONException e) {
                        Intent intentRes = new Intent(BROADCAST_EATS_GET_RESTAURANTS_RESPONSE);
                        intentRes.putExtra(BROADCAST_EATS_GET_RESTAURANTS_RESPONSE_STATUS, false);
                        LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
                        e.printStackTrace();
                    }
                } else {
                    Intent intentRes = new Intent(BROADCAST_EATS_GET_RESTAURANTS_RESPONSE);
                    intentRes.putExtra(BROADCAST_EATS_GET_RESTAURANTS_RESPONSE_STATUS, false);
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ProgressbarDismiss();
                Intent intentRes = new Intent(BROADCAST_EATS_HOME_RESPONSE);
                intentRes.putExtra(BROADCAST_EATS_OFFERS_RESPONSE_STATUS, false);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
            }
        });

    }

    /*RESTAURANTS MENU RESPONSE*/
    public void eatsRestaurantsMenu(GetRestaurantMenuRequest request) {
        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(context).getEatsApiService().getRestaurantsMenu(request);
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
                                getRestaurantMenuResponse = new Gson().fromJson(responseStr, GetRestaurantMenuResponse.class);
                                Intent intentRes = new Intent(BROADCAST_EATS_GET_RESTAURANT_MENU_RESPONSE);
                                intentRes.putExtra(BROADCAST_EATS_GET_RESTAURANT_MENU_RESPONSE_STATUS, true);
                                LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
                                //complete

                            } else {
                                Intent intentRes = new Intent(BROADCAST_EATS_GET_RESTAURANT_MENU_RESPONSE);
                                intentRes.putExtra(BROADCAST_EATS_GET_RESTAURANT_MENU_RESPONSE_STATUS, false);
                                LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
                            }
                        }
                    } catch (IOException e) {
                        Intent intentRes = new Intent(BROADCAST_EATS_GET_RESTAURANT_MENU_RESPONSE);
                        intentRes.putExtra(BROADCAST_EATS_GET_RESTAURANT_MENU_RESPONSE_STATUS, false);
                        LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
                        e.printStackTrace();
                    } catch (JSONException e) {
                        Intent intentRes = new Intent(BROADCAST_EATS_GET_RESTAURANT_MENU_RESPONSE);
                        intentRes.putExtra(BROADCAST_EATS_GET_RESTAURANT_MENU_RESPONSE_STATUS, false);
                        LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
                        e.printStackTrace();
                    }
                } else {
                    Intent intentRes = new Intent(BROADCAST_EATS_GET_RESTAURANT_MENU_RESPONSE);
                    intentRes.putExtra(BROADCAST_EATS_GET_RESTAURANT_MENU_RESPONSE_STATUS, false);
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ProgressbarDismiss();
                Intent intentRes = new Intent(BROADCAST_EATS_GET_RESTAURANT_MENU_RESPONSE);
                intentRes.putExtra(BROADCAST_EATS_GET_RESTAURANT_MENU_RESPONSE_STATUS, false);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
            }
        });

    }

    /*GetMenuOptions*/
    public void getMenuOptions(GetMenuOptionsRequest request) {
        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(context).getEatsApiService().getMenuOptions(request);
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
                                menuOptionsResponse = new Gson().fromJson(responseStr, GetMenuOptionsResponse.class);
                                Intent intentRes = new Intent(BROADCAST_EATS_GET_MENU_OPTIONS_RESPONSE);
                                intentRes.putExtra(BROADCAST_EATS_GET_MENU_OPTIONS_RESPONSE_STATUS, true);
                                LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
                                //complete

                            } else {
                                Intent intentRes = new Intent(BROADCAST_EATS_GET_MENU_OPTIONS_RESPONSE);
                                intentRes.putExtra(BROADCAST_EATS_GET_MENU_OPTIONS_RESPONSE_STATUS, false);
                                LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
                            }
                        }
                    } catch (IOException e) {
                        Intent intentRes = new Intent(BROADCAST_EATS_GET_MENU_OPTIONS_RESPONSE);
                        intentRes.putExtra(BROADCAST_EATS_GET_MENU_OPTIONS_RESPONSE_STATUS, false);
                        LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
                        e.printStackTrace();
                    } catch (JSONException e) {
                        Intent intentRes = new Intent(BROADCAST_EATS_GET_MENU_OPTIONS_RESPONSE);
                        intentRes.putExtra(BROADCAST_EATS_GET_MENU_OPTIONS_RESPONSE_STATUS, false);
                        LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
                        e.printStackTrace();
                    }
                } else {
                    Intent intentRes = new Intent(BROADCAST_EATS_GET_MENU_OPTIONS_RESPONSE);
                    intentRes.putExtra(BROADCAST_EATS_GET_MENU_OPTIONS_RESPONSE_STATUS, false);
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ProgressbarDismiss();
                Intent intentRes = new Intent(BROADCAST_EATS_GET_MENU_OPTIONS_RESPONSE);
                intentRes.putExtra(BROADCAST_EATS_GET_MENU_OPTIONS_RESPONSE_STATUS, false);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
            }
        });

    }

    /*add to cart restaurant*/
    public void addToCartWafieats(AddToEatsCartRequest request) {
        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(context).getEatsApiService().addToCartWafiapps(request);
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


                                Intent intentRes = new Intent(BROADCAST_ADD_TO_CART_EATS);
                                intentRes.putExtra(BROADCAST_ADD_TO_CART_EATS_STATUS, true);
                                intentRes.putExtra(BROADCAST_ADD_TO_CART_EATS_MESSAGE, jsonObject.getString("data"));
                                LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
                                //complete
                            } else {
                                Intent intentRes = new Intent(BROADCAST_ADD_TO_CART_EATS);
                                intentRes.putExtra(BROADCAST_ADD_TO_CART_EATS_STATUS, false);
                                intentRes.putExtra(BROADCAST_ADD_TO_CART_EATS_MESSAGE, jsonObject.getString("errorMessage"));

                                LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
                            }
                        }
                    } catch (IOException e) {
                        Intent intentRes = new Intent(BROADCAST_ADD_TO_CART_EATS);
                        intentRes.putExtra(BROADCAST_ADD_TO_CART_EATS_STATUS, false);
                        LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
                        e.printStackTrace();
                    } catch (JSONException e) {
                        Intent intentRes = new Intent(BROADCAST_ADD_TO_CART_EATS);
                        intentRes.putExtra(BROADCAST_ADD_TO_CART_EATS_STATUS, false);
                        LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
                        e.printStackTrace();
                    }
                } else {
                    Intent intentRes = new Intent(BROADCAST_ADD_TO_CART_EATS);
                    intentRes.putExtra(BROADCAST_ADD_TO_CART_EATS_STATUS, false);
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ProgressbarDismiss();
                Intent intentRes = new Intent(BROADCAST_ADD_TO_CART_EATS);
                intentRes.putExtra(BROADCAST_ADD_TO_CART_EATS_STATUS, false);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
            }
        });

    }


    public void ProgressbarDismiss() {
        Intent intent = new Intent(Constants.Login.BROADCAST_PROGRESS_WHEEL);
        intent.putExtra(Constants.Login.BROADCAST_ISDISPLAY_PROGRESS_WHEEL, false);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    public ArrayList<String> getAllCountries() {
        ArrayList<String> countries = new ArrayList<>();
        for (int i = 0; i < eatsHomeResponse.getData().getLstLocations().size(); i++) {
            countries.add(eatsHomeResponse.getData().getLstLocations().get(i).getLocation());

        }
        return countries;
    }

    public List<String> getAllCusines() {

        ArrayList<String> cuisines = new ArrayList<>();
        for (int i = 0; i < eatsHomeResponse.getData().getLstCuisines().size(); i++) {
            cuisines.add(eatsHomeResponse.getData().getLstCuisines().get(i).getCuisine());
        }
        return cuisines;
    }

    public List<LstLocation> getCountryDetails() {
        if (eatsHomeResponse != null) {
            return eatsHomeResponse.getData().getLstLocations();
        } else return new ArrayList<>();
    }

    public List<LstCuisine> getCuisions() {
        if (eatsHomeResponse != null) {
            return eatsHomeResponse.getData().getLstCuisines();
        } else return new ArrayList<>();
    }

    public List<RestaurantsInArea> getResturants() {
        if (eatsHomeResponse != null) {
            return eatsGetResturantsResponse.getData().getRestaurantsInArea();
        } else return new ArrayList<>();
    }

    public List<RestaurantsInArea> getAllResturants() {
        if (eatsRestrauntsResponse != null) {
            return eatsRestrauntsResponse.getData().getAllRestaurants();
        } else return new ArrayList<>();

    }

    public MenuData getMenuDataOfRest() {
        if (getRestaurantMenuResponse != null) {
            return getRestaurantMenuResponse.getData();
        } else {
            return new MenuData();
        }
    }

    public GetMenuOptionsResponse getMenuOptionsList() {
        if (menuOptionsResponse != null) {
            return menuOptionsResponse;
        } else {
            return new GetMenuOptionsResponse();
        }
    }

    public List<AllOffer> getOffersList() {
        if (eatsGetAllOffersResponse != null) {
            return eatsGetAllOffersResponse.getData().getAllOffers();
        } else {
            return new ArrayList<>();
        }
    }

    public EatsRestrauntsDetailsResponse getResturantDetails() {
        if (eatsRestrauntsDetailsResponse != null) {
            return eatsRestrauntsDetailsResponse;
        } else return new EatsRestrauntsDetailsResponse();

    }

    public List<CartItem> getEatsCartList() {
        if (cartListingEatsResponse != null) {
            return cartListingEatsResponse.getData().getCartItems();
        } else return new ArrayList<>();
    }

    public ArrayList<String> getCheckedItems() {
        if (checked_items.size() > 0) {
            return checked_items;
        } else return new ArrayList<String>();
    }

    public void setCheckedItems(ArrayList<String> checked_items) {
        this.checked_items = new ArrayList<>();
        this.checked_items = checked_items;
    }

    public ArrayList<CustomModelForExpandableLIst> getMenu() {

        if (customModelForExpandableLIsts.size() > 0) {
            return customModelForExpandableLIsts;
        } else return new ArrayList<>();
    }

    public void setMenu(ArrayList<CustomModelForExpandableLIst> customModelForExpandableLIsts) {
        this.customModelForExpandableLIsts = customModelForExpandableLIsts;
    }
}
