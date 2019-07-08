
package com.mobtecnica.wafiapps.model.checkout.savePaymentInfo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EstimateShipping {

    @SerializedName("Enabled")
    @Expose
    private Boolean enabled;
    @SerializedName("CountryId")
    @Expose
    private String countryId;
    @SerializedName("StateProvinceId")
    @Expose
    private String stateProvinceId;
    @SerializedName("ZipPostalCode")
    @Expose
    private String zipPostalCode;
    @SerializedName("AvailableCountries")
    @Expose
    private List<Object> availableCountries = null;
    @SerializedName("AvailableStates")
    @Expose
    private List<Object> availableStates = null;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getStateProvinceId() {
        return stateProvinceId;
    }

    public void setStateProvinceId(String stateProvinceId) {
        this.stateProvinceId = stateProvinceId;
    }

    public String getZipPostalCode() {
        return zipPostalCode;
    }

    public void setZipPostalCode(String zipPostalCode) {
        this.zipPostalCode = zipPostalCode;
    }

    public List<Object> getAvailableCountries() {
        return availableCountries;
    }

    public void setAvailableCountries(List<Object> availableCountries) {
        this.availableCountries = availableCountries;
    }

    public List<Object> getAvailableStates() {
        return availableStates;
    }

    public void setAvailableStates(List<Object> availableStates) {
        this.availableStates = availableStates;
    }
}
