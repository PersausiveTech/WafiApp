package com.mobtecnica.wafiapps.model.cart.getCartCount;

import com.mobtecnica.wafiapps.model.BaseUserRequest;

public class GetCartCountRequest extends BaseUserRequest {
    public GetCartCountRequest(String guid) {
        super();
        setGuid(guid);
    }
}
