package com.mobtecnica.wafiapps.model.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ashikvashraf on 21/12/17.
 */

public class HomePageLinksResponse {
    @SerializedName("HomePageLinks")
    @Expose
    public List<HomePageLink> homePageLinks = null;

    public List<HomePageLink> getHomePageLinks() {
        return homePageLinks;
    }

    public void setHomePageLinks(List<HomePageLink> homePageLinks) {
        this.homePageLinks = homePageLinks;
    }
}
