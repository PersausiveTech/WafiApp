
package com.mobtecnica.wafiapps.model.checkout.opcBillingForm;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OnePageBillingFormData {

    @SerializedName("ExistingAddresses")
    @Expose
    private List<ExistingAddress> existingAddresses = null;
    @SerializedName("NewAddress")
    @Expose
    private NewAddress newAddress;
    @SerializedName("ShipToSameAddress")
    @Expose
    private Boolean shipToSameAddress;
    @SerializedName("ShipToSameAddressAllowed")
    @Expose
    private Boolean shipToSameAddressAllowed;
    @SerializedName("NewAddressPreselected")
    @Expose
    private Boolean newAddressPreselected;
    @SerializedName("CustomProperties")
    @Expose
    private CustomProperties___ customProperties;

    public List<ExistingAddress> getExistingAddresses() {
        return existingAddresses;
    }

    public void setExistingAddresses(List<ExistingAddress> existingAddresses) {
        this.existingAddresses = existingAddresses;
    }

    public NewAddress getNewAddress() {
        return newAddress;
    }

    public void setNewAddress(NewAddress newAddress) {
        this.newAddress = newAddress;
    }

    public Boolean getShipToSameAddress() {
        return shipToSameAddress;
    }

    public void setShipToSameAddress(Boolean shipToSameAddress) {
        this.shipToSameAddress = shipToSameAddress;
    }

    public Boolean getShipToSameAddressAllowed() {
        return shipToSameAddressAllowed;
    }

    public void setShipToSameAddressAllowed(Boolean shipToSameAddressAllowed) {
        this.shipToSameAddressAllowed = shipToSameAddressAllowed;
    }

    public Boolean getNewAddressPreselected() {
        return newAddressPreselected;
    }

    public void setNewAddressPreselected(Boolean newAddressPreselected) {
        this.newAddressPreselected = newAddressPreselected;
    }

    public CustomProperties___ getCustomProperties() {
        return customProperties;
    }

    public void setCustomProperties(CustomProperties___ customProperties) {
        this.customProperties = customProperties;
    }

}
