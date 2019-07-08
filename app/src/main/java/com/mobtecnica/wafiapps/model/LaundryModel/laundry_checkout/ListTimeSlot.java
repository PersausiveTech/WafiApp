
package com.mobtecnica.wafiapps.model.LaundryModel.laundry_checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListTimeSlot {

    @SerializedName("TimeSlot")
    @Expose
    private String timeSlot;
    @SerializedName("TimeSlotValue")
    @Expose
    private Integer timeSlotValue;

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Integer getTimeSlotValue() {
        return timeSlotValue;
    }

    public void setTimeSlotValue(Integer timeSlotValue) {
        this.timeSlotValue = timeSlotValue;
    }

}
