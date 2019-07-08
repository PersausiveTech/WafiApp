package com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse;

public class FilteredItems {
    private String ManufacturerId;
    private String ManufacturerName;
    private CustomProperties CustomProperties;

    private boolean isSelected = false;//for parent (left)
    private boolean isOptionsSelected = false;//for child(right)
    private boolean isOptionItemSelected = false;//to notify parent

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isOptionsSelected() {
        return isOptionsSelected;
    }

    public void setOptionsSelected(boolean optionsSelected) {
        isOptionsSelected = optionsSelected;
    }

    public boolean isOptionItemSelected() {
        return isOptionItemSelected;
    }

    public void setOptionItemSelected(boolean optionItemSelected) {
        isOptionItemSelected = optionItemSelected;
    }

    public String getManufacturerId() {
        return ManufacturerId;
    }

    public void setManufacturerId(String manufacturerId) {
        ManufacturerId = manufacturerId;
    }

    public String getManufacturerName() {
        return ManufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        ManufacturerName = manufacturerName;
    }

    public com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse.CustomProperties getCustomProperties() {
        return CustomProperties;
    }

    public void setCustomProperties(com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse.CustomProperties customProperties) {
        CustomProperties = customProperties;
    }
}
