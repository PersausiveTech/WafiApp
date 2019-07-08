package com.mobtecnica.wafiapps.model.profile.add_address.add_address_response;

/**
 * Created by SIby on 11-01-2017.
 */
public class Data {
    private String guid;
    private String Created;
    private String customerid;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getCreated() {
        return Created;
    }

    public void setCreated(String Created) {
        this.Created = Created;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }


    @Override
    public String toString() {
        return "ClassPojo [guid = " + guid + ", Created = " + Created + ", customerid = " + customerid + "]";
    }
}