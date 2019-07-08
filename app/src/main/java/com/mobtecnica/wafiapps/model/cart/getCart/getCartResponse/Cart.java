package com.mobtecnica.wafiapps.model.cart.getCart.getCartResponse;

import java.util.ArrayList;

/**
 * Created by SIby on 20-03-2017.
 */

public class Cart {
    private ArrayList<Items> Items;

    private String DisplayTaxShippingInfo;

    private CheckoutAttributes[] CheckoutAttributes;

    private String TermsOfServiceOnShoppingCartPage;

    private String[] ButtonPaymentMethodActionNames;

    private String MinOrderSubtotalWarning;

    private OrderReviewData OrderReviewData;

    private DiscountBox DiscountBox;

    private EstimateShipping EstimateShipping;

    private String[] Warnings;

    private String[] ButtonPaymentMethodControllerNames;

    private String OnePageCheckoutEnabled;

    private String CheckoutAttributeInfo;

    private String[] ButtonPaymentMethodRouteValues;

    private String ShowProductImages;

    private String ShowSku;

    private String IsEditable;

//    private String CustomProperties;

    private GiftCardBox GiftCardBox;

    private String TermsOfServiceOnOrderConfirmPage;

    public ArrayList<com.mobtecnica.wafiapps.model.cart.getCart.getCartResponse.Items> getItems() {
        return Items;
    }

    public void setItems(ArrayList<com.mobtecnica.wafiapps.model.cart.getCart.getCartResponse.Items> items) {
        Items = items;
    }

    public String getDisplayTaxShippingInfo() {
        return DisplayTaxShippingInfo;
    }

    public void setDisplayTaxShippingInfo(String DisplayTaxShippingInfo) {
        this.DisplayTaxShippingInfo = DisplayTaxShippingInfo;
    }

    public CheckoutAttributes[] getCheckoutAttributes() {
        return CheckoutAttributes;
    }

    public void setCheckoutAttributes(CheckoutAttributes[] CheckoutAttributes) {
        this.CheckoutAttributes = CheckoutAttributes;
    }

    public String getTermsOfServiceOnShoppingCartPage() {
        return TermsOfServiceOnShoppingCartPage;
    }

    public void setTermsOfServiceOnShoppingCartPage(String TermsOfServiceOnShoppingCartPage) {
        this.TermsOfServiceOnShoppingCartPage = TermsOfServiceOnShoppingCartPage;
    }

    public String[] getButtonPaymentMethodActionNames() {
        return ButtonPaymentMethodActionNames;
    }

    public void setButtonPaymentMethodActionNames(String[] ButtonPaymentMethodActionNames) {
        this.ButtonPaymentMethodActionNames = ButtonPaymentMethodActionNames;
    }

    public String getMinOrderSubtotalWarning() {
        return MinOrderSubtotalWarning;
    }

    public void setMinOrderSubtotalWarning(String minOrderSubtotalWarning) {
        MinOrderSubtotalWarning = minOrderSubtotalWarning;
    }

    public OrderReviewData getOrderReviewData() {
        return OrderReviewData;
    }

    public void setOrderReviewData(OrderReviewData OrderReviewData) {
        this.OrderReviewData = OrderReviewData;
    }

    public DiscountBox getDiscountBox() {
        return DiscountBox;
    }

    public void setDiscountBox(DiscountBox DiscountBox) {
        this.DiscountBox = DiscountBox;
    }

    public EstimateShipping getEstimateShipping() {
        return EstimateShipping;
    }

    public void setEstimateShipping(EstimateShipping EstimateShipping) {
        this.EstimateShipping = EstimateShipping;
    }

    public String[] getWarnings() {
        return Warnings;
    }

    public void setWarnings(String[] Warnings) {
        this.Warnings = Warnings;
    }

    public String[] getButtonPaymentMethodControllerNames() {
        return ButtonPaymentMethodControllerNames;
    }

    public void setButtonPaymentMethodControllerNames(String[] ButtonPaymentMethodControllerNames) {
        this.ButtonPaymentMethodControllerNames = ButtonPaymentMethodControllerNames;
    }

    public String getOnePageCheckoutEnabled() {
        return OnePageCheckoutEnabled;
    }

    public void setOnePageCheckoutEnabled(String OnePageCheckoutEnabled) {
        this.OnePageCheckoutEnabled = OnePageCheckoutEnabled;
    }

    public String getCheckoutAttributeInfo() {
        return CheckoutAttributeInfo;
    }

    public void setCheckoutAttributeInfo(String CheckoutAttributeInfo) {
        this.CheckoutAttributeInfo = CheckoutAttributeInfo;
    }

    public String[] getButtonPaymentMethodRouteValues() {
        return ButtonPaymentMethodRouteValues;
    }

    public void setButtonPaymentMethodRouteValues(String[] ButtonPaymentMethodRouteValues) {
        this.ButtonPaymentMethodRouteValues = ButtonPaymentMethodRouteValues;
    }

    public String getShowProductImages() {
        return ShowProductImages;
    }

    public void setShowProductImages(String ShowProductImages) {
        this.ShowProductImages = ShowProductImages;
    }

    public String getShowSku() {
        return ShowSku;
    }

    public void setShowSku(String ShowSku) {
        this.ShowSku = ShowSku;
    }

    public String getIsEditable() {
        return IsEditable;
    }

    public void setIsEditable(String IsEditable) {
        this.IsEditable = IsEditable;
    }


    public GiftCardBox getGiftCardBox() {
        return GiftCardBox;
    }

    public void setGiftCardBox(GiftCardBox GiftCardBox) {
        this.GiftCardBox = GiftCardBox;
    }

    public String getTermsOfServiceOnOrderConfirmPage() {
        return TermsOfServiceOnOrderConfirmPage;
    }

    public void setTermsOfServiceOnOrderConfirmPage(String TermsOfServiceOnOrderConfirmPage) {
        this.TermsOfServiceOnOrderConfirmPage = TermsOfServiceOnOrderConfirmPage;
    }

    @Override
    public String toString() {
        return "ClassPojo [Items = " + Items + ", DisplayTaxShippingInfo = " + DisplayTaxShippingInfo + ", CheckoutAttributes = " + CheckoutAttributes + ", TermsOfServiceOnShoppingCartPage = " + TermsOfServiceOnShoppingCartPage + ", ButtonPaymentMethodActionNames = " + ButtonPaymentMethodActionNames + ", MinOrderSubtotalWarning = " + MinOrderSubtotalWarning + ", OrderReviewData = " + OrderReviewData + ", DiscountBox = " + DiscountBox + ", EstimateShipping = " + EstimateShipping + ", Warnings = " + Warnings + ", ButtonPaymentMethodControllerNames = " + ButtonPaymentMethodControllerNames + ", OnePageCheckoutEnabled = " + OnePageCheckoutEnabled + ", CheckoutAttributeInfo = " + CheckoutAttributeInfo + ", ButtonPaymentMethodRouteValues = " + ButtonPaymentMethodRouteValues + ", ShowProductImages = " + ShowProductImages + ", ShowSku = " + ShowSku + ", IsEditable = " + IsEditable + ", CustomProperties = GiftCardBox = " + GiftCardBox + ", TermsOfServiceOnOrderConfirmPage = " + TermsOfServiceOnOrderConfirmPage + "]";
    }
}