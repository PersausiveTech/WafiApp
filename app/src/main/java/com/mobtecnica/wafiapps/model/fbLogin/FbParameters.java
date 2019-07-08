package com.mobtecnica.wafiapps.model.fbLogin;

/**
 * Created by Ashik V Ashraf on 09-08-2017.
 */

public class FbParameters {
    String OAuthToken;
    String apitoken;
    UserClaims UserClaims;
    String ExternalIdentifier;
    String ProviderSystemName;
    String OAuthAccessToken;

    public FbParameters() {
    }

    public String getOAuthToken() {
        return OAuthToken;
    }

    public void setOAuthToken(String OAuthToken) {
        this.OAuthToken = OAuthToken;
    }

    public com.mobtecnica.wafiapps.model.fbLogin.UserClaims getUserClaims() {
        return UserClaims;
    }

    public void setUserClaims(UserClaims userClaims) {
        UserClaims = userClaims;
    }

    public String getExternalIdentifier() {
        return ExternalIdentifier;
    }

    public void setExternalIdentifier(String externalIdentifier) {
        ExternalIdentifier = externalIdentifier;
    }

    public String getProviderSystemName() {
        return ProviderSystemName;
    }

    public void setProviderSystemName(String providerSystemName) {
        ProviderSystemName = providerSystemName;
    }

    public String getOAuthAccessToken() {
        return OAuthAccessToken;
    }

    public void setOAuthAccessToken(String OAuthAccessToken) {
        this.OAuthAccessToken = OAuthAccessToken;
    }

    public String getApitoken() {
        return apitoken;
    }

    public void setApitoken(String apitoken) {
        this.apitoken = apitoken;
    }
}
