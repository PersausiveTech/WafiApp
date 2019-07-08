package com.mobtecnica.wafiapps.model.discountCoupon;

import com.mobtecnica.wafiapps.model.BaseUserRequest;

public class RemoveDiscountCouponRequest extends BaseUserRequest{
    public RemoveDiscountCouponRequest(String guid){
        super();
        setGuid(guid);
    }
}
