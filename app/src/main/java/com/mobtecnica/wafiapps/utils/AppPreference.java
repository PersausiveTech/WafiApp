package com.mobtecnica.wafiapps.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.model.home.HomePageLinksResponse;
import com.mobtecnica.wafiapps.model.profile.cusomerDetails.customerDetails.CustomerDetailsResponse;


public class AppPreference {

    public static final String KEY_LOGIN = "KEY_LOGIN";
    public static final String ERROR_MESSAGE_LOGIN = "ERROR_MESSAGE_LOGIN";
    public static final String KEY_GUID = "KEY_GUID";
    public static final String KEY_SIGN_UP = "KEY_SIGN_UP";
    public static final String TOKEN_PASSWORD_RECOVERY = "TOKEN_PASSWORD_RECOVERY";
    public static final String TOKEN_PASSWORD_RECOVERY_FINAL = "TOKEN_PASSWORD_RECOVERY_FINAL";
    public static final String CATEGORIES_DATA = "CATEGORIES_DATA";
    public static final String RECOVERY_EMAIL = "RECOVERY_EMAIL";
    public static final String HOME_DATA = "HOME_DATA";
    public static final String CATEGORY_ID = "CATEGORY_ID";
    public static final String PRODUCT_ID = "PRODUCT_ID";
    public static final String PROFILE_DATA = "PROFILE_DATA";
    public static final String IS_LOGGED_IN = "IS_LOGGED_IN";
    public static final String LAUNDRY_IMAGE = "LAUNDRY_IMAGE";
    public static final String USER_EMAIL = "USER_EMAIL";
    public static final String CUSTOMER_ID = "CUSTOMER_ID";
    private static final String HOME_PAGE_LINKS_DATA = "HOME_PAGE_LINKS_DATA";
    public static int key_position = 0;
    public static int serviceType = 0;
    public ArrayList<String> deleteIds = new ArrayList<>();
    public ArrayList<String> deleteIds_laundry = new ArrayList<>();
    private SharedPreferences mSharedPreferences;
    public static int billingAddressPosition = 0;
    private CustomerDetailsResponse customerDetailsResponse;

    public AppPreference(Context context) {
        super();
        if (context != null) {
            mSharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
        }
    }

    public void updateContext(Context context) {
        if (context != null) {
            mSharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
        }
    }

    public void saveUserType(String value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(KEY_LOGIN, value);
        editor.apply();
    }



    public String getUser() {
        return mSharedPreferences.getString(KEY_LOGIN, "");
    }

    public String getGuid() {
        return mSharedPreferences.getString(KEY_GUID, "");
    }

    public void setGuid(String value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(KEY_GUID, value);
        editor.apply();
    }

    public void saveSignUpDetails(String responseStr) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(KEY_SIGN_UP, responseStr);
        editor.apply();
    }

    public String getSignUpDetails() {
        return mSharedPreferences.getString(KEY_SIGN_UP, "");
    }


    //used in ForgotPasswordFragment

    public void setPasswordRecoveryToken(String passwordRecoveryToken) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(TOKEN_PASSWORD_RECOVERY, passwordRecoveryToken);
        editor.apply();
    }

    public String getPasswordRrecoveryToken() {
        return mSharedPreferences.getString(TOKEN_PASSWORD_RECOVERY, "");
    }

    public String getPasswordRecoveryTokenFinal() {
        return mSharedPreferences.getString(TOKEN_PASSWORD_RECOVERY_FINAL, "");
    }

    //used in ForgotPasswordConfirmFragment.java
    public void setPasswordRecoveryTokenFinal(String token) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(TOKEN_PASSWORD_RECOVERY_FINAL, token);
        editor.apply();
    }

    public String getRecoveryEmail() {
        return mSharedPreferences.getString(RECOVERY_EMAIL, "");
    }

    public void setRecoveryEmail(String email) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(RECOVERY_EMAIL, email);
        editor.apply();
    }

    public void saveCategories(String responseString) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(CATEGORIES_DATA, responseString);
        editor.apply();
    }

    public String getCategoriesData() {
        return mSharedPreferences.getString(CATEGORIES_DATA, "");
    }


    public void saveHomeData(String responseString) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(HOME_DATA, responseString);
        editor.apply();
    }

    public String getHomeData() {
        return mSharedPreferences.getString(HOME_DATA, "");
    }

    public void setCategoryId(String item_id) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(CATEGORY_ID, item_id);
        editor.apply();
    }

    public String getCategoryID() {
        return mSharedPreferences.getString(CATEGORY_ID, "");
    }

    public String getProductId() {
        return mSharedPreferences.getString(PRODUCT_ID, "");
    }

    public void setProductId(String id) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(PRODUCT_ID, id);
        editor.apply();
    }

    public void saveProfileData(String responseString) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(PROFILE_DATA, responseString);
        editor.apply();
    }

    public String getProfileData() {
        return mSharedPreferences.getString(PROFILE_DATA, "");
    }

    public String getIsLoggedIn() {
        return mSharedPreferences.getString(IS_LOGGED_IN, "");
    }

    public void setIsLoggedIn(String b) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(IS_LOGGED_IN, b);
        editor.apply();
    }

    public void setAddressPosition(int position) {
        this.key_position = position;
    }

    public int getCurrrentPosition() {
        return key_position;
    }

    public String getLaundryImage() {
        return mSharedPreferences.getString(LAUNDRY_IMAGE, "");
    }

    public void setLaundryImage(String image_url) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(LAUNDRY_IMAGE, image_url);
        editor.apply();
    }

    public void setServices(int standard) {
        serviceType = standard;
    }

    public int getServiceType() {
        return serviceType;
    }

    public void setDeletedIds(ArrayList<String> deleteIds) {
        this.deleteIds = new ArrayList<>();
        this.deleteIds = deleteIds;
    }

    public void setDeletedIdsLaundry(ArrayList<String> deleteIds) {
        this.deleteIds_laundry = new ArrayList<>();
        this.deleteIds_laundry = deleteIds;
    }

    public ArrayList<String> getDeleteIds() {
        return deleteIds;
    }

    public ArrayList<String> getDeleteIdsLaundry() {
        return deleteIds_laundry;
    }

    public String getEmailId() {

        return mSharedPreferences.getString(USER_EMAIL, "");
    }

    public void setEmailId(String email) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(USER_EMAIL, email);
        editor.apply();

    }

    public void setCustomerID(String customer_id){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(CUSTOMER_ID, customer_id);
        editor.apply();
    }
    public String getCustomerId() {
        return mSharedPreferences.getString(CUSTOMER_ID,"");
    }

    public void setBillingAddressPosition(int billingAddressPosition) {
        this.billingAddressPosition = billingAddressPosition;
    }

    public int getBillingAddressPosition() {
        return billingAddressPosition;
    }

    public void saveProfileData(CustomerDetailsResponse customerDetailsResponse) {
        this.customerDetailsResponse = customerDetailsResponse;
    }

    public CustomerDetailsResponse getCustomerDetailsResponse() {
        return customerDetailsResponse;
    }

    public void setHomePageLinksData(HomePageLinksResponse homePageLinksData) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(homePageLinksData);
        editor.putString(HOME_PAGE_LINKS_DATA, json);
        editor.apply();
    }

    public HomePageLinksResponse getHomePageLinksData() {
        String json =  mSharedPreferences.getString(HOME_PAGE_LINKS_DATA, "");
        Gson gson = new Gson();
        if(!json.equals("")) {
            return gson.fromJson(json,HomePageLinksResponse.class);
        } else {
            return null;
        }
    }
}
