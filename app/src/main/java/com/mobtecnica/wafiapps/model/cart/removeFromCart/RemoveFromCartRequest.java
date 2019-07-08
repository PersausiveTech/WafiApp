package com.mobtecnica.wafiapps.model.cart.removeFromCart;

import com.mobtecnica.wafiapps.utils.Constants;

/**
 * Created by SIby on 22-03-2017.
 */

public class RemoveFromCartRequest {
    public RemoveFromCartRequest() {}
    public RemoveFromCartRequest(String guid,String id_delete) {
        this.guid = guid;
        setApiToken(Constants.API_TOKEN);
        UpdateModelRemove updateModel = new UpdateModelRemove();
        String [] deleteIds =new String[1];
        deleteIds[0]=id_delete;
        updateModel.setRemovefromcart(deleteIds);
        setUpdateModel(updateModel);
    }

    private String guid;

    private String apiToken;

    private UpdateModelRemove updateModel;

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

    public UpdateModelRemove getUpdateModel ()
    {
        return updateModel;
    }

    public void setUpdateModel (UpdateModelRemove updateModel)
    {
        this.updateModel = updateModel;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [guid = "+guid+", apiToken = "+apiToken+", updateModel = "+updateModel+"]";
    }
}
