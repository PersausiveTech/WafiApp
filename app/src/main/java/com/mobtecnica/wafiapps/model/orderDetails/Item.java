
package com.mobtecnica.wafiapps.model.orderDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("OrderItemGuid")
    @Expose
    public String orderItemGuid;
    @SerializedName("Sku")
    @Expose
    public String sku;
    @SerializedName("ProductId")
    @Expose
    public Integer productId;
    @SerializedName("ProductName")
    @Expose
    public String productName;
    @SerializedName("ProductSeName")
    @Expose
    public String productSeName;
    @SerializedName("UnitPrice")
    @Expose
    public String unitPrice;
    @SerializedName("SubTotal")
    @Expose
    public String subTotal;
    @SerializedName("Quantity")
    @Expose
    public Integer quantity;
    @SerializedName("AttributeInfo")
    @Expose
    public String attributeInfo;
    @SerializedName("RentalInfo")
    @Expose
    public Object rentalInfo;
    @SerializedName("DownloadId")
    @Expose
    public Integer downloadId;
    @SerializedName("LicenseId")
    @Expose
    public Integer licenseId;
    @SerializedName("Id")
    @Expose
    public Integer id;

    public String getOrderItemGuid() {
        return orderItemGuid;
    }

    public void setOrderItemGuid(String orderItemGuid) {
        this.orderItemGuid = orderItemGuid;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSeName() {
        return productSeName;
    }

    public void setProductSeName(String productSeName) {
        this.productSeName = productSeName;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getAttributeInfo() {
        return attributeInfo;
    }

    public void setAttributeInfo(String attributeInfo) {
        this.attributeInfo = attributeInfo;
    }

    public Object getRentalInfo() {
        return rentalInfo;
    }

    public void setRentalInfo(Object rentalInfo) {
        this.rentalInfo = rentalInfo;
    }

    public Integer getDownloadId() {
        return downloadId;
    }

    public void setDownloadId(Integer downloadId) {
        this.downloadId = downloadId;
    }

    public Integer getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(Integer licenseId) {
        this.licenseId = licenseId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
