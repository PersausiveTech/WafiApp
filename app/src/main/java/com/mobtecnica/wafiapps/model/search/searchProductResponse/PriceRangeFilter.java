package com.mobtecnica.wafiapps.model.search.searchProductResponse;

/**
 * Created by SIby on 02-03-2017.
 */

public class PriceRangeFilter {
    private String[] Items;

    private String RemoveFilterUrl;

    private String Enabled;

//    private String CustomProperties;

    public String[] getItems() {
        return Items;
    }

    public void setItems(String[] items) {
        Items = items;
    }

    public String getRemoveFilterUrl() {
        return RemoveFilterUrl;
    }

    public void setRemoveFilterUrl(String removeFilterUrl) {
        RemoveFilterUrl = removeFilterUrl;
    }

    public String getEnabled() {
        return Enabled;
    }

    public void setEnabled(String enabled) {
        Enabled = enabled;
    }

//    public String getCustomProperties() {
//        return CustomProperties;
//    }
//
//    public void setCustomProperties(String customProperties) {
//        CustomProperties = customProperties;
//    }
}
