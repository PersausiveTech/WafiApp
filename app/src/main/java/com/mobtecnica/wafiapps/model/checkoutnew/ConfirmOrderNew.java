package com.mobtecnica.wafiapps.model.checkoutnew;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by siby on 13-Jun-17.
 */

public class ConfirmOrderNew implements Parcelable
{
    private String guid;

    private String apiToken;

    public ConfirmOrderNew(Parcel in) {
        guid = in.readString();
        apiToken = in.readString();
    }

    public static final Creator<ConfirmOrderNew> CREATOR = new Creator<ConfirmOrderNew>() {
        @Override
        public ConfirmOrderNew createFromParcel(Parcel in) {
            return new ConfirmOrderNew(in);
        }

        @Override
        public ConfirmOrderNew[] newArray(int size) {
            return new ConfirmOrderNew[size];
        }
    };

    public ConfirmOrderNew() {

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

    @Override
    public String toString()
    {
        return "ClassPojo [guid = "+guid+", apiToken = "+apiToken+"]";
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

