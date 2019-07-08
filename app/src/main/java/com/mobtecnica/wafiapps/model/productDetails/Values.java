package com.mobtecnica.wafiapps.model.productDetails;

/**
 * Created by SIby on 20-02-2017.
 */

public class Values {private String Name;

    private String IsPreSelected;

    private String ColorSquaresRgb;

    private String PriceAdjustmentValue;

    private String Id;

    private String PriceAdjustment;

//    private String CustomProperties;

    private String PictureId;

    private ImageSquaresPictureModel ImageSquaresPictureModel;

    public String getName ()
    {
        return Name;
    }

    public void setName (String Name)
    {
        this.Name = Name;
    }

    public String getIsPreSelected ()
    {
        return IsPreSelected;
    }

    public void setIsPreSelected (String IsPreSelected)
    {
        this.IsPreSelected = IsPreSelected;
    }

    public String getColorSquaresRgb ()
    {
        return ColorSquaresRgb;
    }

    public void setColorSquaresRgb (String ColorSquaresRgb)
    {
        this.ColorSquaresRgb = ColorSquaresRgb;
    }

    public String getPriceAdjustmentValue ()
    {
        return PriceAdjustmentValue;
    }

    public void setPriceAdjustmentValue (String PriceAdjustmentValue)
    {
        this.PriceAdjustmentValue = PriceAdjustmentValue;
    }

    public String getId ()
    {
        return Id;
    }

    public void setId (String Id)
    {
        this.Id = Id;
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

    public String getPictureId ()
    {
        return PictureId;
    }

    public void setPictureId (String PictureId)
    {
        this.PictureId = PictureId;
    }

    public ImageSquaresPictureModel getImageSquaresPictureModel ()
    {
        return ImageSquaresPictureModel;
    }

    public void setImageSquaresPictureModel (ImageSquaresPictureModel ImageSquaresPictureModel)
    {
        this.ImageSquaresPictureModel = ImageSquaresPictureModel;
    }

    public String getPriceAdjustment() {
        return PriceAdjustment;
    }

    public void setPriceAdjustment(String priceAdjustment) {
        PriceAdjustment = priceAdjustment;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Name = "+Name+", IsPreSelected = "+IsPreSelected+", ColorSquaresRgb = "+ColorSquaresRgb+", PriceAdjustmentValue = "+PriceAdjustmentValue+", Id = "+Id+", PriceAdjustment = "+PriceAdjustment+", CustomProperties =  PictureId = "+PictureId+", ImageSquaresPictureModel = "+ImageSquaresPictureModel+"]";
    }
}
