package com.mobtecnica.wafiapps.model.wishlist.wishListResponse;

/**
 * Created by SIby on 19-04-2017.
 */

public class WishListResponse {

    private String statusCode;

    private WishListData data;

    private String success;

    public String getStatusCode ()
    {
        return statusCode;
    }

    public void setStatusCode (String statusCode)
    {
        this.statusCode = statusCode;
    }

    public WishListData getData ()
    {
        return data;
    }

    public void setData (WishListData data)
    {
        this.data = data;
    }

    public String getSuccess ()
    {
        return success;
    }

    public void setSuccess (String success)
    {
        this.success = success;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [statusCode = "+statusCode+", data = "+data+", success = "+success+"]";
    }
}
