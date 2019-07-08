package com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse;

public class NotFilteredItems {
    private String SpecificationAttributeId;
    private String SpecificationAttributeOptionId;
    private String SpecificationAttributeName;
    private String SpecificationAttributeOptionName;
    private String SpecificationAttributeOptionColorRgb;
    private String FilterUrl;

    private boolean isSelected = false;//for parent (left)
    private boolean isOptionsSelected = false;//for child(right)
    private boolean isOptionItemSelected = false;//to notify parent

    public boolean isOptionItemSelected() {
        return isOptionItemSelected;
    }

    public void setOptionItemSelected(boolean optionItemSelected) {
        isOptionItemSelected = optionItemSelected;
    }


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isOptionsSelected() {
        return isOptionsSelected;
    }

    public void setOptionsSelected(boolean optionsSelected) {
        isOptionsSelected = optionsSelected;
    }

    public String getSpecificationAttributeId() {
        return SpecificationAttributeId;
    }

    public void setSpecificationAttributeId(String specificationAttributeId) {
        SpecificationAttributeId = specificationAttributeId;
    }

    public String getSpecificationAttributeOptionId() {
        return SpecificationAttributeOptionId;
    }

    public void setSpecificationAttributeOptionId(String specificationAttributeOptionId) {
        SpecificationAttributeOptionId = specificationAttributeOptionId;
    }

    public String getSpecificationAttributeName() {
        return SpecificationAttributeName;
    }

    public void setSpecificationAttributeName(String specificationAttributeName) {
        SpecificationAttributeName = specificationAttributeName;
    }

    public String getSpecificationAttributeOptionName() {
        return SpecificationAttributeOptionName;
    }

    public void setSpecificationAttributeOptionName(String specificationAttributeOptionName) {
        SpecificationAttributeOptionName = specificationAttributeOptionName;
    }

    public String getSpecificationAttributeOptionColorRgb() {
        return SpecificationAttributeOptionColorRgb;
    }

    public void setSpecificationAttributeOptionColorRgb(String specificationAttributeOptionColorRgb) {
        SpecificationAttributeOptionColorRgb = specificationAttributeOptionColorRgb;
    }

    public String getFilterUrl() {
        return FilterUrl;
    }

    public void setFilterUrl(String filterUrl) {
        FilterUrl = filterUrl;
    }

    public com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse.CustomProperties getCustomProperties() {
        return CustomProperties;
    }

    public void setCustomProperties(com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse.CustomProperties customProperties) {
        CustomProperties = customProperties;
    }

    private CustomProperties CustomProperties;

}
