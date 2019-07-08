
package com.mobtecnica.wafiapps.model.wafiEats.checkout;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlaceOrderWafieatsRequest {

    @SerializedName("apiToken")
    @Expose
    private String apiToken;
    @SerializedName("custGuid")
    @Expose
    private String custGuid;
    @SerializedName("emailID")
    @Expose
    private String emailID;
    @SerializedName("model")
    @Expose
    private Model model;
    @SerializedName("CustDetails")
    @Expose
    private CustDetails custDetails;
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

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public CustDetails getCustDetails() {
        return custDetails;
    }

    public void setCustDetails(CustDetails custDetails) {
        this.custDetails = custDetails;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

}
