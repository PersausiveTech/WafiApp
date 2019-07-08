package com.mobtecnica.wafiapps.model.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ashikvashraf on 21/12/17.
 */

public class HomePageLink {
    @SerializedName("SectionName")
    @Expose
    public String sectionName;
    @SerializedName("HomePageLinksList")
    @Expose
    public List<HomePageLinksList> homePageLinksList = null;

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public List<HomePageLinksList> getHomePageLinksList() {
        return homePageLinksList;
    }

    public void setHomePageLinksList(List<HomePageLinksList> homePageLinksList) {
        this.homePageLinksList = homePageLinksList;
    }
}
