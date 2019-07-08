
package com.mobtecnica.wafiapps.model.orderDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TaxRate {

    @SerializedName("Rate")
    @Expose
    public String rate;
    @SerializedName("Value")
    @Expose
    public String value;

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
