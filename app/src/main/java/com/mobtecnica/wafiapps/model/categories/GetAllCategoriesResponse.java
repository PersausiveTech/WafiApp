package com.mobtecnica.wafiapps.model.categories;

/**
 * Created by SIby on 02-02-2017.
 */


public class GetAllCategoriesResponse
{
    private String BlogEnabled;

    private String ForumEnabled;

    private String[] Topics;

    private String NewProductsEnabled;

//    private String CustomProperties;

    private Categories[] Categories;

    private String LogoPath;

    public String getBlogEnabled ()
    {
        return BlogEnabled;
    }

    public void setBlogEnabled (String BlogEnabled)
    {
        this.BlogEnabled = BlogEnabled;
    }

    public String getForumEnabled ()
    {
        return ForumEnabled;
    }

    public void setForumEnabled (String ForumEnabled)
    {
        this.ForumEnabled = ForumEnabled;
    }

    public String[] getTopics ()
    {
        return Topics;
    }

    public void setTopics (String[] Topics)
    {
        this.Topics = Topics;
    }

    public String getNewProductsEnabled ()
    {
        return NewProductsEnabled;
    }

    public void setNewProductsEnabled (String NewProductsEnabled)
    {
        this.NewProductsEnabled = NewProductsEnabled;
    }

//    public String getCustomProperties ()
//    {
//        return CustomProperties;
//    }
//
//    public void setCustomProperties (String CustomProperties)
//    {
//        this.CustomProperties = CustomProperties;
//    }

    public Categories[] getCategories ()
    {
        return Categories;
    }

    public void setCategories (Categories[] Categories)
    {
        this.Categories = Categories;
    }

    public String getLogoPath ()
    {
        return LogoPath;
    }

    public void setLogoPath (String LogoPath)
    {
        this.LogoPath = LogoPath;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [BlogEnabled = "+BlogEnabled+", ForumEnabled = "+ForumEnabled+", Topics = "+Topics+", NewProductsEnabled = "+NewProductsEnabled+", CustomProperties  HomeCategories = "+Categories+", LogoPath = "+LogoPath+"]";
    }
}