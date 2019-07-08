package com.mobtecnica.wafiapps.manager;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.mobtecnica.wafiapps.listeners.OnWebserviceCallback;
import com.mobtecnica.wafiapps.model.cart.addToCart.response.AddToCartResponse;
import com.mobtecnica.wafiapps.model.cart.getCart.getCartResponse.GetCartResponse;
import com.mobtecnica.wafiapps.model.categories.GetAllCategoriesResponse;
import com.mobtecnica.wafiapps.model.deals.DealsResponse;
import com.mobtecnica.wafiapps.model.forgotPassword.password_change.ChangePasswordResponse;
import com.mobtecnica.wafiapps.model.home.HomeBrandsResponse;
import com.mobtecnica.wafiapps.model.home.HomeResponse;
import com.mobtecnica.wafiapps.model.orderHistory.ReOrderResponse;
import com.mobtecnica.wafiapps.model.paymentmethod.PaymentMethodResponse;
import com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse.ProductsInCategoryResponse;
import com.mobtecnica.wafiapps.model.profile.cusomerDetails.customerDetails.CustomerDetailsResponse;
import com.mobtecnica.wafiapps.model.profile.get_address.CustomerAddressResponse;
import com.mobtecnica.wafiapps.model.search.searchProductResponse.SearchProductResponse;
import com.mobtecnica.wafiapps.model.wishlist.addToWishlist.WishListResponse;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WebserviceRequestManager {
    private  GetCartResponse getCartResponse;

    public GetCartResponse getGetCartResponse() {
        return getCartResponse;
    }

    public void setGetCartResponse(GetCartResponse getCartResponse) {
        this.getCartResponse = getCartResponse;
    }

    public enum RequestType {
        CATEGORY_PRODUCT_LIST,
        ADD_TO_CART,
        ADD_TO_WISHLIST,
        CART_LIST,
        UPDATE_ITEM_QUANTITY,
        DELETE_ITEM, SEARCH,
        PAYMENT_METHODS,
        CUSTOMER_PERSONAL_DETAILS,
        USER_ADDRESS_REQUEST,
        GET_CART_COUNT,
        APPLY_GIFT_CARD,
        APPLY_DISCOUNT_COUPON,
        DELETE_GIFT_CARD,
        DELETE_DISCOUNT_COUPON,
        MANUFACTURER_PRODUCT_LIST,
        CATEGORY_LIST,
        HOME_DATA,
        HOME_DEALS,
        HOME_HEADER,
        HOME_PRODUCTS,
        HOME_BRANDS,
        CHANGE_PASSWORD,
        RE_ORDER
    }

    private static final WebserviceRequestManager ourInstance = new WebserviceRequestManager();

    public static WebserviceRequestManager getInstance() {
        return ourInstance;
    }

    private WebserviceRequestManager() {
    }

    public Call<ResponseBody> enqueueRequest(@NonNull Call<ResponseBody> responseBodyCall, @NonNull final OnWebserviceCallback webserviceCallback, @NonNull final RequestType requestType) {
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.body() != null) {
                    try {
                        String responseString = new String(response.body().bytes());
                        Log.e("responseString",responseString);

                        if (responseString != null) {
                            JSONObject jsonObject = new JSONObject(responseString);
                            processResponse(jsonObject, requestType, webserviceCallback);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        webserviceCallback.onFailure(e.getMessage());
                    }
                }else{
                    webserviceCallback.onFailure(response.toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                webserviceCallback.onFailure(t.getMessage());

            }
        });
        return responseBodyCall;

    }

    private void processResponse(JSONObject jsonObject, RequestType requestType, OnWebserviceCallback webserviceCallback) {
        switch (requestType) {
            case CATEGORY_PRODUCT_LIST:
                try {
                    if (jsonObject != null) {
                        JSONObject object = jsonObject.getJSONObject("data");
                        String responseString1 = new String(String.valueOf(object));
                        ProductsInCategoryResponse productsInCategoryResponse = new Gson().fromJson(responseString1, ProductsInCategoryResponse.class);
                        if (productsInCategoryResponse != null) {
                            webserviceCallback.onSuccess(productsInCategoryResponse, requestType);
                        } else {
                            webserviceCallback.onFailure("Failed");
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    webserviceCallback.onFailure("Failed");

                }
                break;
            case SEARCH:
                try {
                    if (jsonObject != null) {
                        if (jsonObject.getBoolean(HomeManager.JSON_RESPONSE_STATUS)) {
                            SearchProductResponse searchProductResponse = new Gson().fromJson(jsonObject.toString(), SearchProductResponse.class);
                            if (searchProductResponse != null && searchProductResponse.getData().getProducts() != null) {
                                webserviceCallback.onSuccess(searchProductResponse, requestType);
                            }
                        } else {
                            webserviceCallback.onFailure("Failed");
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    webserviceCallback.onFailure("Failed");
                }
                break;

            case ADD_TO_CART:
                try {
                    if (jsonObject != null) {
                        if (jsonObject.getBoolean(HomeManager.JSON_RESPONSE_STATUS)) {
                            AddToCartResponse cartResponse = new Gson().fromJson(jsonObject.toString(), AddToCartResponse.class);
                            if (cartResponse != null && jsonObject.getBoolean(HomeManager.JSON_RESPONSE_STATUS)) {
                                webserviceCallback.onSuccess(cartResponse, requestType);
                            }
                        } else {
                            webserviceCallback.onSuccess(jsonObject.getString("errorMessage"), requestType); //
//
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    webserviceCallback.onFailure("Failed");
                }
                break;

            case ADD_TO_WISHLIST:
                try {
                    if (jsonObject != null) {
                        if (jsonObject.getBoolean(HomeManager.JSON_RESPONSE_STATUS)) {
                            WishListResponse wishListResponse = new Gson().fromJson(jsonObject.toString(), WishListResponse.class);
                            if (wishListResponse != null && jsonObject.getBoolean(HomeManager.JSON_RESPONSE_STATUS)) {
                                webserviceCallback.onSuccess(wishListResponse, requestType);
                            }
                        } else {
                            webserviceCallback.onSuccess(jsonObject.getString("errorMessage"), requestType); //
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    webserviceCallback.onFailure("Failed");
                }
                break;
            case CART_LIST:
                try {
                    if (jsonObject != null) {
                        if (jsonObject.getBoolean(HomeManager.JSON_RESPONSE_STATUS)) {
                            getCartResponse = new Gson().fromJson(jsonObject.toString(), GetCartResponse.class);
                            if (getCartResponse != null && jsonObject.getBoolean(HomeManager.JSON_RESPONSE_STATUS)) {
                                webserviceCallback.onSuccess(getCartResponse, requestType);
                            }
                        } else {
                            webserviceCallback.onSuccess(jsonObject.getString("errorMessage"), requestType); //
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    webserviceCallback.onFailure("Failed");
                }
                break;
            case UPDATE_ITEM_QUANTITY:
                try {
                    if (jsonObject != null) {
                        if (jsonObject.getBoolean(HomeManager.JSON_RESPONSE_STATUS)) {
                            getCartResponse = new Gson().fromJson(jsonObject.toString(), GetCartResponse.class);
                            if (getCartResponse != null && jsonObject.getBoolean(HomeManager.JSON_RESPONSE_STATUS)) {
                                webserviceCallback.onSuccess(getCartResponse, requestType);
                            }
                        } else {
                            webserviceCallback.onSuccess(jsonObject.getString("errorMessage"), requestType); //
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    webserviceCallback.onFailure("Failed");
                }
                break;
            case DELETE_ITEM:
                try {
                    if (jsonObject != null) {
                        if (jsonObject.getBoolean(HomeManager.JSON_RESPONSE_STATUS)) {
                            getCartResponse = new Gson().fromJson(jsonObject.toString(), GetCartResponse.class);
                            if (getCartResponse != null && jsonObject.getBoolean(HomeManager.JSON_RESPONSE_STATUS)) {
                                webserviceCallback.onSuccess(getCartResponse, requestType);
                            }
                        } else {
                            webserviceCallback.onSuccess(jsonObject.getString("errorMessage"), requestType); //
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    webserviceCallback.onFailure("Failed");
                }
                break;
            case PAYMENT_METHODS:
                try {
                    if (jsonObject != null) {
                        if (jsonObject.getBoolean(HomeManager.JSON_RESPONSE_STATUS)) {
                            PaymentMethodResponse paymentMethodResponse = new Gson().fromJson(jsonObject.toString(), PaymentMethodResponse.class);
                            if (paymentMethodResponse != null && jsonObject.getBoolean(HomeManager.JSON_RESPONSE_STATUS)) {
                                webserviceCallback.onSuccess(paymentMethodResponse, requestType);
                            }
                        } else {
                            webserviceCallback.onSuccess(jsonObject.getString("errorMessage"), requestType); //
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    webserviceCallback.onFailure("Failed");
                }
                break;
            case CUSTOMER_PERSONAL_DETAILS:
                try {
                    if (jsonObject != null) {
                        if (jsonObject.getBoolean(HomeManager.JSON_RESPONSE_STATUS)) {
                            CustomerDetailsResponse customerDetailsResponse = new Gson().fromJson(jsonObject.toString(), CustomerDetailsResponse.class);
                            if (customerDetailsResponse != null && jsonObject.getBoolean(HomeManager.JSON_RESPONSE_STATUS)) {
                                webserviceCallback.onSuccess(customerDetailsResponse, requestType);
                            }
                        } else {
                            webserviceCallback.onSuccess(jsonObject.getString("errorMessage"), requestType); //
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    webserviceCallback.onFailure("Failed");
                }
                break;
            case USER_ADDRESS_REQUEST:
                try {
                    if (jsonObject != null) {
                        if (jsonObject.getBoolean(HomeManager.JSON_RESPONSE_STATUS)) {
                            CustomerAddressResponse customerAddressResponse = new Gson().fromJson(jsonObject.toString(), CustomerAddressResponse.class);
                            if (customerAddressResponse != null && jsonObject.getBoolean(HomeManager.JSON_RESPONSE_STATUS)) {
                                webserviceCallback.onSuccess(customerAddressResponse, requestType);
                            }
                        } else {
                            webserviceCallback.onSuccess(jsonObject.getString("errorMessage"), requestType); //
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    webserviceCallback.onFailure("Failed");
                }
                break;
            case GET_CART_COUNT:
                try {
                    if (jsonObject != null) {
                        if (jsonObject.getBoolean(HomeManager.JSON_RESPONSE_STATUS)) {
                            int cartItemCount = jsonObject.getJSONObject("data").getInt("CartItemCount");
                            webserviceCallback.onSuccess(cartItemCount, requestType);
                        } else {
                            webserviceCallback.onSuccess(jsonObject.getString("errorMessage"), requestType); //
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    webserviceCallback.onFailure("Failed");
                }
                break;
            case APPLY_DISCOUNT_COUPON:
                try {
                    if (jsonObject != null) {
                        if (jsonObject.getBoolean(HomeManager.JSON_RESPONSE_STATUS)) {
                            getCartResponse = new Gson().fromJson(jsonObject.toString(), GetCartResponse.class);
                            if (getCartResponse != null && jsonObject.getBoolean(HomeManager.JSON_RESPONSE_STATUS)) {
                                webserviceCallback.onSuccess(getCartResponse, requestType);
                            }
                        } else {
                            webserviceCallback.onSuccess(jsonObject.getString("errorMessage"), requestType); //
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    webserviceCallback.onFailure("Failed");
                }
                break;
            case APPLY_GIFT_CARD:
                try {
                    if (jsonObject != null) {
                        if (jsonObject.getBoolean(HomeManager.JSON_RESPONSE_STATUS)) {
                            getCartResponse = new Gson().fromJson(jsonObject.toString(), GetCartResponse.class);
                            if (getCartResponse != null && jsonObject.getBoolean(HomeManager.JSON_RESPONSE_STATUS)) {
                                webserviceCallback.onSuccess(getCartResponse, requestType);
                            }
                        } else {
                            webserviceCallback.onSuccess(jsonObject.getString("errorMessage"), requestType); //
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    webserviceCallback.onFailure("Failed");
                }
                break;
            case DELETE_DISCOUNT_COUPON:
                try {
                    if (jsonObject != null) {
                        if (jsonObject.getBoolean(HomeManager.JSON_RESPONSE_STATUS)) {
                            getCartResponse = new Gson().fromJson(jsonObject.toString(), GetCartResponse.class);
                            if (getCartResponse != null && jsonObject.getBoolean(HomeManager.JSON_RESPONSE_STATUS)) {
                                webserviceCallback.onSuccess(getCartResponse, requestType);
                            }
                        } else {
                            webserviceCallback.onSuccess(jsonObject.getString("errorMessage"), requestType); //
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    webserviceCallback.onFailure("Failed");
                }
                break;
            case DELETE_GIFT_CARD:
                try {
                    if (jsonObject != null) {
                        if (jsonObject.getBoolean(HomeManager.JSON_RESPONSE_STATUS)) {
                            getCartResponse = new Gson().fromJson(jsonObject.toString(), GetCartResponse.class);
                            if (getCartResponse != null && jsonObject.getBoolean(HomeManager.JSON_RESPONSE_STATUS)) {
                                webserviceCallback.onSuccess(getCartResponse, requestType);
                            }
                        } else {
                            webserviceCallback.onSuccess(jsonObject.getString("errorMessage"), requestType); //
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    webserviceCallback.onFailure("Failed");
                }
                break;
            case MANUFACTURER_PRODUCT_LIST:
                try {
                    if (jsonObject != null) {
                        JSONObject object = jsonObject.getJSONObject("data");
                        String responseString1 = new String(String.valueOf(object));
                        ProductsInCategoryResponse productsInCategoryResponse = new Gson().fromJson(responseString1, ProductsInCategoryResponse.class);
                        if (productsInCategoryResponse != null) {
                            webserviceCallback.onSuccess(productsInCategoryResponse, requestType);
                        } else {
                            webserviceCallback.onFailure("Failed");
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    webserviceCallback.onFailure("Failed");

                }
                break;
            case CATEGORY_LIST:
                try {
                    if (jsonObject != null) {
                        JSONObject object = jsonObject.getJSONObject("data");
                        String responseString1 = new String(String.valueOf(object));
                        GetAllCategoriesResponse getAllCategoriesResponse = new Gson().fromJson(responseString1, GetAllCategoriesResponse.class);
                        if (getAllCategoriesResponse != null) {
                            webserviceCallback.onSuccess(getAllCategoriesResponse, requestType);
                        } else {
                            webserviceCallback.onFailure("Failed");
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    webserviceCallback.onFailure("Failed");

                }
                break;
            case HOME_DATA:
                try {
                    if (jsonObject != null) {
                        JSONObject object = jsonObject.getJSONObject("data");
                        String responseString1 = new String(String.valueOf(object));
                        HomeResponse homeResponse = new Gson().fromJson(responseString1, HomeResponse.class);
                        if (homeResponse != null) {
                            webserviceCallback.onSuccess(homeResponse, requestType);
                        } else {
                            webserviceCallback.onFailure("Failed");
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    webserviceCallback.onFailure("Failed");

                }
                break;
            case HOME_BRANDS:
                try {
                    if (jsonObject != null) {
                        JSONObject object = jsonObject.getJSONObject("data");
                        String responseString1 = new String(String.valueOf(object));
                        HomeBrandsResponse homeBrandsResponse = new Gson().fromJson(responseString1, HomeBrandsResponse.class);
                        if (homeBrandsResponse != null) {
                            webserviceCallback.onSuccess(homeBrandsResponse, requestType);
                        } else {
                            webserviceCallback.onFailure("Failed");
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    webserviceCallback.onFailure("Failed");

                }
                break;
            case HOME_DEALS:
                try {
                    if (jsonObject != null) {
                        String responseString = new String(String.valueOf(jsonObject));
                        DealsResponse dealsResponse = new Gson().fromJson(responseString, DealsResponse.class);
                        if (dealsResponse != null) {
                            webserviceCallback.onSuccess(dealsResponse, requestType);
                        } else {
                            webserviceCallback.onFailure("Failed");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    webserviceCallback.onFailure("Failed");

                }
                break;
            case HOME_HEADER:
                try {
                    if (jsonObject != null) {
                        JSONObject object = jsonObject.getJSONObject("data");
                        String responseString1 = new String(String.valueOf(object));
                        HomeResponse homeResponse = new Gson().fromJson(responseString1, HomeResponse.class);
                        if (homeResponse != null) {
                            webserviceCallback.onSuccess(homeResponse, requestType);
                        } else {
                            webserviceCallback.onFailure("Failed");
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    webserviceCallback.onFailure("Failed");

                }
                break;
            case HOME_PRODUCTS:
                try {
                    if (jsonObject != null) {
                        JSONObject object = jsonObject.getJSONObject("data");
                        String responseString1 = new String(String.valueOf(object));
                        HomeResponse homeResponse = new Gson().fromJson(responseString1, HomeResponse.class);
                        if (homeResponse != null) {
                            webserviceCallback.onSuccess(homeResponse, requestType);
                        } else {
                            webserviceCallback.onFailure("Failed");
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    webserviceCallback.onFailure("Failed");

                }
                break;
            case CHANGE_PASSWORD:
                try {
                    if (jsonObject != null) {
                        String responseString1 = new String(String.valueOf(jsonObject));
                        ChangePasswordResponse changePasswordResponse = new Gson().fromJson(responseString1, ChangePasswordResponse.class);
                        if (changePasswordResponse != null) {
                            webserviceCallback.onSuccess(changePasswordResponse, requestType);
                        } else {
                            webserviceCallback.onFailure("Failed");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    webserviceCallback.onFailure("Failed");

                }
                break;
            case RE_ORDER:
                try {
                    if (jsonObject != null) {
                        String responseString = new String(String.valueOf(jsonObject));
                        ReOrderResponse reOrderResponse = new Gson().fromJson(responseString, ReOrderResponse.class);
                        if (reOrderResponse != null) {
                            webserviceCallback.onSuccess(reOrderResponse, requestType);
                        } else {
                            webserviceCallback.onFailure("Failed");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    webserviceCallback.onFailure("Failed");

                }
                break;
        }
    }

}
