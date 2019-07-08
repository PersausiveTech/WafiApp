package com.mobtecnica.wafiapps.model.productDetails;

/**
 * Created by SIby on 20-02-2017.
 */

public class VendorModel {
    private String Name;

    private String SeName;

    private String Id;

//    private String CustomProperties;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSeName() {
        return SeName;
    }

    public void setSeName(String seName) {
        SeName = seName;
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

    @Override
    public String toString() {
        return "ClassPojo [Name = " + Name + ", SeName = " + SeName + ", Id = " + Id + ", CustomProperties =]";
    }
}
