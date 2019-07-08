package com.mobtecnica.wafiapps.model.productDetails;

/**
 * Created by SIby on 20-02-2017.
 */

public class ProductSpecifications { private String SpecificationAttributeId;

    private String ColorSquaresRgb;

    private String SpecificationAttributeName;

    private String ValueRaw;

//    private String CustomProperties;

    public String getSpecificationAttributeId ()
    {
        return SpecificationAttributeId;
    }

    public void setSpecificationAttributeId (String SpecificationAttributeId)
    {
        this.SpecificationAttributeId = SpecificationAttributeId;
    }

    public String getSpecificationAttributeName ()
    {
        return SpecificationAttributeName;
    }

    public void setSpecificationAttributeName (String SpecificationAttributeName)
    {
        this.SpecificationAttributeName = SpecificationAttributeName;
    }

    public String getValueRaw ()
    {
        return ValueRaw;
    }

    public void setValueRaw (String ValueRaw)
    {
        this.ValueRaw = ValueRaw;
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

    public String getColorSquaresRgb() {
        return ColorSquaresRgb;
    }

    public void setColorSquaresRgb(String colorSquaresRgb) {
        ColorSquaresRgb = colorSquaresRgb;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [SpecificationAttributeId = "+SpecificationAttributeId+", ColorSquaresRgb = "+ColorSquaresRgb+", SpecificationAttributeName = "+SpecificationAttributeName+", ValueRaw = "+ValueRaw+", CustomProperties ]";
    }
}
