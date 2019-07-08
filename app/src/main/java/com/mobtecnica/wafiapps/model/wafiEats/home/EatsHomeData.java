
package com.mobtecnica.wafiapps.model.wafiEats.home;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EatsHomeData {

    @SerializedName("lstLocations")
    @Expose
    private List<LstLocation> lstLocations = null;
    @SerializedName("lstCuisines")
    @Expose
    private List<LstCuisine> lstCuisines = null;

    public List<LstLocation> getLstLocations() {
        return lstLocations;
    }

    public void setLstLocations(List<LstLocation> lstLocations) {
        this.lstLocations = lstLocations;
    }

    public List<LstCuisine> getLstCuisines() {
        return lstCuisines;
    }

    public void setLstCuisines(List<LstCuisine> lstCuisines) {
        this.lstCuisines = lstCuisines;
    }

}
