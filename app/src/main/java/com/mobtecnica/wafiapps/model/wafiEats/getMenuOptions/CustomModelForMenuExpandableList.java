package com.mobtecnica.wafiapps.model.wafiEats.getMenuOptions;

import java.util.ArrayList;

/**
 * Created by SIby on 08-06-2017.
 */

public class CustomModelForMenuExpandableList {
    String group_name;
    ArrayList<MenuOption> list;
    int MenuSubItemGroupID;
    int maxCount = 0;
    int minCount = 0;
    int count = 0;

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public int getMinCount() {
        return minCount;
    }

    public void setMinCount(int minCount) {
        this.minCount = minCount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMenuSubItemGroupID() {
        return MenuSubItemGroupID;
    }

    public void setMenuSubItemGroupID(int menuSubItemGroupID) {
        MenuSubItemGroupID = menuSubItemGroupID;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public ArrayList<MenuOption> getList() {
        return list;
    }

    public void setList(ArrayList<MenuOption> list) {
        this.list = list;
    }
}
