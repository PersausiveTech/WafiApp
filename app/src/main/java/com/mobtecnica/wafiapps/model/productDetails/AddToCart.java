package com.mobtecnica.wafiapps.model.productDetails;

/**
 * Created by SIby on 20-02-2017.
 */

public class AddToCart {private String IsRental;

    private String AvailableForPreOrder;

    private String PreOrderAvailabilityStartDateTimeUtc;

    private String UpdateShoppingCartItemType;

    private String ProductId;

    private String DisableBuyButton;

//    private String[] AllowedQuantities;

    private String CustomerEnteredPriceRange;

//    private String CustomProperties;

    private String EnteredQuantity;

    private String MinimumQuantityNotification;

    private String UpdatedShoppingCartItemId;

    private String CustomerEnteredPrice;

    private String DisableWishlistButton;

    private String CustomerEntersPrice;

    public String getPreOrderAvailabilityStartDateTimeUtc() {
        return PreOrderAvailabilityStartDateTimeUtc;
    }

    public void setPreOrderAvailabilityStartDateTimeUtc(String preOrderAvailabilityStartDateTimeUtc) {
        PreOrderAvailabilityStartDateTimeUtc = preOrderAvailabilityStartDateTimeUtc;
    }

    public String getUpdateShoppingCartItemType() {
        return UpdateShoppingCartItemType;
    }

    public void setUpdateShoppingCartItemType(String updateShoppingCartItemType) {
        UpdateShoppingCartItemType = updateShoppingCartItemType;
    }

    public String getCustomerEnteredPriceRange() {
        return CustomerEnteredPriceRange;
    }

    public void setCustomerEnteredPriceRange(String customerEnteredPriceRange) {
        CustomerEnteredPriceRange = customerEnteredPriceRange;
    }

    public String getMinimumQuantityNotification() {
        return MinimumQuantityNotification;
    }

    public void setMinimumQuantityNotification(String minimumQuantityNotification) {
        MinimumQuantityNotification = minimumQuantityNotification;
    }

    public String getIsRental ()
    {
        return IsRental;
    }

    public void setIsRental (String IsRental)
    {
        this.IsRental = IsRental;
    }

    public String getAvailableForPreOrder ()
    {
        return AvailableForPreOrder;
    }

    public void setAvailableForPreOrder (String AvailableForPreOrder)
    {
        this.AvailableForPreOrder = AvailableForPreOrder;
    }



    public String getProductId ()
    {
        return ProductId;
    }

    public void setProductId (String ProductId)
    {
        this.ProductId = ProductId;
    }

    public String getDisableBuyButton ()
    {
        return DisableBuyButton;
    }

    public void setDisableBuyButton (String DisableBuyButton)
    {
        this.DisableBuyButton = DisableBuyButton;
    }

//    public String[] getAllowedQuantities ()
//    {
//        return AllowedQuantities;
//    }
//
//    public void setAllowedQuantities (String[] AllowedQuantities)
//    {
//        this.AllowedQuantities = AllowedQuantities;
//    }

//
//    public String getCustomProperties ()
//    {
//        return CustomProperties;
//    }
//
//    public void setCustomProperties (String CustomProperties)
//    {
//        this.CustomProperties = CustomProperties;
//    }

    public String getEnteredQuantity ()
    {
        return EnteredQuantity;
    }

    public void setEnteredQuantity (String EnteredQuantity)
    {
        this.EnteredQuantity = EnteredQuantity;
    }



    public String getUpdatedShoppingCartItemId ()
    {
        return UpdatedShoppingCartItemId;
    }

    public void setUpdatedShoppingCartItemId (String UpdatedShoppingCartItemId)
    {
        this.UpdatedShoppingCartItemId = UpdatedShoppingCartItemId;
    }

    public String getCustomerEnteredPrice ()
    {
        return CustomerEnteredPrice;
    }

    public void setCustomerEnteredPrice (String CustomerEnteredPrice)
    {
        this.CustomerEnteredPrice = CustomerEnteredPrice;
    }

    public String getDisableWishlistButton ()
    {
        return DisableWishlistButton;
    }

    public void setDisableWishlistButton (String DisableWishlistButton)
    {
        this.DisableWishlistButton = DisableWishlistButton;
    }

    public String getCustomerEntersPrice ()
    {
        return CustomerEntersPrice;
    }

    public void setCustomerEntersPrice (String CustomerEntersPrice)
    {
        this.CustomerEntersPrice = CustomerEntersPrice;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [IsRental = "+IsRental+", AvailableForPreOrder = "+AvailableForPreOrder+", PreOrderAvailabilityStartDateTimeUtc = "+PreOrderAvailabilityStartDateTimeUtc+", UpdateShoppingCartItemType = "+UpdateShoppingCartItemType+", ProductId = "+ProductId+", DisableBuyButton = "+DisableBuyButton+", AllowedQuantities =  CustomerEnteredPriceRange = "+CustomerEnteredPriceRange+", CustomProperties =, EnteredQuantity = "+EnteredQuantity+", MinimumQuantityNotification = "+MinimumQuantityNotification+", UpdatedShoppingCartItemId = "+UpdatedShoppingCartItemId+", CustomerEnteredPrice = "+CustomerEnteredPrice+", DisableWishlistButton = "+DisableWishlistButton+", CustomerEntersPrice = "+CustomerEntersPrice+"]";
    }
}
