package com.mobtecnica.wafiapps.model.cart.addToCart;

import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.utils.Constants;

/**
 * Created by SIby on 22-02-2017.
 */

public class AddToCartRequest
{
    private String guid;

    private Model model;

    private String shoppingCartTypeId;

    private String apiToken;

    private String enteredQuantity;

    private String productId;

    public AddToCartRequest() {
    }

    public AddToCartRequest(String productId,String guid) {
        setApiToken(Constants.API_TOKEN);
        setEnteredQuantity("1");
        setGuid(guid);
        setProductId(productId);
        setShoppingCartTypeId("1");
    }
    public AddToCartRequest(String productId,String guid,String quantity,String shoppingCartTypeId) {
        setApiToken(Constants.API_TOKEN);
        setEnteredQuantity(quantity);
        setGuid(guid);
        setProductId(productId);
        setShoppingCartTypeId(shoppingCartTypeId);
    }
    public String getGuid ()
    {
        return guid;
    }

    public void setGuid (String guid)
    {
        this.guid = guid;
    }

    public Model getModel ()
    {
        return model;
    }

    public void setModel (Model model)
    {
        this.model = model;
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
        return "ClassPojo [guid = "+guid+", model = "+model+", shoppingCartTypeId = "+shoppingCartTypeId+", apiToken = "+apiToken+", enteredQuantity = "+enteredQuantity+", productId = "+productId+"]";
    }
}
