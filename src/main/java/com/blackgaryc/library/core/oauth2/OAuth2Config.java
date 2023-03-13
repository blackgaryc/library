package com.blackgaryc.library.core.oauth2;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;

@ConfigurationProperties(prefix = "library.login.oauth2")
public class OAuth2Config implements InitializingBean {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private Map<String, OAuth2ProviderProperty> provider;
    private Map<String, OAuth2ClientProperty> client;

    private final Map<String,OAuth2Property> properties = new Hashtable<>();

    public Map<String,OAuth2Property> getProperties() {
        //返回一个不可修改的map
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
    // 设置为内部类，防止在外部进行类型强转进而修改数据
    private static class OAuth2ClientProperty implements Serializable, IOAuth2ClientProperty {
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
    private static class OAuth2ProviderProperty implements Serializable, IOAuth2ProviderProperty {
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

}
