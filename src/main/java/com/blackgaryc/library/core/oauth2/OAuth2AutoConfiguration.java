package com.blackgaryc.library.core.oauth2;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

public class OAuth2AutoConfiguration {
    @Import(OAuth2Config.class)
    @Configuration
    protected static class OAuth2PropertyAutoConfiguration{

    }
}
