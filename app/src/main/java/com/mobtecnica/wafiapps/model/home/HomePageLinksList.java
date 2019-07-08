package com.mobtecnica.wafiapps.model.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ashikvashraf on 21/12/17.
 */

public class HomePageLinksList {
    @SerializedName("Id")
    @Expose
    public Integer id;
    @SerializedName("Name")
    @Expose
    public String name;
    @SerializedName("Title")
    @Expose
    public String title;
    @SerializedName("Description")
    @Expose
    public String description;
    @SerializedName("Action")
    @Expose
    public String action;
    @SerializedName("LinkUrl")
    @Expose
    public String linkUrl;
    @SerializedName("DisplayOrder")
    @Expose
    public Integer displayOrder;
    @SerializedName("LogoPictureModel")
    @Expose
    public LogoPictureModel logoPictureModel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public LogoPictureModel getLogoPictureModel() {
        return logoPictureModel;
    }

    public void setLogoPictureModel(LogoPictureModel logoPictureModel) {
        this.logoPictureModel = logoPictureModel;
    }
}
