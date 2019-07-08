
package com.mobtecnica.wafiapps.model.wafiEats.getRestaurantsMenu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MenuList {

    @SerializedName("MenuID")
    @Expose
    private String menuID;
    @SerializedName("ShopID")
    @Expose
    private String shopID;
    @SerializedName("MenuTypeID")
    @Expose
    private String menuTypeID;
    @SerializedName("MenuType")
    @Expose
    private String menuType;
    @SerializedName("Menu")
    @Expose
    private String menu;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("DisplayOrder")
    @Expose
    private String displayOrder;
    @SerializedName("Active")
    @Expose
    private String active;
    @SerializedName("Discount")
    @Expose
    private String discount;
    @SerializedName("Rate")
    @Expose
    private String rate;
    @SerializedName("Qty")
    @Expose
    private String qty;
    @SerializedName("MenuSubMenuItemGroupID")
    @Expose
    private String menuSubMenuItemGroupID;
    @SerializedName("MenuSubItemGroup")
    @Expose
    private String menuSubItemGroup;
    @SerializedName("MenuSubItem")
    @Expose
    private String menuSubItem;
    @SerializedName("Submenuitem")
    @Expose
    private String submenuitem;
    @SerializedName("MenuPic")
    @Expose
    private String menuPic;
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

    public String getMenuID() {
        return menuID;
    }

    public void setMenuID(String menuID) {
        this.menuID = menuID;
    }

    public String getShopID() {
        return shopID;
    }

    public void setShopID(String shopID) {
        this.shopID = shopID;
    }

    public String getMenuTypeID() {
        return menuTypeID;
    }

    public void setMenuTypeID(String menuTypeID) {
        this.menuTypeID = menuTypeID;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(String displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getMenuSubMenuItemGroupID() {
        return menuSubMenuItemGroupID;
    }

    public void setMenuSubMenuItemGroupID(String menuSubMenuItemGroupID) {
        this.menuSubMenuItemGroupID = menuSubMenuItemGroupID;
    }

    public String getMenuSubItemGroup() {
        return menuSubItemGroup;
    }

    public void setMenuSubItemGroup(String menuSubItemGroup) {
        this.menuSubItemGroup = menuSubItemGroup;
    }

    public String getMenuSubItem() {
        return menuSubItem;
    }

    public void setMenuSubItem(String menuSubItem) {
        this.menuSubItem = menuSubItem;
    }

    public String getSubmenuitem() {
        return submenuitem;
    }

    public void setSubmenuitem(String submenuitem) {
        this.submenuitem = submenuitem;
    }

    public String getMenuPic() {
        return menuPic;
    }

    public void setMenuPic(String menuPic) {
        this.menuPic = menuPic;
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
