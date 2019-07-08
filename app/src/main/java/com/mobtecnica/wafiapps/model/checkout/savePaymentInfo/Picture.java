
package com.mobtecnica.wafiapps.model.checkout.savePaymentInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Picture {

    @SerializedName("ImageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("FullSizeImageUrl")
    @Expose
    private String fullSizeImageUrl;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("AlternateText")
    @Expose
    private String alternateText;

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
