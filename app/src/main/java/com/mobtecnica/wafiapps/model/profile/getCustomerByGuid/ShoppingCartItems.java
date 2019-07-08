package com.mobtecnica.wafiapps.model.profile.getCustomerByGuid;

/**
 * Created by SIby on 10-01-2017.
 */


public class ShoppingCartItems
{
    private String IsFreeShipping;

    private String Quantity;

    private String AdditionalShippingCharge;

    private String IsTaxExempt;

    private String AttributesXml;

    private String UpdatedOnUtc;

    private String ShoppingCartTypeId;

    private String ProductId;

    private String IsShipEnabled;

    private String CreatedOnUtc;

    private String StoreId;

    private String CustomerId;

    private String Id;

    private String CustomerEnteredPrice;

    public String getIsFreeShipping ()
    {
        return IsFreeShipping;
    }

    public void setIsFreeShipping (String IsFreeShipping)
    {
        this.IsFreeShipping = IsFreeShipping;
    }

    public String getQuantity ()
    {
        return Quantity;
    }

    public void setQuantity (String Quantity)
    {
        this.Quantity = Quantity;
    }

    public String getAdditionalShippingCharge ()
    {
        return AdditionalShippingCharge;
    }

    public void setAdditionalShippingCharge (String AdditionalShippingCharge)
    {
        this.AdditionalShippingCharge = AdditionalShippingCharge;
    }

    public String getIsTaxExempt ()
    {
        return IsTaxExempt;
    }

    public void setIsTaxExempt (String IsTaxExempt)
    {
        this.IsTaxExempt = IsTaxExempt;
    }

    public String getAttributesXml ()
    {
        return AttributesXml;
    }

    public void setAttributesXml (String AttributesXml)
    {
        this.AttributesXml = AttributesXml;
    }

    public String getUpdatedOnUtc ()
    {
        return UpdatedOnUtc;
    }

    public void setUpdatedOnUtc (String UpdatedOnUtc)
    {
        this.UpdatedOnUtc = UpdatedOnUtc;
    }

    public String getShoppingCartTypeId ()
    {
        return ShoppingCartTypeId;
    }

    public void setShoppingCartTypeId (String ShoppingCartTypeId)
    {
        this.ShoppingCartTypeId = ShoppingCartTypeId;
    }

    public String getProductId ()
    {
        return ProductId;
    }

    public void setProductId (String ProductId)
    {
        this.ProductId = ProductId;
    }

    public String getIsShipEnabled ()
    {
        return IsShipEnabled;
    }

    public void setIsShipEnabled (String IsShipEnabled)
    {
        this.IsShipEnabled = IsShipEnabled;
    }

    public String getCreatedOnUtc ()
    {
        return CreatedOnUtc;
    }

    public void setCreatedOnUtc (String CreatedOnUtc)
    {
        this.CreatedOnUtc = CreatedOnUtc;
    }

    public String getStoreId ()
    {
        return StoreId;
    }

    public void setStoreId (String StoreId)
    {
        this.StoreId = StoreId;
    }

    public String getCustomerId ()
    {
        return CustomerId;
    }

    public void setCustomerId (String CustomerId)
    {
        this.CustomerId = CustomerId;
    }

    public String getId ()
    {
        return Id;
    }

    public void setId (String Id)
    {
        this.Id = Id;
    }

    public String getCustomerEnteredPrice ()
    {
        return CustomerEnteredPrice;
    }

    public void setCustomerEnteredPrice (String CustomerEnteredPrice)
    {
        this.CustomerEnteredPrice = CustomerEnteredPrice;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [IsFreeShipping = "+IsFreeShipping+", Quantity = "+Quantity+", AdditionalShippingCharge = "+AdditionalShippingCharge+", IsTaxExempt = "+IsTaxExempt+", AttributesXml = "+AttributesXml+", UpdatedOnUtc = "+UpdatedOnUtc+", ShoppingCartTypeId = "+ShoppingCartTypeId+", ProductId = "+ProductId+", IsShipEnabled = "+IsShipEnabled+", CreatedOnUtc = "+CreatedOnUtc+", StoreId = "+StoreId+", CustomerId = "+CustomerId+", Id = "+Id+", CustomerEnteredPrice = "+CustomerEnteredPrice+"]";
    }
}

