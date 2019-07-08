package com.mobtecnica.wafiapps.model.home;

/**
 * Created by SIby on 09-02-2017.
 */

public class HomeProducts {
    private HomeCustomProducts[] homeCustomProducts;

    private HomeCategoryProducts[] homeCategoryProducts;

    public HomeCustomProducts[] getHomeCustomProducts() {
        return homeCustomProducts;
    }

    public void setHomeCustomProducts(HomeCustomProducts[] homeCustomProducts) {
        this.homeCustomProducts = homeCustomProducts;
    }

    public HomeCategoryProducts[] getHomeCategoryProducts() {
        return homeCategoryProducts;
    }

    public void setHomeCategoryProducts(HomeCategoryProducts[] homeCategoryProducts) {
        this.homeCategoryProducts = homeCategoryProducts;
    }
}
