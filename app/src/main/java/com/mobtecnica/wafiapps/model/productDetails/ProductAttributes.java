package com.mobtecnica.wafiapps.model.productDetails;

/**
 * Created by SIby on 20-02-2017.
 */

public class ProductAttributes {
    private Values[] Values;

    private String TextPrompt;

    private String[] AllowedFileExtensions;

    private String Description;

    private String IsRequired;

    private String SelectedYear;

    private String DefaultValue;

    private String ProductAttributeId;

    private String SelectedMonth;

    private String ProductId;

    private String HasCondition;

    private String Name;

    private String SelectedDay;

    private String Id;

//    private String CustomProperties;

    private String AttributeControlType;

    public Values[] getValues() {
        return Values;
    }

    public void setValues(Values[] Values) {
        this.Values = Values;
    }
        public String[] getAllowedFileExtensions() {
        return AllowedFileExtensions;
    }

    public void setAllowedFileExtensions(String[] AllowedFileExtensions) {
        this.AllowedFileExtensions = AllowedFileExtensions;
    }
    public String getIsRequired() {
        return IsRequired;
    }

    public void setIsRequired(String IsRequired) {
        this.IsRequired = IsRequired;
    }


    public String getProductAttributeId() {
        return ProductAttributeId;
    }

    public void setProductAttributeId(String ProductAttributeId) {
        this.ProductAttributeId = ProductAttributeId;
    }
    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String ProductId) {
        this.ProductId = ProductId;
    }

    public String getHasCondition() {
        return HasCondition;
    }

    public void setHasCondition(String HasCondition) {
        this.HasCondition = HasCondition;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }


    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

//    public String getCustomProperties() {
//        return CustomProperties;
//    }
//
//    public void setCustomProperties(String CustomProperties) {
//        this.CustomProperties = CustomProperties;
//    }

    public String getAttributeControlType() {
        return AttributeControlType;
    }

    public void setAttributeControlType(String AttributeControlType) {
        this.AttributeControlType = AttributeControlType;
    }

    public String getTextPrompt() {
        return TextPrompt;
    }

    public void setTextPrompt(String textPrompt) {
        TextPrompt = textPrompt;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getSelectedYear() {
        return SelectedYear;
    }

    public void setSelectedYear(String selectedYear) {
        SelectedYear = selectedYear;
    }

    public String getDefaultValue() {
        return DefaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        DefaultValue = defaultValue;
    }

    public String getSelectedMonth() {
        return SelectedMonth;
    }

    public void setSelectedMonth(String selectedMonth) {
        SelectedMonth = selectedMonth;
    }

    public String getSelectedDay() {
        return SelectedDay;
    }

    public void setSelectedDay(String selectedDay) {
        SelectedDay = selectedDay;
    }

    @Override
    public String toString() {
        return "ClassPojo [Values = " + Values + ", TextPrompt = " + TextPrompt + ", AllowedFileExtensions = " + AllowedFileExtensions + ", Description = " + Description + ", IsRequired = " + IsRequired + ", SelectedYear = " + SelectedYear + ", DefaultValue = " + DefaultValue + ", ProductAttributeId = " + ProductAttributeId + ", SelectedMonth = " + SelectedMonth + ", ProductId = " + ProductId + ", HasCondition = " + HasCondition + ", Name = " + Name + ", SelectedDay = " + SelectedDay + ", Id = " + Id + ", CustomProperties = AttributeControlType = " + AttributeControlType + "]";
    }
}
