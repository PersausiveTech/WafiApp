
package com.mobtecnica.wafiapps.model.wafiEats.Offers;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EatsOffersData {

    @SerializedName("AllOffers")
    @Expose
    private List<AllOffer> allOffers = null;

    public List<AllOffer> getAllOffers() {
        return allOffers;
    }

    public void setAllOffers(List<AllOffer> allOffers) {
        this.allOffers = allOffers;
    }

}
