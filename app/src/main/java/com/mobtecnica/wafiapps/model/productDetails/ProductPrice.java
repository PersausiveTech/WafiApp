package com.mobtecnica.wafiapps.model.productDetails;

/**
 * Created by SIby on 20-02-2017.
 */

public class ProductPrice {
    private String OldPrice;

    private String RentalPrice;

    private String DisplayTaxShippingInfo;

    private String IsRental;

    private String PriceWithDiscount;

    private String CurrencyCode;

    private String CallForPrice;

    private String ProductId;

    private String PriceValue;

    private String BasePricePAngV;

    private String Price;

//    private String CustomProperties;

    private String HidePrices;

    private String CustomerEntersPrice;

    public String getOldPrice() {
        return OldPrice;
    }

    public void setOldPrice(String oldPrice) {
        OldPrice = oldPrice;
    }

    public String getDisplayTaxShippingInfo() {
        return DisplayTaxShippingInfo;
    }

    public void setDisplayTaxShippingInfo(String DisplayTaxShippingInfo) {
        this.DisplayTaxShippingInfo = DisplayTaxShippingInfo;
    }

    public String getIsRental() {
        return IsRental;
    }

    public void setIsRental(String IsRental) {
        this.IsRental = IsRental;
    }


    public String getCurrencyCode() {
        return CurrencyCode;
    }

    public void setCurrencyCode(String CurrencyCode) {
        this.CurrencyCode = CurrencyCode;
    }

    public String getCallForPrice() {
        return CallForPrice;
    }

    public void setCallForPrice(String CallForPrice) {
        this.CallForPrice = CallForPrice;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String ProductId) {
        this.ProductId = ProductId;
    }

    public String getPriceValue() {
        return PriceValue;
    }

    public void setPriceValue(String PriceValue) {
        this.PriceValue = PriceValue;
    }


    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }

//    public String getCustomProperties() {
//        return CustomProperties;
//    }
//
//    public void setCustomProperties(String CustomProperties) {
//        this.CustomProperties = CustomProperties;
//    }

    public String getHidePrices() {
        return HidePrices;
    }

    public void setHidePrices(String HidePrices) {
        this.HidePrices = HidePrices;
    }

    public String getCustomerEntersPrice() {
        return CustomerEntersPrice;
    }

    public void setCustomerEntersPrice(String CustomerEntersPrice) {
        this.CustomerEntersPrice = CustomerEntersPrice;
    }

    public String getRentalPrice() {
        return RentalPrice;
    }

    public void setRentalPrice(String rentalPrice) {
        RentalPrice = rentalPrice;
    }

    public String getPriceWithDiscount() {
        return PriceWithDiscount;
    }

    public void setPriceWithDiscount(String priceWithDiscount) {
        PriceWithDiscount = priceWithDiscount;
    }

    public String getBasePricePAngV() {
        return BasePricePAngV;
    }

    public void setBasePricePAngV(String basePricePAngV) {
        BasePricePAngV = basePricePAngV;
    }

    @Override
    public String toString() {
        return "ClassPojo [OldPrice = " + OldPrice + ", RentalPrice = " + RentalPrice + ", DisplayTaxShippingInfo = " + DisplayTaxShippingInfo + ", IsRental = " + IsRental + ", PriceWithDiscount = " + PriceWithDiscount + ", CurrencyCode = " + CurrencyCode + ", CallForPrice = " + CallForPrice + ", ProductId = " + ProductId + ", PriceValue = " + PriceValue + ", BasePricePAngV = " + BasePricePAngV + ", Price = " + Price + ", CustomProperties =  HidePrices = " + HidePrices + ", CustomerEntersPrice = " + CustomerEntersPrice + "]";
    }
}
