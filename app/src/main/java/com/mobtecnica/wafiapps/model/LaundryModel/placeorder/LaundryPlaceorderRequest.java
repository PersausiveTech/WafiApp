
package com.mobtecnica.wafiapps.model.LaundryModel.placeorder;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LaundryPlaceorderRequest {

    @SerializedName("apiToken")
    @Expose
    private String apiToken;
    @SerializedName("custGuid")
    @Expose
    private String custGuid;
    @SerializedName("emailID")
    @Expose
    private String emailID;
    @SerializedName("isExpressCheckout")
    @Expose
    private String isExpressCheckout;
    @SerializedName("model")
    @Expose
    private Model model;
    @SerializedName("cartItems")
    @Expose
    private List<CartItem> cartItems = null;

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getCustGuid() {
        return custGuid;
    }

    public void setCustGuid(String custGuid) {
        this.custGuid = custGuid;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getIsExpressCheckout() {
        return isExpressCheckout;
    }

    public void setIsExpressCheckout(String isExpressCheckout) {
        this.isExpressCheckout = isExpressCheckout;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

}
