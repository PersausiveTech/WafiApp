package com.mobtecnica.wafiapps.model.productDetails;

/**
 * Created by SIby on 20-02-2017.
 */

public class CategoryBreadcrumb {
    private String Name;

    private String NumberOfProducts;

    private String SeName;

    private String IncludeInTopMenu;

    private String Id;

//    private String CustomProperties;

    private String[] SubCategories;

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }


    public String getSeName() {
        return SeName;
    }

    public void setSeName(String SeName) {
        this.SeName = SeName;
    }

    public String getIncludeInTopMenu() {
        return IncludeInTopMenu;
    }

    public void setIncludeInTopMenu(String IncludeInTopMenu) {
        this.IncludeInTopMenu = IncludeInTopMenu;
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

    public String[] getSubCategories() {
        return SubCategories;
    }

    public void setSubCategories(String[] SubCategories) {
        this.SubCategories = SubCategories;
    }

    public String getNumberOfProducts() {
        return NumberOfProducts;
    }

    public void setNumberOfProducts(String numberOfProducts) {
        NumberOfProducts = numberOfProducts;
    }

    @Override
    public String toString() {
        return "ClassPojo [Name = " + Name + ", NumberOfProducts = " + NumberOfProducts + ", SeName = " + SeName + ", IncludeInTopMenu = " + IncludeInTopMenu + ", Id = " + Id + ", CustomProperties = SubCategories = " + SubCategories + "]";
    }
}
