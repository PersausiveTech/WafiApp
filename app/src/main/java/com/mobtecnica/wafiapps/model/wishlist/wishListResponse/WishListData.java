package com.mobtecnica.wafiapps.model.wishlist.wishListResponse;

import java.util.ArrayList;

/**
 * Created by SIby on 19-04-2017.
 */

public class WishListData {
    private ArrayList<WishItems> Items;

    private String DisplayTaxShippingInfo;

    private String DisplayAddToCart;

    private String ShowProductImages;

    private String ShowSku;

    private String CustomerFullname;

    private String CustomerGuid;

    private String IsEditable;

    private String CustomProperties;

    private String[] Warnings;

    private String EmailWishlistEnabled;

    public ArrayList<WishItems> getItems() {
        return Items;
    }

    public void setItems(ArrayList<WishItems> items) {
        Items = items;
    }

    public String getDisplayTaxShippingInfo ()
    {
        return DisplayTaxShippingInfo;
    }

    public void setDisplayTaxShippingInfo (String DisplayTaxShippingInfo)
    {
        this.DisplayTaxShippingInfo = DisplayTaxShippingInfo;
    }

    public String getDisplayAddToCart ()
    {
        return DisplayAddToCart;
    }

    public void setDisplayAddToCart (String DisplayAddToCart)
    {
        this.DisplayAddToCart = DisplayAddToCart;
    }

    public String getShowProductImages ()
    {
        return ShowProductImages;
    }

    public void setShowProductImages (String ShowProductImages)
    {
        this.ShowProductImages = ShowProductImages;
    }

    public String getShowSku ()
    {
        return ShowSku;
    }

    public void setShowSku (String ShowSku)
    {
        this.ShowSku = ShowSku;
    }

    public String getCustomerFullname ()
    {
        return CustomerFullname;
    }

    public void setCustomerFullname (String CustomerFullname)
    {
        this.CustomerFullname = CustomerFullname;
    }

    public String getCustomerGuid ()
    {
        return CustomerGuid;
    }

    public void setCustomerGuid (String CustomerGuid)
    {
        this.CustomerGuid = CustomerGuid;
    }

    public String getIsEditable ()
    {
        return IsEditable;
    }

    public void setIsEditable (String IsEditable)
    {
        this.IsEditable = IsEditable;
    }

    public String getCustomProperties ()
    {
        return CustomProperties;
    }

    public void setCustomProperties (String CustomProperties)
    {
        this.CustomProperties = CustomProperties;
    }

    public String[] getWarnings ()
    {
        return Warnings;
    }

    public void setWarnings (String[] Warnings)
    {
        this.Warnings = Warnings;
    }

    public String getEmailWishlistEnabled ()
    {
        return EmailWishlistEnabled;
    }

    public void setEmailWishlistEnabled (String EmailWishlistEnabled)
    {
        this.EmailWishlistEnabled = EmailWishlistEnabled;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Items = "+Items+", DisplayTaxShippingInfo = "+DisplayTaxShippingInfo+", DisplayAddToCart = "+DisplayAddToCart+", ShowProductImages = "+ShowProductImages+", ShowSku = "+ShowSku+", CustomerFullname = "+CustomerFullname+", CustomerGuid = "+CustomerGuid+", IsEditable = "+IsEditable+", CustomProperties = "+CustomProperties+", Warnings = "+Warnings+", EmailWishlistEnabled = "+EmailWishlistEnabled+"]";
    }
}
