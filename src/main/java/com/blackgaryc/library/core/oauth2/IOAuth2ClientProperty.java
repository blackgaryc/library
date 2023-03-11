package com.blackgaryc.library.core.oauth2;

public interface IOAuth2ClientProperty {
    String getCallBackUrl();

    String getProviderId();

    String getName();

    String getAccessKey();

    String getSecretKey();
}
