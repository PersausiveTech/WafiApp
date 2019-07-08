package com.mobtecnica.wafiapps.model.LaundryModel.priceLIst;

import java.util.ArrayList;

/**
 * Created by SIby on 03-04-2017.
 */

public class DataLaundry {
    private ArrayList<LstMensWear> lstMensWear;

    private ArrayList<LstMensWear> lstHousehold;

    private ArrayList<LstMensWear> lstWomensWear;

    public ArrayList<LstMensWear> getLstMensWear() {
        return lstMensWear;
    }

    public void setLstMensWear(ArrayList<LstMensWear> lstMensWear) {
        this.lstMensWear = lstMensWear;
    }

    public ArrayList<LstMensWear> getLstHousehold() {
        return lstHousehold;
    }

    public void setLstHousehold(ArrayList<LstMensWear> lstHousehold) {
        this.lstHousehold = lstHousehold;
    }

    public ArrayList<LstMensWear> getLstWomensWear() {
        return lstWomensWear;
    }

    public void setLstWomensWear(ArrayList<LstMensWear> lstWomensWear) {
        this.lstWomensWear = lstWomensWear;
    }
}
