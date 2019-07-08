package com.mobtecnica.wafiapps.model.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ashikvashraf on 17/12/17.
 */

public class LogoPictureModel {
    @SerializedName("ImageUrl")
    @Expose
    public String imageUrl;
    @SerializedName("FullSizeImageUrl")
    @Expose
    public String fullSizeImageUrl;
    @SerializedName("Title")
    @Expose
    public String title;
    @SerializedName("AlternateText")
    @Expose
    public String alternateText;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFullSizeImageUrl() {
        return fullSizeImageUrl;
    }

    public void setFullSizeImageUrl(String fullSizeImageUrl) {
        this.fullSizeImageUrl = fullSizeImageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlternateText() {
        return alternateText;
    }

    public void setAlternateText(String alternateText) {
        this.alternateText = alternateText;
    }
}
