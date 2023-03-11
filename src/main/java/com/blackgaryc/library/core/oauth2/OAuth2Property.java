package com.blackgaryc.library.core.oauth2;

import java.io.Serializable;

/**
 * 绑定provider和client对应的属性
 * 仅对外提供读取接口,隐藏修改接口.
 */
public class OAuth2Property implements Serializable {
    private final IOAuth2ProviderProperty provider;
    private final IOAuth2ClientProperty client;
    private final String providerId;
    @Override
    public String toString() {
        return "OAuth2Configuration{" +
                "provider=" + provider +
                ", client=" + client +
                '}';
    }

    public OAuth2Property(String providerId, IOAuth2ProviderProperty provider, IOAuth2ClientProperty client) {
        this.providerId = providerId;
        this.provider = provider;
        this.client = client;
    }


    public String getCallBackUrl() {
        return client.getCallBackUrl();
    }

    public String getName() {
        return client.getName();
    }

    public String getAccessKey() {
        return client.getAccessKey();
    }

    public String getSecretKey() {
        return client.getSecretKey();
    }

    public String getUserIdKey() {
        return provider.getUserIdKey();
    }

    public String getAuthUrl() {
        return provider.getAuthUrl();
    }

    public String getTokenUrl() {
        return provider.getTokenUrl();
    }

    public String getUserInfoUrl() {
        return provider.getUserInfoUrl();
    }

    public String getProviderId(){
        return this.providerId;
    }
}
