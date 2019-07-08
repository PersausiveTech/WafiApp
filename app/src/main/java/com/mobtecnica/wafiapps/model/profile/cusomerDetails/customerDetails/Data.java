package com.mobtecnica.wafiapps.model.profile.cusomerDetails.customerDetails;

/**
 * Created by SIby on 22-02-2017.
 */

public class Data {
    private String guid;

    private CustomerInfo CustomerInfo;

    private String Email;

    private String customerid;

    public String getGuid ()
    {
        return guid;
    }

    public void setGuid (String guid)
    {
        this.guid = guid;
    }

    public CustomerInfo getCustomerInfo ()
    {
        return CustomerInfo;
    }

    public void setCustomerInfo (CustomerInfo CustomerInfo)
    {
        this.CustomerInfo = CustomerInfo;
    }

    public String getEmail ()
    {
        return Email;
    }

    public void setEmail (String Email)
    {
        this.Email = Email;
    }

    public String getCustomerid ()
    {
        return customerid;
    }

    public void setCustomerid (String customerid)
    {
        this.customerid = customerid;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [guid = "+guid+", CustomerInfo = "+CustomerInfo+", Email = "+Email+", customerid = "+customerid+"]";
    }
}
