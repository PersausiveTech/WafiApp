package com.mobtecnica.wafiapps.model.LaundryModel.priceLIst;

/**
 * Created by SIby on 03-04-2017.
 */

public class LstMensWear {
    private String ItemID;

    private String Menu;

    private String StdPrice;

    private String ServiceTypeID;

    private String ServiceID;

    private String PremPrice;

    private String ServiceType;

    private String Title;

    private String MenuID;

    public String getItemID ()
    {
        return ItemID;
    }

    public void setItemID (String ItemID)
    {
        this.ItemID = ItemID;
    }

    public String getMenu ()
    {
        return Menu;
    }

    public void setMenu (String Menu)
    {
        this.Menu = Menu;
    }

    public String getStdPrice ()
    {
        return StdPrice;
    }

    public void setStdPrice (String StdPrice)
    {
        this.StdPrice = StdPrice;
    }

    public String getServiceTypeID ()
    {
        return ServiceTypeID;
    }

    public void setServiceTypeID (String ServiceTypeID)
    {
        this.ServiceTypeID = ServiceTypeID;
    }

    public String getServiceID ()
    {
        return ServiceID;
    }

    public void setServiceID (String ServiceID)
    {
        this.ServiceID = ServiceID;
    }

    public String getPremPrice ()
    {
        return PremPrice;
    }

    public void setPremPrice (String PremPrice)
    {
        this.PremPrice = PremPrice;
    }

    public String getServiceType ()
    {
        return ServiceType;
    }

    public void setServiceType (String ServiceType)
    {
        this.ServiceType = ServiceType;
    }

    public String getTitle ()
    {
        return Title;
    }

    public void setTitle (String Title)
    {
        this.Title = Title;
    }

    public String getMenuID ()
    {
        return MenuID;
    }

    public void setMenuID (String MenuID)
    {
        this.MenuID = MenuID;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [ItemID = "+ItemID+", Menu = "+Menu+", StdPrice = "+StdPrice+", ServiceTypeID = "+ServiceTypeID+", ServiceID = "+ServiceID+", PremPrice = "+PremPrice+", ServiceType = "+ServiceType+", Title = "+Title+", MenuID = "+MenuID+"]";
    }
}
