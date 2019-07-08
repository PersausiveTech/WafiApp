
package com.mobtecnica.wafiapps.model.checkout.savePaymentInfo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderSummary {

    @SerializedName("OnePageCheckoutEnabled")
    @Expose
    private Boolean onePageCheckoutEnabled;
    @SerializedName("ShowSku")
    @Expose
    private Boolean showSku;
    @SerializedName("ShowProductImages")
    @Expose
    private Boolean showProductImages;
    @SerializedName("IsEditable")
    @Expose
    private Boolean isEditable;
    @SerializedName("Items")
    @Expose
    private List<Item> items = null;
    @SerializedName("CheckoutAttributeInfo")
    @Expose
    private String checkoutAttributeInfo;
    @SerializedName("CheckoutAttributes")
    @Expose
    private List<CheckoutAttribute> checkoutAttributes = null;
    @SerializedName("Warnings")
    @Expose
    private List<String> warnings = null;
    @SerializedName("MinOrderSubtotalWarning")
    @Expose
    private String minOrderSubtotalWarning;
    @SerializedName("DisplayTaxShippingInfo")
    @Expose
    private Boolean displayTaxShippingInfo;
    @SerializedName("TermsOfServiceOnShoppingCartPage")
    @Expose
    private Boolean termsOfServiceOnShoppingCartPage;
    @SerializedName("TermsOfServiceOnOrderConfirmPage")
    @Expose
    private Boolean termsOfServiceOnOrderConfirmPage;
    @SerializedName("EstimateShipping")
    @Expose
    private EstimateShipping estimateShipping;
    @SerializedName("DiscountBox")
    @Expose
    private DiscountBox discountBox;
    @SerializedName("GiftCardBox")
    @Expose
    private GiftCardBox giftCardBox;
    @SerializedName("OrderReviewData")
    @Expose
    private OrderReviewData orderReviewData;
    @SerializedName("ButtonPaymentMethodActionNames")
    @Expose
    private List<String> buttonPaymentMethodActionNames = null;
    @SerializedName("ButtonPaymentMethodControllerNames")
    @Expose
    private List<String> buttonPaymentMethodControllerNames = null;
    @SerializedName("ButtonPaymentMethodRouteValues")
    @Expose
    private List<String> buttonPaymentMethodRouteValues = null;

    public Boolean getOnePageCheckoutEnabled() {
        return onePageCheckoutEnabled;
    }

    public void setOnePageCheckoutEnabled(Boolean onePageCheckoutEnabled) {
        this.onePageCheckoutEnabled = onePageCheckoutEnabled;
    }

    public Boolean getShowSku() {
        return showSku;
    }

    public void setShowSku(Boolean showSku) {
        this.showSku = showSku;
    }

    public Boolean getShowProductImages() {
        return showProductImages;
    }

    public void setShowProductImages(Boolean showProductImages) {
        this.showProductImages = showProductImages;
    }

    public Boolean getEditable() {
        return isEditable;
    }

    public void setEditable(Boolean editable) {
        isEditable = editable;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getCheckoutAttributeInfo() {
        return checkoutAttributeInfo;
    }

    public void setCheckoutAttributeInfo(String checkoutAttributeInfo) {
        this.checkoutAttributeInfo = checkoutAttributeInfo;
    }

    public List<CheckoutAttribute> getCheckoutAttributes() {
        return checkoutAttributes;
    }

    public void setCheckoutAttributes(List<CheckoutAttribute> checkoutAttributes) {
        this.checkoutAttributes = checkoutAttributes;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }

    public String getMinOrderSubtotalWarning() {
        return minOrderSubtotalWarning;
    }

    public void setMinOrderSubtotalWarning(String minOrderSubtotalWarning) {
        this.minOrderSubtotalWarning = minOrderSubtotalWarning;
    }

    public Boolean getDisplayTaxShippingInfo() {
        return displayTaxShippingInfo;
    }

    public void setDisplayTaxShippingInfo(Boolean displayTaxShippingInfo) {
        this.displayTaxShippingInfo = displayTaxShippingInfo;
    }

    public Boolean getTermsOfServiceOnShoppingCartPage() {
        return termsOfServiceOnShoppingCartPage;
    }

    public void setTermsOfServiceOnShoppingCartPage(Boolean termsOfServiceOnShoppingCartPage) {
        this.termsOfServiceOnShoppingCartPage = termsOfServiceOnShoppingCartPage;
    }

    public Boolean getTermsOfServiceOnOrderConfirmPage() {
        return termsOfServiceOnOrderConfirmPage;
    }

    public void setTermsOfServiceOnOrderConfirmPage(Boolean termsOfServiceOnOrderConfirmPage) {
        this.termsOfServiceOnOrderConfirmPage = termsOfServiceOnOrderConfirmPage;
    }

    public EstimateShipping getEstimateShipping() {
        return estimateShipping;
    }

    public void setEstimateShipping(EstimateShipping estimateShipping) {
        this.estimateShipping = estimateShipping;
    }

    public DiscountBox getDiscountBox() {
        return discountBox;
    }

    public void setDiscountBox(DiscountBox discountBox) {
        this.discountBox = discountBox;
    }

    public GiftCardBox getGiftCardBox() {
        return giftCardBox;
    }

    public void setGiftCardBox(GiftCardBox giftCardBox) {
        this.giftCardBox = giftCardBox;
    }

    public OrderReviewData getOrderReviewData() {
        return orderReviewData;
    }

    public void setOrderReviewData(OrderReviewData orderReviewData) {
        this.orderReviewData = orderReviewData;
    }

    public List<String> getButtonPaymentMethodActionNames() {
        return buttonPaymentMethodActionNames;
    }

    public void setButtonPaymentMethodActionNames(List<String> buttonPaymentMethodActionNames) {
        this.buttonPaymentMethodActionNames = buttonPaymentMethodActionNames;
    }

    public List<String> getButtonPaymentMethodControllerNames() {
        return buttonPaymentMethodControllerNames;
    }

    public void setButtonPaymentMethodControllerNames(List<String> buttonPaymentMethodControllerNames) {
        this.buttonPaymentMethodControllerNames = buttonPaymentMethodControllerNames;
    }

    public List<String> getButtonPaymentMethodRouteValues() {
        return buttonPaymentMethodRouteValues;
    }

    public void setButtonPaymentMethodRouteValues(List<String> buttonPaymentMethodRouteValues) {
        this.buttonPaymentMethodRouteValues = buttonPaymentMethodRouteValues;
    }
}
