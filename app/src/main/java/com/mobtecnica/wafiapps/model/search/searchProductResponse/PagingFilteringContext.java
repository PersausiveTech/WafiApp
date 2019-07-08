package com.mobtecnica.wafiapps.model.search.searchProductResponse;

import com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse.ManufacturerFilter;

/**
 * Created by SIby on 02-03-2017.
 */

public class PagingFilteringContext {

    private PriceRangeFilter PriceRangeFilter;

    private String PageNumber;

    private String AllowProductViewModeChanging;

    private com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse.SpecificationFilter SpecificationFilter;

    private com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse.ManufacturerFilter ManufacturerFilter;

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

    public PriceRangeFilter getPriceRangeFilter() {
        return PriceRangeFilter;
    }

    public void setPriceRangeFilter(PriceRangeFilter priceRangeFilter) {
        PriceRangeFilter = priceRangeFilter;
    }

    public String getPageNumber() {
        return PageNumber;
    }

    public void setPageNumber(String pageNumber) {
        PageNumber = pageNumber;
    }

    public String getAllowProductViewModeChanging() {
        return AllowProductViewModeChanging;
    }

    public void setAllowProductViewModeChanging(String allowProductViewModeChanging) {
        AllowProductViewModeChanging = allowProductViewModeChanging;
    }

    public com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse.ManufacturerFilter getManufacturerFilter() {
        return ManufacturerFilter;
    }

    public void setManufacturerFilter(com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse.ManufacturerFilter manufacturerFilter) {
        ManufacturerFilter = manufacturerFilter;
    }

    public com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse.SpecificationFilter getSpecificationFilter() {
        return SpecificationFilter;
    }

    public void setSpecificationFilter(com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse.SpecificationFilter specificationFilter) {
        SpecificationFilter = specificationFilter;
    }

    public String getAllowCustomersToSelectPageSize() {
        return AllowCustomersToSelectPageSize;
    }

    public void setAllowCustomersToSelectPageSize(String allowCustomersToSelectPageSize) {
        AllowCustomersToSelectPageSize = allowCustomersToSelectPageSize;
    }

    public String getViewMode() {
        return ViewMode;
    }

    public void setViewMode(String viewMode) {
        ViewMode = viewMode;
    }

    public String getPageSize() {
        return PageSize;
    }

    public void setPageSize(String pageSize) {
        PageSize = pageSize;
    }

    public String getOrderBy() {
        return OrderBy;
    }

    public void setOrderBy(String orderBy) {
        OrderBy = orderBy;
    }

    public AvailableViewModes[] getAvailableViewModes() {
        return AvailableViewModes;
    }

    public void setAvailableViewModes(AvailableViewModes[] availableViewModes) {
        AvailableViewModes = availableViewModes;
    }

    public String getTotalItems() {
        return TotalItems;
    }

    public void setTotalItems(String totalItems) {
        TotalItems = totalItems;
    }

    public String getFirstItem() {
        return FirstItem;
    }

    public void setFirstItem(String firstItem) {
        FirstItem = firstItem;
    }

    public String getTotalPages() {
        return TotalPages;
    }

    public void setTotalPages(String totalPages) {
        TotalPages = totalPages;
    }

    public String getHasPreviousPage() {
        return HasPreviousPage;
    }

    public void setHasPreviousPage(String hasPreviousPage) {
        HasPreviousPage = hasPreviousPage;
    }

    public String getHasNextPage() {
        return HasNextPage;
    }

    public void setHasNextPage(String hasNextPage) {
        HasNextPage = hasNextPage;
    }

    public String getLastItem() {
        return LastItem;
    }

    public void setLastItem(String lastItem) {
        LastItem = lastItem;
    }

    public PageSizeOptions[] getPageSizeOptions() {
        return PageSizeOptions;
    }

    public void setPageSizeOptions(PageSizeOptions[] pageSizeOptions) {
        PageSizeOptions = pageSizeOptions;
    }

    public AvailableSortOptions[] getAvailableSortOptions() {
        return AvailableSortOptions;
    }

    public void setAvailableSortOptions(AvailableSortOptions[] availableSortOptions) {
        AvailableSortOptions = availableSortOptions;
    }

    public String getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(String pageIndex) {
        PageIndex = pageIndex;
    }

    public String getAllowProductSorting() {
        return AllowProductSorting;
    }

    public void setAllowProductSorting(String allowProductSorting) {
        AllowProductSorting = allowProductSorting;
    }
}
