
package com.mobtecnica.wafiapps.model.checkout.opcBillingForm;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewAddress {

    @SerializedName("FirstName")
    @Expose
    private String firstName;
    @SerializedName("LastName")
    @Expose
    private String lastName;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("CompanyEnabled")
    @Expose
    private Boolean companyEnabled;
    @SerializedName("CompanyRequired")
    @Expose
    private Boolean companyRequired;
    @SerializedName("Company")
    @Expose
    private Object company;
    @SerializedName("CountryEnabled")
    @Expose
    private Boolean countryEnabled;
    @SerializedName("CountryId")
    @Expose
    private Object countryId;
    @SerializedName("CountryName")
    @Expose
    private Object countryName;
    @SerializedName("StateProvinceEnabled")
    @Expose
    private Boolean stateProvinceEnabled;
    @SerializedName("StateProvinceId")
    @Expose
    private Object stateProvinceId;
    @SerializedName("StateProvinceName")
    @Expose
    private Object stateProvinceName;
    @SerializedName("CityEnabled")
    @Expose
    private Boolean cityEnabled;
    @SerializedName("CityRequired")
    @Expose
    private Boolean cityRequired;
    @SerializedName("City")
    @Expose
    private Object city;
    @SerializedName("StreetAddressEnabled")
    @Expose
    private Boolean streetAddressEnabled;
    @SerializedName("StreetAddressRequired")
    @Expose
    private Boolean streetAddressRequired;
    @SerializedName("Address1")
    @Expose
    private Object address1;
    @SerializedName("StreetAddress2Enabled")
    @Expose
    private Boolean streetAddress2Enabled;
    @SerializedName("StreetAddress2Required")
    @Expose
    private Boolean streetAddress2Required;
    @SerializedName("Address2")
    @Expose
    private Object address2;
    @SerializedName("ZipPostalCodeEnabled")
    @Expose
    private Boolean zipPostalCodeEnabled;
    @SerializedName("ZipPostalCodeRequired")
    @Expose
    private Boolean zipPostalCodeRequired;
    @SerializedName("ZipPostalCode")
    @Expose
    private Object zipPostalCode;
    @SerializedName("PhoneEnabled")
    @Expose
    private Boolean phoneEnabled;
    @SerializedName("PhoneRequired")
    @Expose
    private Boolean phoneRequired;
    @SerializedName("PhoneNumber")
    @Expose
    private Object phoneNumber;
    @SerializedName("FaxEnabled")
    @Expose
    private Boolean faxEnabled;
    @SerializedName("FaxRequired")
    @Expose
    private Boolean faxRequired;
    @SerializedName("FaxNumber")
    @Expose
    private Object faxNumber;
    @SerializedName("AvailableCountries")
    @Expose
    private List<AvailableCountry> availableCountries = null;
    @SerializedName("AvailableStates")
    @Expose
    private List<Object> availableStates = null;
    @SerializedName("FormattedCustomAddressAttributes")
    @Expose
    private Object formattedCustomAddressAttributes;
    @SerializedName("CustomAddressAttributes")
    @Expose
    private List<CustomAddressAttribute> customAddressAttributes = null;
    @SerializedName("address_attribute_1")
    @Expose
    private Object addressAttribute1;
    @SerializedName("address_attribute_2")
    @Expose
    private Object addressAttribute2;
    @SerializedName("address_attribute_3")
    @Expose
    private Object addressAttribute3;
    @SerializedName("address_attribute_4")
    @Expose
    private Object addressAttribute4;
    @SerializedName("address_attribute_5")
    @Expose
    private Object addressAttribute5;
    @SerializedName("address_attribute_6")
    @Expose
    private Object addressAttribute6;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("CustomProperties")
    @Expose
    private CustomProperties__ customProperties;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getCompanyEnabled() {
        return companyEnabled;
    }

    public void setCompanyEnabled(Boolean companyEnabled) {
        this.companyEnabled = companyEnabled;
    }

    public Boolean getCompanyRequired() {
        return companyRequired;
    }

    public void setCompanyRequired(Boolean companyRequired) {
        this.companyRequired = companyRequired;
    }

    public Object getCompany() {
        return company;
    }

    public void setCompany(Object company) {
        this.company = company;
    }

    public Boolean getCountryEnabled() {
        return countryEnabled;
    }

    public void setCountryEnabled(Boolean countryEnabled) {
        this.countryEnabled = countryEnabled;
    }

    public Object getCountryId() {
        return countryId;
    }

    public void setCountryId(Object countryId) {
        this.countryId = countryId;
    }

    public Object getCountryName() {
        return countryName;
    }

    public void setCountryName(Object countryName) {
        this.countryName = countryName;
    }

    public Boolean getStateProvinceEnabled() {
        return stateProvinceEnabled;
    }

    public void setStateProvinceEnabled(Boolean stateProvinceEnabled) {
        this.stateProvinceEnabled = stateProvinceEnabled;
    }

    public Object getStateProvinceId() {
        return stateProvinceId;
    }

    public void setStateProvinceId(Object stateProvinceId) {
        this.stateProvinceId = stateProvinceId;
    }

    public Object getStateProvinceName() {
        return stateProvinceName;
    }

    public void setStateProvinceName(Object stateProvinceName) {
        this.stateProvinceName = stateProvinceName;
    }

    public Boolean getCityEnabled() {
        return cityEnabled;
    }

    public void setCityEnabled(Boolean cityEnabled) {
        this.cityEnabled = cityEnabled;
    }

    public Boolean getCityRequired() {
        return cityRequired;
    }

    public void setCityRequired(Boolean cityRequired) {
        this.cityRequired = cityRequired;
    }

    public Object getCity() {
        return city;
    }

    public void setCity(Object city) {
        this.city = city;
    }

    public Boolean getStreetAddressEnabled() {
        return streetAddressEnabled;
    }

    public void setStreetAddressEnabled(Boolean streetAddressEnabled) {
        this.streetAddressEnabled = streetAddressEnabled;
    }

    public Boolean getStreetAddressRequired() {
        return streetAddressRequired;
    }

    public void setStreetAddressRequired(Boolean streetAddressRequired) {
        this.streetAddressRequired = streetAddressRequired;
    }

    public Object getAddress1() {
        return address1;
    }

    public void setAddress1(Object address1) {
        this.address1 = address1;
    }

    public Boolean getStreetAddress2Enabled() {
        return streetAddress2Enabled;
    }

    public void setStreetAddress2Enabled(Boolean streetAddress2Enabled) {
        this.streetAddress2Enabled = streetAddress2Enabled;
    }

    public Boolean getStreetAddress2Required() {
        return streetAddress2Required;
    }

    public void setStreetAddress2Required(Boolean streetAddress2Required) {
        this.streetAddress2Required = streetAddress2Required;
    }

    public Object getAddress2() {
        return address2;
    }

    public void setAddress2(Object address2) {
        this.address2 = address2;
    }

    public Boolean getZipPostalCodeEnabled() {
        return zipPostalCodeEnabled;
    }

    public void setZipPostalCodeEnabled(Boolean zipPostalCodeEnabled) {
        this.zipPostalCodeEnabled = zipPostalCodeEnabled;
    }

    public Boolean getZipPostalCodeRequired() {
        return zipPostalCodeRequired;
    }

    public void setZipPostalCodeRequired(Boolean zipPostalCodeRequired) {
        this.zipPostalCodeRequired = zipPostalCodeRequired;
    }

    public Object getZipPostalCode() {
        return zipPostalCode;
    }

    public void setZipPostalCode(Object zipPostalCode) {
        this.zipPostalCode = zipPostalCode;
    }

    public Boolean getPhoneEnabled() {
        return phoneEnabled;
    }

    public void setPhoneEnabled(Boolean phoneEnabled) {
        this.phoneEnabled = phoneEnabled;
    }

    public Boolean getPhoneRequired() {
        return phoneRequired;
    }

    public void setPhoneRequired(Boolean phoneRequired) {
        this.phoneRequired = phoneRequired;
    }

    public Object getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Object phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getFaxEnabled() {
        return faxEnabled;
    }

    public void setFaxEnabled(Boolean faxEnabled) {
        this.faxEnabled = faxEnabled;
    }

    public Boolean getFaxRequired() {
        return faxRequired;
    }

    public void setFaxRequired(Boolean faxRequired) {
        this.faxRequired = faxRequired;
    }

    public Object getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(Object faxNumber) {
        this.faxNumber = faxNumber;
    }

    public List<AvailableCountry> getAvailableCountries() {
        return availableCountries;
    }

    public void setAvailableCountries(List<AvailableCountry> availableCountries) {
        this.availableCountries = availableCountries;
    }

    public List<Object> getAvailableStates() {
        return availableStates;
    }

    public void setAvailableStates(List<Object> availableStates) {
        this.availableStates = availableStates;
    }

    public Object getFormattedCustomAddressAttributes() {
        return formattedCustomAddressAttributes;
    }

    public void setFormattedCustomAddressAttributes(Object formattedCustomAddressAttributes) {
        this.formattedCustomAddressAttributes = formattedCustomAddressAttributes;
    }

    public List<CustomAddressAttribute> getCustomAddressAttributes() {
        return customAddressAttributes;
    }

    public void setCustomAddressAttributes(List<CustomAddressAttribute> customAddressAttributes) {
        this.customAddressAttributes = customAddressAttributes;
    }

    public Object getAddressAttribute1() {
        return addressAttribute1;
    }

    public void setAddressAttribute1(Object addressAttribute1) {
        this.addressAttribute1 = addressAttribute1;
    }

    public Object getAddressAttribute2() {
        return addressAttribute2;
    }

    public void setAddressAttribute2(Object addressAttribute2) {
        this.addressAttribute2 = addressAttribute2;
    }

    public Object getAddressAttribute3() {
        return addressAttribute3;
    }

    public void setAddressAttribute3(Object addressAttribute3) {
        this.addressAttribute3 = addressAttribute3;
    }

    public Object getAddressAttribute4() {
        return addressAttribute4;
    }

    public void setAddressAttribute4(Object addressAttribute4) {
        this.addressAttribute4 = addressAttribute4;
    }

    public Object getAddressAttribute5() {
        return addressAttribute5;
    }

    public void setAddressAttribute5(Object addressAttribute5) {
        this.addressAttribute5 = addressAttribute5;
    }

    public Object getAddressAttribute6() {
        return addressAttribute6;
    }

    public void setAddressAttribute6(Object addressAttribute6) {
        this.addressAttribute6 = addressAttribute6;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CustomProperties__ getCustomProperties() {
        return customProperties;
    }

    public void setCustomProperties(CustomProperties__ customProperties) {
        this.customProperties = customProperties;
    }

}
