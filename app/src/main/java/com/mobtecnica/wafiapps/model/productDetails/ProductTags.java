package com.mobtecnica.wafiapps.model.productDetails;

/**
 * Created by SIby on 20-02-2017.
 */

public class ProductTags {private String Name;

    private String SeName;

    private String Id;

//    private String CustomProperties;

    private String ProductCount;

    public String getName ()
    {
        return Name;
    }

    public void setName (String Name)
    {
        this.Name = Name;
    }

    public String getSeName ()
    {
        return SeName;
    }

    public void setSeName (String SeName)
    {
        this.SeName = SeName;
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

    public String getProductCount ()
    {
        return ProductCount;
    }

    public void setProductCount (String ProductCount)
    {
        this.ProductCount = ProductCount;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Name = "+Name+", SeName = "+SeName+", Id = "+Id+", CustomProperties ProductCount = "+ProductCount+"]";
    }
}
