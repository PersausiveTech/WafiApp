
package com.mobtecnica.wafiapps.model.wafiEats.cartRestaurant;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstCartSubItem {

    @SerializedName("MenuSubItemID")
    @Expose
    private String menuSubItemID;
    @SerializedName("MenuSubItem")
    @Expose
    private String menuSubItem;
    @SerializedName("MenuSubItemRate")
    @Expose
    private String menuSubItemRate;
    @SerializedName("MenuSubItemGroup")
    @Expose
    private String menuSubItemGroup;
    @SerializedName("IsAutoSelectable")
    @Expose
    private String isAutoSelectable;
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
    @SerializedName("amount_J")
    @Expose
    private String amountJ;
    @SerializedName("base_J")
    @Expose
    private String baseJ;

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

    public String getMenuSubItemRate() {
        return menuSubItemRate;
    }

    public void setMenuSubItemRate(String menuSubItemRate) {
        this.menuSubItemRate = menuSubItemRate;
    }

    public String getMenuSubItemGroup() {
        return menuSubItemGroup;
    }

    public void setMenuSubItemGroup(String menuSubItemGroup) {
        this.menuSubItemGroup = menuSubItemGroup;
    }

    public String getIsAutoSelectable() {
        return isAutoSelectable;
    }

    public void setIsAutoSelectable(String isAutoSelectable) {
        this.isAutoSelectable = isAutoSelectable;
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

}
