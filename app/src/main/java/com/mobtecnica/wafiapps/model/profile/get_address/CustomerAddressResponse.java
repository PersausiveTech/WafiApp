package com.mobtecnica.wafiapps.model.profile.get_address;

/**
 * Created by SIby on 10-01-2017.
 */

public class CustomerAddressResponse
{
    private String statusCode;

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
