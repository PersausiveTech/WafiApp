package com.mobtecnica.wafiapps.model.cart.getCart.getCartResponse;

/**
 * Created by SIby on 20-03-2017.
 */

public class OrderReviewData {
    private PickupAddress PickupAddress;

    private String ShippingMethod;

    private String IsShippable;

    private ShippingAddress ShippingAddress;

    private String Display;

    private BillingAddress BillingAddress;

//    private String CustomProperties;

    private String PaymentMethod;

    private CustomValues CustomValues;

    private String SelectedPickUpInStore;

    public PickupAddress getPickupAddress() {
        return PickupAddress;
    }

    public void setPickupAddress(PickupAddress PickupAddress) {
        this.PickupAddress = PickupAddress;
    }


    public String getIsShippable() {
        return IsShippable;
    }

    public void setIsShippable(String IsShippable) {
        this.IsShippable = IsShippable;
    }

    public ShippingAddress getShippingAddress() {
        return ShippingAddress;
    }

    public void setShippingAddress(ShippingAddress ShippingAddress) {
        this.ShippingAddress = ShippingAddress;
    }

    public String getDisplay() {
        return Display;
    }

    public void setDisplay(String Display) {
        this.Display = Display;
    }

    public BillingAddress getBillingAddress() {
        return BillingAddress;
    }

    public void setBillingAddress(BillingAddress BillingAddress) {
        this.BillingAddress = BillingAddress;
    }


    public String getShippingMethod() {
        return ShippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        ShippingMethod = shippingMethod;
    }

    public String getPaymentMethod() {
        return PaymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        PaymentMethod = paymentMethod;
    }

    public CustomValues getCustomValues() {
        return CustomValues;
    }

    public void setCustomValues(CustomValues customValues) {
        CustomValues = customValues;
    }

    public String getSelectedPickUpInStore() {
        return SelectedPickUpInStore;
    }

    public void setSelectedPickUpInStore(String SelectedPickUpInStore) {
        this.SelectedPickUpInStore = SelectedPickUpInStore;
    }

    @Override
    public String toString() {
        return "ClassPojo [PickupAddress = " + PickupAddress + ", ShippingMethod = " + ShippingMethod + ", IsShippable = " + IsShippable + ", ShippingAddress = " + ShippingAddress + ", Display = " + Display + ", BillingAddress = " + BillingAddress + ", CustomProperties  PaymentMethod = " + PaymentMethod + ", CustomValues = " + CustomValues + ", SelectedPickUpInStore = " + SelectedPickUpInStore + "]";
    }
}


