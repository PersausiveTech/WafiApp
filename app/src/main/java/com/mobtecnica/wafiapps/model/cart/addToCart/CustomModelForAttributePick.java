package com.mobtecnica.wafiapps.model.cart.addToCart;

/**
 * Created by siby on 30-Mar-17.
 */

public class CustomModelForAttributePick {
    public int id;
    public Product_attributesCart attributes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product_attributesCart getAttributes() {
        return attributes;
    }

    public void setAttributes(Product_attributesCart attributes) {
        this.attributes = attributes;
    }
}
