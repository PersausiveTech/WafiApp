
package com.mobtecnica.wafiapps.model.wishlist.newWishList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetWishListResponse {

    private String statusCode;

    private GetWishListData data;

    private String success;

    public String getStatusCode ()
    {
        return statusCode;
    }

    public void setStatusCode (String statusCode)
    {
        this.statusCode = statusCode;
    }

    public GetWishListData getData ()
    {
        return data;
    }

    public void setData (GetWishListData data)
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
