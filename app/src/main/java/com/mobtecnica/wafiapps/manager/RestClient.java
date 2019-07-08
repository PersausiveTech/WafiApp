package com.mobtecnica.wafiapps.manager;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.mobtecnica.wafiapps.model.LaundryModel.LaundryCart.LaundryCartRequest;
import com.mobtecnica.wafiapps.model.LaundryModel.getlaundryItems.LaundryItemsRequest;
import com.mobtecnica.wafiapps.model.LaundryModel.laundryAddToCart.LaundryAddtoCartRequest;
import com.mobtecnica.wafiapps.model.LaundryModel.laundryHome.LaundryHomeImageRequest;
import com.mobtecnica.wafiapps.model.LaundryModel.laundry_checkout.AreaList;
import com.mobtecnica.wafiapps.model.LaundryModel.priceLIst.PriceListRequest;
import com.mobtecnica.wafiapps.model.LaundryModel.removeFromCart.RemoveFromCartRequestLaundry;
import com.mobtecnica.wafiapps.model.cart.addToCart.AddToCartRequest;
import com.mobtecnica.wafiapps.model.cart.getCart.GetCart;
import com.mobtecnica.wafiapps.model.cart.getCartCount.GetCartCountRequest;
import com.mobtecnica.wafiapps.model.cart.removeFromCart.RemoveFromCartRequest;
import com.mobtecnica.wafiapps.model.cart.updateCart.changeQuantity.ChangeQuantityRequest;
import com.mobtecnica.wafiapps.model.categories.CategoriesRequest;
import com.mobtecnica.wafiapps.model.checkout.BasicRequest;
import com.mobtecnica.wafiapps.model.checkoutnew.ConfirmOrderNew;
import com.mobtecnica.wafiapps.model.checkoutnew.PaymentMethodRequestNew;
import com.mobtecnica.wafiapps.model.checkoutnew.SaveBillingNew;
import com.mobtecnica.wafiapps.model.checkoutnew.SavePaymentInfoNew;
import com.mobtecnica.wafiapps.model.checkoutnew.SaveShippingMethodNew;
import com.mobtecnica.wafiapps.model.checkoutnew.SaveShippingNew;
import com.mobtecnica.wafiapps.model.checkoutnew.startCheckout.StartCheckoutNew;
import com.mobtecnica.wafiapps.model.deals.DealsResponse;
import com.mobtecnica.wafiapps.model.discountCoupon.ApplyDiscountCoupenRequest;
import com.mobtecnica.wafiapps.model.discountCoupon.RemoveDiscountCouponRequest;
import com.mobtecnica.wafiapps.model.forgotPassword.ForgotPasswordRequest;
import com.mobtecnica.wafiapps.model.forgotPassword.password_change.ChangePasswordRequest;
import com.mobtecnica.wafiapps.model.forgotPassword.password_recovery_change_password.ForgotPasswordChangeRequest;
import com.mobtecnica.wafiapps.model.forgotPassword.password_recovery_confirmation_token.ForgotPasswordTokenRequest;
import com.mobtecnica.wafiapps.model.giftCard.GiftCardRequest;
import com.mobtecnica.wafiapps.model.giftCard.RemoveGiftCardRequest;
import com.mobtecnica.wafiapps.model.home.DealsRequest;
import com.mobtecnica.wafiapps.model.home.HomePageRequest;
import com.mobtecnica.wafiapps.model.login.LoginRequest;
import com.mobtecnica.wafiapps.model.notification.NotificationRegistrationRequest;
import com.mobtecnica.wafiapps.model.orderHistory.ReOrderRequest;
import com.mobtecnica.wafiapps.model.paymentmethod.PaymentMethodRequest;
import com.mobtecnica.wafiapps.model.productDetails.productDetailsRequest.ProductDetailsRequest;
import com.mobtecnica.wafiapps.model.productsInCategories.ProductsInCategroiesRequest;
import com.mobtecnica.wafiapps.model.productsInManufacturer.ProductsInManufacturerRequest;
import com.mobtecnica.wafiapps.model.profile.add_address.AddAddressRequest;
import com.mobtecnica.wafiapps.model.profile.cusomerDetails.CustomerPersonalDetailsRequest;
import com.mobtecnica.wafiapps.model.profile.deleteAddress.DeleteUserAddressRequest;
import com.mobtecnica.wafiapps.model.profile.getCustomerByGuid.CustomerDetailsRequest;
import com.mobtecnica.wafiapps.model.profile.get_address.GetUserAddressRequest;
import com.mobtecnica.wafiapps.model.profile.update_address.UpdateUserAddressRequest;
import com.mobtecnica.wafiapps.model.search.SearchRequest;
import com.mobtecnica.wafiapps.model.signup.SignUpRequest;
import com.mobtecnica.wafiapps.model.wafiEats.addToCart.AddToEatsCartRequest;
import com.mobtecnica.wafiapps.model.wafiEats.cartRestaurant.CartRequest;
import com.mobtecnica.wafiapps.model.wafiEats.cartRestaurant.removeFromCart.RemoveFromEatsCartRequest;
import com.mobtecnica.wafiapps.model.wafiEats.getAllrestaruntsDetails.GetRestaurantsDetailsRequest;
import com.mobtecnica.wafiapps.model.wafiEats.getMenuOptions.GetMenuOptionsRequest;
import com.mobtecnica.wafiapps.model.wafiEats.getRestaurants.GetResturantsRequest;
import com.mobtecnica.wafiapps.model.wafiEats.getRestaurantsMenu.GetRestaurantMenuRequest;
import com.mobtecnica.wafiapps.model.wafiEats.home.HomeRequest;
import com.mobtecnica.wafiapps.model.wishlist.addToCart.AddToCartFromWishListRequest;
import com.mobtecnica.wafiapps.model.wishlist.addToWishlist.WishListRequest;
import com.mobtecnica.wafiapps.model.wishlist.newWishList.GetWishListRequest;
import com.mobtecnica.wafiapps.model.wishlist.removeWishList.RemoveWishListRequest;
import com.mobtecnica.wafiapps.model.wishlist.updateWishlist.UpdateWishListRequest;
import com.mobtecnica.wafiapps.utils.Constants;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;


public class RestClient {

    private Context context;

    public RestClient(Context context) {
        this.context = context.getApplicationContext();
    }

    public void updateContext(Context context) {
        if (context != null)
            this.context = context.getApplicationContext();
    }

    private Retrofit getAdapter() {
        OkHttpClient mOkHttpClient = new OkHttpClient()
                .newBuilder()
                .addNetworkInterceptor(new StethoInterceptor())
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public WebServiceApi getApiService() {
        WebServiceApi api = getAdapter().create(WebServiceApi.class);
        return api;
    }
    private Retrofit getCrediMaxAdapter() {
        OkHttpClient mOkHttpClient = new OkHttpClient()
                .newBuilder()
                .addNetworkInterceptor(new StethoInterceptor())
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_CREDIMAX)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public WebServiceApi getCrediMaxApiService() {
        WebServiceApi api = getCrediMaxAdapter().create(WebServiceApi.class);
        return api;
    }

    /*
    *
    * TODO :dcvbn
    * */

    private Retrofit getLaundryAdapter() {
        OkHttpClient mOkHttpClient = new OkHttpClient()
                .newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_LAUNDRY)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public WebServiceApi getLaundryApiService() {
        WebServiceApi api = getLaundryAdapter().create(WebServiceApi.class);
        return api;
    }
/*Wafi eats*/

    private Retrofit getEatsAdapter() {
        OkHttpClient mOkHttpClient = new OkHttpClient()
                .newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_EATS)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public WebServiceApi getEatsApiService() {
        WebServiceApi api = getEatsAdapter().create(WebServiceApi.class);
        return api;
    }

    public interface WebServiceApi {

        @POST("login")
        Call<ResponseBody> userLogin(@Body LoginRequest body);

        @POST("register")
        Call<ResponseBody> userRegister(@Body SignUpRequest request);

        @POST("GetCustomerByGuid")
        Call<ResponseBody> getCustomerDetails(@Body CustomerDetailsRequest request);

        @POST("AddressAdd")
        Call<ResponseBody> addCustomerAddress(@Body AddAddressRequest request);

        @POST("GetCustomerAddresses")
        Call<ResponseBody> getCustomerAddresses(@Body GetUserAddressRequest request);

        @POST("AddressUpdate")
        Call<ResponseBody> updateCustomerAddress(@Body UpdateUserAddressRequest request);

        @POST("AddressDelete")
        Call<ResponseBody> deleteCustomerAddress(@Body DeleteUserAddressRequest request);

        @POST("PasswordRecoverySend")
        Call<ResponseBody> passwordRecoverySend(@Body ForgotPasswordRequest request);

        @POST("PasswordRecoveryConfirmToken")
        Call<ResponseBody> passwordRecoveryToken(@Body ForgotPasswordTokenRequest request);

        @POST("PasswordRecoveryChangePassword")
        Call<ResponseBody> passwordRecoveryChangePassword(@Body ForgotPasswordChangeRequest request);

        @POST("ChangePassword")
        Call<ResponseBody> changePassword(@Body ChangePasswordRequest request);

        @POST("GetAllCategories")
        Call<ResponseBody> getCategories(@Body CategoriesRequest request);

//        @POST("GetAllInHomePage")
//        Call<ResponseBody> getHomePageData(@Body HomePageRequest request);

        @POST("GetAllInHomePageVer2")
        Call<ResponseBody> getHomePageData(@Body HomePageRequest request);

        @POST("GetAppHomeHeader")
        Call<ResponseBody> getHomeHeader(@Body HomePageRequest request);

        @POST("GetAppHomeProducts")
        Call<ResponseBody> getHomeProducts(@Body HomePageRequest request);

        @POST("GetHomePageBrands")
        Call<ResponseBody> getHomeBrand(@Body HomePageRequest request);

        @POST("GetDealLinks")
        Call<ResponseBody> getHomePageDeals(@Body DealsRequest dealsRequest);

        @POST("ProductsInCategory")
        Call<ResponseBody> getProductsInCategory(@Body ProductsInCategroiesRequest request);


        @POST("ProductsInManufacturer")
        Call<ResponseBody> getProductsInManufacturer(@Body ProductsInManufacturerRequest request);


        @POST("GetProductDetails")
        Call<ResponseBody> getProductsDetails(@Body ProductDetailsRequest request);

        @POST("CustomerInfo")
        Call<ResponseBody> getCustomer(@Body CustomerPersonalDetailsRequest request);

        @POST("AddProductToCart")
        Call<ResponseBody> addToWishLIst(@Body WishListRequest request);

        @POST("AddProductToCart")
        Call<ResponseBody> addToCart(@Body AddToCartRequest request);

        @POST("SearchProducts")
        Call<ResponseBody> searchProduct(@Body SearchRequest request);

//        @POST("TestSearchProducts")
//        Call<ResponseBody> testSearchProduct(@Body SearchRequest request);

        @POST("SearchProductsVer1")
        Call<ResponseBody> testSearchProduct(@Body SearchRequest request);

        @POST("Cart")
        Call<ResponseBody> getCart(@Body GetCart request);

        @POST("UpdateCart")
        Call<ResponseBody> deleteProducts(@Body RemoveFromCartRequest request);

        @POST("UpdateCart")
        Call<ResponseBody> changeQuantity(@Body ChangeQuantityRequest request);

//        @POST("ApplyGiftCard")
//        Call<ResponseBody> applyGiftCard(@Body GiftCardRequest request);

        @POST("ApplyGiftCardVer1")
        Call<ResponseBody> applyGiftCard(@Body GiftCardRequest request);

        @POST("RemoveGiftCardCodeVer1")
        Call<ResponseBody> removeGiftCardCode(@Body RemoveGiftCardRequest request);

        @POST("ApplyDiscountCoupon")
        Call<ResponseBody> applyDiscountCoupon(@Body RequestBody request);

        @POST("ApplyDiscountCouponVer1")
        Call<ResponseBody> applyDiscountCoupon(@Body ApplyDiscountCoupenRequest request);

        @POST("RemoveDiscountCouponVer1")
        Call<ResponseBody> removeDiscountCoupon(@Body RemoveDiscountCouponRequest request);

        @POST("Wishlist")
        Call<ResponseBody> getWishList(@Body GetWishListRequest request);

        @POST("CreateAppSubscriber")
        Call<ResponseBody> registerDeviceToken(@Body NotificationRegistrationRequest registrationRequest);

        /*
        * LAUNDRY :)
        * */
        @POST("Index")
        Call<ResponseBody> getLaundryHomeImage(@Body LaundryHomeImageRequest request);

        @POST("pricelist")
        Call<ResponseBody> getPriceLIst(@Body PriceListRequest request);


        @POST("GetLaundryItems")
        Call<ResponseBody> getLaundryItems(@Body LaundryItemsRequest request);

        @POST("GetCartItems")
        Call<ResponseBody> getlaundryCart(@Body LaundryCartRequest request);

        @POST("GetLaundryBagCount")
        Call<ResponseBody> getLaundryCartCount(@Body LaundryCartRequest request);

        @POST("AddToCart")
        Call<ResponseBody> addToLaundryCart(@Body LaundryAddtoCartRequest request);


        @POST("RemoveFromCart")
        Call<ResponseBody> removeFromCart(@Body RemoveFromCartRequestLaundry request);

        @POST("GetCustomerAddressesAndAreaList")
        Call<ResponseBody> getCustomerAddressesAndAreaList(@Body AreaList requestBody);

        @POST("CheckOut")
        Call<ResponseBody> laundryCheckout(@Body RequestBody request);

        @POST("PlaceOrder")
        Call<ResponseBody> laundryPlaceorder(@Body RequestBody request);


        /*Checkout*/
        @POST("StartCheckout")
        Call<ResponseBody> startCheckout(@Body RequestBody request); //ook

        @POST("OnePageCheckout")
        Call<ResponseBody> onePageCheckout(@Body RequestBody request);//ok

        @POST("OpcBillingForm")
        Call<ResponseBody> opcBillingForm(@Body RequestBody request); //ok

        @POST("OpcSaveBilling")
        Call<ResponseBody> opcSaveBilling(@Body RequestBody request);

        @POST("OpcSaveShipping")
        Call<ResponseBody> opcSaveShipping(@Body RequestBody request);

        @POST("OpcSaveShippingMethod")
        Call<ResponseBody> opcSaveShippingMethod(@Body RequestBody request);

        @POST("OpcSavePaymentMethod")
        Call<ResponseBody> opcSavePaymentMethod(@Body RequestBody request);

        @POST("OpcSavePaymentInfo")
        Call<ResponseBody> opcSavePaymentInfo(@Body RequestBody request);

        @POST("OpcConfirmOrder")
        Call<ResponseBody> opcConfirmOrder(@Body RequestBody request);

        @POST("Session")
        Call<ResponseBody> opcGetCredimaxSession(@Body RequestBody request);

        @POST("Transaction")
        Call<ResponseBody> opcCompleteTransaction(@Body RequestBody request);

        /*Wafi eats*/
        @POST("Home")
        Call<ResponseBody> eatsHome(@Body HomeRequest request);

        @POST("GetAllRestaurants")
        Call<ResponseBody> eatsGetAllRestaurants(@Body HomeRequest request);

        @POST("GetRestaurantDetail")
        Call<ResponseBody> getRestaurantDetail(@Body GetRestaurantsDetailsRequest request);

        @POST("GetAllOffers")
        Call<ResponseBody> eatsGetAllOffers(@Body HomeRequest request);

        @POST("GetCartItems")
        Call<ResponseBody> eatsGetCart(@Body CartRequest request);

        @POST("GetRestaurants")
        Call<ResponseBody> eatsRestaurants(@Body GetResturantsRequest request);

        @POST("GetRestaurantMenu")
        Call<ResponseBody> getRestaurantsMenu(@Body GetRestaurantMenuRequest request);

        @POST("GetMenuOptions")
        Call<ResponseBody> getMenuOptions(@Body GetMenuOptionsRequest request);

        @POST("RemoveFromCart")
        Call<ResponseBody> removeFromEatsCart(@Body RemoveFromEatsCartRequest request);

        @POST("AddToCart")
        Call<ResponseBody> addToCartWafiapps(@Body AddToEatsCartRequest request);


        /*wafi apps*/
        @POST("AddItemsToCartFromWishlist")
        Call<ResponseBody> addItemsToWishList(@Body AddToCartFromWishListRequest request);

        @POST("UpdateWishlist")
        Call<ResponseBody> deleteFromWishList(@Body RemoveWishListRequest request);

        @POST("UpdateWishlist")
        Call<ResponseBody> updateWishList(@Body UpdateWishListRequest request);


        //f***kin
        @POST("OpcSaveShippingMethod")
        Call<ResponseBody> newSaveShippingMethod(@Body SaveShippingMethodNew request);

        @POST("OpcSavePaymentMethod")
        Call<ResponseBody> newSavePaymentMethod(@Body PaymentMethodRequestNew request);

        @POST("OpcSavePaymentInfo")
        Call<ResponseBody> newOpcSavePaymentInfo(@Body SavePaymentInfoNew request);

        @POST("OpcConfirmOrder")
        Call<ResponseBody> newConfirmOrder(@Body ConfirmOrderNew request);


        //

        @POST("StartCheckout")
        Call<ResponseBody> newStartCheckout(@Body StartCheckoutNew request);

        @POST("OnePageCheckout")
        Call<ResponseBody> newOnepageCheckout(@Body BasicRequest request);

        @POST("OpcBillingForm")
        Call<ResponseBody> newOpcBillingForm(@Body BasicRequest request);

        @POST("OpcSaveBilling")
        Call<ResponseBody> newOpcSaveBillingForm(@Body SaveBillingNew request);

        @POST("OpcSaveShipping")
        Call<ResponseBody> newOpcSaveShipping(@Body SaveShippingNew request);

        //topi contents

        @POST("GetTopicContent")
        Call<ResponseBody> getTopicContent(@Body RequestBody json);

        //Order history
        @POST("OrderHistory")
        Call<ResponseBody> getOrderHistory(@Body RequestBody json);

        @POST("OrderDetails")
        Call<ResponseBody> getOrderHistoryDetails(@Body RequestBody json);

        @POST("ReOrder")
        Call<ResponseBody> reOrder(@Body ReOrderRequest reOrderRequest);

        //FB Login
        @POST("FBLogin")
        Call<ResponseBody> fbLogin(@Body RequestBody json);

        //Payment Methods
        @POST("LoadPaymentMethods")
        Call<ResponseBody> getPaymentMethods(@Body PaymentMethodRequest paymentMethodRequest);
        //Get cart items count
        @POST("GetCartCount")
        Call<ResponseBody> getCartCount(@Body GetCartCountRequest getCartCountRequest);

    }

}