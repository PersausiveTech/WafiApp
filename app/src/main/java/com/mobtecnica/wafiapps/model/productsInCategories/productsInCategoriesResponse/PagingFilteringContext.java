package com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse;

/**
 * Created by SIby on 16-02-2017.
 */

public class PagingFilteringContext {private PriceRangeFilter PriceRangeFilter;

    private String PageNumber;

    private String AllowProductViewModeChanging;

    private SpecificationFilter SpecificationFilter;

    private ManufacturerFilter ManufacturerFilter;

    private String AllowCustomersToSelectPageSize;

    private String ViewMode;

    private String PageSize;

    private String OrderBy;

    private AvailableViewModes[] AvailableViewModes;

    private String TotalItems;

    private String FirstItem;

    private String TotalPages;

    private String HasPreviousPage;

    private String HasNextPage;

    private String LastItem;

    private PageSizeOptions[] PageSizeOptions;

    private AvailableSortOptions[] AvailableSortOptions;

    private String PageIndex;

    private String AllowProductSorting;

    public com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse.ManufacturerFilter getManufacturerFilter() {
        return ManufacturerFilter;
    }

    public void setManufacturerFilter(com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse.ManufacturerFilter manufacturerFilter) {
        ManufacturerFilter = manufacturerFilter;
    }

    public PriceRangeFilter getPriceRangeFilter ()
    {
        return PriceRangeFilter;
    }

    public void setPriceRangeFilter (PriceRangeFilter PriceRangeFilter)
    {
        this.PriceRangeFilter = PriceRangeFilter;
    }

    public String getPageNumber ()
    {
        return PageNumber;
    }

    public void setPageNumber (String PageNumber)
    {
        this.PageNumber = PageNumber;
    }

    public String getAllowProductViewModeChanging ()
    {
        return AllowProductViewModeChanging;
    }

    public void setAllowProductViewModeChanging (String AllowProductViewModeChanging)
    {
        this.AllowProductViewModeChanging = AllowProductViewModeChanging;
    }

    public SpecificationFilter getSpecificationFilter ()
    {
        return SpecificationFilter;
    }

    public void setSpecificationFilter (SpecificationFilter SpecificationFilter)
    {
        this.SpecificationFilter = SpecificationFilter;
    }

    public String getAllowCustomersToSelectPageSize ()
    {
        return AllowCustomersToSelectPageSize;
    }

    public void setAllowCustomersToSelectPageSize (String AllowCustomersToSelectPageSize)
    {
        this.AllowCustomersToSelectPageSize = AllowCustomersToSelectPageSize;
    }

    public String getViewMode ()
    {
        return ViewMode;
    }

    public void setViewMode (String ViewMode)
    {
        this.ViewMode = ViewMode;
    }

    public String getPageSize ()
    {
        return PageSize;
    }

    public void setPageSize (String PageSize)
    {
        this.PageSize = PageSize;
    }

    public String getOrderBy() {
        return OrderBy;
    }

    public void setOrderBy(String orderBy) {
        OrderBy = orderBy;
    }

    public AvailableViewModes[] getAvailableViewModes ()
    {
        return AvailableViewModes;
    }

    public void setAvailableViewModes (AvailableViewModes[] AvailableViewModes)
    {
        this.AvailableViewModes = AvailableViewModes;
    }

    public String getTotalItems ()
    {
        return TotalItems;
    }

    public void setTotalItems (String TotalItems)
    {
        this.TotalItems = TotalItems;
    }

    public String getFirstItem ()
    {
        return FirstItem;
    }

    public void setFirstItem (String FirstItem)
    {
        this.FirstItem = FirstItem;
    }

    public String getTotalPages ()
    {
        return TotalPages;
    }

    public void setTotalPages (String TotalPages)
    {
        this.TotalPages = TotalPages;
    }

    public String getHasPreviousPage ()
    {
        return HasPreviousPage;
    }

    public void setHasPreviousPage (String HasPreviousPage)
    {
        this.HasPreviousPage = HasPreviousPage;
    }

    public String getHasNextPage ()
    {
        return HasNextPage;
    }

    public void setHasNextPage (String HasNextPage)
    {
        this.HasNextPage = HasNextPage;
    }

    public String getLastItem ()
    {
        return LastItem;
    }

    public void setLastItem (String LastItem)
    {
        this.LastItem = LastItem;
    }

    public PageSizeOptions[] getPageSizeOptions ()
    {
        return PageSizeOptions;
    }

    public void setPageSizeOptions (PageSizeOptions[] PageSizeOptions)
    {
        this.PageSizeOptions = PageSizeOptions;
    }

    public AvailableSortOptions[] getAvailableSortOptions ()
    {
        return AvailableSortOptions;
    }

    public void setAvailableSortOptions (AvailableSortOptions[] AvailableSortOptions)
    {
        this.AvailableSortOptions = AvailableSortOptions;
    }

    public String getPageIndex ()
    {
        return PageIndex;
    }

    public void setPageIndex (String PageIndex)
    {
        this.PageIndex = PageIndex;
    }

    public String getAllowProductSorting ()
    {
        return AllowProductSorting;
    }

    public void setAllowProductSorting (String AllowProductSorting)
    {
        this.AllowProductSorting = AllowProductSorting;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [PriceRangeFilter = "+PriceRangeFilter+", PageNumber = "+PageNumber+", AllowProductViewModeChanging = "+AllowProductViewModeChanging+", SpecificationFilter = "+SpecificationFilter+", AllowCustomersToSelectPageSize = "+AllowCustomersToSelectPageSize+", ViewMode = "+ViewMode+", PageSize = "+PageSize+", OrderBy = "+OrderBy+", AvailableViewModes = "+AvailableViewModes+", TotalItems = "+TotalItems+", FirstItem = "+FirstItem+", TotalPages = "+TotalPages+", HasPreviousPage = "+HasPreviousPage+", HasNextPage = "+HasNextPage+", LastItem = "+LastItem+", PageSizeOptions = "+PageSizeOptions+", AvailableSortOptions = "+AvailableSortOptions+", PageIndex = "+PageIndex+", AllowProductSorting = "+AllowProductSorting+"]";
    }
}
