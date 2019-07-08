
package com.mobtecnica.wafiapps.model.checkout.onePageCheckout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataOnePage {

    @SerializedName("onePageCheckoutData")
    @Expose
    private OnePageCheckoutData onePageCheckoutData;
    @SerializedName("Message")
    @Expose
    private String message;

    public OnePageCheckoutData getOnePageCheckoutData() {
        return onePageCheckoutData;
    }

    public void setOnePageCheckoutData(OnePageCheckoutData onePageCheckoutData) {
        this.onePageCheckoutData = onePageCheckoutData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
