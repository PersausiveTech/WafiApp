
package com.mobtecnica.wafiapps.model.wafiEats.getRestaurantsMenu;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MenuData {

    @SerializedName("ShopDetail")
    @Expose
    private ShopDetail shopDetail;
    @SerializedName("CuisineList")
    @Expose
    private List<CuisineList> cuisineList = null;
    @SerializedName("MenuList")
    @Expose
    private List<MenuList> menuList = null;

    public ShopDetail getShopDetail() {
        return shopDetail;
    }

    public void setShopDetail(ShopDetail shopDetail) {
        this.shopDetail = shopDetail;
    }

    public List<CuisineList> getCuisineList() {
        return cuisineList;
    }

    public void setCuisineList(List<CuisineList> cuisineList) {
        this.cuisineList = cuisineList;
    }

    public List<MenuList> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<MenuList> menuList) {
        this.menuList = menuList;
    }

}
