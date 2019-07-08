
package com.mobtecnica.wafiapps.model.wafiEats.cartRestaurant;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartItem {

    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("MenuID")
    @Expose
    private String menuID;
    @SerializedName("Menu")
    @Expose
    private String menu;
    @SerializedName("LocationID")
    @Expose
    private String locationID;
    @SerializedName("ShopID")
    @Expose
    private String shopID;
    @SerializedName("ShopName")
    @Expose
    private String shopName;
    @SerializedName("HeaderPic")
    @Expose
    private String headerPic;
    @SerializedName("ShopLocationID")
    @Expose
    private String shopLocationID;
    @SerializedName("CheckedItems")
    @Expose
    private String checkedItems;
    @SerializedName("Rate")
    @Expose
    private String rate;
    @SerializedName("MinOrder")
    @Expose
    private String minOrder;
    @SerializedName("LocDeliveryCharge")
    @Expose
    private String locDeliveryCharge;
    @SerializedName("Discount")
    @Expose
    private String discount;
    @SerializedName("CartItemId")
    @Expose
    private String cartItemId;
    @SerializedName("lstCartSubItems")
    @Expose
    private List<LstCartSubItem> lstCartSubItems = null;
    @SerializedName("promoCode")
    @Expose
    private List<String> promoCode = null;
    @SerializedName("itemcode_J")
    @Expose
    private String itemcodeJ;
    @SerializedName("size_J")
    @Expose
    private String sizeJ;
    @SerializedName("grp_J")
    @Expose
    private String grpJ;
    @SerializedName("rate_J")
    @Expose
    private String rateJ;
    @SerializedName("base_J")
    @Expose
    private String baseJ;

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getMenuID() {
        return menuID;
    }

    public void setMenuID(String menuID) {
        this.menuID = menuID;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getLocationID() {
        return locationID;
    }

    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }

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

    public String getShopLocationID() {
        return shopLocationID;
    }

    public void setShopLocationID(String shopLocationID) {
        this.shopLocationID = shopLocationID;
    }

    public String getCheckedItems() {
        return checkedItems;
    }

    public void setCheckedItems(String checkedItems) {
        this.checkedItems = checkedItems;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getMinOrder() {
        return minOrder;
    }

    public void setMinOrder(String minOrder) {
        this.minOrder = minOrder;
    }

    public String getLocDeliveryCharge() {
        return locDeliveryCharge;
    }

    public void setLocDeliveryCharge(String locDeliveryCharge) {
        this.locDeliveryCharge = locDeliveryCharge;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(String cartItemId) {
        this.cartItemId = cartItemId;
    }

    public List<LstCartSubItem> getLstCartSubItems() {
        return lstCartSubItems;
    }

    public void setLstCartSubItems(List<LstCartSubItem> lstCartSubItems) {
        this.lstCartSubItems = lstCartSubItems;
    }

    public List<String> getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(List<String> promoCode) {
        this.promoCode = promoCode;
    }

    public String getItemcodeJ() {
        return itemcodeJ;
    }

    public void setItemcodeJ(String itemcodeJ) {
        this.itemcodeJ = itemcodeJ;
    }

    public String getSizeJ() {
        return sizeJ;
    }

    public void setSizeJ(String sizeJ) {
        this.sizeJ = sizeJ;
    }

    public String getGrpJ() {
        return grpJ;
    }

    public void setGrpJ(String grpJ) {
        this.grpJ = grpJ;
    }

    public String getRateJ() {
        return rateJ;
    }

    public void setRateJ(String rateJ) {
        this.rateJ = rateJ;
    }

    public String getBaseJ() {
        return baseJ;
    }

    public void setBaseJ(String baseJ) {
        this.baseJ = baseJ;
    }

}
