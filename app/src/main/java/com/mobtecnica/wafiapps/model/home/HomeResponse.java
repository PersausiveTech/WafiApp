package com.mobtecnica.wafiapps.model.home;

/**
 * Created by SIby on 09-02-2017.
 */

public class HomeResponse {
    private HomeSliders[] homeSliders;

    private HomeBrands[] homeBrands;

    private HomeCategoriesMenu homeCategoriesMenu;

    private HomeProducts homeProducts;

    public HomeSliders[] getHomeSliders() {
        return homeSliders;
    }

    public void setHomeSliders(HomeSliders[] homeSliders) {
        this.homeSliders = homeSliders;
    }

    public HomeBrands[] getHomeBrands() {
        return homeBrands;
    }

    public void setHomeBrands(HomeBrands[] homeBrands) {
        this.homeBrands = homeBrands;
    }

    public HomeCategoriesMenu getHomeCategoriesMenu() {
        return homeCategoriesMenu;
    }

    public void setHomeCategoriesMenu(HomeCategoriesMenu homeCategoriesMenu) {
        this.homeCategoriesMenu = homeCategoriesMenu;
    }

    public HomeProducts getHomeProducts() {
        return homeProducts;
    }

    public void setHomeProducts(HomeProducts homeProducts) {
        this.homeProducts = homeProducts;
    }
}
