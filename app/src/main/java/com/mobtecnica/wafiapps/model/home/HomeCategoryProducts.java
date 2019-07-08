package com.mobtecnica.wafiapps.model.home;

/**
 * Created by SIby on 09-02-2017.
 */

public class HomeCategoryProducts {
    private String CategoryGroupTitle;

    private DefaultPictureModel DefaultPictureModel;

    private ReviewOverviewModel ReviewOverviewModel;

    private String ProductGroupTitle;

    private String GroupId;

    private ProductPrice ProductPrice;

    private String Name;

    private String ShortDescription;

    private String[] SpecificationAttributeModels;

    private String SeName;

    private String ProductType;

    private String FullDescription;

    private String Id;

    private CustomProperties CustomProperties;

    private String MarkAsNew;

    public String getCategoryGroupTitle() {
        return CategoryGroupTitle;
    }

    public void setCategoryGroupTitle(String CategoryGroupTitle) {
        this.CategoryGroupTitle = CategoryGroupTitle;
    }

    public DefaultPictureModel getDefaultPictureModel() {
        return DefaultPictureModel;
    }

    public void setDefaultPictureModel(DefaultPictureModel DefaultPictureModel) {
        this.DefaultPictureModel = DefaultPictureModel;
    }

    public ReviewOverviewModel getReviewOverviewModel() {
        return ReviewOverviewModel;
    }

    public void setReviewOverviewModel(ReviewOverviewModel ReviewOverviewModel) {
        this.ReviewOverviewModel = ReviewOverviewModel;
    }

    public String getProductGroupTitle() {
        return ProductGroupTitle;
    }

    public void setProductGroupTitle(String productGroupTitle) {
        ProductGroupTitle = productGroupTitle;
    }

    public String getGroupId() {
        return GroupId;
    }

    public void setGroupId(String GroupId) {
        this.GroupId = GroupId;
    }

    public ProductPrice getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(ProductPrice ProductPrice) {
        this.ProductPrice = ProductPrice;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getShortDescription() {
        return ShortDescription;
    }

    public void setShortDescription(String ShortDescription) {
        this.ShortDescription = ShortDescription;
    }

    public String[] getSpecificationAttributeModels() {
        return SpecificationAttributeModels;
    }

    public void setSpecificationAttributeModels(String[] SpecificationAttributeModels) {
        this.SpecificationAttributeModels = SpecificationAttributeModels;
    }

    public String getSeName() {
        return SeName;
    }

    public void setSeName(String SeName) {
        this.SeName = SeName;
    }

    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String ProductType) {
        this.ProductType = ProductType;
    }

    public String getFullDescription() {
        return FullDescription;
    }

    public void setFullDescription(String FullDescription) {
        this.FullDescription = FullDescription;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public com.mobtecnica.wafiapps.model.home.CustomProperties getCustomProperties() {
        return CustomProperties;
    }

    public void setCustomProperties(com.mobtecnica.wafiapps.model.home.CustomProperties customProperties) {
        CustomProperties = customProperties;
    }

    public String getMarkAsNew() {
        return MarkAsNew;
    }

    public void setMarkAsNew(String MarkAsNew) {
        this.MarkAsNew = MarkAsNew;
    }

    @Override
    public String toString() {
        return "ClassPojo [CategoryGroupTitle = " + CategoryGroupTitle + ", DefaultPictureModel = " + DefaultPictureModel + ", ReviewOverviewModel = " + ReviewOverviewModel + ", ProductGroupTitle = " + ProductGroupTitle + ", GroupId = " + GroupId + ", ProductPrice = " + ProductPrice + ", Name = " + Name + ", ShortDescription = " + ShortDescription + ", SpecificationAttributeModels = " + SpecificationAttributeModels + ", SeName = " + SeName + ", ProductType = " + ProductType + ", FullDescription = " + FullDescription + ", Id = " + Id + ", CustomProperties = " + CustomProperties + ", MarkAsNew = " + MarkAsNew + "]";
    }
}
