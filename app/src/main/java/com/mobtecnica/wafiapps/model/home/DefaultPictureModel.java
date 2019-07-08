package com.mobtecnica.wafiapps.model.home;

/**
 * Created by SIby on 09-02-2017.
 */

public class DefaultPictureModel {
    private String FullSizeImageUrl;

    private String AlternateText;

    private String ImageUrl;

    private CustomProperties CustomProperties;

    private String Title;

    public String getFullSizeImageUrl() {
        return FullSizeImageUrl;
    }

    public void setFullSizeImageUrl(String FullSizeImageUrl) {
        this.FullSizeImageUrl = FullSizeImageUrl;
    }

    public String getAlternateText() {
        return AlternateText;
    }

    public void setAlternateText(String AlternateText) {
        this.AlternateText = AlternateText;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String ImageUrl) {
        this.ImageUrl = ImageUrl;
    }

    public com.mobtecnica.wafiapps.model.home.CustomProperties getCustomProperties() {
        return CustomProperties;
    }

    public void setCustomProperties(com.mobtecnica.wafiapps.model.home.CustomProperties customProperties) {
        CustomProperties = customProperties;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    @Override
    public String toString() {
        return "ClassPojo [FullSizeImageUrl = " + FullSizeImageUrl + ", AlternateText = " + AlternateText + ", ImageUrl = " + ImageUrl + ", CustomProperties = " + CustomProperties + ", Title = " + Title + "]";
    }

}
