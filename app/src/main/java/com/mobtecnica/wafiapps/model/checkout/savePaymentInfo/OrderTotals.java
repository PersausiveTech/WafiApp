
package com.mobtecnica.wafiapps.model.checkout.savePaymentInfo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderTotals {

    @SerializedName("IsEditable")
    @Expose
    private Boolean isEditable;
    @SerializedName("SubTotal")
    @Expose
    private String subTotal;
    @SerializedName("SubTotalDiscount")
    @Expose
    private String subTotalDiscount;
    @SerializedName("AllowRemovingSubTotalDiscount")
    @Expose
    private Boolean allowRemovingSubTotalDiscount;
    @SerializedName("Shipping")
    @Expose
    private String shipping;
    @SerializedName("RequiresShipping")
    @Expose
    private Boolean requiresShipping;
    @SerializedName("SelectedShippingMethod")
    @Expose
    private String selectedShippingMethod;
    @SerializedName("PaymentMethodAdditionalFee")
    @Expose
    private String paymentMethodAdditionalFee;
    @SerializedName("Tax")
    @Expose
    private String tax;
    @SerializedName("TaxRates")
    @Expose
    private List<TaxRate> taxRates = null;
    @SerializedName("DisplayTax")
    @Expose
    private Boolean displayTax;
    @SerializedName("DisplayTaxRates")
    @Expose
    private Boolean displayTaxRates;
    @SerializedName("GiftCards")
    @Expose
    private Object giftCards = null;
    @SerializedName("OrderTotalDiscount")
    @Expose
    private String orderTotalDiscount;
    @SerializedName("AllowRemovingOrderTotalDiscount")
    @Expose
    private Boolean allowRemovingOrderTotalDiscount;
    @SerializedName("RedeemedRewardPoints")
    @Expose
    private Integer redeemedRewardPoints;
    @SerializedName("RedeemedRewardPointsAmount")
    @Expose
    private String redeemedRewardPointsAmount;
    @SerializedName("WillEarnRewardPoints")
    @Expose
    private Integer willEarnRewardPoints;
    @SerializedName("OrderTotal")
    @Expose
    private String orderTotal;

    public Boolean getEditable() {
        return isEditable;
    }

    public void setEditable(Boolean editable) {
        isEditable = editable;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public String getSubTotalDiscount() {
        return subTotalDiscount;
    }

    public void setSubTotalDiscount(String subTotalDiscount) {
        this.subTotalDiscount = subTotalDiscount;
    }

    public Boolean getAllowRemovingSubTotalDiscount() {
        return allowRemovingSubTotalDiscount;
    }

    public void setAllowRemovingSubTotalDiscount(Boolean allowRemovingSubTotalDiscount) {
        this.allowRemovingSubTotalDiscount = allowRemovingSubTotalDiscount;
    }

    public String getShipping() {
        return shipping;
    }

    public void setShipping(String shipping) {
        this.shipping = shipping;
    }

    public Boolean getRequiresShipping() {
        return requiresShipping;
    }

    public void setRequiresShipping(Boolean requiresShipping) {
        this.requiresShipping = requiresShipping;
    }

    public String getSelectedShippingMethod() {
        return selectedShippingMethod;
    }

    public void setSelectedShippingMethod(String selectedShippingMethod) {
        this.selectedShippingMethod = selectedShippingMethod;
    }

    public String getPaymentMethodAdditionalFee() {
        return paymentMethodAdditionalFee;
    }

    public void setPaymentMethodAdditionalFee(String paymentMethodAdditionalFee) {
        this.paymentMethodAdditionalFee = paymentMethodAdditionalFee;
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

    public Object getGiftCards() {
        return giftCards;
    }

    public void setGiftCards(List<String> giftCards) {
        this.giftCards = giftCards;
    }

    public String getOrderTotalDiscount() {
        return orderTotalDiscount;
    }

    public void setOrderTotalDiscount(String orderTotalDiscount) {
        this.orderTotalDiscount = orderTotalDiscount;
    }

    public Boolean getAllowRemovingOrderTotalDiscount() {
        return allowRemovingOrderTotalDiscount;
    }

    public void setAllowRemovingOrderTotalDiscount(Boolean allowRemovingOrderTotalDiscount) {
        this.allowRemovingOrderTotalDiscount = allowRemovingOrderTotalDiscount;
    }

    public Integer getRedeemedRewardPoints() {
        return redeemedRewardPoints;
    }

    public void setRedeemedRewardPoints(Integer redeemedRewardPoints) {
        this.redeemedRewardPoints = redeemedRewardPoints;
    }

    public String getRedeemedRewardPointsAmount() {
        return redeemedRewardPointsAmount;
    }

    public void setRedeemedRewardPointsAmount(String redeemedRewardPointsAmount) {
        this.redeemedRewardPointsAmount = redeemedRewardPointsAmount;
    }

    public Integer getWillEarnRewardPoints() {
        return willEarnRewardPoints;
    }

    public void setWillEarnRewardPoints(Integer willEarnRewardPoints) {
        this.willEarnRewardPoints = willEarnRewardPoints;
    }

    public String getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(String orderTotal) {
        this.orderTotal = orderTotal;
    }
}
