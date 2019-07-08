
package com.mobtecnica.wafiapps.model.wafiEats.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustDetails {

    @SerializedName("FirstName")
    @Expose
    private String firstName;
    @SerializedName("LastName")
    @Expose
    private String lastName;
    @SerializedName("CountryId")
    @Expose
    private String countryId;
    @SerializedName("Address1")
    @Expose
    private String address1;
    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Mobile")
    @Expose
    private String mobile;
    @SerializedName("FlatVilla")
    @Expose
    private String flatVilla;
    @SerializedName("Building")
    @Expose
    private String building;
    @SerializedName("Road")
    @Expose
    private String road;
    @SerializedName("Block")
    @Expose
    private String block;
    @SerializedName("Location")
    @Expose
    private String location;
    @SerializedName("Landmark")
    @Expose
    private String landmark;
    @SerializedName("SelectedAddressId")
    @Expose
    private String selectedAddressId;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFlatVilla() {
        return flatVilla;
    }

    public void setFlatVilla(String flatVilla) {
        this.flatVilla = flatVilla;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getSelectedAddressId() {
        return selectedAddressId;
    }

    public void setSelectedAddressId(String selectedAddressId) {
        this.selectedAddressId = selectedAddressId;
    }

}
