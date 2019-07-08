package com.mobtecnica.wafiapps.model.checkoutnew.startCheckout;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by siby on 14-Jun-17.
 */

public class StartCheckoutNew implements Parcelable
{
    private String guid;

    private String apiToken;

    private List<Item_quantityStart> item_quantity;

    private List<Checkout_attributesStart> checkout_attributes;

    public StartCheckoutNew(Parcel in) {
        guid = in.readString();
        apiToken = in.readString();
    }

    public static final Creator<StartCheckoutNew> CREATOR = new Creator<StartCheckoutNew>() {
        @Override
        public StartCheckoutNew createFromParcel(Parcel in) {
            return new StartCheckoutNew(in);
        }

        @Override
        public StartCheckoutNew[] newArray(int size) {
            return new StartCheckoutNew[size];
        }
    };

    public StartCheckoutNew() {

    }

    public String getGuid ()
    {
        return guid;
    }

    public void setGuid (String guid)
    {
        this.guid = guid;
    }

    public String getApiToken ()
    {
        return apiToken;
    }

    public void setApiToken (String apiToken)
    {
        this.apiToken = apiToken;
    }

    public List<Item_quantityStart> getItem_quantity() {
        return item_quantity;
    }

    public void setItem_quantity(List<Item_quantityStart> item_quantity) {
        this.item_quantity = item_quantity;
    }

    public List<Checkout_attributesStart> getCheckout_attributes() {
        return checkout_attributes;
    }

    public void setCheckout_attributes(List<Checkout_attributesStart> checkout_attributes) {
        this.checkout_attributes = checkout_attributes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(guid);
        parcel.writeString(apiToken);
    }
}
