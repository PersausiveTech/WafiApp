package com.mobtecnica.wafiapps.model.checkoutnew;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by siby on 14-Jun-17.
 */


public class SaveShippingNew implements Parcelable{
    private String guid;

    private String apiToken;

    private String shipping_address_id;

    public SaveShippingNew(Parcel in) {
        guid = in.readString();
        apiToken = in.readString();
        shipping_address_id = in.readString();
    }

    public static final Creator<SaveShippingNew> CREATOR = new Creator<SaveShippingNew>() {
        @Override
        public SaveShippingNew createFromParcel(Parcel in) {
            return new SaveShippingNew(in);
        }

        @Override
        public SaveShippingNew[] newArray(int size) {
            return new SaveShippingNew[size];
        }
    };

    public SaveShippingNew() {

    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getShipping_address_id() {
        return shipping_address_id;
    }

    public void setShipping_address_id(String shipping_address_id) {
        this.shipping_address_id = shipping_address_id;
    }

    @Override
    public String toString() {
        return "ClassPojo [guid = " + guid + ", apiToken = " + apiToken + ", shipping_address_id = " + shipping_address_id + "]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(guid);
        parcel.writeString(apiToken);
        parcel.writeString(shipping_address_id);
    }
}

