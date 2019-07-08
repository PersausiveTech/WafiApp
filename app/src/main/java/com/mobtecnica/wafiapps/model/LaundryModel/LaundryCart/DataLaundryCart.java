package com.mobtecnica.wafiapps.model.LaundryModel.LaundryCart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SIby on 12-04-2017.
 */

public class DataLaundryCart {
    @SerializedName("cartsItems")
    @Expose
    private ArrayList<CartsItem> cartsItems = null;
    @SerializedName("TotalAmount")
    @Expose
    private Double totalAmount;

    public ArrayList<CartsItem> getCartsItems() {
        return cartsItems;
    }

    public void setCartsItems(ArrayList<CartsItem> cartsItems) {
        this.cartsItems = cartsItems;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
