package com.mobtecnica.wafiapps.model.profile.getCustomerByGuid;

/**
 * Created by SIby on 10-01-2017.
 */

public class CustomerRoles
{
    private String Name;

    private String SystemName;

    private String Id;

    public String getName ()
    {
        return Name;
    }

    public void setName (String Name)
    {
        this.Name = Name;
    }

    public String getSystemName ()
    {
        return SystemName;
    }

    public void setSystemName (String SystemName)
    {
        this.SystemName = SystemName;
    }

    public String getId ()
    {
        return Id;
    }

    public void setId (String Id)
    {
        this.Id = Id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Name = "+Name+", SystemName = "+SystemName+", Id = "+Id+"]";
    }
}

