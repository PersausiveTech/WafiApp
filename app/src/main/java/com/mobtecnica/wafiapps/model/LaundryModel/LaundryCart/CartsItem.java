package com.mobtecnica.wafiapps.model.LaundryModel.LaundryCart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartsItem {

    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Price")
    @Expose
    private String price;
    @SerializedName("Quantity")
    @Expose
    private String quantity;
    @SerializedName("Amount")
    @Expose
    private String amount;
    @SerializedName("CartItemId")
    @Expose
    private String cartItemId;
    @SerializedName("ItemId")
    @Expose
    private String itemId;
    @SerializedName("MenuID")
    @Expose
    private String menuID;
    @SerializedName("ServiceID")
    @Expose
    private String serviceID;
    @SerializedName("StoreID")
    @Expose
    private Integer storeID;
    @SerializedName("ServiceType")
    @Expose
    private String serviceType;
    @SerializedName("StoreName")
    @Expose
    private String storeName;
    @SerializedName("ImageName")
    @Expose
    private String imageName;
    @SerializedName("ExpressPercent")
    @Expose
    private String expressPercent;
    @SerializedName("MinimumOrder")
    @Expose
    private String minimumOrder;
    @SerializedName("promoCode")
    @Expose
    private String promoCode;
    @SerializedName("DiscountPercent")
    @Expose
    private String discountPercent;
    @SerializedName("LaundryService")
    @Expose
    private String laundryService;
    @SerializedName("LaundryTypeID")
    @Expose
    private String laundryTypeID;
    @SerializedName("ImageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("ModifiedOn")
    @Expose
    private String modifiedOn;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(String cartItemId) {
        this.cartItemId = cartItemId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getMenuID() {
        return menuID;
    }

    public void setMenuID(String menuID) {
        this.menuID = menuID;
    }

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public Integer getStoreID() {
        return storeID;
    }

    public void setStoreID(Integer storeID) {
        this.storeID = storeID;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getExpressPercent() {
        return expressPercent;
    }

    public void setExpressPercent(String expressPercent) {
        this.expressPercent = expressPercent;
    }

    public String getMinimumOrder() {
        return minimumOrder;
    }

    public void setMinimumOrder(String minimumOrder) {
        this.minimumOrder = minimumOrder;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public String getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(String discountPercent) {
        this.discountPercent = discountPercent;
    }

    public String getLaundryService() {
        return laundryService;
    }

    public void setLaundryService(String laundryService) {
        this.laundryService = laundryService;
    }

    public String getLaundryTypeID() {
        return laundryTypeID;
    }

    public void setLaundryTypeID(String laundryTypeID) {
        this.laundryTypeID = laundryTypeID;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(String modifiedOn) {
        this.modifiedOn = modifiedOn;
    }
}
