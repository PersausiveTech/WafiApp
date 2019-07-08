package com.mobtecnica.wafiapps.model.LaundryModel.laundryAddToCart;

/**
 * Created by SIby on 18-04-2017.
 */

public class LaundryAddToCartModel {
    private String ItemId;

    private String Quantity;

    private String ServiceID;

    private String Price;

    private String LaundryTypeID;

    private String MenuID;

    public String getItemId() {
        return ItemId;
    }

    public void setItemId(String ItemId) {
        this.ItemId = ItemId;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String Quantity) {
        this.Quantity = Quantity;
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

    public String getLaundryTypeID() {
        return LaundryTypeID;
    }

    public void setLaundryTypeID(String LaundryTypeID) {
        this.LaundryTypeID = LaundryTypeID;
    }

    public String getMenuID() {
        return MenuID;
    }

    public void setMenuID(String MenuID) {
        this.MenuID = MenuID;
    }

    @Override
    public String toString() {
        return "ClassPojo [ItemId = " + ItemId + ", Quantity = " + Quantity + ", ServiceID = " + ServiceID + ", Price = " + Price + ", LaundryTypeID = " + LaundryTypeID + ", MenuID = " + MenuID + "]";
    }
}
