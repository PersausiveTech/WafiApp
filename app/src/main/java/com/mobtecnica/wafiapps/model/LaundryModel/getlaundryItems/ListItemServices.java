package com.mobtecnica.wafiapps.model.LaundryModel.getlaundryItems;

/**
 * Created by SIby on 04-04-2017.
 */

public class ListItemServices {

    private String ItemID;

    private String ServiceTypeID;

    private String ServiceID;

    private String Price;

    private String itemService;

    private String ServiceType;

    private String ServicesList;

    private String MenuID;

    public String getItemID() {
        return ItemID;
    }

    public void setItemID(String ItemID) {
        this.ItemID = ItemID;
    }

    public String getServiceTypeID() {
        return ServiceTypeID;
    }

    public void setServiceTypeID(String ServiceTypeID) {
        this.ServiceTypeID = ServiceTypeID;
    }

    public String getServiceID() {
        return ServiceID;
    }

    public void setServiceID(String ServiceID) {
        this.ServiceID = ServiceID;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }

    public String getItemService() {
        return itemService;
    }

    public void setItemService(String itemService) {
        this.itemService = itemService;
    }

    public String getServiceType() {
        return ServiceType;
    }

    public void setServiceType(String ServiceType) {
        this.ServiceType = ServiceType;
    }

    public String getServicesList() {
        return ServicesList;
    }

    public void setServicesList(String ServicesList) {
        this.ServicesList = ServicesList;
    }

    public String getMenuID() {
        return MenuID;
    }

    public void setMenuID(String MenuID) {
        this.MenuID = MenuID;
    }

    @Override
    public String toString() {
        return "ClassPojo [ItemID = " + ItemID + ", ServiceTypeID = " + ServiceTypeID + ", ServiceID = " + ServiceID + ", Price = " + Price + ", itemService = " + itemService + ", ServiceType = " + ServiceType + ", ServicesList = " + ServicesList + ", MenuID = " + MenuID + "]";
    }
}
