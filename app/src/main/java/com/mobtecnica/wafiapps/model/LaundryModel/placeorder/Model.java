
package com.mobtecnica.wafiapps.model.LaundryModel.placeorder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Model {

    @SerializedName("PickupDate")
    @Expose
    private String pickupDate;
    @SerializedName("DeliveryDate")
    @Expose
    private String deliveryDate;
    @SerializedName("PickupTimeID")
    @Expose
    private String pickupTimeID;
    @SerializedName("DeliveryTimeID")
    @Expose
    private String deliveryTimeID;
    @SerializedName("DeliveryArea")
    @Expose
    private String deliveryArea;
    @SerializedName("AmoutPayable")
    @Expose
    private String amoutPayable;
    @SerializedName("DiscountPercent")
    @Expose
    private String discountPercent;
    @SerializedName("SpecialRequest")
    @Expose
    private String specialRequest;
    @SerializedName("LaundryTypeID")
    @Expose
    private String laundryTypeID;
    @SerializedName("CustDetails")
    @Expose
    private CustDetails custDetails;

    public String getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getPickupTimeID() {
        return pickupTimeID;
    }

    public void setPickupTimeID(String pickupTimeID) {
        this.pickupTimeID = pickupTimeID;
    }

    public String getDeliveryTimeID() {
        return deliveryTimeID;
    }

    public void setDeliveryTimeID(String deliveryTimeID) {
        this.deliveryTimeID = deliveryTimeID;
    }

    public String getDeliveryArea() {
        return deliveryArea;
    }

    public void setDeliveryArea(String deliveryArea) {
        this.deliveryArea = deliveryArea;
    }

    public String getAmoutPayable() {
        return amoutPayable;
    }

    public void setAmoutPayable(String amoutPayable) {
        this.amoutPayable = amoutPayable;
    }

    public String getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(String discountPercent) {
        this.discountPercent = discountPercent;
    }

    public String getSpecialRequest() {
        return specialRequest;
    }

    public void setSpecialRequest(String specialRequest) {
        this.specialRequest = specialRequest;
    }

    public String getLaundryTypeID() {
        return laundryTypeID;
    }

    public void setLaundryTypeID(String laundryTypeID) {
        this.laundryTypeID = laundryTypeID;
    }

    public CustDetails getCustDetails() {
        return custDetails;
    }

    public void setCustDetails(CustDetails custDetails) {
        this.custDetails = custDetails;
    }

}
