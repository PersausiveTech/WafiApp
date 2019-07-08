
package com.mobtecnica.wafiapps.model.checkout.savePaymentInfo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("Sku")
    @Expose
    private String sku;
    @SerializedName("Picture")
    @Expose
    private Picture picture;
    @SerializedName("ProductId")
    @Expose
    private Integer productId;
    @SerializedName("ProductName")
    @Expose
    private String productName;
    @SerializedName("ProductSeName")
    @Expose
    private String productSeName;
    @SerializedName("UnitPrice")
    @Expose
    private String unitPrice;
    @SerializedName("SubTotal")
    @Expose
    private String subTotal;
    @SerializedName("Discount")
    @Expose
    private String discount;
    @SerializedName("Quantity")
    @Expose
    private Integer quantity;
    @SerializedName("AllowedQuantities")
    @Expose
    private List<Object> allowedQuantities = null;
    @SerializedName("AttributeInfo")
    @Expose
    private String attributeInfo;
    @SerializedName("RecurringInfo")
    @Expose
    private String recurringInfo;
    @SerializedName("RentalInfo")
    @Expose
    private String rentalInfo;
    @SerializedName("AllowItemEditing")
    @Expose
    private Boolean allowItemEditing;
    @SerializedName("Warnings")
    @Expose
    private List<Object> warnings = null;
    @SerializedName("Id")
    @Expose
    private Integer id;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
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

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<Object> getAllowedQuantities() {
        return allowedQuantities;
    }

    public void setAllowedQuantities(List<Object> allowedQuantities) {
        this.allowedQuantities = allowedQuantities;
    }

    public String getAttributeInfo() {
        return attributeInfo;
    }

    public void setAttributeInfo(String attributeInfo) {
        this.attributeInfo = attributeInfo;
    }

    public String getRecurringInfo() {
        return recurringInfo;
    }

    public void setRecurringInfo(String recurringInfo) {
        this.recurringInfo = recurringInfo;
    }

    public String getRentalInfo() {
        return rentalInfo;
    }

    public void setRentalInfo(String rentalInfo) {
        this.rentalInfo = rentalInfo;
    }

    public Boolean getAllowItemEditing() {
        return allowItemEditing;
    }

    public void setAllowItemEditing(Boolean allowItemEditing) {
        this.allowItemEditing = allowItemEditing;
    }

    public List<Object> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<Object> warnings) {
        this.warnings = warnings;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
