package com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse;

/**
 * Created by SIby on 16-02-2017.
 */

public class ProductPrice {
    private String DisplayTaxShippingInfo;

    private String OldPrice;

    private String IsRental;

    private String AvailableForPreOrder;

    private String DisableBuyButton;

    private String BasePricePAngV;

    private String ForceRedirectionAfterAddingToCart;

    private String Price;

    private String DisableAddToCompareListButton;

    private String PreOrderAvailabilityStartDateTimeUtc;

    private CustomProperties CustomProperties;

    private String DisableWishlistButton;

    private String PriceValue;

    public String getDisplayTaxShippingInfo() {
        return DisplayTaxShippingInfo;
    }

    public void setDisplayTaxShippingInfo(String DisplayTaxShippingInfo) {
        this.DisplayTaxShippingInfo = DisplayTaxShippingInfo;
    }

    public String getOldPrice() {
        return OldPrice;
    }

    public void setOldPrice(String OldPrice) {
        this.OldPrice = OldPrice;
    }

    public String getIsRental() {
        return IsRental;
    }

    public void setIsRental(String IsRental) {
        this.IsRental = IsRental;
    }

    public String getAvailableForPreOrder() {
        return AvailableForPreOrder;
    }

    public void setAvailableForPreOrder(String AvailableForPreOrder) {
        this.AvailableForPreOrder = AvailableForPreOrder;
    }

    public String getDisableBuyButton() {
        return DisableBuyButton;
    }

    public void setDisableBuyButton(String DisableBuyButton) {
        this.DisableBuyButton = DisableBuyButton;
    }


    public String getForceRedirectionAfterAddingToCart() {
        return ForceRedirectionAfterAddingToCart;
    }

    public void setForceRedirectionAfterAddingToCart(String ForceRedirectionAfterAddingToCart) {
        this.ForceRedirectionAfterAddingToCart = ForceRedirectionAfterAddingToCart;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }

    public String getDisableAddToCompareListButton() {
        return DisableAddToCompareListButton;
    }

    public void setDisableAddToCompareListButton(String DisableAddToCompareListButton) {
        this.DisableAddToCompareListButton = DisableAddToCompareListButton;
    }


    public com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse.CustomProperties getCustomProperties() {
        return CustomProperties;
    }

    public void setCustomProperties(com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse.CustomProperties customProperties) {
        CustomProperties = customProperties;
    }

    public String getDisableWishlistButton() {
        return DisableWishlistButton;
    }

    public void setDisableWishlistButton(String DisableWishlistButton) {
        this.DisableWishlistButton = DisableWishlistButton;
    }

    public String getPriceValue() {
        return PriceValue;
    }

    public void setPriceValue(String PriceValue) {
        this.PriceValue = PriceValue;
    }

    public String getBasePricePAngV() {
        return BasePricePAngV;
    }

    public void setBasePricePAngV(String basePricePAngV) {
        BasePricePAngV = basePricePAngV;
    }

    public String getPreOrderAvailabilityStartDateTimeUtc() {
        return PreOrderAvailabilityStartDateTimeUtc;
    }

    public void setPreOrderAvailabilityStartDateTimeUtc(String preOrderAvailabilityStartDateTimeUtc) {
        PreOrderAvailabilityStartDateTimeUtc = preOrderAvailabilityStartDateTimeUtc;
    }

    @Override
    public String toString() {
        return "ClassPojo [DisplayTaxShippingInfo = " + DisplayTaxShippingInfo + ", OldPrice = " + OldPrice + ", IsRental = " + IsRental + ", AvailableForPreOrder = " + AvailableForPreOrder + ", DisableBuyButton = " + DisableBuyButton + ", BasePricePAngV = " + BasePricePAngV + ", ForceRedirectionAfterAddingToCart = " + ForceRedirectionAfterAddingToCart + ", Price = " + Price + ", DisableAddToCompareListButton = " + DisableAddToCompareListButton + ", PreOrderAvailabilityStartDateTimeUtc = " + PreOrderAvailabilityStartDateTimeUtc + ", CustomProperties = " + CustomProperties + ", DisableWishlistButton = " + DisableWishlistButton + ", PriceValue = " + PriceValue + "]";
    }
}
