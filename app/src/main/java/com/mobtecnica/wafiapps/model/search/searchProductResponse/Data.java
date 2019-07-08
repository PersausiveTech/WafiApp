package com.mobtecnica.wafiapps.model.search.searchProductResponse;

/**
 * Created by SIby on 02-03-2017.
 */
public class Data {

    private Products[] Products;

    private String Warning;

    private String asv;

    private com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse.PagingFilteringContext PagingFilteringContext;

    private String[] AvailableVendors;

    private String isc;

    private String SearchInBrand;

    private String SearchInCategory;

    private String vid;

    private String adv;

    private String PriceFrom;

    private String sid;

    private String OrderBy;

    private String PriceTo;

    private String AdvancedSearch;

    private String SearchInDescriptions;

    private String cid;

    private String NoResults;

    private AvailableCategories[] AvailableCategories;

    private String pf;

    private AvailableManufacturers[] AvailableManufacturers;

    private String q;

    private String SearchInSubCategories;

    private String pt;

    private CustomProperties CustomProperties;

    private String mid;

    public Products[] getProducts() {
        return Products;
    }

    public void setProducts(Products[] products) {
        Products = products;
    }

    public String getWarning() {
        return Warning;
    }

    public void setWarning(String warning) {
        Warning = warning;
    }

    public String getAsv() {
        return asv;
    }

    public void setAsv(String asv) {
        this.asv = asv;
    }

    public com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse.PagingFilteringContext getPagingFilteringContext() {
        return PagingFilteringContext;
    }

    public void setPagingFilteringContext(com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse.PagingFilteringContext pagingFilteringContext) {
        PagingFilteringContext = pagingFilteringContext;
    }

    public String[] getAvailableVendors() {
        return AvailableVendors;
    }

    public void setAvailableVendors(String[] availableVendors) {
        AvailableVendors = availableVendors;
    }

    public String getIsc() {
        return isc;
    }

    public void setIsc(String isc) {
        this.isc = isc;
    }

    public String getSearchInBrand() {
        return SearchInBrand;
    }

    public void setSearchInBrand(String searchInBrand) {
        SearchInBrand = searchInBrand;
    }

    public String getSearchInCategory() {
        return SearchInCategory;
    }

    public void setSearchInCategory(String searchInCategory) {
        SearchInCategory = searchInCategory;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getAdv() {
        return adv;
    }

    public void setAdv(String adv) {
        this.adv = adv;
    }

    public String getPriceFrom() {
        return PriceFrom;
    }

    public void setPriceFrom(String priceFrom) {
        PriceFrom = priceFrom;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getOrderBy() {
        return OrderBy;
    }

    public void setOrderBy(String orderBy) {
        OrderBy = orderBy;
    }

    public String getPriceTo() {
        return PriceTo;
    }

    public void setPriceTo(String priceTo) {
        PriceTo = priceTo;
    }

    public String getAdvancedSearch() {
        return AdvancedSearch;
    }

    public void setAdvancedSearch(String advancedSearch) {
        AdvancedSearch = advancedSearch;
    }

    public String getSearchInDescriptions() {
        return SearchInDescriptions;
    }

    public void setSearchInDescriptions(String searchInDescriptions) {
        SearchInDescriptions = searchInDescriptions;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getNoResults() {
        return NoResults;
    }

    public void setNoResults(String noResults) {
        NoResults = noResults;
    }

    public AvailableCategories[] getAvailableCategories() {
        return AvailableCategories;
    }

    public void setAvailableCategories(AvailableCategories[] availableCategories) {
        AvailableCategories = availableCategories;
    }

    public String getPf() {
        return pf;
    }

    public void setPf(String pf) {
        this.pf = pf;
    }

    public AvailableManufacturers[] getAvailableManufacturers() {
        return AvailableManufacturers;
    }

    public void setAvailableManufacturers(AvailableManufacturers[] availableManufacturers) {
        AvailableManufacturers = availableManufacturers;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getSearchInSubCategories() {
        return SearchInSubCategories;
    }

    public void setSearchInSubCategories(String searchInSubCategories) {
        SearchInSubCategories = searchInSubCategories;
    }

    public String getPt() {
        return pt;
    }

    public void setPt(String pt) {
        this.pt = pt;
    }

    public CustomProperties getCustomProperties() {
        return CustomProperties;
    }

    public void setCustomProperties(CustomProperties customProperties) {
        CustomProperties = customProperties;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Products = "+Products+", Warning = "+Warning+", asv = "+asv+", PagingFilteringContext = "+PagingFilteringContext+", AvailableVendors = "+AvailableVendors+", isc = "+isc+", SearchInBrand = "+SearchInBrand+", SearchInCategory = "+SearchInCategory+", vid = "+vid+", adv = "+adv+", PriceFrom = "+PriceFrom+", sid = "+sid+", OrderBy = "+OrderBy+", PriceTo = "+PriceTo+", AdvancedSearch = "+AdvancedSearch+", SearchInDescriptions = "+SearchInDescriptions+", cid = "+cid+", NoResults = "+NoResults+", AvailableCategories = "+AvailableCategories+", pf = "+pf+", AvailableManufacturers = "+AvailableManufacturers+", q = "+q+", SearchInSubCategories = "+SearchInSubCategories+", pt = "+pt+", CustomProperties = "+CustomProperties+", mid = "+mid+"]";
    }
}
