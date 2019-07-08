
package com.mobtecnica.wafiapps.model.wafiEats.getRestaurants;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShopTiming {

    @SerializedName("OpenTime")
    @Expose
    private String openTime;
    @SerializedName("CloseTime")
    @Expose
    private String closeTime;
    @SerializedName("Day_OfWeek")
    @Expose
    private String dayOfWeek;
    @SerializedName("CurrentStatus")
    @Expose
    private String currentStatus;

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

}
