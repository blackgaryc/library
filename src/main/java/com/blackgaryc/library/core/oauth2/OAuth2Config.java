package com.blackgaryc.library.core.oauth2;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "library.login.oauth2")
public class OAuth2Config implements InitializingBean {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private Map<String, OAuth2ProviderProperty> provider;
    private Map<String, OAuth2ClientProperty> client;

    private final Map<String,OAuth2Property> properties = new HashMap<>();

    public Map<String,OAuth2Property> getProperties() {
        return Collections.unmodifiableMap(properties);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        client.forEach((k, v) -> {
            String providerId = v.getProviderId();
            if (!provider.containsKey(providerId)) {
                log.error("unable to find providerId[{}]", v.getProviderId());
            }else {
                OAuth2Property e = new OAuth2Property(providerId,provider.get(providerId), v);
                properties.put(k,e);
            }
        });
    }

    public Map<String, OAuth2ProviderProperty> getProvider() {
        return Collections.unmodifiableMap(provider);
    }

    public void setProvider(Map<String, OAuth2ProviderProperty> provider) {
        this.provider = provider;
    }

    public Map<String, OAuth2ClientProperty> getClient() {
        return Collections.unmodifiableMap(client);
    }

    public void setClient(Map<String, OAuth2ClientProperty> client) {
        this.client = client;
    }
}
