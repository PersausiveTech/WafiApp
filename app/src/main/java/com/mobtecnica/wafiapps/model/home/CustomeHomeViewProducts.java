package com.mobtecnica.wafiapps.model.home;

import java.util.ArrayList;

/**
 * Created by SIby on 13-02-2017.
 */

public class CustomeHomeViewProducts {
    String GroupId;

    ArrayList<HomeCustomProducts> homeCustomProducts;

    public String getGroupId() {
        return GroupId;
    }

    public void setGroupId(String groupId) {
        GroupId = groupId;
    }

    public ArrayList<HomeCustomProducts> getHomeCustomProducts() {
        return homeCustomProducts;
    }

    public void setHomeCustomProducts(ArrayList<HomeCustomProducts> homeCustomProducts) {
        this.homeCustomProducts = homeCustomProducts;
    }
}
