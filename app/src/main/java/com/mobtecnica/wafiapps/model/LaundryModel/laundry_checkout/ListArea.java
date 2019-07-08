
package com.mobtecnica.wafiapps.model.LaundryModel.laundry_checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListArea {

    @SerializedName("AreaID")
    @Expose
    private Integer areaID;
    @SerializedName("AreaName")
    @Expose
    private String areaName;
    @SerializedName("DeliveryCharge")
    @Expose
    private String deliveryCharge;

    public Integer getAreaID() {
        return areaID;
    }

    public void setAreaID(Integer areaID) {
        this.areaID = areaID;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(String deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

}
