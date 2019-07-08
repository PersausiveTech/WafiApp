package com.mobtecnica.wafiapps.model.productDetails;

/**
 * Created by SIby on 20-02-2017.
 */

public class PriceRangeFilter {private String[] Items;

    private String RemoveFilterUrl;

    private String Enabled;

   // private String CustomProperties;

    public String[] getItems ()
    {
        return Items;
    }

    public void setItems (String[] Items)
    {
        this.Items = Items;
    }

    public String getRemoveFilterUrl() {
        return RemoveFilterUrl;
    }

    public void setRemoveFilterUrl(String removeFilterUrl) {
        RemoveFilterUrl = removeFilterUrl;
    }

    public String getEnabled ()
    {
        return Enabled;
    }

    public void setEnabled (String Enabled)
    {
        this.Enabled = Enabled;
    }
////
//    public String getCustomProperties ()
//    {
//        return CustomProperties;
//    }
//
//    public void setCustomProperties (String CustomProperties)
//    {
//        this.CustomProperties = CustomProperties;
//    }

    @Override
    public String toString()
    {
        return "ClassPojo [Items = "+Items+", RemoveFilterUrl = "+RemoveFilterUrl+", Enabled = "+Enabled+", CustomProperties ]";
    }
}
