package com.mobtecnica.wafiapps.model.deals;

import com.mobtecnica.wafiapps.model.CustomProperties;

public class AppPictureModel {
    private String ImageUrl;
    private String FullSizeImageUrl;
    private String Title;
    private String AlternateText;
    private CustomProperties CustomProperties;

    public String getFullSizeImageUrl() {
        return FullSizeImageUrl;
    }
}
