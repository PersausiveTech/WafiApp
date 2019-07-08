
package com.mobtecnica.wafiapps.model.LaundryModel.laundry_checkout;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("LaundryTypeID")
    @Expose
    private Integer laundryTypeID;
    @SerializedName("SelectAddress")
    @Expose
    private List<SelectAddress> selectAddress = null;
    @SerializedName("SelectedAddressId")
    @Expose
    private String selectedAddressId;
    @SerializedName("listArea")
    @Expose
    private List<ListArea> listArea = null;
    @SerializedName("listTimeSlots")
    @Expose
    private List<ListTimeSlot> listTimeSlots = null;
    @SerializedName("PickupTimeID")
    @Expose
    private String pickupTimeID;
    @SerializedName("DeliveryTimeID")
    @Expose
    private String deliveryTimeID;
    @SerializedName("PickupDate")
    @Expose
    private String pickupDate;
    @SerializedName("DeliveryDate")
    @Expose
    private String deliveryDate;
    @SerializedName("DeliveryArea")
    @Expose
    private String deliveryArea;
    @SerializedName("SpecialRequest")
    @Expose
    private String specialRequest;
    @SerializedName("AmoutPayable")
    @Expose
    private String amoutPayable;
    @SerializedName("DiscountPercent")
    @Expose
    private String discountPercent;
    @SerializedName("CustDetails")
    @Expose
    private String custDetails;

    public Integer getLaundryTypeID() {
        return laundryTypeID;
    }

    public void setLaundryTypeID(Integer laundryTypeID) {
        this.laundryTypeID = laundryTypeID;
    }

    public List<SelectAddress> getSelectAddress() {
        return selectAddress;
    }

    public void setSelectAddress(List<SelectAddress> selectAddress) {
        this.selectAddress = selectAddress;
    }

    public String getSelectedAddressId() {
        return selectedAddressId;
    }

    public void setSelectedAddressId(String selectedAddressId) {
        this.selectedAddressId = selectedAddressId;
    }

    public List<ListArea> getListArea() {
        return listArea;
    }

    public void setListArea(List<ListArea> listArea) {
        this.listArea = listArea;
    }

    public List<ListTimeSlot> getListTimeSlots() {
        return listTimeSlots;
    }

    public void setListTimeSlots(List<ListTimeSlot> listTimeSlots) {
        this.listTimeSlots = listTimeSlots;
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

    public String getDeliveryArea() {
        return deliveryArea;
    }

    public void setDeliveryArea(String deliveryArea) {
        this.deliveryArea = deliveryArea;
    }

    public String getSpecialRequest() {
        return specialRequest;
    }

    public void setSpecialRequest(String specialRequest) {
        this.specialRequest = specialRequest;
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

    public String getCustDetails() {
        return custDetails;
    }

    public void setCustDetails(String custDetails) {
        this.custDetails = custDetails;
    }

}
