package com.mobtecnica.wafiapps.utils;

import java.io.Serializable;
import java.util.ArrayList;

public class PaymentDataHolder implements Serializable {
    public static final String KEY = "PAYMENT_HOLDER";
    private String cartTotal;
    private String subTotal;
    private ArrayList<PriceBreakdown> priceBreakdownArrayList = new ArrayList<>();

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public ArrayList<PriceBreakdown> getPriceBreakdownArrayList() {
        return priceBreakdownArrayList;
    }

    public void addPriceBreakdownData(String name, String value) {
        priceBreakdownArrayList.add(new PriceBreakdown(name, value));
    }
    public void clearPriceBreakdownData(){
        priceBreakdownArrayList.clear();
    }

public class PriceBreakdown {
        public PriceBreakdown(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        String name;
        String value;
    }

    public void setCartTotal(String cartTotal) {
        this.cartTotal = cartTotal;
    }

    public String getCartTotal() {
        return cartTotal;
    }
}
