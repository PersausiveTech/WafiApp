
package com.mobtecnica.wafiapps.model.checkout.opcBillingForm;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomAddressAttribute {

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("IsRequired")
    @Expose
    private Boolean isRequired;
    @SerializedName("DefaultValue")
    @Expose
    private String defaultValue;
    @SerializedName("AttributeControlType")
    @Expose
    private Integer attributeControlType;
    @SerializedName("Values")
    @Expose
    private List<Object> values = null;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("CustomProperties")
    @Expose
    private CustomProperties_ customProperties;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(Boolean isRequired) {
        this.isRequired = isRequired;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Integer getAttributeControlType() {
        return attributeControlType;
    }

    public void setAttributeControlType(Integer attributeControlType) {
        this.attributeControlType = attributeControlType;
    }

    public List<Object> getValues() {
        return values;
    }

    public void setValues(List<Object> values) {
        this.values = values;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CustomProperties_ getCustomProperties() {
        return customProperties;
    }

    public void setCustomProperties(CustomProperties_ customProperties) {
        this.customProperties = customProperties;
    }

}
