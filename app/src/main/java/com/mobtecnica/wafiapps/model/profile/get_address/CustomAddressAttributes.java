package com.mobtecnica.wafiapps.model.profile.get_address;

import android.text.TextUtils;

/**
 * Created by SIby on 10-01-2017.
 */

public class CustomAddressAttributes {
//    private String[] Values;

    private String Name;

    private String IsRequired;

    private String DefaultValue;

    private String Id;

//    private String CustomProperties;

    private String AttributeControlType;

//    public String[] getValues() {
//        return Values;
//    }
//
//    public void setValues(String[] Values) {
//        this.Values = Values;
//    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getIsRequired() {
        return IsRequired;
    }

    public void setIsRequired(String IsRequired) {
        this.IsRequired = IsRequired;
    }

    public String getDefaultValue() {
        if (!TextUtils.isEmpty(DefaultValue)) {
            return DefaultValue;
        }else{
            return "";
        }
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

    @Override
    public String toString() {
        return "ClassPojo [Values = , Name = " + Name + ", IsRequired = " + IsRequired + ", DefaultValue = " + DefaultValue + ", Id = " + Id + ", CustomProperties = , AttributeControlType = " + AttributeControlType + "]";
    }
}

