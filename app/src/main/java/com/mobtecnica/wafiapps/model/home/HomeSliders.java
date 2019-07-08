package com.mobtecnica.wafiapps.model.home;

/**
 * Created by SIby on 09-02-2017.
 */

public class HomeSliders {
    private String Alt;

    private String DisplayOrder;

    private String Visible;

    private String ImageClickLink;

    private String DisplayText;

    private String ImageUrl;

    private String Id;

    private String PictureId;

    private String SliderId;


    public String getDisplayOrder() {
        return DisplayOrder;
    }

    public void setDisplayOrder(String DisplayOrder) {
        this.DisplayOrder = DisplayOrder;
    }

    public String getVisible() {
        return Visible;
    }

    public void setVisible(String Visible) {
        this.Visible = Visible;
    }


    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String ImageUrl) {
        this.ImageUrl = ImageUrl;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getPictureId() {
        return PictureId;
    }

    public void setPictureId(String PictureId) {
        this.PictureId = PictureId;
    }

    public String getSliderId() {
        return SliderId;
    }

    public void setSliderId(String SliderId) {
        this.SliderId = SliderId;
    }

    public String getAlt() {
        return Alt;
    }

    public void setAlt(String alt) {
        Alt = alt;
    }

    public String getImageClickLink() {
        return ImageClickLink;
    }

    public void setImageClickLink(String imageClickLink) {
        ImageClickLink = imageClickLink;
    }

    public String getDisplayText() {
        return DisplayText;
    }

    public void setDisplayText(String displayText) {
        DisplayText = displayText;
    }

    @Override
    public String toString() {
        return "ClassPojo [Alt = " + Alt + ", DisplayOrder = " + DisplayOrder + ", Visible = " + Visible + ", ImageClickLink = " + ImageClickLink + ", DisplayText = " + DisplayText + ", ImageUrl = " + ImageUrl + ", Id = " + Id + ", PictureId = " + PictureId + ", SliderId = " + SliderId + "]";
    }
}
