package com.blackgaryc.library.core.oauth2;

import java.io.Serializable;

public class OAuth2ClientProperty implements Serializable, IOAuth2ClientProperty {
    private String providerId;
    private String name;
    private String accessKey;
    private String secretKey;

    private String callBackUrl;

    @Override
    public String getCallBackUrl() {
        return callBackUrl;
    }

    public void setCallBackUrl(String callBackUrl) {
        this.callBackUrl = callBackUrl;
    }

    @Override
    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    @Override
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    @Override
    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public String toString() {
        return "OAuth2Client{" +
                "providerId='" + providerId + '\'' +
                ", name='" + name + '\'' +
                ", accessKey='" + accessKey + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", callBackUrl='" + callBackUrl + '\'' +
                '}';
    }
}
