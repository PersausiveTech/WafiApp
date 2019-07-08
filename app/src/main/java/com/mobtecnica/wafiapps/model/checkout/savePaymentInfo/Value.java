
package com.mobtecnica.wafiapps.model.checkout.savePaymentInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Value {

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("ColorSquaresRgb")
    @Expose
    private Object colorSquaresRgb;
    @SerializedName("PriceAdjustment")
    @Expose
    private String priceAdjustment;
    @SerializedName("IsPreSelected")
    @Expose
    private Boolean isPreSelected;
    @SerializedName("Id")
    @Expose
    private Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getColorSquaresRgb() {
        return colorSquaresRgb;
    }

    public void setColorSquaresRgb(Object colorSquaresRgb) {
        this.colorSquaresRgb = colorSquaresRgb;
    }

    public String getPriceAdjustment() {
        return priceAdjustment;
    }

    public void setPriceAdjustment(String priceAdjustment) {
        this.priceAdjustment = priceAdjustment;
    }

    public Boolean getPreSelected() {
        return isPreSelected;
    }

    public void setPreSelected(Boolean preSelected) {
        isPreSelected = preSelected;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
