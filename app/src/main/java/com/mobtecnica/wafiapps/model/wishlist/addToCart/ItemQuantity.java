
package com.mobtecnica.wafiapps.model.wishlist.addToCart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemQuantity {

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Value")
    @Expose
    private Integer value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}
