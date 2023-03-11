package com.blackgaryc.library.core.oauth2;

import java.io.Serializable;

public class OAuth2ProviderProperty implements Serializable, IOAuth2ProviderProperty {
    private String authUrl;
    private String tokenUrl;
    private String userInfoUrl;
    private String userIdKey;

    @Override
    public String getUserIdKey() {
        return userIdKey;
    }

    public void setUserIdKey(String userIdKey) {
        this.userIdKey = userIdKey;
    }

    @Override
    public String getAuthUrl() {
        return authUrl;
    }

    public void setAuthUrl(String authUrl) {
        this.authUrl = authUrl;
    }

    @Override
    public String getTokenUrl() {
        return tokenUrl;
    }

    public void setTokenUrl(String tokenUrl) {
        this.tokenUrl = tokenUrl;
    }

    @Override
    public String getUserInfoUrl() {
        return userInfoUrl;
    }

    public void setUserInfoUrl(String userInfoUrl) {
        this.userInfoUrl = userInfoUrl;
    }

    @Override
    public String toString() {
        return "OAuth2Provider{" +
                ", authUrl='" + authUrl + '\'' +
                ", tokenUrl='" + tokenUrl + '\'' +
                ", userInfoUrl='" + userInfoUrl + '\'' +
                ", userIdKey='" + userIdKey + '\'' +
                '}';
    }
}
