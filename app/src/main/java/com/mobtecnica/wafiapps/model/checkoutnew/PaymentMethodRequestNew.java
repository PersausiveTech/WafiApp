package com.mobtecnica.wafiapps.model.checkoutnew;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by siby on 13-Jun-17.
 */

public class PaymentMethodRequestNew implements Parcelable
{
    private String guid;

    private String apiToken;

    private String paymentmethod;

    public PaymentMethodRequestNew(Parcel in) {
        guid = in.readString();
        apiToken = in.readString();
        paymentmethod = in.readString();
    }

    public static final Creator<PaymentMethodRequestNew> CREATOR = new Creator<PaymentMethodRequestNew>() {
        @Override
        public PaymentMethodRequestNew createFromParcel(Parcel in) {
            return new PaymentMethodRequestNew(in);
        }

        @Override
        public PaymentMethodRequestNew[] newArray(int size) {
            return new PaymentMethodRequestNew[size];
        }
    };

    public PaymentMethodRequestNew() {

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

    public String getPaymentmethod ()
    {
        return paymentmethod;
    }

    public void setPaymentmethod (String paymentmethod)
    {
        this.paymentmethod = paymentmethod;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [guid = "+guid+", apiToken = "+apiToken+", paymentmethod = "+paymentmethod+"]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(guid);
        parcel.writeString(apiToken);
        parcel.writeString(paymentmethod);
    }
}

