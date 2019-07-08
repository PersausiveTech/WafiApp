
package com.mobtecnica.wafiapps.model.LaundryModel.removeFromCart;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateModel {

    @SerializedName("removefromcart")
    @Expose
    private List<String> removefromcart = null;

    public List<String> getRemovefromcart() {
        return removefromcart;
    }

    public void setRemovefromcart(List<String> removefromcart) {
        this.removefromcart = removefromcart;
    }

}
