package com.mobtecnica.wafiapps.model.home;

import com.mobtecnica.wafiapps.model.categories.SubCategories;

/**
 * Created by SIby on 09-02-2017.
 */

public class HomeCategories {
    private String Name;

    private String Deleted;

    private String ParentCategoryId;

    private String NumberOfProducts;

    private String Published;

    private String SeName;

    private PictureModel PictureModel;

    public com.mobtecnica.wafiapps.model.home.CustomProperties getCustomProperties() {
        return CustomProperties;
    }

    public void setCustomProperties(com.mobtecnica.wafiapps.model.home.CustomProperties customProperties) {
        CustomProperties = customProperties;
    }

    private String DisplayOrder;

    private String IncludeInTopMenu;

    private String Id;

    private CustomProperties CustomProperties;

    private com.mobtecnica.wafiapps.model.categories.SubCategories[] SubCategories;

    public String getNumberOfProducts() {
        return NumberOfProducts;
    }

    public void setNumberOfProducts(String numberOfProducts) {
        NumberOfProducts = numberOfProducts;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getDeleted() {
        return Deleted;
    }

    public void setDeleted(String Deleted) {
        this.Deleted = Deleted;
    }

    public String getParentCategoryId() {
        return ParentCategoryId;
    }

    public void setParentCategoryId(String ParentCategoryId) {
        this.ParentCategoryId = ParentCategoryId;
    }


    public String getPublished() {
        return Published;
    }

    public void setPublished(String Published) {
        this.Published = Published;
    }

    public String getSeName() {
        return SeName;
    }

    public void setSeName(String SeName) {
        this.SeName = SeName;
    }

    public PictureModel getPictureModel() {
        return PictureModel;
    }

    public void setPictureModel(PictureModel PictureModel) {
        this.PictureModel = PictureModel;
    }

    public String getDisplayOrder() {
        return DisplayOrder;
    }

    public void setDisplayOrder(String DisplayOrder) {
        this.DisplayOrder = DisplayOrder;
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


    public SubCategories[] getSubCategories() {
        if(SubCategories!=null)
        return SubCategories;
        else
            return new SubCategories[0];
    }

    public void setSubCategories(SubCategories[] SubCategories) {
        this.SubCategories = SubCategories;
    }

    @Override
    public String toString() {
        return "ClassPojo [Name = " + Name + ", Deleted = " + Deleted + ", ParentCategoryId = " + ParentCategoryId + ", NumberOfProducts = " + NumberOfProducts + ", Published = " + Published + ", SeName = " + SeName + ", PictureModel = " + PictureModel + ", DisplayOrder = " + DisplayOrder + ", IncludeInTopMenu = " + IncludeInTopMenu + ", Id = " + Id + ", CustomProperties = " + CustomProperties + ", SubCategories = " + SubCategories + "]";
    }
}
