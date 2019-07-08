package com.mobtecnica.wafiapps.manager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mobtecnica.wafiapps.model.cart.addToCart.AddToCartRequest;
import com.mobtecnica.wafiapps.model.cart.addToCart.Product_attributesCart;
import com.mobtecnica.wafiapps.model.cart.addToCart.response.AddToCartResponse;
import com.mobtecnica.wafiapps.model.cart.getCart.GetCart;
import com.mobtecnica.wafiapps.model.cart.getCart.getCartResponse.GetCartResponse;
import com.mobtecnica.wafiapps.model.categories.SubCategories;
import com.mobtecnica.wafiapps.model.deals.Deal;
import com.mobtecnica.wafiapps.model.deals.DealsResponse;
import com.mobtecnica.wafiapps.model.home.DealsRequest;
import com.mobtecnica.wafiapps.model.home.HomeBrandsResponse;
import com.mobtecnica.wafiapps.model.home.HomePageLinksResponse;
import com.mobtecnica.wafiapps.model.notification.NotificationRegistrationRequest;
import com.mobtecnica.wafiapps.model.orderDetails.OrderDetailsResponse;
import com.mobtecnica.wafiapps.model.orderHistory.Order;
import com.mobtecnica.wafiapps.model.cart.updateCart.changeQuantity.ChangeQuantityRequest;
import com.mobtecnica.wafiapps.model.categories.Categories;
import com.mobtecnica.wafiapps.model.categories.CategoriesRequest;
import com.mobtecnica.wafiapps.model.categories.GetAllCategoriesResponse;
import com.mobtecnica.wafiapps.model.discountCoupon.ApplyDiscountCoupenRequest;
import com.mobtecnica.wafiapps.model.giftCard.GiftCardRequest;
import com.mobtecnica.wafiapps.model.home.CustomeHomeViewProducts;
import com.mobtecnica.wafiapps.model.home.HomeBrands;
import com.mobtecnica.wafiapps.model.home.HomeCategories;
import com.mobtecnica.wafiapps.model.home.HomeCategoryProducts;
import com.mobtecnica.wafiapps.model.home.HomeCustomProducts;
import com.mobtecnica.wafiapps.model.home.HomePageRequest;
import com.mobtecnica.wafiapps.model.home.HomeResponse;
import com.mobtecnica.wafiapps.model.home.HomeSliders;
import com.mobtecnica.wafiapps.model.productDetails.ProductDetailsResponse;
import com.mobtecnica.wafiapps.model.productDetails.productDetailsRequest.ProductDetailsRequest;
import com.mobtecnica.wafiapps.model.productsInCategories.ProductsInCategroiesRequest;
import com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse.ProductsInCategoryResponse;
import com.mobtecnica.wafiapps.model.profile.cusomerDetails.CustomerPersonalDetailsRequest;
import com.mobtecnica.wafiapps.model.profile.cusomerDetails.customerDetails.CustomerDetailsResponse;
import com.mobtecnica.wafiapps.model.search.searchProductResponse.Products;
import com.mobtecnica.wafiapps.model.search.searchProductResponse.SearchProductResponse;
import com.mobtecnica.wafiapps.model.topicContent.GetTopicContentResponse;
import com.mobtecnica.wafiapps.model.wishlist.addToWishlist.WishListResponse;
import com.mobtecnica.wafiapps.model.wishlist.newWishList.GetWishListItems;
import com.mobtecnica.wafiapps.model.wishlist.newWishList.GetWishListRequest;
import com.mobtecnica.wafiapps.model.wishlist.newWishList.GetWishListResponse;
import com.mobtecnica.wafiapps.utils.Constants;
import com.mobtecnica.wafiapps.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by SIby on 02-02-2017.
 */

public class HomeManager {


    public static final String BROADCAST_HIDE_SEARCH = "BROADCAST_HIDE_SEARCH";
    public static final String BROADCAST_HIDE_SEARCH_STATUS = "BROADCAST_HIDE_SEARCH_STATUS";
    public static final String BROADCAST_TITTLE = "BROADCAST_TITTLE";
    public static final String BROADCAST_TITTLE_TEXT = "BROADCAST_TITTLE_TEXT";


    public static final String JSON_RESPONSE_STATUS = "success";
    //categories Response
    public static final String BROADCAST_CATEGORIES_RESPONSE = "BROADCAST_CTEGORIES_RESPONSE";
    public static final String BROADCAST_CATEGORIES_RESPONSE_STATUS = "BROADCAST_CTEGORIES_RESPONSE_STATUS";
    //home page Response
    public static final String BROADCAST_HOME_DATA = "BROADCAST_HOME_DATA";
    public static final String BROADCAST_HOME_BRAND_DATA_STATUS = "BROADCAST_HOME_BRAND_DATA_STATUS";
    public static final String BROADCAST_HOME_DATA_STATUS = "BROADCAST_HOME_DATA_STATUS";
    public static final String BROADCAST_HOME_DEALS_DATA_STATUS = "BROADCAST_HOME_DEALS_DATA_STATUS";
    //products in categories
    public static final String BROADCAST_PRODUCT_IN_CATEGORY = "BROADCAST_PRODUCT_IN_CATEGORY";
    public static final String BROADCAST_PRODUCT_IN_CATEGORY_STATUS = "BROADCAST_PRODUCT_IN_CATEGORY_STATUS";
    // product detailed view
    public static final String BROADCAST_PRODUCT_DETAILS = "BROADCAST_PRODUCT_DETAILS";
    public static final String BROADCAST_PRODUCT_DETAILS_STATUS = "BROADCAST_PRODUCT_DETAILS_STATUS";
    //profile
    public static final String BROADCAST_PROFILE_DETAILS = "BROADCAST_PROFILE_DETAILS";
    public static final String BROADCAST_PROFILE_DETAILS_STATUS = "BROADCAST_PROFILE_DETAILS_STATUS";
    //ADD TO CART
    public static final String BROADCAST_ADD_TO_CART = "BROADCAST_ADD_TO_CART";
    public static final String BROADCAST_ADD_TO_CART_STATUS = "BROADCAST_ADD_TO_CART_STATUS";
    public static final String BROADCAST_ADD_TO_CART_STATUS_MESSAGE = "BROADCAST_ADD_TO_CART_STATUS_MESSAGE";

    public static final String BROADCAST_SEARCH_STATUS = "BROADCAST_ADD_TO_CART_STATUS";
    public static final String BROADCAST_SEARCH = "BROADCAST_SEARCH";

    public static final String BROADCAST_CART_RESPONSE = "BROADCAST_CART_RESPONSE";
    public static final String BROADCAST_CART_RESPONSE_STATUS = "BROADCAST_CART_RESPONSE_STATUS";


    public static final String BROADCAST_TOPIC_CONTENT_RESPONSE = "BROADCAST_TOPIC_CONTENT_RESPONSE";
    public static final String BROADCAST_TOPIC_CONTENT_RESPONSE_STATUS = "BROADCAST_TOPIC_CONTENT_RESPONSE_STATUS";


    public static final String BROADCAST_APPLY_GIFT_CARD = "BROADCAST_APPLY_GIFT_CARD";
    public static final String BROADCAST_APPLY_GIFT_CARD_STATUS = "BROADCAST_APPLY_GIFT_CARD_STATUS";


    public static final String BROADCAST_GET_WISHLIST_RESPONSE = "BROADCAST_GET_WISHLIST_RESPONSE";
    public static final String BROADCAST_GET_WISHLIST_RESPONSE_STATUS = "BROADCAST_GET_WISHLIST_RESPONSE_STATUS";

    public static final String BROADCAST_APPLY_DISCOUNT_COUPON = "BROADCAST_APPLY_DISCOUNT_COUPON";
    public static final String BROADCAST_APPLYBROADCAST_APPLY_DISCOUNT_COUPON_STATUS = "BROADCAST_APPLYBROADCAST_APPLY_DISCOUNT_COUPON_STATUS";
    public static final String BROADCAST_NEW_TOTAL = "BROADCAST_NEW_TOTAL";
    public static final String BROADCAST_MESSAGE = "BROADCAST_MESSAGE";

    public static final String BROADCAST_UPDATE_FIELD = "BROADCAST_UPDATE_FIELD";
    public static final String BROADCAST_UPDATE_FIELD_STATUS = "BROADCAST_UPDATE_FIELD_STATUS";
    public static final String BROADCAST_UPDATE = "BROADCAST_UPDATE";

    public static final String BROADCAST_MY_ORDER = "BROADCAST_MY_ORDER";
    public static final String BROADCAST_MY_ORDER_STATUS = "BROADCAST_MY_ORDER_STATUS";
    public static final String BROADCAST_ORDER_DETAILS = "BROADCAST_ORDER_DETAILS";
    public static final String BROADCAST_ORDER_DETAILS_STATUS = "BROADCAST_ORDER_DETAILS_STATUS";
    public static final String BROADCAST_HOME_LINKS_DATA = "BROADCAST_HOME_LINKS_DATA";
    public static final String BROADCAST_HOME_LINKS_DATA_STATUS = "BROADCAST_HOME_LINKS_DATA_STATUS";
    private ArrayList<HomeCategories> homeCategories = new ArrayList<>();

    GetAllCategoriesResponse categoriesResponse = null;
    HomeResponse homeResponse = null;
    //    HomeBrands homeBrands=null;
    HomeBrandsResponse homeBrandsResponse = null;
    ArrayList<HomeBrands> homeBrandsArrayList = new ArrayList<>();
    GetTopicContentResponse getTopicContentResponse = null;
    ProductsInCategoryResponse productsInCategoryResponse = null;
    ProductDetailsResponse productDetailsResponse = null;
    CustomerDetailsResponse customerDetailsResponse = null;
    WishListResponse wishListResponse = null;
    DealsResponse dealsResponse;
    AddToCartResponse cartResponse = null;
    SearchProductResponse searchProductResponse = null;
    GetCartResponse getCartResponse = null;
    List<Product_attributesCart> productAttributesCarts = new ArrayList<>();
    private Gson gson;
    private Context mcontext;
    private GetWishListResponse getWishListResponse = null;
    private ArrayList<Order> orderHistory;
    private OrderDetailsResponse orderDetailsResponse;
    private String searchText;
    private Integer pagenumber = 1;
    private SubCategories selectedSubCategory;
    private HomePageLinksResponse homePageLinks;

    public HomeManager(Context mcontext) {
        this.mcontext = mcontext;
    }

    public void updateContext(Context mcontext) {
        if (mcontext != null)
            this.mcontext = mcontext.getApplicationContext();
    }

    public void getAllCategories(CategoriesRequest token) {

        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(mcontext).getApiService().getCategories(token);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                ProgressbarDismiss();
                if (response.body() != null) {
                    try {
                        String responseString = new String(response.body().bytes());
                        if (responseString != null) {
                            JSONObject jsonObject = new JSONObject(responseString);
                            JSONObject object = jsonObject.getJSONObject("data");
                            String responseString1 = new String(String.valueOf(object));
                            if (jsonObject != null) {
                                categoriesResponse = new Gson().fromJson(responseString1, GetAllCategoriesResponse.class);
                                if (categoriesResponse != null) {
                                    ObjectFactory.getInstance().getAppPreference(mcontext).saveCategories(responseString1);
                                    Intent intentRes = new Intent(BROADCAST_CATEGORIES_RESPONSE);
                                    intentRes.putExtra(BROADCAST_CATEGORIES_RESPONSE_STATUS, true);
                                    LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
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
                Intent intentRes = new Intent(BROADCAST_CATEGORIES_RESPONSE);
                intentRes.putExtra(BROADCAST_CATEGORIES_RESPONSE_STATUS, false);
                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
            }
        });

    }

//    public void getHomePageStaticData(Context context) {
//        try {
//            String responseString = Utils.loadJSONFromAsset(context);
//            if (responseString != null) {
//                JSONObject jsonObject = new JSONObject(responseString);
//                JSONObject object = jsonObject.getJSONObject("data");
//                String responseString1 = new String(String.valueOf((object)));
//                if (jsonObject != null) {
//                    homeResponse = new Gson().fromJson(responseString1, HomeResponse.class);
//                    if (homeResponse != null) {
//                        ObjectFactory.getInstance().getAppPreference(mcontext).saveHomeData(responseString1);
//                        Intent intentRes = new Intent(BROADCAST_HOME_DATA);
//                        intentRes.putExtra(BROADCAST_HOME_DATA_STATUS, true);
//                        LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
//                    }
//                }
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

//    public void getHomePageData(HomePageRequest request) {
//
//        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(mcontext).getApiService().getHomePageData(request);
//        responseBodyCall.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
////                ProgressbarDismiss();
//                if (response.body() != null) {
//                    try {alBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
//                                }
//                                String responseString = new String(response.body().bytes());
//                                if (responseString != null) {
//                                    JSONObject jsonObject = new JSONObject(responseString);
//                                    JSONObject object = jsonObject.getJSONObject("data");
//                                    String responseString1 = new String(String.valueOf((object)));
//                                    if (jsonObject != null) {
//                                        homeResponse = new Gson().fromJson(responseString1, HomeResponse.class);
//                                        if (homeResponse != null) {
//                                            ObjectFactory.getInstance().getAppPreference(mcontext).saveHomeData(responseString1);
//                                            Intent intentRes = new Intent(BROADCAST_HOME_DATA);
//                                            intentRes.putExtra(BROADCAST_HOME_DATA_STATUS, true);
//                                            Loc
//                            }
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
////                ProgressbarDismiss();
//                Intent intentRes = new Intent(BROADCAST_HOME_DATA);
//                intentRes.putExtra(BROADCAST_HOME_DATA_STATUS, false);
//                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
//            }
//        });
//
//    }

    public void getHomePageBrands(HomePageRequest request) {

        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(mcontext).getApiService().getHomeBrand(request);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                ProgressbarDismiss();
                if (response.body() != null) {
                    try {
                        String responseString = new String(response.body().bytes());
                        if (responseString != null) {
                            JSONObject jsonObject = new JSONObject(responseString);
                            JSONObject object = jsonObject.getJSONObject("data");
                            String responseString1 = new String(String.valueOf((object)));
                            if (jsonObject != null) {

                                homeBrandsResponse = new Gson().fromJson(responseString1, HomeBrandsResponse.class);
                                if (homeBrandsResponse != null) {
                                    ObjectFactory.getInstance().getAppPreference(mcontext).saveHomeData(responseString1);
                                    Intent intentRes = new Intent(BROADCAST_HOME_DATA);
                                    intentRes.putExtra(BROADCAST_HOME_BRAND_DATA_STATUS, true);
                                    LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
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
//                ProgressbarDismiss();
                Intent intentRes = new Intent(BROADCAST_HOME_DATA);
                intentRes.putExtra(BROADCAST_HOME_BRAND_DATA_STATUS, false);
                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
            }
        });

    }

    public void getHomePagedeals(DealsRequest request) {

        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(mcontext).getApiService().getHomePageDeals(request);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                ProgressbarDismiss();
                if (response.body() != null) {
                    try {
                        String responseString = new String(response.body().bytes());
                        if (responseString != null) {
                            dealsResponse = new Gson().fromJson(responseString, DealsResponse.class);
                            if (dealsResponse != null) {
                                ObjectFactory.getInstance().getAppPreference(mcontext).saveHomeData(responseString);
                                Intent intentRes = new Intent(BROADCAST_HOME_DATA);
                                intentRes.putExtra(BROADCAST_HOME_DEALS_DATA_STATUS, true);
                                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                            }
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                ProgressbarDismiss();
                Intent intentRes = new Intent(BROADCAST_HOME_DATA);
                intentRes.putExtra(BROADCAST_HOME_DEALS_DATA_STATUS, false);
                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
            }
        });

    }



    /*public void getHomePageLinks(HomePageRequest request) {

        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(mcontext).getApiService().getHomePageData(request);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ProgressbarDismiss();
                if (response.body() != null) {
                    try {
                        String responseString = new String(response.body().bytes());
                        if (responseString != null) {
                            JSONObject jsonObject = new JSONObject(responseString);
                            JSONObject object = jsonObject.getJSONObject("data");
                            String responseString1 = new String(String.valueOf((object)));
                            HomePageLinksResponse homePageResponseNew = new HomePageLinksResponse();
                            if (jsonObject != null) {
                                homePageResponseNew = new Gson().fromJson(responseString1, HomePageLinksResponse.class);
                                if (homePageResponseNew != null) {
                                    setHomePageLinks(homePageResponseNew);
                                    ObjectFactory.getInstance().getAppPreference(mcontext).setHomePageLinksData(homePageResponseNew);
                                    Intent intentRes = new Intent(BROADCAST_HOME_LINKS_DATA);
                                    intentRes.putExtra(BROADCAST_HOME_LINKS_DATA_STATUS, true);
                                    LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
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
                ProgressbarDismiss();
                Intent intentRes = new Intent(BROADCAST_HOME_LINKS_DATA);
                intentRes.putExtra(BROADCAST_HOME_LINKS_DATA_STATUS, false);
                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
            }
        });

    }
*///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void apiGetTopicContent(String apiToken, String topicSystemName) {

        HashMap<String, String> jsonMap = new HashMap<>();
        jsonMap.put("ApiToken", apiToken);
        jsonMap.put("TopicSystemName", topicSystemName);
        String json = new JSONObject(jsonMap).toString();
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);

        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(mcontext).getApiService().getTopicContent(body);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ProgressbarDismiss();
                String json = "";
                Intent intentRes = new Intent(BROADCAST_TOPIC_CONTENT_RESPONSE);
                try {

                    json = new String(response.body().bytes());
                    if (!json.isEmpty()) {
                        gson = new Gson();
                        JSONObject jsonObject = new JSONObject(json);
                        if (jsonObject.has("success")) {
                            if (jsonObject.getBoolean("success")) {
                                GetTopicContentResponse getTopicContentResponse = gson.fromJson(json, GetTopicContentResponse.class);
                                if (getTopicContentResponse != null) {
                                    intentRes.putExtra(BROADCAST_TOPIC_CONTENT_RESPONSE_STATUS, true);
                                    setGetTopicContentData(getTopicContentResponse);
                                } else {
                                    intentRes.putExtra(BROADCAST_TOPIC_CONTENT_RESPONSE_STATUS, false);
                                }
                            } else {
                                intentRes.putExtra(BROADCAST_TOPIC_CONTENT_RESPONSE_STATUS, false);
                            }
                            LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                        } else {
                            Toast.makeText(mcontext, "" + jsonObject.optString("message"), Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }catch (Exception e) {
                    e.printStackTrace();
                }

            }


            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ProgressbarDismiss();

            }
        });

    }

    public GetTopicContentResponse getGetTopicContentData() {
        return getTopicContentResponse;
    }

    private void setGetTopicContentData(GetTopicContentResponse getTopicContentResponse) {
        this.getTopicContentResponse = getTopicContentResponse;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void getProductsInCategory(ProductsInCategroiesRequest request) {

        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(mcontext).getApiService().getProductsInCategory(request);
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
                                JSONObject object = jsonObject.getJSONObject("data");
                                String responseString1 = new String(String.valueOf(object));
                                productsInCategoryResponse = new Gson().fromJson(responseString1, ProductsInCategoryResponse.class);
                                if (productsInCategoryResponse != null) {
                                    // ObjectFactory.getInstance().getAppPreference(mcontext).saveHomeData(responseString);
                                    Intent intentRes = new Intent(BROADCAST_PRODUCT_IN_CATEGORY);
                                    intentRes.putExtra(BROADCAST_PRODUCT_IN_CATEGORY_STATUS, true);
                                    LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
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
                ProgressbarDismiss();
                Intent intentRes = new Intent(BROADCAST_PRODUCT_IN_CATEGORY);
                intentRes.putExtra(BROADCAST_PRODUCT_IN_CATEGORY_STATUS, false);
                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
            }
        });
    }

    public void getProductDetails(ProductDetailsRequest request) {

        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(mcontext).getApiService().getProductsDetails(request);
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
                                JSONObject object = jsonObject.getJSONObject("data");
                                String responseString1 = new String(String.valueOf(object));
                                productDetailsResponse = new Gson().fromJson(responseString1, ProductDetailsResponse.class);
                                if (productDetailsResponse != null) {
                                    // ObjectFactory.getInstance().getAppPreference(mcontext).saveHomeData(responseString);
                                    Intent intentRes = new Intent(BROADCAST_PRODUCT_DETAILS);
                                    intentRes.putExtra(BROADCAST_PRODUCT_DETAILS_STATUS, true);
                                    LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                                }
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        Intent intentRes = new Intent(BROADCAST_PRODUCT_DETAILS);
                        intentRes.putExtra(BROADCAST_PRODUCT_DETAILS_STATUS, false);
                        LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Intent intentRes = new Intent(BROADCAST_PRODUCT_DETAILS);
                        intentRes.putExtra(BROADCAST_PRODUCT_DETAILS_STATUS, false);
                        LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ProgressbarDismiss();
                Intent intentRes = new Intent(BROADCAST_PRODUCT_DETAILS);
                intentRes.putExtra(BROADCAST_PRODUCT_DETAILS_STATUS, false);
                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
            }
        });

    }

    public void getCustomerDetails(CustomerPersonalDetailsRequest request) {

        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(mcontext).getApiService().getCustomer(request);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                ProgressbarDismiss();
                if (response.body() != null) {
                    try {
                        String responseString = new String(response.body().bytes());
                        if (responseString != null) {
                            JSONObject jsonObject = new JSONObject(responseString);
                            if (jsonObject != null && jsonObject.getBoolean(JSON_RESPONSE_STATUS)) {
                                customerDetailsResponse = new Gson().fromJson(responseString, CustomerDetailsResponse.class);
                                if (customerDetailsResponse != null) {
//                                    ObjectFactory.getInstance().getAppPreference(mcontext).saveProfileData(responseString);
                                    ObjectFactory.getInstance().getAppPreference(mcontext).saveProfileData(customerDetailsResponse);
                                    Intent intentRes = new Intent(BROADCAST_PROFILE_DETAILS);
                                    intentRes.putExtra(BROADCAST_PROFILE_DETAILS_STATUS, true);
                                    LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
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
                ProgressbarDismiss();
                Intent intentRes = new Intent(BROADCAST_PROFILE_DETAILS);
                intentRes.putExtra(BROADCAST_PROFILE_DETAILS_STATUS, false);
                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
            }
        });

    }

    public void getProductsInCart(GetCart request) {

        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(mcontext).getApiService().getCart(request);
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
                                getCartResponse = new Gson().fromJson(responseString, GetCartResponse.class);
                                if (getCartResponse != null) {
                                    Intent intentRes = new Intent(BROADCAST_CART_RESPONSE);
                                    intentRes.putExtra(BROADCAST_CART_RESPONSE_STATUS, true);
                                    LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
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
                ProgressbarDismiss();
                Intent intentRes = new Intent(BROADCAST_CART_RESPONSE);
                intentRes.putExtra(BROADCAST_CART_RESPONSE_STATUS, true);
                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
            }
        });

    }

    public void changeQuantity(ChangeQuantityRequest request) {

        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(mcontext).getApiService().changeQuantity(request);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ProgressbarDismiss();
                if (response.body() != null) {
                    try {
                        String responseString = new String(response.body().bytes());
                        if (responseString != null) {
                            JSONObject jsonObject = new JSONObject(responseString);
                            if (jsonObject != null && jsonObject.getBoolean(HomeManager.JSON_RESPONSE_STATUS)) {
                                getCartResponse = new Gson().fromJson(responseString, GetCartResponse.class);
                                if (getCartResponse != null) {
                                    Intent intentRes = new Intent(BROADCAST_CART_RESPONSE);
                                    intentRes.putExtra(BROADCAST_CART_RESPONSE_STATUS, true);
                                    LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
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
                ProgressbarDismiss();

            }
        });


    }

    public void addToCart(AddToCartRequest request) {

        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(mcontext).getApiService().addToCart(request);
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
                                cartResponse = new Gson().fromJson(responseString, AddToCartResponse.class);
                                if (cartResponse != null && jsonObject.getBoolean(JSON_RESPONSE_STATUS)) {
                                    productAttributesCarts = new ArrayList<Product_attributesCart>();
                                    Intent intentRes = new Intent(BROADCAST_ADD_TO_CART);
                                    intentRes.putExtra(BROADCAST_ADD_TO_CART_STATUS, true);
                                    intentRes.putExtra(BROADCAST_ADD_TO_CART_STATUS_MESSAGE, cartResponse.getData().getValue());
                                    LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                                }
                            } else {
                                if (jsonObject != null && !jsonObject.getBoolean(JSON_RESPONSE_STATUS)) {
                                    productAttributesCarts = new ArrayList<Product_attributesCart>();
                                    Intent intentRes = new Intent(BROADCAST_ADD_TO_CART);
                                    intentRes.putExtra(BROADCAST_ADD_TO_CART_STATUS, false);
                                    intentRes.putExtra(BROADCAST_ADD_TO_CART_STATUS_MESSAGE, jsonObject.getString("errorMessage"));
                                    LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
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
                ProgressbarDismiss();
                productAttributesCarts = new ArrayList<Product_attributesCart>();
                Toast.makeText(mcontext, "Oops Some Error occured..", Toast.LENGTH_SHORT).show();
                Intent intentRes = new Intent(BROADCAST_ADD_TO_CART);
                intentRes.putExtra(BROADCAST_ADD_TO_CART_STATUS, true);
                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
            }
        });

    }

    /*public void searchProduct(SearchRequest request) {

        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(mcontext).getApiService().searchProduct(request);
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
                                searchProductResponse = new Gson().fromJson(responseString, SearchProductResponse.class);
                                if (searchProductResponse != null && searchProductResponse.getData().getProducts() != null) {
                                    Intent intentRes = new Intent(BROADCAST_SEARCH);
                                    intentRes.putExtra(BROADCAST_SEARCH_STATUS, true);
                                    LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
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
                ProgressbarDismiss();
                Toast.makeText(mcontext, "Oops Some Error occured..", Toast.LENGTH_SHORT).show();
                Intent intentRes = new Intent(BROADCAST_SEARCH);
                intentRes.putExtra(BROADCAST_SEARCH_STATUS, false);
                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
            }
        });

    }*/

    public void applyGiftCard(GiftCardRequest request, final Integer num) {

        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(mcontext).getApiService().applyGiftCard(request);
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
                                getCartResponse = new Gson().fromJson(responseString, GetCartResponse.class);
                                Intent intentRes = new Intent(BROADCAST_APPLY_GIFT_CARD);
                                intentRes.putExtra(BROADCAST_APPLY_GIFT_CARD_STATUS, true);
                                intentRes.putExtra(BROADCAST_NEW_TOTAL, getCartResponse.getData().getOrderTotals().getOrderTotal());
                                if (num == 1) {
                                    intentRes.putExtra(BROADCAST_MESSAGE, "Gift wrapping unselected");

                                } else if (num == 2) {
                                    intentRes.putExtra(BROADCAST_MESSAGE, "Gift wrapping selected");

                                } else {
                                    intentRes.putExtra(BROADCAST_MESSAGE, getCartResponse.getData().getCart().getGiftCardBox().getMessage());

                                }
                                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);

                            }
                        }
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                        Intent intentRes = new Intent(BROADCAST_APPLY_GIFT_CARD);
                        intentRes.putExtra(BROADCAST_APPLY_GIFT_CARD_STATUS, false);
                        LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ProgressbarDismiss();
                Intent intentRes = new Intent(BROADCAST_APPLY_GIFT_CARD);
                intentRes.putExtra(BROADCAST_APPLY_GIFT_CARD_STATUS, false);
                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
            }
        });

    }

    public void applyDiscountCoupon(ApplyDiscountCoupenRequest request) {
        String json = new Gson().toJson(request);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(mcontext).getApiService().applyDiscountCoupon(body);
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
                                getCartResponse = new Gson().fromJson(responseString, GetCartResponse.class);
                                Intent intentRes = new Intent(BROADCAST_APPLY_DISCOUNT_COUPON);
                                intentRes.putExtra(BROADCAST_APPLYBROADCAST_APPLY_DISCOUNT_COUPON_STATUS, true);
                                intentRes.putExtra(BROADCAST_NEW_TOTAL, getCartResponse.getData().getOrderTotals().getOrderTotal());
                                intentRes.putExtra(BROADCAST_MESSAGE, getCartResponse.getData().getCart().getDiscountBox().getMessage());
                                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                            }
                        }
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                        Intent intentRes = new Intent(BROADCAST_APPLY_DISCOUNT_COUPON);
                        intentRes.putExtra(BROADCAST_APPLYBROADCAST_APPLY_DISCOUNT_COUPON_STATUS, false);
                        LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ProgressbarDismiss();
                Intent intentRes = new Intent(BROADCAST_APPLY_DISCOUNT_COUPON);
                intentRes.putExtra(BROADCAST_APPLYBROADCAST_APPLY_DISCOUNT_COUPON_STATUS, false);
                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
            }
        });
    }

    public void getWishList(GetWishListRequest request) {
        Call<ResponseBody> responseBodyCall = ObjectFactory.getInstance().getRestClient(mcontext).getApiService().getWishList(request);
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
                                getWishListResponse = new Gson().fromJson(responseString, GetWishListResponse.class);
                                Intent intentRes = new Intent(BROADCAST_GET_WISHLIST_RESPONSE);
                                intentRes.putExtra(BROADCAST_GET_WISHLIST_RESPONSE_STATUS, true);
                                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                            }
                        }
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                        Intent intentRes = new Intent(BROADCAST_GET_WISHLIST_RESPONSE);
                        intentRes.putExtra(BROADCAST_GET_WISHLIST_RESPONSE_STATUS, false);
                        LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ProgressbarDismiss();
                Intent intentRes = new Intent(BROADCAST_GET_WISHLIST_RESPONSE);
                intentRes.putExtra(BROADCAST_GET_WISHLIST_RESPONSE_STATUS, false);
                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
            }
        });
    }

    public void ProgressbarDismiss() {
        Intent intent = new Intent(Constants.Login.BROADCAST_PROGRESS_WHEEL);
        intent.putExtra(Constants.Login.BROADCAST_ISDISPLAY_PROGRESS_WHEEL, false);
        LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intent);
    }

    public ArrayList<Categories> getCategoryList() {
        ArrayList<Categories> arrayList = null;
        if (categoriesResponse != null) {
            Categories[] value = categoriesResponse.getCategories();
            arrayList = new ArrayList<Categories>(Arrays.asList(categoriesResponse.getCategories()));
        }
        return arrayList;
    }

    public ArrayList<HomeSliders> getHomeSliders() {
        ArrayList<HomeSliders> arrayList = null;
        try {
            if (homeResponse != null) {
                HomeSliders[] value = homeResponse.getHomeSliders();
                arrayList = new ArrayList<HomeSliders>(Arrays.asList(homeResponse.getHomeSliders()));
            } else {
                String response = ObjectFactory.getInstance().getAppPreference(mcontext).getHomeData();
                homeResponse = new Gson().fromJson(response, HomeResponse.class);
                HomeSliders[] value = homeResponse.getHomeSliders();
                arrayList = new ArrayList<HomeSliders>(Arrays.asList(homeResponse.getHomeSliders()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public ArrayList<HomeCategories> getHomeCategories() {
        ArrayList<HomeCategories> arrayList = null;
        if (homeResponse != null) {
            arrayList = new ArrayList<HomeCategories>(Arrays.asList(homeResponse.getHomeCategoriesMenu().getCategories()));
        }
        return arrayList;
    }
    public ArrayList<HomeBrands> getHomeBrand() {

        if (homeBrandsResponse != null) {
            homeBrandsArrayList = new ArrayList(Arrays.asList(homeBrandsResponse.getHomeBrands()));
        }
        return homeBrandsArrayList;
    }

    public void setHomeCategories(ArrayList<HomeCategories> homeCategories) {
        this.homeCategories = homeCategories;
    }

    public ArrayList<HomeCategories> getSelectedHomeCategories() {
        return homeCategories;
    }


    public ArrayList<Deal> getHomeDealsOfWeek() {
        ArrayList<Deal> arrayList = null;
        if (dealsResponse != null) {
            arrayList = new ArrayList(Arrays.asList(dealsResponse.getData().getDeals()));
        }
        return arrayList;
    }

    public ArrayList<CustomeHomeViewProducts> getHomeCustomeProducts() {
        String[] tittle = new String[0];
        ArrayList<CustomeHomeViewProducts> arrayList = null;
        if (homeResponse != null) {
            int size = 0;
            size = homeResponse.getHomeProducts().getHomeCustomProducts().length;
            HomeCustomProducts[] homeCustomProductses = homeResponse.getHomeProducts().getHomeCustomProducts();
            for (int i = 0; i < size; i++) {
                int j = 0;
                boolean status = false;
                String id = homeCustomProductses[i].getGroupId();
                if (tittle == null) {
                    tittle[0] = id;
                    j++;
                } else {
                    while (j < tittle.length) {
                        if (tittle[j].matches(id)) {
                            status = true;
                            j++;
                        }
                    }
                    if (status == false) {
                        tittle[j] = id;
                    }
                }

            }
            int response_size = 0;
            int i = 0;
            HomeCustomProducts[] homeProducts = homeResponse.getHomeProducts().getHomeCustomProducts();
            while (i < tittle.length) {
                CustomeHomeViewProducts customeHomeViewProducts = null;
                HomeCustomProducts[] homeProducts1 = null;
                for (int j = 0; j < homeProducts.length; j++) {
                    int count = 0;
                    if (tittle[i].matches(homeProducts[j].getGroupId())) {
                        homeProducts1[count] = homeProducts[i];
                        count++;
                    }
                }
                customeHomeViewProducts.setGroupId(tittle[i]);
                //    customeHomeViewProducts.setHomeCustomProducts(homeProducts1);
                arrayList.add(i, customeHomeViewProducts);
            }
        }
        return arrayList;
    }

    public ArrayList<HomeCustomProducts> getHomeCustomeProductsDemo() {
        ArrayList<HomeCustomProducts> arrayList = null;
        try {
            if (homeResponse != null) {
                arrayList = new ArrayList(Arrays.asList(homeResponse.getHomeProducts().getHomeCustomProducts()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public ArrayList<ProductsInCategoryResponse> getProductsInCategory() {
        ArrayList<ProductsInCategoryResponse> arrayList = null;
        if (productsInCategoryResponse != null) {
            arrayList = new ArrayList<ProductsInCategoryResponse>(Arrays.asList(productsInCategoryResponse));
        }
        return arrayList;
    }

    public ArrayList<ProductDetailsResponse> getProductDetailsList() {
        ArrayList<ProductDetailsResponse> arrayList = null;
        if (productDetailsResponse != null) {
            arrayList = new ArrayList<ProductDetailsResponse>(Arrays.asList(productDetailsResponse));
        }
        return arrayList;
    }

    public ArrayList<CustomerDetailsResponse> getProfileDetailsList() {
        ArrayList<CustomerDetailsResponse> arrayList = null;
        if (customerDetailsResponse != null) {
            arrayList = new ArrayList<CustomerDetailsResponse>(Arrays.asList(customerDetailsResponse));
        }
        return arrayList;
    }

    public ArrayList<Products> getSearchResultsList() {
        ArrayList<Products> arrayList = null;
        if (searchProductResponse != null) {
            arrayList = new ArrayList<Products>(Arrays.asList(searchProductResponse.getData().getProducts()));
        }
        return arrayList;
    }

    public ArrayList<GetCartResponse> getCartProductsList() {
        ArrayList<GetCartResponse> arrayList = new ArrayList<>();
        if (getCartResponse != null) {
            arrayList = new ArrayList<GetCartResponse>(Arrays.asList(getCartResponse));
        }
        return arrayList;
    }

    public void setCartResponse(GetCartResponse cartResponse) {
        getCartResponse = cartResponse;
    }

    public void alertDialog(String st) {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mcontext);
        alertDialogBuilder.setMessage(st);
        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void setAttributes(String name, String value) {
        Product_attributesCart product_attributes = new Product_attributesCart();
        product_attributes.setName(name);
        product_attributes.setValue(value);

        if (productAttributesCarts == null || productAttributesCarts.size() == 0) {
            productAttributesCarts.add(product_attributes);
        } else {
            boolean status = false;
            for (int j = 0; j < productAttributesCarts.size(); j++) {
                if (productAttributesCarts.get(j).getName().matches(name)) {
                    productAttributesCarts.remove(j);
                    productAttributesCarts.add(j, product_attributes);
                    status = true;
                }
            }
            if (status == false) {
                productAttributesCarts.add(product_attributes);
            }
        }
    }

    public List<Product_attributesCart> getAttribueSet() {
        return productAttributesCarts;
    }

    public void initializeArraylist() {
        productAttributesCarts = new ArrayList<>();
    }

    public ArrayList<GetWishListItems> getWishListItems() {


        ArrayList<GetWishListItems> arrayList = null;
        if (getWishListResponse != null) {
            arrayList = getWishListResponse.getData().getItems();
        }
        return arrayList;

    }

    //Order history

    public void apiGetOrderHistory(String apiToken, String guid) {
        HashMap<String, String> jsonMap = new HashMap<>();
        jsonMap.put("apiToken", apiToken);
        jsonMap.put("guid", guid);
        String json = new JSONObject(jsonMap).toString();
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
        Call<ResponseBody> call = ObjectFactory.getInstance().getRestClient(mcontext).getApiService().getOrderHistory(body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String json = "";

                if (response.body() != null) {
                    try {
                        json = new String(response.body().bytes());

                        if (!json.isEmpty()) {
                            gson = new Gson();
                            ArrayList<Order> orderList = new ArrayList<Order>();
                            JSONObject jsonObject = new JSONObject(json);
                            if (jsonObject.has("success")) {
                                if (jsonObject.optBoolean("success")) {
                                    Type listType = new TypeToken<List<Order>>() {
                                    }.getType();
                                    orderList = new Gson().fromJson(jsonObject.optJSONObject("data").optJSONArray("Orders").toString(), listType);
                                    Intent intentRes = new Intent(BROADCAST_MY_ORDER);
                                    if (orderList != null) {
                                        intentRes.putExtra(BROADCAST_MY_ORDER_STATUS, true);
                                        setOrderHistory(orderList);
                                    } else {
                                        intentRes.putExtra(BROADCAST_MY_ORDER_STATUS, false);
                                    }
                                    LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                                } else {
//                                    Toast.makeText(mcontext, "" + jsonObject.optString("statusCode"), Toast.LENGTH_SHORT).show();
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
                System.out.println("Failed");
            }
        });
    }

    public void setOrderHistory(ArrayList<Order> orderHistory) {
        this.orderHistory = orderHistory;
    }

    public ArrayList<Order> getOrderHistory() {
        return orderHistory;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getSearchText() {
        return searchText;
    }

    //Order history details

    public void apiGetOrderHistoryDetails(String apiToken, String guid, String orderId) {
        HashMap<String, String> jsonMap = new HashMap<>();
        jsonMap.put("apiToken", apiToken);
        jsonMap.put("guid", guid);
        jsonMap.put("orderId", orderId);
        String json = new JSONObject(jsonMap).toString();
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
        Call<ResponseBody> call = ObjectFactory.getInstance().getRestClient(mcontext).getApiService().getOrderHistoryDetails(body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String json = "";

                if (response.body() != null) {
                    try {
                        json = new String(response.body().bytes());

                        if (!json.isEmpty()) {
                            gson = new Gson();
                            OrderDetailsResponse orderDetailsResponses = new OrderDetailsResponse();
                            JSONObject jsonObject = new JSONObject(json);
                            if (jsonObject.has("success")) {
                                if (jsonObject.optBoolean("success")) {
                                    orderDetailsResponses = gson.fromJson(json, OrderDetailsResponse.class);
                                    Intent intentRes = new Intent(BROADCAST_ORDER_DETAILS);
                                    if (orderDetailsResponses != null) {
                                        intentRes.putExtra(BROADCAST_ORDER_DETAILS_STATUS, true);
                                        setOrderDetails(orderDetailsResponses);
                                    } else {
                                        intentRes.putExtra(BROADCAST_ORDER_DETAILS_STATUS, false);
                                    }
                                    LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                                } else {
//                                    Toast.makeText(mcontext, "" + jsonObject.optString("statusCode"), Toast.LENGTH_SHORT).show();
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
                System.out.println("Failed");
            }
        });
    }

    public void setOrderDetails(OrderDetailsResponse orderDetailsResponse) {
        this.orderDetailsResponse = orderDetailsResponse;
    }

    public OrderDetailsResponse getOrderDetails() {
        return orderDetailsResponse;
    }

    public Integer getPageNumber() {
        return pagenumber;
    }

    public void setPageNumber(Integer pagenumber) {
        this.pagenumber = pagenumber;
    }

    public void setSelectedSubCategory(SubCategories selectedSubCategory) {
        this.selectedSubCategory = selectedSubCategory;
    }

    public SubCategories getSelectedSubCategory() {
        return selectedSubCategory;
    }

    public HomeManager(SubCategories selectedSubCategory) {
        this.selectedSubCategory = selectedSubCategory;
    }

    public void setCategoryList(ArrayList<Categories> categoryList) {
        this.categoriesResponse.setCategories(categoryList.toArray(new Categories[categoryList.size()]));
    }

    public void apiRegisterToken(NotificationRegistrationRequest registrationRequest) {
        Call<ResponseBody> call = ObjectFactory.getInstance().getRestClient(mcontext).getApiService().registerDeviceToken(registrationRequest);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String json = "";

                if (response.body() != null) {
                    try {
                        json = new String(response.body().bytes());

                        if (!json.isEmpty()) {
                            gson = new Gson();
                            JSONObject jsonObject = new JSONObject(json);
                            if (jsonObject.has("success")) {
                                if (jsonObject.optBoolean("success")) {

                                    Intent intentRes = new Intent(BROADCAST_ORDER_DETAILS);
//                                    if (orderDetailsResponses != null){
//                                        intentRes.putExtra(BROADCAST_ORDER_DETAILS_STATUS, true);
//                                        setOrderDetails(orderDetailsResponses);
//                                    }else {
//                                        intentRes.putExtra(BROADCAST_ORDER_DETAILS_STATUS, false);
//                                    }
                                    LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intentRes);
                                } else {
//                                    Toast.makeText(mcontext, "" + jsonObject.optString("statusCode"), Toast.LENGTH_SHORT).show();
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
                System.out.println("Failed");
            }
        });
    }

    public void setHomePageLinks(HomePageLinksResponse homePageLinks) {
        this.homePageLinks = homePageLinks;
    }

    public HomePageLinksResponse getHomePageLinks() {
        return homePageLinks;
    }
}
