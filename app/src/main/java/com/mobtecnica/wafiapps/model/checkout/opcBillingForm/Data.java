
package com.mobtecnica.wafiapps.model.checkout.opcBillingForm;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("onePageBillingFormData")
    @Expose
    private OnePageBillingFormData onePageBillingFormData;
    @SerializedName("Message")
    @Expose
    private String message;

    public OnePageBillingFormData getOnePageBillingFormData() {
        return onePageBillingFormData;
    }

    public void setOnePageBillingFormData(OnePageBillingFormData onePageBillingFormData) {
        this.onePageBillingFormData = onePageBillingFormData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
