package com.mobtecnica.wafiapps.model.checkoutnew.startCheckout;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by siby on 14-Jun-17.
 */

public class Item_quantityStart implements Parcelable {

    private String Name;

    private Integer Value;

    public Item_quantityStart(Parcel in) {
        Name = in.readString();
        Value = in.readInt();
    }

    public static final Creator<Item_quantityStart> CREATOR = new Creator<Item_quantityStart>() {
        @Override
        public Item_quantityStart createFromParcel(Parcel in) {
            return new Item_quantityStart(in);
        }

        @Override
        public Item_quantityStart[] newArray(int size) {
            return new Item_quantityStart[size];
        }
    };

    public Item_quantityStart() {

    }

    public String getName ()
    {
        return Name;
    }

    public void setName (String Name)
    {
        this.Name = Name;
    }

    public Integer getValue ()
    {
        return Value;
    }

    public void setValue (Integer Value)
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
        parcel.writeInt(Value);
    }
}
