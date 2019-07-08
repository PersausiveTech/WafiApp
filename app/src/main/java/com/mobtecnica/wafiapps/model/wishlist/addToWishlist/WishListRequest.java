package com.mobtecnica.wafiapps.model.wishlist.addToWishlist;

import com.mobtecnica.wafiapps.utils.Constants;

/**
 * Created by SIby on 23-02-2017.
 */

public class WishListRequest {

    private String guid;

    private String shoppingCartTypeId;

    private String apiToken;

    private String enteredQuantity;

    private UpdateModel updateModel;

    private String productId;
    public WishListRequest() {}
    public WishListRequest(String productId, String guid) {
        setGuid(guid);
        setApiToken(Constants.API_TOKEN);
        setProductId(productId);
        setShoppingCartTypeId("2");
        setEnteredQuantity("1");
    }

    public String getGuid ()
    {
        return guid;
    }

    public void setGuid (String guid)
    {
        this.guid = guid;
    }

    public String getShoppingCartTypeId ()
    {
        return shoppingCartTypeId;
    }

    public void setShoppingCartTypeId (String shoppingCartTypeId)
    {
        this.shoppingCartTypeId = shoppingCartTypeId;
    }

    public String getApiToken ()
    {
        return apiToken;
    }

    public void setApiToken (String apiToken)
    {
        this.apiToken = apiToken;
    }

    public String getEnteredQuantity ()
    {
        return enteredQuantity;
    }

    public void setEnteredQuantity (String enteredQuantity)
    {
        this.enteredQuantity = enteredQuantity;
    }

    public UpdateModel getUpdateModel ()
    {
        return updateModel;
    }

    public void setUpdateModel (UpdateModel updateModel)
    {
        this.updateModel = updateModel;
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
        return "ClassPojo [guid = "+guid+", shoppingCartTypeId = "+shoppingCartTypeId+", apiToken = "+apiToken+", enteredQuantity = "+enteredQuantity+", updateModel = "+updateModel+", productId = "+productId+"]";
    }
}
