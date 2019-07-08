
package com.mobtecnica.wafiapps.model.checkout.onePageCheckout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OnePageCheckoutData {

    @SerializedName("ShippingRequired")
    @Expose
    private Boolean shippingRequired;
    @SerializedName("DisableBillingAddressCheckoutStep")
    @Expose
    private Boolean disableBillingAddressCheckoutStep;
    @SerializedName("CustomProperties")
    @Expose
    private CustomPropertiesOnePage customProperties;

    public Boolean getShippingRequired() {
        return shippingRequired;
    }

    public void setShippingRequired(Boolean shippingRequired) {
        this.shippingRequired = shippingRequired;
    }

    public Boolean getDisableBillingAddressCheckoutStep() {
        return disableBillingAddressCheckoutStep;
    }

    public void setDisableBillingAddressCheckoutStep(Boolean disableBillingAddressCheckoutStep) {
        this.disableBillingAddressCheckoutStep = disableBillingAddressCheckoutStep;
    }

    public CustomPropertiesOnePage getCustomProperties() {
        return customProperties;
    }

    public void setCustomProperties(CustomPropertiesOnePage customProperties) {
        this.customProperties = customProperties;
    }

}
