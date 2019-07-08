package com.mobtecnica.wafiapps.model.LaundryModel.laundry_checkout;

/**
 * Created by SIby on 25-04-2017.
 */

public class LaundryCheckoutRequest {
    String apiToken;
    String custGuid;
    Integer AreaID;
    String isExpressCheckout;
    Integer LaundryTypeID;
    String PromoCode;

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getCustGuid() {
        return custGuid;
    }

    public void setCustGuid(String custGuid) {
        this.custGuid = custGuid;
    }

    public Integer getAreaID() {
        return AreaID;
    }

    public void setAreaID(Integer areaID) {
        AreaID = areaID;
    }

    public String getIsExpressCheckout() {
        return isExpressCheckout;
    }

    public void setIsExpressCheckout(String isExpressCheckout) {
        this.isExpressCheckout = isExpressCheckout;
    }

    public Integer getLaundryTypeID() {
        return LaundryTypeID;
    }

    public void setLaundryTypeID(Integer laundryTypeID) {
        LaundryTypeID = laundryTypeID;
    }

    public String getPromoCode() {
        return PromoCode;
    }

    public void setPromoCode(String promoCode) {
        PromoCode = promoCode;
    }
}
