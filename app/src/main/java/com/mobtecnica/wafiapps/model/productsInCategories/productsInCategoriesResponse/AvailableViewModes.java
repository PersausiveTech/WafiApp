package com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse;

/**
 * Created by SIby on 16-02-2017.
 */

public class AvailableViewModes {
    private String Text;

    private String Value;

    private String Disabled;

    private String Selected;

    private String Group;

    public String getText() {
        return Text;
    }

    public void setText(String Text) {
        this.Text = Text;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String Value) {
        this.Value = Value;
    }

    public String getDisabled() {
        return Disabled;
    }

    public void setDisabled(String Disabled) {
        this.Disabled = Disabled;
    }

    public String getSelected() {
        return Selected;
    }

    public void setSelected(String Selected) {
        this.Selected = Selected;
    }

    public String getGroup() {
        return Group;
    }

    public void setGroup(String group) {
        Group = group;
    }

    @Override
    public String toString() {
        return "ClassPojo [Text = " + Text + ", Value = " + Value + ", Disabled = " + Disabled + ", Selected = " + Selected + ", Group = " + Group + "]";
    }
}
