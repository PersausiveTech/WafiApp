package com.mobtecnica.wafiapps.model.paymentmethod;

public class PaymentMethod {
    private String PaymentMethodSystemName;
    private String Name;
    private String Fee;
    private boolean Selected;
    private String LogoUrl;

    public String getPaymentMethodSystemName() {
        return PaymentMethodSystemName;
    }

    public void setPaymentMethodSystemName(String paymentMethodSystemName) {
        PaymentMethodSystemName = paymentMethodSystemName;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFee() {
        return Fee;
    }

    public void setFee(String fee) {
        Fee = fee;
    }

    public boolean getSelected() {
        return Selected;
    }

    public void setSelected(boolean selected) {
        Selected = selected;
    }

    public String getLogoUrl() {
        return LogoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        LogoUrl = logoUrl;
    }
}
