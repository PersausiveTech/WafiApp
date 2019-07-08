
package com.mobtecnica.wafiapps.model.checkout.savePaymentInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DiscountBox {

    @SerializedName("Display")
    @Expose
    private Boolean display;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("CurrentCode")
    @Expose
    private String currentCode;
    @SerializedName("IsApplied")
    @Expose
    private Boolean isApplied;

    public Boolean getDisplay() {
        return display;
    }

    public void setDisplay(Boolean display) {
        this.display = display;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCurrentCode() {
        return currentCode;
    }

    public void setCurrentCode(String currentCode) {
        this.currentCode = currentCode;
    }

    public Boolean getApplied() {
        return isApplied;
    }

    public void setApplied(Boolean applied) {
        isApplied = applied;
    }
}
