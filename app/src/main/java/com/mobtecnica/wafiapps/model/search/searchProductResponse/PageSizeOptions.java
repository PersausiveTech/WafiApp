package com.mobtecnica.wafiapps.model.search.searchProductResponse;

/**
 * Created by SIby on 02-03-2017.
 */

public class PageSizeOptions {
    private String Text;

    private String Value;

    private String Disabled;

    private String Selected;

    private String Group;

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public String getDisabled() {
        return Disabled;
    }

    public void setDisabled(String disabled) {
        Disabled = disabled;
    }

    public String getSelected() {
        return Selected;
    }

    public void setSelected(String selected) {
        Selected = selected;
    }

    public String getGroup() {
        return Group;
    }

    public void setGroup(String group) {
        Group = group;
    }
}
