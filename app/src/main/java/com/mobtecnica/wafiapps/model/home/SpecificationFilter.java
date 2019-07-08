package com.mobtecnica.wafiapps.model.home;

/**
 * Created by SIby on 09-02-2017.
 */

public class SpecificationFilter {
    private String RemoveFilterUrl;

    private String Enabled;

    private String[] AlreadyFilteredItems;

    private String[] NotFilteredItems;

    private CustomProperties CustomProperties;

    public String getRemoveFilterUrl() {
        return RemoveFilterUrl;
    }

    public void setRemoveFilterUrl(String removeFilterUrl) {
        RemoveFilterUrl = removeFilterUrl;
    }

    public String getEnabled() {
        return Enabled;
    }

    public void setEnabled(String Enabled) {
        this.Enabled = Enabled;
    }

    public String[] getAlreadyFilteredItems() {
        return AlreadyFilteredItems;
    }

    public void setAlreadyFilteredItems(String[] AlreadyFilteredItems) {
        this.AlreadyFilteredItems = AlreadyFilteredItems;
    }

    public String[] getNotFilteredItems() {
        return NotFilteredItems;
    }

    public void setNotFilteredItems(String[] NotFilteredItems) {
        this.NotFilteredItems = NotFilteredItems;
    }

    public com.mobtecnica.wafiapps.model.home.CustomProperties getCustomProperties() {
        return CustomProperties;
    }

    public void setCustomProperties(com.mobtecnica.wafiapps.model.home.CustomProperties customProperties) {
        CustomProperties = customProperties;
    }

    @Override
    public String toString() {
        return "ClassPojo [RemoveFilterUrl = " + RemoveFilterUrl + ", Enabled = " + Enabled + ", AlreadyFilteredItems = " + AlreadyFilteredItems + ", NotFilteredItems = " + NotFilteredItems + ", CustomProperties = " + CustomProperties + "]";
    }
}
