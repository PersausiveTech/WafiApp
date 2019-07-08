package com.mobtecnica.wafiapps.model.giftCard;

import com.mobtecnica.wafiapps.model.BaseUserRequest;

public class RemoveGiftCardRequest extends BaseUserRequest {
    public RemoveGiftCardRequest(String guid) {
        super();
        setGuid(guid);
    }
    private String removeGiftCardID;

    public String getRemoveGiftCardID() {
        return removeGiftCardID;
    }

    public void setRemoveGiftCardID(String removeGiftCardID) {
        this.removeGiftCardID = removeGiftCardID;
    }
}
