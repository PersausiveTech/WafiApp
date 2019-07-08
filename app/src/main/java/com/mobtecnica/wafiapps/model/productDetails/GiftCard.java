package com.mobtecnica.wafiapps.model.productDetails;

/**
 * Created by SIby on 20-02-2017.
 */

public class GiftCard {
    private String RecipientEmail;

    private String Message;

    private String RecipientName;

//    private String CustomProperties;

    private String GiftCardType;

    private String SenderEmail;

    private String SenderName;

    private String IsGiftCard;

    public String getRecipientEmail() {
        return RecipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        RecipientEmail = recipientEmail;
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

    public String getGiftCardType ()
    {
        return GiftCardType;
    }

    public void setGiftCardType (String GiftCardType)
    {
        this.GiftCardType = GiftCardType;
    }

    public String getIsGiftCard ()
    {
        return IsGiftCard;
    }

    public void setIsGiftCard (String IsGiftCard)
    {
        this.IsGiftCard = IsGiftCard;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getRecipientName() {
        return RecipientName;
    }

    public void setRecipientName(String recipientName) {
        RecipientName = recipientName;
    }

    public String getSenderEmail() {
        return SenderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        SenderEmail = senderEmail;
    }

    public String getSenderName() {
        return SenderName;
    }

    public void setSenderName(String senderName) {
        SenderName = senderName;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [RecipientEmail = "+RecipientEmail+", Message = "+Message+", RecipientName = "+RecipientName+", CustomProperties =  GiftCardType = "+GiftCardType+", SenderEmail = "+SenderEmail+", SenderName = "+SenderName+", IsGiftCard = "+IsGiftCard+"]";
    }
}
