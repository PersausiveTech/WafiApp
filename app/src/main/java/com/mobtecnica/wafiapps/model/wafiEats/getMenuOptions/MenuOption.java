package com.mobtecnica.wafiapps.model.wafiEats.getMenuOptions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MenuOption {

    boolean checked = false;
    @SerializedName("MenuSubItemGroupID")
    @Expose
    private String menuSubItemGroupID;
    @SerializedName("ShopID")
    @Expose
    private String shopID;
    @SerializedName("MenuSubItemGroup")
    @Expose
    private String menuSubItemGroup;
    @SerializedName("MinValue")
    @Expose
    private String minValue;
    @SerializedName("MaxValue")
    @Expose
    private String maxValue;
    @SerializedName("MenuSubItemID")
    @Expose
    private String menuSubItemID;
    @SerializedName("MenuSubItem")
    @Expose
    private String menuSubItem;
    @SerializedName("MultipleSelectable")
    @Expose
    private String multipleSelectable;
    @SerializedName("Paid")
    @Expose
    private String paid;
    @SerializedName("Amount")
    @Expose
    private String amount;
    @SerializedName("MenuID")
    @Expose
    private String menuID;
    @SerializedName("DisplayOrder")
    @Expose
    private String displayOrder;
    @SerializedName("itemcode_J")
    @Expose
    private String itemcodeJ;
    @SerializedName("size_J")
    @Expose
    private String sizeJ;
    @SerializedName("grp_J")
    @Expose
    private String grpJ;
    @SerializedName("discval_J")
    @Expose
    private String discvalJ;
    @SerializedName("cost_J")
    @Expose
    private String costJ;
    @SerializedName("DiscID_J")
    @Expose
    private String discIDJ;
    @SerializedName("rate_J")
    @Expose
    private String rateJ;
    @SerializedName("ItemName_J")
    @Expose
    private String itemNameJ;
    @SerializedName("amount_J")
    @Expose
    private String amountJ;
    @SerializedName("base_J")
    @Expose
    private String baseJ;
    @SerializedName("IsAutoSelectable")
    @Expose
    private String isAutoSelectable;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getMenuSubItemGroupID() {
        return menuSubItemGroupID;
    }

    public void setMenuSubItemGroupID(String menuSubItemGroupID) {
        this.menuSubItemGroupID = menuSubItemGroupID;
    }

    public String getShopID() {
        return shopID;
    }

    public void setShopID(String shopID) {
        this.shopID = shopID;
    }

    public String getMenuSubItemGroup() {
        return menuSubItemGroup;
    }

    public void setMenuSubItemGroup(String menuSubItemGroup) {
        this.menuSubItemGroup = menuSubItemGroup;
    }

    public String getMinValue() {
        return minValue;
    }

    public void setMinValue(String minValue) {
        this.minValue = minValue;
    }

    public String getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(String maxValue) {
        this.maxValue = maxValue;
    }

    public String getMenuSubItemID() {
        return menuSubItemID;
    }

    public void setMenuSubItemID(String menuSubItemID) {
        this.menuSubItemID = menuSubItemID;
    }

    public String getMenuSubItem() {
        return menuSubItem;
    }

    public void setMenuSubItem(String menuSubItem) {
        this.menuSubItem = menuSubItem;
    }

    public String getMultipleSelectable() {
        return multipleSelectable;
    }

    public void setMultipleSelectable(String multipleSelectable) {
        this.multipleSelectable = multipleSelectable;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMenuID() {
        return menuID;
    }

    public void setMenuID(String menuID) {
        this.menuID = menuID;
    }

    public String getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(String displayOrder) {
        this.displayOrder = displayOrder;
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

    public String getDiscvalJ() {
        return discvalJ;
    }

    public void setDiscvalJ(String discvalJ) {
        this.discvalJ = discvalJ;
    }

    public String getCostJ() {
        return costJ;
    }

    public void setCostJ(String costJ) {
        this.costJ = costJ;
    }

    public String getDiscIDJ() {
        return discIDJ;
    }

    public void setDiscIDJ(String discIDJ) {
        this.discIDJ = discIDJ;
    }

    public String getRateJ() {
        return rateJ;
    }

    public void setRateJ(String rateJ) {
        this.rateJ = rateJ;
    }

    public String getItemNameJ() {
        return itemNameJ;
    }

    public void setItemNameJ(String itemNameJ) {
        this.itemNameJ = itemNameJ;
    }

    public String getAmountJ() {
        return amountJ;
    }

    public void setAmountJ(String amountJ) {
        this.amountJ = amountJ;
    }

    public String getBaseJ() {
        return baseJ;
    }

    public void setBaseJ(String baseJ) {
        this.baseJ = baseJ;
    }

    public String getIsAutoSelectable() {
        return isAutoSelectable;
    }

    public void setIsAutoSelectable(String isAutoSelectable) {
        this.isAutoSelectable = isAutoSelectable;
    }

}
