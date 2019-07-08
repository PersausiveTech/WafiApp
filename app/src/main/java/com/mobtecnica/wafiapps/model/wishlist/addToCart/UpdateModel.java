
package com.mobtecnica.wafiapps.model.wishlist.addToCart;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateModel {

    @SerializedName("addtocart")
    @Expose
    private List<Integer> addtocart = null;
    @SerializedName("item_quantity")
    @Expose
    private List<ItemQuantity> itemQuantity = null;

    public List<Integer> getAddtocart() {
        return addtocart;
    }

    public void setAddtocart(List<Integer> addtocart) {
        this.addtocart = addtocart;
    }

    public List<ItemQuantity> getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(List<ItemQuantity> itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

}
