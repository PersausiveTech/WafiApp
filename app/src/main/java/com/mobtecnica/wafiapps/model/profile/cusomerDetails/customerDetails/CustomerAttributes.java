package com.mobtecnica.wafiapps.model.profile.cusomerDetails.customerDetails;

/**
 * Created by SIby on 22-02-2017.
 */

public class CustomerAttributes {
    private String[] Values;

    private String Name;

    private String IsRequired;

    private String DefaultValue;

    private String Id;

//    private String CustomProperties;

    private String AttributeControlType;

    public String[] getValues ()
    {
        return Values;
    }

    public void setValues (String[] Values)
    {
        this.Values = Values;
    }

    public String getName ()
    {
        return Name;
    }

    public void setName (String Name)
    {
        this.Name = Name;
    }

    public String getIsRequired ()
    {
        return IsRequired;
    }

    public void setIsRequired (String IsRequired)
    {
        this.IsRequired = IsRequired;
    }



    public String getId ()
    {
        return Id;
    }

    public void setId (String Id)
    {
        this.Id = Id;
    }

//    public String getCustomProperties ()
//    {
//        return CustomProperties;
//    }
//
//    public void setCustomProperties (String CustomProperties)
//    {
//        this.CustomProperties = CustomProperties;
//    }

    public String getAttributeControlType ()
    {
        return AttributeControlType;
    }

    public void setAttributeControlType (String AttributeControlType)
    {
        this.AttributeControlType = AttributeControlType;
    }

    public String getDefaultValue() {
        return DefaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        DefaultValue = defaultValue;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Values = "+Values+", Name = "+Name+", IsRequired = "+IsRequired+", DefaultValue = "+DefaultValue+", Id = "+Id+", CustomProperties=, AttributeControlType = "+AttributeControlType+"]";
    }
}
