package com.mobtecnica.wafiapps.model.search;

/**
 * Created by SIby on 02-03-2017.
 */

public class Model
{
    private String PriceFrom;

    private String q;

    private String PriceTo;

    private String SearchInSubCategories;

    private String AdvancedSearch;

    private String orderby;

    private String PageNumber;

    private String SearchInDescriptions;

    private String SearchInBrand;

    private String SearchInCategory;

    public String getPageNumber() {
        return PageNumber;
    }

    private String[] specs;
    private String[] filteredManufacturerIds;

    public String[] getSpecs() {
        return specs;
    }

    public void setSpecs(String[] specs) {
        this.specs = specs;
    }

    public String[] getFilteredManufacturerIds() {
        return filteredManufacturerIds;
    }

    public void setFilteredManufacturerIds(String[] filteredManufacturerIds) {
        this.filteredManufacturerIds = filteredManufacturerIds;
    }

    public void setPageNumber(String PageNumber) {
        this.PageNumber = PageNumber;
    }


    public String getPriceFrom ()
    {
        return PriceFrom;
    }

    public void setPriceFrom (String PriceFrom)
    {
        this.PriceFrom = PriceFrom;
    }

    public String getQ ()
    {
        return q;
    }

    public void setQ (String q)
    {
        this.q = q;
    }

    public String getPriceTo ()
    {
        return PriceTo;
    }

    public void setPriceTo (String PriceTo)
    {
        this.PriceTo = PriceTo;
    }

    public String getSearchInSubCategories ()
    {
        return SearchInSubCategories;
    }

    public void setSearchInSubCategories (String SearchInSubCategories)
    {
        this.SearchInSubCategories = SearchInSubCategories;
    }

    public String getAdvancedSearch ()
    {
        return AdvancedSearch;
    }

    public void setAdvancedSearch (String AdvancedSearch)
    {
        this.AdvancedSearch = AdvancedSearch;
    }

    public String getOrderby ()
    {
        return orderby;
    }

    public void setOrderby (String orderby)
    {
        this.orderby = orderby;
    }

    public String getSearchInDescriptions ()
    {
        return SearchInDescriptions;
    }

    public void setSearchInDescriptions (String SearchInDescriptions)
    {
        this.SearchInDescriptions = SearchInDescriptions;
    }

    public String getSearchInBrand ()
    {
        return SearchInBrand;
    }

    public void setSearchInBrand (String SearchInBrand)
    {
        this.SearchInBrand = SearchInBrand;
    }

    public String getSearchInCategory ()
    {
        return SearchInCategory;
    }

    public void setSearchInCategory (String SearchInCategory)
    {
        this.SearchInCategory = SearchInCategory;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [PriceFrom = "+PriceFrom+", q = "+q+", PriceTo = "+PriceTo+", SearchInSubCategories = "+SearchInSubCategories+", AdvancedSearch = "+AdvancedSearch+", orderby = "+orderby+", PageNumber = "+PageNumber+", SearchInDescriptions = "+SearchInDescriptions+", SearchInBrand = "+SearchInBrand+", SearchInCategory = "+SearchInCategory+"]";
    }
}
