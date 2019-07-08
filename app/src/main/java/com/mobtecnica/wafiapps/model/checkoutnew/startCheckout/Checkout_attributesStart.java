package com.mobtecnica.wafiapps.model.checkoutnew.startCheckout;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by siby on 14-Jun-17.
 */

public class Checkout_attributesStart implements Parcelable {

    private String Name;

    private String Value;

    public Checkout_attributesStart(Parcel in) {
        Name = in.readString();
        Value = in.readString();
    }

    public static final Creator<Checkout_attributesStart> CREATOR = new Creator<Checkout_attributesStart>() {
        @Override
        public Checkout_attributesStart createFromParcel(Parcel in) {
            return new Checkout_attributesStart(in);
        }

        @Override
        public Checkout_attributesStart[] newArray(int size) {
            return new Checkout_attributesStart[size];
        }
    };

    public Checkout_attributesStart() {

    }

    public String getName ()
    {
        return Name;
    }

    public void setName (String Name)
    {
        this.Name = Name;
    }

    public String getValue ()
    {
        return Value;
    }

    public void setValue (String Value)
    {
        this.Value = Value;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Name = "+Name+", Value = "+Value+"]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Name);
        parcel.writeString(Value);
    }
}
