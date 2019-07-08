package com.mobtecnica.wafiapps.model.checkout.savePaymentInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderReviewData {

    @SerializedName("Display")
    @Expose
    private Boolean display;
    @SerializedName("BillingAddress")
    @Expose
    private BillingAddress billingAddress;
    @SerializedName("IsShippable")
    @Expose
    private Boolean isShippable;
    @SerializedName("ShippingAddress")
    @Expose
    private ShippingAddress shippingAddress;
    @SerializedName("SelectedPickUpInStore")
    @Expose
    private Boolean selectedPickUpInStore;
    @SerializedName("PickupAddress")
    @Expose
    private PickupAddress pickupAddress;
    @SerializedName("ShippingMethod")
    @Expose
    private String shippingMethod;
    @SerializedName("PaymentMethod")
    @Expose
    private String paymentMethod;
    @SerializedName("CustomValues")
    @Expose
    private CustomValues customValues;

    public Boolean getDisplay() {
        return display;
    }

    public void setDisplay(Boolean display) {
        this.display = display;
    }

    public BillingAddress getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(BillingAddress billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Boolean getShippable() {
        return isShippable;
    }

    public void setShippable(Boolean shippable) {
        isShippable = shippable;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Boolean getSelectedPickUpInStore() {
        return selectedPickUpInStore;
    }

    public void setSelectedPickUpInStore(Boolean selectedPickUpInStore) {
        this.selectedPickUpInStore = selectedPickUpInStore;
    }

    public PickupAddress getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(PickupAddress pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public CustomValues getCustomValues() {
        return customValues;
    }

    public void setCustomValues(CustomValues customValues) {
        this.customValues = customValues;
    }
}
