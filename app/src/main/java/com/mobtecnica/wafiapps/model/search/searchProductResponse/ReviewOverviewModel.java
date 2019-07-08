package com.mobtecnica.wafiapps.model.search.searchProductResponse;

/**
 * Created by SIby on 02-03-2017.
 */

public class ReviewOverviewModel {
    private String AllowCustomerReviews;

    private String TotalReviews;

    private String RatingSum;

//    private String CustomProperties;

    private String ProductId;

    public String getAllowCustomerReviews() {
        return AllowCustomerReviews;
    }

    public void setAllowCustomerReviews(String allowCustomerReviews) {
        AllowCustomerReviews = allowCustomerReviews;
    }

    public String getTotalReviews() {
        return TotalReviews;
    }

    public void setTotalReviews(String totalReviews) {
        TotalReviews = totalReviews;
    }

    public String getRatingSum() {
        return RatingSum;
    }

    public void setRatingSum(String ratingSum) {
        RatingSum = ratingSum;
    }

//    public String getCustomProperties() {
//        return CustomProperties;
//    }
//
//    public void setCustomProperties(String customProperties) {
//        CustomProperties = customProperties;
//    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }
}
