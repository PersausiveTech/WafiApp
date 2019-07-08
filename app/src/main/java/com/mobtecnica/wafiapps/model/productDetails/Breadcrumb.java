package com.mobtecnica.wafiapps.model.productDetails;

/**
 * Created by SIby on 20-02-2017.
 */

public class Breadcrumb {private String Enabled;

    private CategoryBreadcrumb[] CategoryBreadcrumb;

    private String ProductName;

  //  private String CustomProperties;

    private String ProductId;

    private String ProductSeName;

    public String getEnabled ()
    {
        return Enabled;
    }

    public void setEnabled (String Enabled)
    {
        this.Enabled = Enabled;
    }

    public CategoryBreadcrumb[] getCategoryBreadcrumb ()
    {
        return CategoryBreadcrumb;
    }

    public void setCategoryBreadcrumb (CategoryBreadcrumb[] CategoryBreadcrumb)
    {
        this.CategoryBreadcrumb = CategoryBreadcrumb;
    }

    public String getProductName ()
    {
        return ProductName;
    }

    public void setProductName (String ProductName)
    {
        this.ProductName = ProductName;
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

    public String getProductId ()
    {
        return ProductId;
    }

    public void setProductId (String ProductId)
    {
        this.ProductId = ProductId;
    }

    public String getProductSeName ()
    {
        return ProductSeName;
    }

    public void setProductSeName (String ProductSeName)
    {
        this.ProductSeName = ProductSeName;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Enabled = "+Enabled+", CategoryBreadcrumb = "+CategoryBreadcrumb+", ProductName = "+ProductName+", CustomProperties = ProductId = "+ProductId+", ProductSeName = "+ProductSeName+"]";
    }
}
