
package com.mobtecnica.wafiapps.model.wishlist.newWishList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Picture {

    private String FullSizeImageUrl;

    private String AlternateText;

    private String ImageUrl;

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
}
