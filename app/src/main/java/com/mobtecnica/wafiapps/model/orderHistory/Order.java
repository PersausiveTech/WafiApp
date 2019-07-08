
package com.mobtecnica.wafiapps.model.orderHistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Order {

    @SerializedName("OrderTotal")
    @Expose
    public String orderTotal;
    @SerializedName("IsReturnRequestAllowed")
    @Expose
    public Boolean isReturnRequestAllowed;
    @SerializedName("OrderStatusEnum")
    @Expose
    public String orderStatusEnum;
    @SerializedName("OrderStatus")
    @Expose
    public String orderStatus;
    @SerializedName("PaymentStatus")
    @Expose
    public String paymentStatus;
    @SerializedName("ShippingStatus")
    @Expose
    public String shippingStatus;
    @SerializedName("CreatedOn")
    @Expose
    public String createdOn;
    @SerializedName("Id")
    @Expose
    public Integer id;


    public String getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(String orderTotal) {
        this.orderTotal = orderTotal;
    }

    public Boolean getReturnRequestAllowed() {
        return isReturnRequestAllowed;
    }

    public void setReturnRequestAllowed(Boolean returnRequestAllowed) {
        isReturnRequestAllowed = returnRequestAllowed;
    }

    public String getOrderStatusEnum() {
//        if (orderStatusEnum != null)
            return orderStatusEnum;
//        else return 0;
    }

    public void setOrderStatusEnum(String orderStatusEnum) {
        this.orderStatusEnum = orderStatusEnum;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(String shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public Integer getId() {
        if (id != null)
            return id;
        else return 0;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
