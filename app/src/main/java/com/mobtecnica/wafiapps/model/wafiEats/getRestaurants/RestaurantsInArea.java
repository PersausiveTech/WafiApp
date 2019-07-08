
package com.mobtecnica.wafiapps.model.wafiEats.getRestaurants;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestaurantsInArea {

    @SerializedName("ShopID")
    @Expose
    private String shopID;
    @SerializedName("ShopName")
    @Expose
    private String shopName;
    @SerializedName("HeaderPic")
    @Expose
    private String headerPic;
    @SerializedName("Discount")
    @Expose
    private String discount;
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
    private String promoCodeID;
    @SerializedName("IsBranch")
    @Expose
    private String isBranch;
    @SerializedName("ShopLocationID")
    @Expose
    private String shopLocationID;
    @SerializedName("shopTiming")
    @Expose
    private ShopTiming shopTiming;

    public String getShopID() {
        return shopID;
    }

    public void setShopID(String shopID) {
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

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
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

    public String getPromoCodeID() {
        return promoCodeID;
    }

    public void setPromoCodeID(String promoCodeID) {
        this.promoCodeID = promoCodeID;
    }

    public String getIsBranch() {
        return isBranch;
    }

    public void setIsBranch(String isBranch) {
        this.isBranch = isBranch;
    }

    public String getShopLocationID() {
        return shopLocationID;
    }

    public void setShopLocationID(String shopLocationID) {
        this.shopLocationID = shopLocationID;
    }

    public ShopTiming getShopTiming() {
        return shopTiming;
    }

    public void setShopTiming(ShopTiming shopTiming) {
        this.shopTiming = shopTiming;
    }

}
