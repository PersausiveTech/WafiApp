package com.mobtecnica.wafiapps.model.productsInManufacturer;

/**
 * Created by SIby on 15-02-2017.
 */

public class ManufacturerModel {
    private String PageNumber;

    private String To;

    private String orderby;

    private String From;

    private String[] specs;
    private String[] filteredManufacturerIds;
    public String getPageNumber() {
        return PageNumber;
    }

    public void setPageNumber(String PageNumber) {
        this.PageNumber = PageNumber;
    }

    public String getTo() {
        return To;
    }

    public void setTo(String To) {
        this.To = To;
    }

    public String getOrderby() {
        return orderby;
    }

    public void setOrderby(String orderby) {
        this.orderby = orderby;
    }

    public String getFrom() {
        return From;
    }

    public void setFrom(String From) {
        this.From = From;
    }

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

    @Override
    public String toString() {
        return "ClassPojo [PageNumber = " + PageNumber + ", To = " + To + ", orderby = " + orderby + ", From = " + From + ", specs = " + specs + "]";
    }
}
