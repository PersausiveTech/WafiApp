package com.mobtecnica.wafiapps.model.cart.getCart.getCartResponse;

/**
 * Created by SIby on 20-03-2017.
 */

public class OrderTotals
{
    private String RequiresShipping;

    private String AllowRemovingSubTotalDiscount;

    private GiftCards[] GiftCards;

    private String WillEarnRewardPoints;

    private String DisplayTaxRates;

    private String Tax;

    private TaxRates[] TaxRates;

    private String SubTotalDiscount;

    private String RedeemedRewardPoints;

    private String AllowRemovingOrderTotalDiscount;

    private String OrderTotalDiscount;

    private String RedeemedRewardPointsAmount;

    private String SubTotal;

    private String OrderTotal;

    private String IsEditable;

    private String PaymentMethodAdditionalFee;

    private String Shipping;

    private String SelectedShippingMethod;

    private String DisplayTax;

    public String getRequiresShipping ()
    {
        return RequiresShipping;
    }

    public void setRequiresShipping (String RequiresShipping)
    {
        this.RequiresShipping = RequiresShipping;
    }

    public String getAllowRemovingSubTotalDiscount ()
    {
        return AllowRemovingSubTotalDiscount;
    }

    public void setAllowRemovingSubTotalDiscount (String AllowRemovingSubTotalDiscount)
    {
        this.AllowRemovingSubTotalDiscount = AllowRemovingSubTotalDiscount;
    }

    public GiftCards[] getGiftCards ()
    {
        return GiftCards;
    }

    public void setGiftCards(GiftCards[] GiftCards) {
        this.GiftCards = GiftCards;
    }

    public String getWillEarnRewardPoints ()
    {
        return WillEarnRewardPoints;
    }

    public void setWillEarnRewardPoints (String WillEarnRewardPoints)
    {
        this.WillEarnRewardPoints = WillEarnRewardPoints;
    }

    public String getDisplayTaxRates ()
    {
        return DisplayTaxRates;
    }

    public void setDisplayTaxRates (String DisplayTaxRates)
    {
        this.DisplayTaxRates = DisplayTaxRates;
    }

    public String getTax ()
    {
        return Tax;
    }

    public void setTax (String Tax)
    {
        this.Tax = Tax;
    }

    public TaxRates[] getTaxRates ()
    {
        return TaxRates;
    }

    public void setTaxRates (TaxRates[] TaxRates)
    {
        this.TaxRates = TaxRates;
    }



    public String getRedeemedRewardPoints ()
    {
        return RedeemedRewardPoints;
    }

    public void setRedeemedRewardPoints (String RedeemedRewardPoints)
    {
        this.RedeemedRewardPoints = RedeemedRewardPoints;
    }

    public String getAllowRemovingOrderTotalDiscount ()
    {
        return AllowRemovingOrderTotalDiscount;
    }

    public void setAllowRemovingOrderTotalDiscount (String AllowRemovingOrderTotalDiscount)
    {
        this.AllowRemovingOrderTotalDiscount = AllowRemovingOrderTotalDiscount;
    }

    public String getSubTotal ()
    {
        return SubTotal;
    }

    public void setSubTotal (String SubTotal)
    {
        this.SubTotal = SubTotal;
    }

    public String getOrderTotal ()
    {
        return OrderTotal;
    }

    public void setOrderTotal (String OrderTotal)
    {
        this.OrderTotal = OrderTotal;
    }

    public String getIsEditable ()
    {
        return IsEditable;
    }

    public void setIsEditable (String IsEditable)
    {
        this.IsEditable = IsEditable;
    }

    public String getSubTotalDiscount() {
        return SubTotalDiscount;
    }

    public void setSubTotalDiscount(String subTotalDiscount) {
        SubTotalDiscount = subTotalDiscount;
    }

    public String getOrderTotalDiscount() {
        return OrderTotalDiscount;
    }

    public void setOrderTotalDiscount(String orderTotalDiscount) {
        OrderTotalDiscount = orderTotalDiscount;
    }

    public String getRedeemedRewardPointsAmount() {
        return RedeemedRewardPointsAmount;
    }

    public void setRedeemedRewardPointsAmount(String redeemedRewardPointsAmount) {
        RedeemedRewardPointsAmount = redeemedRewardPointsAmount;
    }

    public String getPaymentMethodAdditionalFee() {
        return PaymentMethodAdditionalFee;
    }

    public void setPaymentMethodAdditionalFee(String paymentMethodAdditionalFee) {
        PaymentMethodAdditionalFee = paymentMethodAdditionalFee;
    }

    public String getSelectedShippingMethod() {
        return SelectedShippingMethod;
    }

    public void setSelectedShippingMethod(String selectedShippingMethod) {
        SelectedShippingMethod = selectedShippingMethod;
    }

    public String getShipping ()
    {
        return Shipping;
    }

    public void setShipping (String Shipping)
    {
        this.Shipping = Shipping;
    }

    public String getDisplayTax ()
    {
        return DisplayTax;
    }

    public void setDisplayTax (String DisplayTax)
    {
        this.DisplayTax = DisplayTax;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [RequiresShipping = "+RequiresShipping+", AllowRemovingSubTotalDiscount = "+AllowRemovingSubTotalDiscount+", GiftCards = "+GiftCards+", WillEarnRewardPoints = "+WillEarnRewardPoints+", DisplayTaxRates = "+DisplayTaxRates+", Tax = "+Tax+", TaxRates = "+TaxRates+", SubTotalDiscount = "+SubTotalDiscount+", RedeemedRewardPoints = "+RedeemedRewardPoints+", AllowRemovingOrderTotalDiscount = "+AllowRemovingOrderTotalDiscount+", OrderTotalDiscount = "+OrderTotalDiscount+", RedeemedRewardPointsAmount = "+RedeemedRewardPointsAmount+", SubTotal = "+SubTotal+", OrderTotal = "+OrderTotal+", IsEditable = "+IsEditable+", PaymentMethodAdditionalFee = "+PaymentMethodAdditionalFee+", Shipping = "+Shipping+", CustomProperties SelectedShippingMethod = "+SelectedShippingMethod+", DisplayTax = "+DisplayTax+"]";
    }
}
