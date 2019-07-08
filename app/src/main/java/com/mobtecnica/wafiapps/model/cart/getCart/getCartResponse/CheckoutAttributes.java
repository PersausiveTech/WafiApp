package com.mobtecnica.wafiapps.model.cart.getCart.getCartResponse;

/**
 * Created by SIby on 20-03-2017.
 */


public class CheckoutAttributes {
    private Values[] Values;

    private String Name;

    private String[] AllowedFileExtensions;

    private String TextPrompt;

    private String IsRequired;

    private String SelectedYear;

    private String SelectedDay;

    private String DefaultValue;

    private String Id;

//    private String CustomProperties;

    private String AttributeControlType;

    private String SelectedMonth;

    public com.mobtecnica.wafiapps.model.cart.getCart.getCartResponse.Values[] getValues() {
        return Values;
    }

    public void setValues(com.mobtecnica.wafiapps.model.cart.getCart.getCartResponse.Values[] values) {
        Values = values;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String[] getAllowedFileExtensions() {
        return AllowedFileExtensions;
    }

    public void setAllowedFileExtensions(String[] AllowedFileExtensions) {
        this.AllowedFileExtensions = AllowedFileExtensions;
    }

    public String getTextPrompt() {
        return TextPrompt;
    }

    public void setTextPrompt(String TextPrompt) {
        this.TextPrompt = TextPrompt;
    }

    public String getIsRequired() {
        return IsRequired;
    }

    public void setIsRequired(String IsRequired) {
        this.IsRequired = IsRequired;
    }


    public String getDefaultValue() {
        return DefaultValue;
    }

    public void setDefaultValue(String DefaultValue) {
        this.DefaultValue = DefaultValue;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getAttributeControlType() {
        return AttributeControlType;
    }

    public void setAttributeControlType(String AttributeControlType) {
        this.AttributeControlType = AttributeControlType;
    }

    public String getSelectedYear() {
        return SelectedYear;
    }

    public void setSelectedYear(String selectedYear) {
        SelectedYear = selectedYear;
    }

    public String getSelectedDay() {
        return SelectedDay;
    }

    public void setSelectedDay(String selectedDay) {
        SelectedDay = selectedDay;
    }

    public String getSelectedMonth() {
        return SelectedMonth;
    }

    public void setSelectedMonth(String selectedMonth) {
        SelectedMonth = selectedMonth;
    }

    @Override
    public String toString() {
        return "ClassPojo [Values = " + Values + ", Name = " + Name + ", AllowedFileExtensions = " + AllowedFileExtensions + ", TextPrompt = " + TextPrompt + ", IsRequired = " + IsRequired + ", SelectedYear = " + SelectedYear + ", SelectedDay = " + SelectedDay + ", DefaultValue = " + DefaultValue + ", Id = " + Id + ", CustomProperties = AttributeControlType = " + AttributeControlType + ", SelectedMonth = " + SelectedMonth + "]";
    }
}

