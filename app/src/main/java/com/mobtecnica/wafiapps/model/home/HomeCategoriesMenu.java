package com.mobtecnica.wafiapps.model.home;

/**
 * Created by SIby on 09-02-2017.
 */

public class HomeCategoriesMenu {
    private String BlogEnabled;

    private String ForumEnabled;

    private String[] Topics;

    private String NewProductsEnabled;

    private CustomProperties CustomProperties;

    private HomeCategories[] Categories;

    private String LogoPath;

    public String getBlogEnabled() {
        return BlogEnabled;
    }

    public void setBlogEnabled(String BlogEnabled) {
        this.BlogEnabled = BlogEnabled;
    }

    public String getForumEnabled() {
        return ForumEnabled;
    }

    public void setForumEnabled(String ForumEnabled) {
        this.ForumEnabled = ForumEnabled;
    }

    public String[] getTopics() {
        return Topics;
    }

    public void setTopics(String[] Topics) {
        this.Topics = Topics;
    }

    public String getNewProductsEnabled() {
        return NewProductsEnabled;
    }

    public void setNewProductsEnabled(String NewProductsEnabled) {
        this.NewProductsEnabled = NewProductsEnabled;
    }

    public com.mobtecnica.wafiapps.model.home.CustomProperties getCustomProperties() {
        return CustomProperties;
    }

    public void setCustomProperties(com.mobtecnica.wafiapps.model.home.CustomProperties customProperties) {
        CustomProperties = customProperties;
    }

    public HomeCategories[] getCategories() {
        return Categories;
    }

    public void setCategories(HomeCategories[] Categories) {
        this.Categories = Categories;
    }

    public String getLogoPath() {
        return LogoPath;
    }

    public void setLogoPath(String logoPath) {
        LogoPath = logoPath;
    }

    @Override
    public String toString() {
        return "ClassPojo [BlogEnabled = " + BlogEnabled + ", ForumEnabled = " + ForumEnabled + ", Topics = " + Topics + ", NewProductsEnabled = " + NewProductsEnabled + ", CustomProperties = " + CustomProperties + ", HomeCategories = " + Categories + ", LogoPath = " + LogoPath + "]";
    }
}
