package com.mobtecnica.wafiapps.model.LaundryModel.getlaundryItems;

import java.util.ArrayList;

/**
 * Created by SIby on 04-04-2017.
 */

public class CustomClassForExpandableView {
    String group_name;
    ArrayList<ListLaundryMenu> list;

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public ArrayList<ListLaundryMenu> getList() {
        return list;
    }

    public void setList(ArrayList<ListLaundryMenu> list) {
        this.list = list;
    }
}
