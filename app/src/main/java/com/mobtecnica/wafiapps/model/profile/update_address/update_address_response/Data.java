package com.mobtecnica.wafiapps.model.profile.update_address.update_address_response;

/**
 * Created by SIby on 11-01-2017.
 */

public class Data {
    private String guid;

    private String Updated;

    private String customerid;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getUpdated() {
        return Updated;
    }

    public void setUpdated(String Updated) {
        this.Updated = Updated;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    @Override
    public String toString() {
        return "ClassPojo [guid = " + guid + ", Updated = " + Updated + ", customerid = " + customerid + "]";
    }
}
