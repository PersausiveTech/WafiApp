
package com.mobtecnica.wafiapps.model.wafiEats.Offers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllOffer {

    @SerializedName("ShopID")
    @Expose
    private Integer shopID;
    @SerializedName("ShopName")
    @Expose
    private String shopName;
    @SerializedName("HeaderPic")
    @Expose
    private String headerPic;
    @SerializedName("Discount")
    @Expose
    private Integer discount;
    @SerializedName("Open")
    @Expose
    private String open;
    @SerializedName("Timings")
    @Expose
    private String timings;
    @SerializedName("MinOrder")
    @Expose
    private String minOrder;
    @SerializedName("DeliveryCharge")
    @Expose
    private String deliveryCharge;
    @SerializedName("LocDeliveryCharge")
    @Expose
    private String locDeliveryCharge;
    @SerializedName("LocationID")
    @Expose
    private String locationID;
    @SerializedName("Location")
    @Expose
    private String location;
    @SerializedName("PromoCodeID")
    @Expose
    private Integer promoCodeID;
    @SerializedName("IsBranch")
    @Expose
    private String isBranch;
    @SerializedName("ShopLocationID")
    @Expose
    private Integer shopLocationID;
    @SerializedName("shopTiming")
    @Expose
    private String shopTiming;

    public Integer getShopID() {
        return shopID;
    }

    public void setShopID(Integer shopID) {
        this.shopID = shopID;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getHeaderPic() {
        return headerPic;
    }

    public void setHeaderPic(String headerPic) {
        this.headerPic = headerPic;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getTimings() {
        return timings;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public String getMinOrder() {
        return minOrder;
    }

    public void setMinOrder(String minOrder) {
        this.minOrder = minOrder;
    }

    public String getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(String deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public String getLocDeliveryCharge() {
        return locDeliveryCharge;
    }

    public void setLocDeliveryCharge(String locDeliveryCharge) {
        this.locDeliveryCharge = locDeliveryCharge;
    }

    public String getLocationID() {
        return locationID;
    }

    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getPromoCodeID() {
        return promoCodeID;
    }

    public void setPromoCodeID(Integer promoCodeID) {
        this.promoCodeID = promoCodeID;
    }

    public String getIsBranch() {
        return isBranch;
    }

    public void setIsBranch(String isBranch) {
        this.isBranch = isBranch;
    }

    public Integer getShopLocationID() {
        return shopLocationID;
    }

    public void setShopLocationID(Integer shopLocationID) {
        this.shopLocationID = shopLocationID;
    }

    public String getShopTiming() {
        return shopTiming;
    }

    public void setShopTiming(String shopTiming) {
        this.shopTiming = shopTiming;
    }
}
