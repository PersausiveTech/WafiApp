package com.mobtecnica.wafiapps.model.cart.getCart.getCartResponse;

import java.util.ArrayList;

/**
 * Created by SIby on 20-03-2017.
 */


public class Items {
    private Picture Picture;

    private String RecurringInfo;

    private String AllowItemEditing;

    private int Quantity;

    private String RentalInfo;

    private String AttributeInfo;

    private String UnitPrice;

    private ArrayList<String> Warnings;

    private String Sku;

    private String ProductId;

    private String ProductSeName;

    private String SubTotal;

    private String[] AllowedQuantities;

    private String Id;

    private String ProductName;

    private String Discount;

//    private String CustomProperties;

    public Picture getPicture() {
        return Picture;
    }

    public void setPicture(Picture Picture) {
        this.Picture = Picture;
    }

    public String getAllowItemEditing() {
        return AllowItemEditing;
    }

    public void setAllowItemEditing(String AllowItemEditing) {
        this.AllowItemEditing = AllowItemEditing;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }


    public String getAttributeInfo() {
        return AttributeInfo;
    }

    public void setAttributeInfo(String AttributeInfo) {
        this.AttributeInfo = AttributeInfo;
    }

    public String getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(String UnitPrice) {
        this.UnitPrice = UnitPrice;
    }

    public ArrayList<String> getWarnings() {
        return Warnings;
    }

    public void setWarnings(ArrayList<String> warnings) {
        Warnings = warnings;
    }

    public String getSku() {
        return Sku;
    }

    public void setSku(String Sku) {
        this.Sku = Sku;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String ProductId) {
        this.ProductId = ProductId;
    }

    public String getProductSeName() {
        return ProductSeName;
    }

    public void setProductSeName(String ProductSeName) {
        this.ProductSeName = ProductSeName;
    }

    public String getSubTotal() {
        return SubTotal;
    }

    public void setSubTotal(String SubTotal) {
        this.SubTotal = SubTotal;
    }

    public String[] getAllowedQuantities() {
        return AllowedQuantities;
    }

    public void setAllowedQuantities(String[] AllowedQuantities) {
        this.AllowedQuantities = AllowedQuantities;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getRecurringInfo() {
        return RecurringInfo;
    }

    public void setRecurringInfo(String recurringInfo) {
        RecurringInfo = recurringInfo;
    }

    public String getRentalInfo() {
        return RentalInfo;
    }

    public void setRentalInfo(String rentalInfo) {
        RentalInfo = rentalInfo;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }




}

