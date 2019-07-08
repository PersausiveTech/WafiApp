package com.mobtecnica.wafiapps.model.deals;


import com.mobtecnica.wafiapps.model.CustomProperties;

public class Deal {
    private String Name;
    private AppPictureModel AppPictureModel;
    private String CategoryId;
    private String ManufacturerId;
    private String DisplayOrder;
    private boolean Published;
    private String Id;
    private CustomProperties CustomProperties;

    public com.mobtecnica.wafiapps.model.deals.AppPictureModel getAppPictureModel() {
        return AppPictureModel;
    }

    public String getCategoryId() {
        return CategoryId;
    }

    public String getManufacturerId() {
        return ManufacturerId;
    }

    public String getName() {
        return Name;
    }
}
