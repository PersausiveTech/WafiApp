package com.mobtecnica.wafiapps.model.discountCoupon;

import com.mobtecnica.wafiapps.utils.Constants;

/**
 * Created by SIby on 31-03-2017.
 */


public class ApplyDiscountCoupenRequest
{
    private String guid;

    private String apiToken;

    String discountcouponcode;

    public ApplyDiscountCoupenRequest() {
    }

    public ApplyDiscountCoupenRequest(String guid) {
        setGuid(guid);
        setApiToken(Constants.API_TOKEN);
    }

    public String getDiscountcouponcode() {
        return discountcouponcode;
    }

    public void setDiscountcouponcode(String discountcouponcode) {
        this.discountcouponcode = discountcouponcode;
    }

    public String getGuid ()
    {
        return guid;
    }

    public void setGuid (String guid)
    {
        this.guid = guid;
    }

    public String getApiToken ()
    {
        return apiToken;
    }

    public void setApiToken (String apiToken)
    {
        this.apiToken = apiToken;
    }

}

