
package com.mobtecnica.wafiapps.model.checkout.savePaymentInfo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckoutAttribute {

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("DefaultValue")
    @Expose
    private String defaultValue;
    @SerializedName("TextPrompt")
    @Expose
    private String textPrompt;
    @SerializedName("IsRequired")
    @Expose
    private Boolean isRequired;
    @SerializedName("SelectedDay")
    @Expose
    private String selectedDay;
    @SerializedName("SelectedMonth")
    @Expose
    private String selectedMonth;
    @SerializedName("SelectedYear")
    @Expose
    private String selectedYear;
    @SerializedName("AllowedFileExtensions")
    @Expose
    private List<Object> allowedFileExtensions = null;
    @SerializedName("AttributeControlType")
    @Expose
    private Integer attributeControlType;
    @SerializedName("Values")
    @Expose
    private List<Value> values = null;
    @SerializedName("Id")
    @Expose
    private Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getTextPrompt() {
        return textPrompt;
    }

    public void setTextPrompt(String textPrompt) {
        this.textPrompt = textPrompt;
    }

    public Boolean getRequired() {
        return isRequired;
    }

    public void setRequired(Boolean required) {
        isRequired = required;
    }

    public String getSelectedDay() {
        return selectedDay;
    }

    public void setSelectedDay(String selectedDay) {
        this.selectedDay = selectedDay;
    }

    public String getSelectedMonth() {
        return selectedMonth;
    }

    public void setSelectedMonth(String selectedMonth) {
        this.selectedMonth = selectedMonth;
    }

    public String getSelectedYear() {
        return selectedYear;
    }

    public void setSelectedYear(String selectedYear) {
        this.selectedYear = selectedYear;
    }

    public List<Object> getAllowedFileExtensions() {
        return allowedFileExtensions;
    }

    public void setAllowedFileExtensions(List<Object> allowedFileExtensions) {
        this.allowedFileExtensions = allowedFileExtensions;
    }

    public Integer getAttributeControlType() {
        return attributeControlType;
    }

    public void setAttributeControlType(Integer attributeControlType) {
        this.attributeControlType = attributeControlType;
    }

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
