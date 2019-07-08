package com.mobtecnica.wafiapps.model.cart.getCart.getCartResponse;

/**
 * Created by SIby on 20-03-2017.
 */


public class EstimateShipping {
    private String Enabled;

    private String[] AvailableStates;

    private String CountryId;

//    private String CustomProperties;

    private String StateProvinceId;

    private String[] AvailableCountries;

    private String ZipPostalCode;

    public String getEnabled() {
        return Enabled;
    }

    public void setEnabled(String Enabled) {
        this.Enabled = Enabled;
    }

    public String[] getAvailableStates() {
        return AvailableStates;
    }

    public void setAvailableStates(String[] AvailableStates) {
        this.AvailableStates = AvailableStates;
    }

    public String[] getAvailableCountries() {
        return AvailableCountries;
    }

    public void setAvailableCountries(String[] AvailableCountries) {
        this.AvailableCountries = AvailableCountries;
    }

    public String getCountryId() {
        return CountryId;
    }

    public void setCountryId(String countryId) {
        CountryId = countryId;
    }

    public String getStateProvinceId() {
        return StateProvinceId;
    }

    public void setStateProvinceId(String stateProvinceId) {
        StateProvinceId = stateProvinceId;
    }

    public String getZipPostalCode() {
        return ZipPostalCode;
    }

    public void setZipPostalCode(String zipPostalCode) {
        ZipPostalCode = zipPostalCode;
    }

    @Override
    public String toString() {
        return "ClassPojo [Enabled = " + Enabled + ", AvailableStates = " + AvailableStates + ", CountryId = " + CountryId + ", CustomPropertie StateProvinceId = " + StateProvinceId + ", AvailableCountries = " + AvailableCountries + ", ZipPostalCode = " + ZipPostalCode + "]";
    }
}

