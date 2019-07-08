package com.mobtecnica.wafiapps.model.orderHistory;

import com.mobtecnica.wafiapps.model.BaseUserRequest;

public class ReOrderRequest extends BaseUserRequest {
    private String orderId;

    public ReOrderRequest(String guid, String orderId) {
        super();
        setGuid(guid);
        setOrderId(orderId);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
