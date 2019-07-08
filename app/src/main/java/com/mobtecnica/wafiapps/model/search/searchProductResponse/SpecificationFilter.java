package com.mobtecnica.wafiapps.model.search.searchProductResponse;

import com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse.NotFilteredItems;

/**
 * Created by SIby on 02-03-2017.
 */

public class SpecificationFilter {
    private String RemoveFilterUrl;

    private String Enabled;

    private NotFilteredItems[] AlreadyFilteredItems;

    private NotFilteredItems[] NotFilteredItems;

//    private String CustomProperties;

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

    public NotFilteredItems[] getAlreadyFilteredItems() {
        return AlreadyFilteredItems;
    }

    public void setAlreadyFilteredItems(NotFilteredItems[] alreadyFilteredItems) {
        AlreadyFilteredItems = alreadyFilteredItems;
    }

    public NotFilteredItems[] getNotFilteredItems() {
        return NotFilteredItems;
    }

    public void setNotFilteredItems(NotFilteredItems[] notFilteredItems) {
        NotFilteredItems = notFilteredItems;
    }

//    public String getCustomProperties() {
//        return CustomProperties;
//    }
//
//    public void setCustomProperties(String customProperties) {
//        CustomProperties = customProperties;
//    }
}
