package com.mobtecnica.wafiapps.model.categories;

/**
 * Created by SIby on 03-02-2017.
 */
public class PictureModelSubCategories {
    private String FullSizeImageUrl;

    private String AlternateText;

    private String ImageUrl;

//    private String CustomProperties;

    private String Title;

    public String getAlternateText() {
        return AlternateText;
    }

    public void setAlternateText(String alternateText) {
        AlternateText = alternateText;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getFullSizeImageUrl ()
    {
        return FullSizeImageUrl;
    }

    public void setFullSizeImageUrl (String FullSizeImageUrl)
    {
        this.FullSizeImageUrl = FullSizeImageUrl;
    }


    public String getImageUrl ()
    {
        return ImageUrl;
    }

    public void setImageUrl (String ImageUrl)
    {
        this.ImageUrl = ImageUrl;
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


    @Override
    public String toString()
    {
        return "ClassPojo [FullSizeImageUrl = "+FullSizeImageUrl+", AlternateText = "+AlternateText+", ImageUrl = "+ImageUrl+", CustomProperties  Title = "+Title+"]";
    }
}
