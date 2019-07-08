package com.mobtecnica.wafiapps.model.productsInManufacturer;

import com.mobtecnica.wafiapps.model.BaseUserRequest;
import com.mobtecnica.wafiapps.utils.Constants;

public class ProductsInManufacturerRequest extends BaseUserRequest {
    private ManufacturerModel manufacturerModel;
    private String manufacturerId;
    public ProductsInManufacturerRequest() {
        super();
    }

    public ProductsInManufacturerRequest(String manufacturerId, int pageNumber, int sortOrderType,String[] data,String[] manufcaspec) {
        super();
        ManufacturerModel model = new ManufacturerModel();
        setManufacturerId(manufacturerId);
        model.setOrderby(sortOrderType + "");
        model.setFrom("0");
        model.setTo("0");
        model.setPageNumber(pageNumber + "");
        //String[] strings = {"0"};
        model.setSpecs(data);
        model.setFilteredManufacturerIds(manufcaspec);
        setManufacturerModel(model);
    }

    public ManufacturerModel getManufacturerModel() {
        return manufacturerModel;
    }

    public void setManufacturerModel(ManufacturerModel manufacturerModel) {
        this.manufacturerModel = manufacturerModel;
    }

    public String getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }
}
