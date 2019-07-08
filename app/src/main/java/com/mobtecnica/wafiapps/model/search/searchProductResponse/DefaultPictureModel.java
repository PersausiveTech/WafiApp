package com.mobtecnica.wafiapps.model.search.searchProductResponse;

/**
 * Created by SIby on 02-03-2017.
 */

public class DefaultPictureModel {

    private String FullSizeImageUrl;

    private String AlternateText;

    private String ImageUrl;

//    private String CustomProperties;

    private String Title;

    public String getFullSizeImageUrl() {
        return FullSizeImageUrl;
    }

    public void setFullSizeImageUrl(String fullSizeImageUrl) {
        FullSizeImageUrl = fullSizeImageUrl;
    }

    public String getAlternateText() {
        return AlternateText;
    }

    public void setAlternateText(String alternateText) {
        AlternateText = alternateText;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

//    public String getCustomProperties() {
//        return CustomProperties;
//    }
//
//    public void setCustomProperties(String customProperties) {
//        CustomProperties = customProperties;
//    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
