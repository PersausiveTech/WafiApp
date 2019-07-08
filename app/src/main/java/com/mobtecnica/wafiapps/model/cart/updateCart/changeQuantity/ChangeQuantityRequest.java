package com.mobtecnica.wafiapps.model.cart.updateCart.changeQuantity;

import com.mobtecnica.wafiapps.utils.Constants;

/**
 * Created by SIby on 27-03-2017.
 */


public class ChangeQuantityRequest
{
    private String guid;

    private String apiToken;

    private UpdateModel updateModel;

    public ChangeQuantityRequest(){}
    public ChangeQuantityRequest(String guid, String itemId,int quantity){
        setGuid(guid);
        setApiToken(Constants.API_TOKEN);
        String s1 = "itemquantity" + itemId;
        UpdateModel updateModel = new UpdateModel();

        Item_quantity item_quantity = new Item_quantity();
        try {
            item_quantity.setName(s1);
            item_quantity.setValue(String.valueOf(quantity));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Item_quantity[] item_quantities = new Item_quantity[1];
        item_quantities[0] = item_quantity;
        updateModel.setItem_quantity(item_quantities);
        setUpdateModel(updateModel);
    }
    public String getGuid ()
    {
        return guid;
    }

    public void setGuid (String guid)
    {
        this.guid = guid;
    }

    public String getApiToken ()
    {
        return apiToken;
    }

    public void setApiToken (String apiToken)
    {
        this.apiToken = apiToken;
    }

    public UpdateModel getUpdateModel ()
    {
        return updateModel;
    }

    public void setUpdateModel (UpdateModel updateModel)
    {
        this.updateModel = updateModel;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [guid = "+guid+", apiToken = "+apiToken+", updateModel = "+updateModel+"]";
    }
}
