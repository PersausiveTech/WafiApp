package com.mobtecnica.wafiapps.model.cart.updateCart;

/**
 * Created by SIby on 20-03-2017.
 */

public class CartUpdateResponse {
        private String guid;

        private String apiToken;

        private UpdateModel updateModel;

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


