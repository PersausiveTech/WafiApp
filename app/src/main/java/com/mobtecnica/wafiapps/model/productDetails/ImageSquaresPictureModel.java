package com.mobtecnica.wafiapps.model.productDetails;

/**
 * Created by SIby on 20-02-2017.
 */

public class ImageSquaresPictureModel {
    private String FullSizeImageUrl;

    private String AlternateText;

    private String ImageUrl;

 //   private String CustomProperties;

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

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
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
        return "ClassPojo [FullSizeImageUrl = "+FullSizeImageUrl+", AlternateText = "+AlternateText+", ImageUrl = "+ImageUrl+", CustomProperties =, Title = "+Title+"]";
    }
}
