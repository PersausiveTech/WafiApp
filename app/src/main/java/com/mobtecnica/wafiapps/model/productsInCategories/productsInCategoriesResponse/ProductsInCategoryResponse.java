package com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by SIby on 16-02-2017.
 */

public class ProductsInCategoryResponse implements Parcelable {
    private String Description;

    private ArrayList<Products> Products;

    private String[] CategoryBreadcrumb;

    private String[] FeaturedProducts;

    private String MetaDescription;

    private String MetaKeywords;

    private PagingFilteringContext PagingFilteringContext;

    private String Name;

    private String SeName;

    private PictureModel PictureModel;

    private String MetaTitle;

    private String Id;

    private CustomProperties CustomProperties;

    private SubCategories[] SubCategories;

    private String DisplayCategoryBreadcrumb;

    public ProductsInCategoryResponse(){

    }
    protected ProductsInCategoryResponse(Parcel in) {
        Description = in.readString();
        CategoryBreadcrumb = in.createStringArray();
        FeaturedProducts = in.createStringArray();
        MetaDescription = in.readString();
        MetaKeywords = in.readString();
        Name = in.readString();
        SeName = in.readString();
        MetaTitle = in.readString();
        Id = in.readString();
        DisplayCategoryBreadcrumb = in.readString();
    }

    public static final Creator<ProductsInCategoryResponse> CREATOR = new Creator<ProductsInCategoryResponse>() {
        @Override
        public ProductsInCategoryResponse createFromParcel(Parcel in) {
            return new ProductsInCategoryResponse(in);
        }

        @Override
        public ProductsInCategoryResponse[] newArray(int size) {
            return new ProductsInCategoryResponse[size];
        }
    };

    public ArrayList<Products> getProducts() {
        return Products;
    }

    public void setProducts(ArrayList<Products> Products) {
        this.Products = Products;
    }

    public String[] getCategoryBreadcrumb() {
        return CategoryBreadcrumb;
    }

    public void setCategoryBreadcrumb(String[] CategoryBreadcrumb) {
        this.CategoryBreadcrumb = CategoryBreadcrumb;
    }

    public String[] getFeaturedProducts() {
        return FeaturedProducts;
    }

    public void setFeaturedProducts(String[] FeaturedProducts) {
        this.FeaturedProducts = FeaturedProducts;
    }


    public PagingFilteringContext getPagingFilteringContext() {
        return PagingFilteringContext;
    }

    public void setPagingFilteringContext(PagingFilteringContext PagingFilteringContext) {
        this.PagingFilteringContext = PagingFilteringContext;
    }

    public String getName() {
        if (Name != null)
            return Name;
        else
            return "";
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

    public com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse.CustomProperties getCustomProperties() {

        return CustomProperties;
    }

    public void setCustomProperties(com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse.CustomProperties customProperties) {
        CustomProperties = customProperties;
    }

    public SubCategories[] getSubCategories() {
        return SubCategories;
    }

    public void setSubCategories(SubCategories[] SubCategories) {
        this.SubCategories = SubCategories;
    }

    public String getDisplayCategoryBreadcrumb() {
        return DisplayCategoryBreadcrumb;
    }

    public void setDisplayCategoryBreadcrumb(String DisplayCategoryBreadcrumb) {
        this.DisplayCategoryBreadcrumb = DisplayCategoryBreadcrumb;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
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

    public String getMetaTitle() {
        return MetaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        MetaTitle = metaTitle;
    }

    @Override
    public String toString() {
        return "ClassPojo [Description = " + Description + ", Products = " + Products + ", CategoryBreadcrumb = " + CategoryBreadcrumb + ", FeaturedProducts = " + FeaturedProducts + ", MetaDescription = " + MetaDescription + ", MetaKeywords = " + MetaKeywords + ", PagingFilteringContext = " + PagingFilteringContext + ", Name = " + Name + ", SeName = " + SeName + ", PictureModel = " + PictureModel + ", MetaTitle = " + MetaTitle + ", Id = " + Id + ", CustomProperties = " + CustomProperties + ", SubCategories = " + SubCategories + ", DisplayCategoryBreadcrumb = " + DisplayCategoryBreadcrumb + "]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Description);
        dest.writeStringArray(CategoryBreadcrumb);
        dest.writeStringArray(FeaturedProducts);
        dest.writeString(MetaDescription);
        dest.writeString(MetaKeywords);
        dest.writeString(Name);
        dest.writeString(SeName);
        dest.writeString(MetaTitle);
        dest.writeString(Id);
        dest.writeString(DisplayCategoryBreadcrumb);
    }
}
