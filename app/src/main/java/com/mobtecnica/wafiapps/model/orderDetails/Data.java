
package com.mobtecnica.wafiapps.model.orderDetails;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("PrintMode")
    @Expose
    public Boolean printMode;
    @SerializedName("PdfInvoiceDisabled")
    @Expose
    public Boolean pdfInvoiceDisabled;
    @SerializedName("CreatedOn")
    @Expose
    public String createdOn;
    @SerializedName("OrderStatus")
    @Expose
    public String orderStatus;
    @SerializedName("IsReOrderAllowed")
    @Expose
    public Boolean isReOrderAllowed;
    @SerializedName("IsReturnRequestAllowed")
    @Expose
    public Boolean isReturnRequestAllowed;
    @SerializedName("IsShippable")
    @Expose
    public Boolean isShippable;
    @SerializedName("PickUpInStore")
    @Expose
    public Boolean pickUpInStore;
    @SerializedName("PickupAddress")
    @Expose
    public PickupAddress pickupAddress;
    @SerializedName("ShippingStatus")
    @Expose
    public String shippingStatus;
    @SerializedName("ShippingAddress")
    @Expose
    public ShippingAddress shippingAddress;
    @SerializedName("ShippingMethod")
    @Expose
    public String shippingMethod;
    @SerializedName("Shipments")
    @Expose
    public List<Object> shipments = null;
    @SerializedName("BillingAddress")
    @Expose
    public BillingAddress billingAddress;
    @SerializedName("VatNumber")
    @Expose
    public Object vatNumber;
    @SerializedName("PaymentMethod")
    @Expose
    public String paymentMethod;
    @SerializedName("PaymentMethodStatus")
    @Expose
    public String paymentMethodStatus;
    @SerializedName("CanRePostProcessPayment")
    @Expose
    public Boolean canRePostProcessPayment;
    @SerializedName("CustomValues")
    @Expose
    public CustomValues customValues;
    @SerializedName("OrderSubtotal")
    @Expose
    public String orderSubtotal;
    @SerializedName("OrderSubTotalDiscount")
    @Expose
    public Object orderSubTotalDiscount;
    @SerializedName("OrderShipping")
    @Expose
    public String orderShipping;
    @SerializedName("PaymentMethodAdditionalFee")
    @Expose
    public Object paymentMethodAdditionalFee;
    @SerializedName("CheckoutAttributeInfo")
    @Expose
    public String checkoutAttributeInfo;
    @SerializedName("PricesIncludeTax")
    @Expose
    public Boolean pricesIncludeTax;
    @SerializedName("DisplayTaxShippingInfo")
    @Expose
    public Boolean displayTaxShippingInfo;
    @SerializedName("Tax")
    @Expose
    public String tax;
    @SerializedName("TaxRates")
    @Expose
    public List<TaxRate> taxRates = null;
    @SerializedName("DisplayTax")
    @Expose
    public Boolean displayTax;
    @SerializedName("DisplayTaxRates")
    @Expose
    public Boolean displayTaxRates;
    @SerializedName("OrderTotalDiscount")
    @Expose
    public Object orderTotalDiscount;
    @SerializedName("RedeemedRewardPoints")
    @Expose
    public Integer redeemedRewardPoints;
    @SerializedName("RedeemedRewardPointsAmount")
    @Expose
    public Object redeemedRewardPointsAmount;
    @SerializedName("OrderTotal")
    @Expose
    public String orderTotal;
    @SerializedName("GiftCards")
    @Expose
    public List<Object> giftCards = null;
    @SerializedName("ShowSku")
    @Expose
    public Boolean showSku;
    @SerializedName("Items")
    @Expose
    public List<Item> items = null;
    @SerializedName("OrderNotes")
    @Expose
    public List<Object> orderNotes = null;
    @SerializedName("Id")
    @Expose
    public Integer id;


    public Boolean getPrintMode() {
        return printMode;
    }

    public void setPrintMode(Boolean printMode) {
        this.printMode = printMode;
    }

    public Boolean getPdfInvoiceDisabled() {
        return pdfInvoiceDisabled;
    }

    public void setPdfInvoiceDisabled(Boolean pdfInvoiceDisabled) {
        this.pdfInvoiceDisabled = pdfInvoiceDisabled;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Boolean getReOrderAllowed() {
        return isReOrderAllowed;
    }

    public void setReOrderAllowed(Boolean reOrderAllowed) {
        isReOrderAllowed = reOrderAllowed;
    }

    public Boolean getReturnRequestAllowed() {
        return isReturnRequestAllowed;
    }

    public void setReturnRequestAllowed(Boolean returnRequestAllowed) {
        isReturnRequestAllowed = returnRequestAllowed;
    }

    public Boolean getShippable() {
        return isShippable;
    }

    public void setShippable(Boolean shippable) {
        isShippable = shippable;
    }

    public Boolean getPickUpInStore() {
        return pickUpInStore;
    }

    public void setPickUpInStore(Boolean pickUpInStore) {
        this.pickUpInStore = pickUpInStore;
    }

    public PickupAddress getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(PickupAddress pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public String getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(String shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public List<Object> getShipments() {
        return shipments;
    }

    public void setShipments(List<Object> shipments) {
        this.shipments = shipments;
    }

    public BillingAddress getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(BillingAddress billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Object getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(Object vatNumber) {
        this.vatNumber = vatNumber;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentMethodStatus() {
        return paymentMethodStatus;
    }

    public void setPaymentMethodStatus(String paymentMethodStatus) {
        this.paymentMethodStatus = paymentMethodStatus;
    }

    public Boolean getCanRePostProcessPayment() {
        return canRePostProcessPayment;
    }

    public void setCanRePostProcessPayment(Boolean canRePostProcessPayment) {
        this.canRePostProcessPayment = canRePostProcessPayment;
    }

    public CustomValues getCustomValues() {
        return customValues;
    }

    public void setCustomValues(CustomValues customValues) {
        this.customValues = customValues;
    }

    public String getOrderSubtotal() {
        return orderSubtotal;
    }

    public void setOrderSubtotal(String orderSubtotal) {
        this.orderSubtotal = orderSubtotal;
    }

    public Object getOrderSubTotalDiscount() {
        return orderSubTotalDiscount;
    }

    public void setOrderSubTotalDiscount(Object orderSubTotalDiscount) {
        this.orderSubTotalDiscount = orderSubTotalDiscount;
    }

    public String getOrderShipping() {
        return orderShipping;
    }

    public void setOrderShipping(String orderShipping) {
        this.orderShipping = orderShipping;
    }

    public Object getPaymentMethodAdditionalFee() {
        return paymentMethodAdditionalFee;
    }

    public void setPaymentMethodAdditionalFee(Object paymentMethodAdditionalFee) {
        this.paymentMethodAdditionalFee = paymentMethodAdditionalFee;
    }

    public String getCheckoutAttributeInfo() {
        return checkoutAttributeInfo;
    }

    public void setCheckoutAttributeInfo(String checkoutAttributeInfo) {
        this.checkoutAttributeInfo = checkoutAttributeInfo;
    }

    public Boolean getPricesIncludeTax() {
        return pricesIncludeTax;
    }

    public void setPricesIncludeTax(Boolean pricesIncludeTax) {
        this.pricesIncludeTax = pricesIncludeTax;
    }

    public Boolean getDisplayTaxShippingInfo() {
        return displayTaxShippingInfo;
    }

    public void setDisplayTaxShippingInfo(Boolean displayTaxShippingInfo) {
        this.displayTaxShippingInfo = displayTaxShippingInfo;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public List<TaxRate> getTaxRates() {
        return taxRates;
    }

    public void setTaxRates(List<TaxRate> taxRates) {
        this.taxRates = taxRates;
    }

    public Boolean getDisplayTax() {
        return displayTax;
    }

    public void setDisplayTax(Boolean displayTax) {
        this.displayTax = displayTax;
    }

    public Boolean getDisplayTaxRates() {
        return displayTaxRates;
    }

    public void setDisplayTaxRates(Boolean displayTaxRates) {
        this.displayTaxRates = displayTaxRates;
    }

    public Object getOrderTotalDiscount() {
        return orderTotalDiscount;
    }

    public void setOrderTotalDiscount(Object orderTotalDiscount) {
        this.orderTotalDiscount = orderTotalDiscount;
    }

    public Integer getRedeemedRewardPoints() {
        return redeemedRewardPoints;
    }

    public void setRedeemedRewardPoints(Integer redeemedRewardPoints) {
        this.redeemedRewardPoints = redeemedRewardPoints;
    }

    public Object getRedeemedRewardPointsAmount() {
        return redeemedRewardPointsAmount;
    }

    public void setRedeemedRewardPointsAmount(Object redeemedRewardPointsAmount) {
        this.redeemedRewardPointsAmount = redeemedRewardPointsAmount;
    }

    public String getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(String orderTotal) {
        this.orderTotal = orderTotal;
    }

    public List<Object> getGiftCards() {
        return giftCards;
    }

    public void setGiftCards(List<Object> giftCards) {
        this.giftCards = giftCards;
    }

    public Boolean getShowSku() {
        return showSku;
    }

    public void setShowSku(Boolean showSku) {
        this.showSku = showSku;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Object> getOrderNotes() {
        return orderNotes;
    }

    public void setOrderNotes(List<Object> orderNotes) {
        this.orderNotes = orderNotes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
