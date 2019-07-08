package com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse;

/**
 * Created by SIby on 16-02-2017.
 */

public class SpecificationFilter {
    private String RemoveFilterUrl;

    private String Enabled;

    private com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse.NotFilteredItems[] AlreadyFilteredItems;

    public com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse.NotFilteredItems[] getNotFilteredItems() {
        return NotFilteredItems;
    }

    public void setNotFilteredItems(com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse.NotFilteredItems[] notFilteredItems) {
        NotFilteredItems = notFilteredItems;
    }

    private NotFilteredItems[] NotFilteredItems;

    private CustomProperties CustomProperties;



    public String getEnabled ()
    {
        return Enabled;
    }

    public void setEnabled (String Enabled)
    {
        this.Enabled = Enabled;
    }

    public com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse.NotFilteredItems[] getAlreadyFilteredItems ()
    {
        return AlreadyFilteredItems;
    }

    public void setAlreadyFilteredItems (com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse.NotFilteredItems[] AlreadyFilteredItems)
    {
        this.AlreadyFilteredItems = AlreadyFilteredItems;
    }

//    public String[] getNotFilteredItems ()
//    {
//        return NotFilteredItems;
//    }
//
//    public void setNotFilteredItems (String[] NotFilteredItems)
//    {
//        this.NotFilteredItems = NotFilteredItems;
//    }

    public com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse.CustomProperties getCustomProperties() {
        return CustomProperties;
    }

    public void setCustomProperties(com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse.CustomProperties customProperties) {
        CustomProperties = customProperties;
    }

    public String getRemoveFilterUrl() {
        return RemoveFilterUrl;
    }

    public void setRemoveFilterUrl(String removeFilterUrl) {
        RemoveFilterUrl = removeFilterUrl;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [RemoveFilterUrl = "+RemoveFilterUrl+", Enabled = "+Enabled+", AlreadyFilteredItems = "+AlreadyFilteredItems+", NotFilteredItems , CustomProperties = "+CustomProperties+"]";
    }
}
