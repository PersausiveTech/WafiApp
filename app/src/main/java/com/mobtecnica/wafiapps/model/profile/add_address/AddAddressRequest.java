package com.mobtecnica.wafiapps.model.profile.add_address;

/**
 * Created by SIby on 10-01-2017.
 */

public class AddAddressRequest {

    private String guid;

    private Model model;
    private String Email;

    private String address_attribute_6;

    private String Address1;

    private String CountryId;

    private String address_attribute_3;

    private String address_attribute_2;

    private String FirstName;

    private String address_attribute_5;

    private String PhoneNumber;

    private String address_attribute_4;

    private String LastName;

    private String address_attribute_1;

    private String apiToken;

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress_attribute_6() {
        return address_attribute_6;
    }

    public void setAddress_attribute_6(String address_attribute_6) {
        this.address_attribute_6 = address_attribute_6;
    }

    public String getAddress1() {
        return Address1;
    }

    public void setAddress1(String address1) {
        Address1 = address1;
    }

    public String getCountryId() {
        return CountryId;
    }

    public void setCountryId(String countryId) {
        CountryId = countryId;
    }

    public String getAddress_attribute_3() {
        return address_attribute_3;
    }

    public void setAddress_attribute_3(String address_attribute_3) {
        this.address_attribute_3 = address_attribute_3;
    }

    public String getAddress_attribute_2() {
        return address_attribute_2;
    }

    public void setAddress_attribute_2(String address_attribute_2) {
        this.address_attribute_2 = address_attribute_2;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getAddress_attribute_5() {
        return address_attribute_5;
    }

    public void setAddress_attribute_5(String address_attribute_5) {
        this.address_attribute_5 = address_attribute_5;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getAddress_attribute_4() {
        return address_attribute_4;
    }

    public void setAddress_attribute_4(String address_attribute_4) {
        this.address_attribute_4 = address_attribute_4;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getAddress_attribute_1() {
        return address_attribute_1;
    }

    public void setAddress_attribute_1(String address_attribute_1) {
        this.address_attribute_1 = address_attribute_1;
    }

    public String getGuid ()
    {
        return guid;
    }

    public void setGuid (String guid)
    {
        this.guid = guid;
    }

    public Model getModel ()
    {
        return model;
    }

    public void setModel (Model model)
    {
        this.model = model;
    }

    public String getApiToken ()
    {
        return apiToken;
    }

    public void setApiToken (String apiToken)
    {
        this.apiToken = apiToken;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [guid = "+guid+", model = "+model+", apiToken = "+apiToken+"]";
    }
    }

