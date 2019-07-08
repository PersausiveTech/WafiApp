
package com.mobtecnica.wafiapps.model.wafiEats.getAllRestaurants;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllRestaurant {

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
    private Object discount;
    @SerializedName("Open")
    @Expose
    private Object open;
    @SerializedName("Timings")
    @Expose
    private Object timings;
    @SerializedName("MinOrder")
    @Expose
    private Object minOrder;
    @SerializedName("DeliveryCharge")
    @Expose
    private Object deliveryCharge;
    @SerializedName("LocDeliveryCharge")
    @Expose
    private Object locDeliveryCharge;
    @SerializedName("LocationID")
    @Expose
    private Object locationID;
    @SerializedName("Location")
    @Expose
    private Object location;
    @SerializedName("PromoCodeID")
    @Expose
    private Integer promoCodeID;
    @SerializedName("IsBranch")
    @Expose
    private Object isBranch;
    @SerializedName("ShopLocationID")
    @Expose
    private Integer shopLocationID;
    @SerializedName("shopTiming")
    @Expose
    private Object shopTiming;

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

    public Object getDiscount() {
        return discount;
    }

    public void setDiscount(Object discount) {
        this.discount = discount;
    }

    public Object getOpen() {
        return open;
    }

    public void setOpen(Object open) {
        this.open = open;
    }

    public Object getTimings() {
        return timings;
    }

    public void setTimings(Object timings) {
        this.timings = timings;
    }

    public Object getMinOrder() {
        return minOrder;
    }

    public void setMinOrder(Object minOrder) {
        this.minOrder = minOrder;
    }

    public Object getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(Object deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public Object getLocDeliveryCharge() {
        return locDeliveryCharge;
    }

    public void setLocDeliveryCharge(Object locDeliveryCharge) {
        this.locDeliveryCharge = locDeliveryCharge;
    }

    public Object getLocationID() {
        return locationID;
    }

    public void setLocationID(Object locationID) {
        this.locationID = locationID;
    }

    public Object getLocation() {
        return location;
    }

    public void setLocation(Object location) {
        this.location = location;
    }

    public Integer getPromoCodeID() {
        return promoCodeID;
    }

    public void setPromoCodeID(Integer promoCodeID) {
        this.promoCodeID = promoCodeID;
    }

    public Object getIsBranch() {
        return isBranch;
    }

    public void setIsBranch(Object isBranch) {
        this.isBranch = isBranch;
    }

    public Integer getShopLocationID() {
        return shopLocationID;
    }

    public void setShopLocationID(Integer shopLocationID) {
        this.shopLocationID = shopLocationID;
    }

    public Object getShopTiming() {
        return shopTiming;
    }

    public void setShopTiming(Object shopTiming) {
        this.shopTiming = shopTiming;
    }

}
