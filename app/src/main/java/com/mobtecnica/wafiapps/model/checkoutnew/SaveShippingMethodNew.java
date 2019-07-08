package com.mobtecnica.wafiapps.model.checkoutnew;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by siby on 13-Jun-17.
 */

public class SaveShippingMethodNew implements Parcelable
{
    private String guid;

    private String apiToken;

    private String shippingoption;

    public SaveShippingMethodNew(Parcel in) {
        guid = in.readString();
        apiToken = in.readString();
        shippingoption = in.readString();
    }

    public static final Creator<SaveShippingMethodNew> CREATOR = new Creator<SaveShippingMethodNew>() {
        @Override
        public SaveShippingMethodNew createFromParcel(Parcel in) {
            return new SaveShippingMethodNew(in);
        }

        @Override
        public SaveShippingMethodNew[] newArray(int size) {
            return new SaveShippingMethodNew[size];
        }
    };

    public SaveShippingMethodNew() {

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

    public String getShippingoption ()
    {
        return shippingoption;
    }

    public void setShippingoption (String shippingoption)
    {
        this.shippingoption = shippingoption;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [guid = "+guid+", apiToken = "+apiToken+", shippingoption = "+shippingoption+"]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(guid);
        parcel.writeString(apiToken);
        parcel.writeString(shippingoption);
    }
}


