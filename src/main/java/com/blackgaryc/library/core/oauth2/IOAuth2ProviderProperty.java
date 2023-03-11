package com.blackgaryc.library.core.oauth2;

public interface IOAuth2ProviderProperty {
    String getUserIdKey();

    String getAuthUrl();

    String getTokenUrl();

    String getUserInfoUrl();
}
