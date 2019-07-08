package com.mobtecnica.wafiapps.model.search.searchAutoComplete;

/**
 * Created by SIby on 02-03-2017.
 */

public class SearchAutoCompletionResponse {
    private String statusCode;

    private String data;

    private String success;

    public String getStatusCode ()
    {
        return statusCode;
    }

    public void setStatusCode (String statusCode)
    {
        this.statusCode = statusCode;
    }

    public String getData ()
    {
        return data;
    }

    public void setData (String data)
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
