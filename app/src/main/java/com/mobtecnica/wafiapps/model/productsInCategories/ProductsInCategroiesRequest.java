package com.mobtecnica.wafiapps.model.productsInCategories;

import com.mobtecnica.wafiapps.utils.Constants;

/**
 * Created by SIby on 15-02-2017.
 */

public class ProductsInCategroiesRequest {
    private CategoryModel categoryModel;

    private String apiToken;

    private String categoryId;

public ProductsInCategroiesRequest(){}

public ProductsInCategroiesRequest(String categoryId,int pageNumber,int sortOrderType,String[] data,String[] manufcaspec){
    CategoryModel model = new CategoryModel();
//        request.setData(this, category_id, pageNumber, sortOrderType,model);
    setApiToken(Constants.API_TOKEN);
    setCategoryId(categoryId);
    model.setOrderby(sortOrderType+"");
    model.setFrom("0");
    model.setTo("0");
    model.setPageNumber(pageNumber+"");
    model.setSpecs(data);
    model.setFilteredManufacturerIds(manufcaspec);
    setCategoryModel(model);
}
 public CategoryModel getCategoryModel() {
        return categoryModel;
    }

    public void setCategoryModel(CategoryModel categoryModel) {
        this.categoryModel = categoryModel;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "ClassPojo [categoryModel = " + categoryModel + ", apiToken = " + apiToken + ", categoryId = " + categoryId + "]";
    }
}