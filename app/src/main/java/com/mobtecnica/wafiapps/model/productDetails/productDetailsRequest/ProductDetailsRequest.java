package com.mobtecnica.wafiapps.model.productDetails.productDetailsRequest;

/**
 * Created by SIby on 20-02-2017.
 */

public class ProductDetailsRequest {
    private String apiToken;

    private String productId;

    public String getApiToken ()
    {
        return apiToken;
    }

    public void setApiToken (String apiToken)
    {
        this.apiToken = apiToken;
    }

    public String getProductId ()
    {
        return productId;
    }

    public void setProductId (String productId)
    {
        this.productId = productId;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [apiToken = "+apiToken+", productId = "+productId+"]";
    }
}
