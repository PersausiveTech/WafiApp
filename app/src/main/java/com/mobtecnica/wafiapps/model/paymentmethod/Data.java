package com.mobtecnica.wafiapps.model.paymentmethod;

import java.util.List;

public class Data {
    private List<PaymentMethod> PaymentMethods;

    public List<PaymentMethod> getPaymentMethods() {
        return PaymentMethods;
    }

    public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
        PaymentMethods = paymentMethods;
    }
}
