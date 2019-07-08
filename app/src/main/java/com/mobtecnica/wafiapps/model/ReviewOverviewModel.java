package com.mobtecnica.wafiapps.model;

public class ReviewOverviewModel {
    private String AllowCustomerReviews;

    private String TotalReviews;

    private String RatingSum;

    private CustomProperties CustomProperties;

    private String ProductId;

    public String getAllowCustomerReviews() {
        return AllowCustomerReviews;
    }

    public void setAllowCustomerReviews(String AllowCustomerReviews) {
        this.AllowCustomerReviews = AllowCustomerReviews;
    }

    public String getTotalReviews() {
        return TotalReviews;
    }

    public void setTotalReviews(String TotalReviews) {
        this.TotalReviews = TotalReviews;
    }

    public String getRatingSum() {
        return RatingSum;
    }

    public void setRatingSum(String RatingSum) {
        this.RatingSum = RatingSum;
    }

    public CustomProperties getCustomProperties() {
        return CustomProperties;
    }

    public void setCustomProperties(CustomProperties customProperties) {
        CustomProperties = customProperties;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String ProductId) {
        this.ProductId = ProductId;
    }

    @Override
    public String toString() {
        return "ClassPojo [AllowCustomerReviews = " + AllowCustomerReviews + ", TotalReviews = " + TotalReviews + ", RatingSum = " + RatingSum + ", CustomProperties = " + CustomProperties + ", ProductId = " + ProductId + "]";
    }
}
