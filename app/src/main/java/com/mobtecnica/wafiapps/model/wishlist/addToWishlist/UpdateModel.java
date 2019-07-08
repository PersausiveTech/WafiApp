package com.mobtecnica.wafiapps.model.wishlist.addToWishlist;

import java.util.ArrayList;

/**
 * Created by SIby on 23-02-2017.
 */

public class UpdateModel {
    private ArrayList<Product_attributes> product_attributes;

    public ArrayList<Product_attributes> getProduct_attributes() {
        return product_attributes;
    }

    public void setProduct_attributes(ArrayList<Product_attributes> product_attributes) {
        this.product_attributes = product_attributes;
    }

    @Override
    public String toString() {
        return "ClassPojo [product_attributes = " + product_attributes + "]";
    }
}
