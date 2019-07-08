package com.mobtecnica.wafiapps.model.LaundryModel.getlaundryItems;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by SIby on 04-04-2017.
 */

public class ListLaundryMenu implements Parcelable {
    public static final Creator<ListLaundryMenu> CREATOR = new Creator<ListLaundryMenu>() {
        @Override
        public ListLaundryMenu createFromParcel(Parcel in) {
            return new ListLaundryMenu(in);
        }

        @Override
        public ListLaundryMenu[] newArray(int size) {
            return new ListLaundryMenu[size];
        }
    };
    private ListItemServices[] listItemServices;
    private String ItemID;
    private String Menu;
    private String Description;
    private String DisplayOrder;
    private String ImageUrl;
    private String LaundryTypeID;
    private String Title;
    private String MenuID;

    public ListLaundryMenu(Parcel in) {
        ItemID = in.readString();
        Menu = in.readString();
        Description = in.readString();
        DisplayOrder = in.readString();
        ImageUrl = in.readString();
        LaundryTypeID = in.readString();
        Title = in.readString();
        MenuID = in.readString();
    }

    public ListItemServices[] getListItemServices() {
        return listItemServices;
    }

    public void setListItemServices(ListItemServices[] listItemServices) {
        this.listItemServices = listItemServices;
    }

    public String getItemID() {
        return ItemID;
    }

    public void setItemID(String ItemID) {
        this.ItemID = ItemID;
    }

    public String getMenu() {
        return Menu;
    }

    public void setMenu(String Menu) {
        this.Menu = Menu;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getDisplayOrder() {
        return DisplayOrder;
    }

    public void setDisplayOrder(String DisplayOrder) {
        this.DisplayOrder = DisplayOrder;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String ImageUrl) {
        this.ImageUrl = ImageUrl;
    }

    public String getLaundryTypeID() {
        return LaundryTypeID;
    }

    public void setLaundryTypeID(String LaundryTypeID) {
        this.LaundryTypeID = LaundryTypeID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getMenuID() {
        return MenuID;
    }

    public void setMenuID(String MenuID) {
        this.MenuID = MenuID;
    }

    @Override
    public String toString() {
        return "ClassPojo [listItemServices = " + listItemServices + ", ItemID = " + ItemID + ", Menu = " + Menu + ", Description = " + Description + ", DisplayOrder = " + DisplayOrder + ", ImageUrl = " + ImageUrl + ", LaundryTypeID = " + LaundryTypeID + ", Title = " + Title + ", MenuID = " + MenuID + "]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ItemID);
        dest.writeString(Menu);
        dest.writeString(Description);
        dest.writeString(DisplayOrder);
        dest.writeString(ImageUrl);
        dest.writeString(LaundryTypeID);
        dest.writeString(Title);
        dest.writeString(MenuID);
    }
}
