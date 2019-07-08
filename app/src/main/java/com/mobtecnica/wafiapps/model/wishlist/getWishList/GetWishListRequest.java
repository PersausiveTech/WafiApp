package com.mobtecnica.wafiapps.model.wishlist.getWishList;

/**
 * Created by SIby on 30-01-2017.
 */


public class GetWishListRequest
{
    private String guid;

    private String apiToken;

    private Item_quantity[] item_quantity;

    public String getGuid ()
    {
        return guid;
    }

    public void setGuid (String guid)
    {
        this.guid = guid;
    }

    public String getApiToken ()
    {
        return apiToken;
    }

    public void setApiToken (String apiToken)
    {
        this.apiToken = apiToken;
    }

    public Item_quantity[] getItem_quantity ()
    {
        return item_quantity;
    }

    public void setItem_quantity (Item_quantity[] item_quantity)
    {
        this.item_quantity = item_quantity;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [guid = "+guid+", apiToken = "+apiToken+", item_quantity = "+item_quantity+"]";
    }
}