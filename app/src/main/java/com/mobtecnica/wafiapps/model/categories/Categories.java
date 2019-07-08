package com.mobtecnica.wafiapps.model.categories;

/**
 * Created by SIby on 02-02-2017.
 */


public class Categories
{
    private String Name;

    private String Deleted;

    private String ParentCategoryId;

    private String NumberOfProducts;

    private String Published;

    private String SeName;

    private PictureModelCategories PictureModel;

    private String DisplayOrder;

    private String IncludeInTopMenu;

    private String Id;

    //private String CustomProperties;

    private SubCategories[] SubCategories;

    public String getNumberOfProducts() {
        return NumberOfProducts;
    }

    public void setNumberOfProducts(String numberOfProducts) {
        NumberOfProducts = numberOfProducts;
    }

    public PictureModelCategories getPictureModel() {
        return PictureModel;
    }

    public void setPictureModel(PictureModelCategories pictureModel) {
        PictureModel = pictureModel;
    }

    public String getName ()
    {
        return Name;
    }

    public void setName (String Name)
    {
        this.Name = Name;
    }

    public String getDeleted ()
    {
        return Deleted;
    }

    public void setDeleted (String Deleted)
    {
        this.Deleted = Deleted;
    }

    public String getParentCategoryId ()
    {
        return ParentCategoryId;
    }

    public void setParentCategoryId (String ParentCategoryId)
    {
        this.ParentCategoryId = ParentCategoryId;
    }



    public String getPublished ()
    {
        return Published;
    }

    public void setPublished (String Published)
    {
        this.Published = Published;
    }

    public String getSeName ()
    {
        return SeName;
    }

    public void setSeName (String SeName)
    {
        this.SeName = SeName;
    }



    public String getDisplayOrder ()
    {
        return DisplayOrder;
    }

    public void setDisplayOrder (String DisplayOrder)
    {
        this.DisplayOrder = DisplayOrder;
    }

    public String getIncludeInTopMenu ()
    {
        return IncludeInTopMenu;
    }

    public void setIncludeInTopMenu (String IncludeInTopMenu)
    {
        this.IncludeInTopMenu = IncludeInTopMenu;
    }

    public String getId ()
    {
        return Id;
    }

    public void setId (String Id)
    {
        this.Id = Id;
    }

//    public String getCustomProperties ()
//    {
//        return CustomProperties;
//    }
//
//    public void setCustomProperties (String CustomProperties)
//    {
//        this.CustomProperties = CustomProperties;
//    }

    public SubCategories[] getSubCategories ()
    {
        return SubCategories;
    }

    public void setSubCategories (SubCategories[] SubCategories)
    {
        this.SubCategories = SubCategories;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Name = "+Name+", Deleted = "+Deleted+", ParentCategoryId = "+ParentCategoryId+", NumberOfProducts = "+NumberOfProducts+", Published = "+Published+", SeName = "+SeName+", PictureModel = "+PictureModel+", DisplayOrder = "+DisplayOrder+", IncludeInTopMenu = "+IncludeInTopMenu+", Id = "+Id+", CustomProperties  SubCategories = "+SubCategories+"]";
    }
}

