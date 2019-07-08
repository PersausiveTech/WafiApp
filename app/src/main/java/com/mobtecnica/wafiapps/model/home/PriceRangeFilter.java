package com.mobtecnica.wafiapps.model.home;

/**
 * Created by SIby on 09-02-2017.
 */

public class PriceRangeFilter {
    private String[] Items;

    private String RemoveFilterUrl;

    private String Enabled;

    private CustomProperties CustomProperties;

    public String[] getItems() {
        return Items;
    }

    public void setItems(String[] Items) {
        this.Items = Items;
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

    public void setEnabled(String Enabled) {
        this.Enabled = Enabled;
    }

    public com.mobtecnica.wafiapps.model.home.CustomProperties getCustomProperties() {
        return CustomProperties;
    }

    public void setCustomProperties(com.mobtecnica.wafiapps.model.home.CustomProperties customProperties) {
        CustomProperties = customProperties;
    }

    @Override
    public String toString() {
        return "ClassPojo [Items = " + Items + ", RemoveFilterUrl = " + RemoveFilterUrl + ", Enabled = " + Enabled + ", CustomProperties = " + CustomProperties + "]";
    }
}
