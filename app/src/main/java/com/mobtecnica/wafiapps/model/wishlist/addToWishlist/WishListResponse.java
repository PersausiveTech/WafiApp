package com.mobtecnica.wafiapps.model.wishlist.addToWishlist;

/**
 * Created by SIby on 23-02-2017.
 */

public class WishListResponse {private String statusCode;

    private Data data;

    private String success;

    public String getStatusCode ()
    {
        return statusCode;
    }

    public void setStatusCode (String statusCode)
    {
        this.statusCode = statusCode;
    }

    public Data getData ()
    {
        return data;
    }

    public void setData (Data data)
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
