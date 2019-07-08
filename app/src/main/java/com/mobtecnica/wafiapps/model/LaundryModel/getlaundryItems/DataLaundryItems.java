package com.mobtecnica.wafiapps.model.LaundryModel.getlaundryItems;

import java.util.ArrayList;

/**
 * Created by SIby on 04-04-2017.
 */

public class DataLaundryItems {

    private ArrayList<ListLaundryMenu> listLaundryMenu;

    private String LaundryTypeID;

    private String MinimumOrder;

    public ArrayList<ListLaundryMenu> getListLaundryMenu() {
        return listLaundryMenu;
    }

    public void setListLaundryMenu(ArrayList<ListLaundryMenu> listLaundryMenu) {
        this.listLaundryMenu = listLaundryMenu;
    }

    public String getLaundryTypeID() {
        return LaundryTypeID;
    }

    public void setLaundryTypeID(String LaundryTypeID) {
        this.LaundryTypeID = LaundryTypeID;
    }

    public String getMinimumOrder() {
        return MinimumOrder;
    }

    public void setMinimumOrder(String MinimumOrder) {
        this.MinimumOrder = MinimumOrder;
    }

    @Override
    public String toString() {
        return "ClassPojo [listLaundryMenu = " + listLaundryMenu + ", LaundryTypeID = " + LaundryTypeID + ", MinimumOrder = " + MinimumOrder + "]";
    }
}
