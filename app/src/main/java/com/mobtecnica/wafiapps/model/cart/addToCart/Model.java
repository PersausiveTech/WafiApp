package com.mobtecnica.wafiapps.model.cart.addToCart;

import java.util.List;

/**
 * Created by SIby on 22-02-2017.
 */

public class Model {
    private List<Product_attributesCart> product_attributes;

    public List<Product_attributesCart> getProduct_attributes() {
        return product_attributes;
    }

    public void setProduct_attributes(List<Product_attributesCart> product_attributes) {
        this.product_attributes = product_attributes;
    }

    @Override
    public String toString() {
        return "ClassPojo [product_attributes = " + product_attributes + "]";
    }
}
