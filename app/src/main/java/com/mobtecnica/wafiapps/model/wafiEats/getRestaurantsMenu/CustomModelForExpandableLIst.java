package com.mobtecnica.wafiapps.model.wafiEats.getRestaurantsMenu;

import java.util.ArrayList;

import com.mobtecnica.wafiapps.model.LaundryModel.getlaundryItems.ListLaundryMenu;

/**
 * Created by siby on 07-Jun-17.
 */

public class CustomModelForExpandableLIst {
    String group_name;
    ArrayList<MenuList> list;

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public ArrayList<MenuList> getList() {
        return list;
    }

    public void setList(ArrayList<MenuList> list) {
        this.list = list;
    }
}
