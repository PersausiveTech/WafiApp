package com.mobtecnica.wafiapps.model.cart.getCart.getCartResponse;

/**
 * Created by SIby on 20-03-2017.
 */
public class DiscountBox {
    private String CurrentCode;

    private String Message;

    private boolean Display;

    private String IsApplied;

//    private String CustomProperties;

    public String getCurrentCode() {
        return CurrentCode;
    }

    public void setCurrentCode(String currentCode) {
        CurrentCode = currentCode;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public boolean getDisplay() {
        return Display;
    }

    public void setDisplay(boolean Display) {
        this.Display = Display;
    }

    public String getIsApplied() {
        return IsApplied;
    }

    public void setIsApplied(String IsApplied) {
        this.IsApplied = IsApplied;
    }


    @Override
    public String toString() {
        return "ClassPojo [CurrentCode = " + CurrentCode + ", Message = " + Message + ", Display = " + Display + ", IsApplied = " + IsApplied + ", CustomProperties =]";
    }
}

