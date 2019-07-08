
package com.mobtecnica.wafiapps.model.wafiEats.getMenuOptions;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetMenuOptionsData {

    @SerializedName("MenuDetail")
    @Expose
    private MenuDetail menuDetail;
    @SerializedName("MenuOptions")
    @Expose
    private List<MenuOption> menuOptions = null;

    public MenuDetail getMenuDetail() {
        return menuDetail;
    }

    public void setMenuDetail(MenuDetail menuDetail) {
        this.menuDetail = menuDetail;
    }

    public List<MenuOption> getMenuOptions() {
        return menuOptions;
    }

    public void setMenuOptions(List<MenuOption> menuOptions) {
        this.menuOptions = menuOptions;
    }

}
