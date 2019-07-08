package com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse;

public class ManufacturerFilter {
    private boolean Enabled;
    private FilteredItems[] AlreadyFilteredItems;
    private FilteredItems[] NotFilteredItems;
    private String RemoveFilterUrl;
    private CustomProperties CustomProperties;

    public boolean isEnabled() {
        return Enabled;
    }

    public void setEnabled(boolean enabled) {
        Enabled = enabled;
    }

    public FilteredItems[] getAlreadyFilteredItems() {
        return AlreadyFilteredItems;
    }

    public void setAlreadyFilteredItems(FilteredItems[] alreadyFilteredItems) {
        AlreadyFilteredItems = alreadyFilteredItems;
    }

    public FilteredItems[] getNotFilteredItems() {
        return NotFilteredItems;
    }

    public void setNotFilteredItems(FilteredItems[] notFilteredItems) {
        NotFilteredItems = notFilteredItems;
    }

    public String getRemoveFilterUrl() {
        return RemoveFilterUrl;
    }

    public void setRemoveFilterUrl(String removeFilterUrl) {
        RemoveFilterUrl = removeFilterUrl;
    }

    public com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse.CustomProperties getCustomProperties() {
        return CustomProperties;
    }

    public void setCustomProperties(com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse.CustomProperties customProperties) {
        CustomProperties = customProperties;
    }
}
