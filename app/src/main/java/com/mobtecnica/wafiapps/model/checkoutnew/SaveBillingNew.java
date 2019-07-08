package com.mobtecnica.wafiapps.model.checkoutnew;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by siby on 14-Jun-17.
 */

public class SaveBillingNew implements Parcelable
{
    private String guid;

    private String apiToken;

    private String ShipToSameAddress;

    private String billing_address_id;

    public SaveBillingNew(Parcel in) {
        guid = in.readString();
        apiToken = in.readString();
        ShipToSameAddress = in.readString();
        billing_address_id = in.readString();
    }

    public static final Creator<SaveBillingNew> CREATOR = new Creator<SaveBillingNew>() {
        @Override
        public SaveBillingNew createFromParcel(Parcel in) {
            return new SaveBillingNew(in);
        }

        @Override
        public SaveBillingNew[] newArray(int size) {
            return new SaveBillingNew[size];
        }
    };

    public SaveBillingNew() {

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

    public String getShipToSameAddress ()
    {
        return ShipToSameAddress;
    }

    public void setShipToSameAddress (String ShipToSameAddress)
    {
        this.ShipToSameAddress = ShipToSameAddress;
    }

    public String getBilling_address_id ()
    {
        return billing_address_id;
    }

    public void setBilling_address_id (String billing_address_id)
    {
        this.billing_address_id = billing_address_id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [guid = "+guid+", apiToken = "+apiToken+", ShipToSameAddress = "+ShipToSameAddress+", billing_address_id = "+billing_address_id+"]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(guid);
        parcel.writeString(apiToken);
        parcel.writeString(ShipToSameAddress);
        parcel.writeString(billing_address_id);
    }
}

