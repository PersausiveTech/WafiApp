package com.mobtecnica.wafiapps.model.productDetails;

/**
 * Created by SIby on 20-02-2017.
 */

public class ProductManufacturers {
    private String Name;

    private String Description;

    private Products[] Products;

    private String SeName;

    private PictureModel PictureModel;

    private String MetaTitle;

    private String Id;

    //private String CustomProperties;

    private String[] FeaturedProducts;

    private String MetaDescription;

    private String MetaKeywords;

    private PagingFilteringContext PagingFilteringContext;

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public com.mobtecnica.wafiapps.model.productDetails.Products[] getProducts() {
        return Products;
    }

    public void setProducts(com.mobtecnica.wafiapps.model.productDetails.Products[] products) {
        Products = products;
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


    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }


    public String[] getFeaturedProducts() {
        return FeaturedProducts;
    }

    public void setFeaturedProducts(String[] FeaturedProducts) {
        this.FeaturedProducts = FeaturedProducts;
    }


    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getMetaTitle() {
        return MetaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        MetaTitle = metaTitle;
    }

    public String getMetaDescription() {
        return MetaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        MetaDescription = metaDescription;
    }

    public String getMetaKeywords() {
        return MetaKeywords;
    }

    public void setMetaKeywords(String metaKeywords) {
        MetaKeywords = metaKeywords;
    }

    public PagingFilteringContext getPagingFilteringContext() {
        return PagingFilteringContext;
    }

    public void setPagingFilteringContext(PagingFilteringContext PagingFilteringContext) {
        this.PagingFilteringContext = PagingFilteringContext;
    }

    @Override
    public String toString() {
        return "ClassPojo [Name = " + Name + ", Description = " + Description + ", Products = " + Products + ", SeName = " + SeName + ", PictureModel = " + PictureModel + ", MetaTitle = " + MetaTitle + ", Id = " + Id + ", CustomProperties =  FeaturedProducts = " + FeaturedProducts + ", MetaDescription = " + MetaDescription + ", MetaKeywords = " + MetaKeywords + ", PagingFilteringContext = " + PagingFilteringContext + "]";
    }
}
