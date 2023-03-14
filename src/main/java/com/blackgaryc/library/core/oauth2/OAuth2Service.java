package com.blackgaryc.library.core.oauth2;

import com.nimbusds.oauth2.sdk.*;
import com.nimbusds.oauth2.sdk.auth.ClientAuthentication;
import com.nimbusds.oauth2.sdk.auth.ClientSecretBasic;
import com.nimbusds.oauth2.sdk.auth.Secret;
import com.nimbusds.oauth2.sdk.http.HTTPRequest;
import com.nimbusds.oauth2.sdk.id.ClientID;
import com.nimbusds.oauth2.sdk.id.State;
import com.nimbusds.oauth2.sdk.token.AccessToken;
import com.nimbusds.oauth2.sdk.token.RefreshToken;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class OAuth2Service {
    private final Map<String,OAuth2Property> properties;
    private final Scope scope = new Scope();
    private final State state = new State();
    private final RestTemplate restTemplate = new RestTemplate();


    public OAuth2Service(OAuth2Config oAuth2Config) {
        this.properties = oAuth2Config.getProperties();
    }

    public String getAuthRedirectUrl(String clientId){
        OAuth2Property property = properties.get(clientId);
        Assert.notNull(property,"unable to find oauth service by id["+clientId+"]");
        ClientID clientID = new ClientID(property.getAccessKey());
        AuthorizationRequest request = new AuthorizationRequest.Builder(ResponseType.CODE, clientID)
                .scope(scope).state(state)
                .redirectionURI(URI.create(property.getCallBackUrl()))
                .endpointURI(URI.create(property.getAuthUrl()))
                .build();

        return request.toURI().toString();
    }

    public String getId(String clientId,String redirectUrl){
        OAuth2Property property = properties.get(clientId);
        Assert.notNull(property,"unable to find oauth service by id["+clientId+"]");

        AuthorizationResponse authorizationResponse = null;
        try {
            //read code and state form redirect url
            authorizationResponse = AuthorizationResponse.parse(URI.create(redirectUrl));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        // Check the returned state parameter, must match the original
        if (!Objects.equals(state, authorizationResponse.getState())) {
            // Unexpected or tampered response, stop!!!
            throw new RuntimeException(state.toString());
        }
        if (!authorizationResponse.indicatesSuccess()) {
            // The request was denied or some error occurred
            AuthorizationErrorResponse errorResponse = authorizationResponse.toErrorResponse();
            throw new RuntimeException(errorResponse.toString());
        }

        AuthorizationSuccessResponse successResponse = authorizationResponse.toSuccessResponse();
        AuthorizationCode authorizationCode = successResponse.getAuthorizationCode();
        // Construct the code grant from the code obtained from the authz endpoint
        // and the original callback URI used at the authz endpoint
        AuthorizationGrant codeGrant = new AuthorizationCodeGrant(authorizationCode, URI.create(property.getCallBackUrl()));
        // The credentials to authenticate the client at the token endpoint
        ClientAuthentication clientAuth = new ClientSecretBasic(new ClientID(property.getAccessKey()), new Secret(property.getSecretKey()));
        // Make the token request
        TokenRequest request = new TokenRequest(URI.create(property.getTokenUrl()), clientAuth, codeGrant);
        HTTPRequest httpRequest = request.toHTTPRequest();
        httpRequest.setAccept(MediaType.APPLICATION_JSON.toString());
        TokenResponse response = null;
        try {
            response = TokenResponse.parse(httpRequest.send());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (!response.indicatesSuccess()) {
            // We got an error response...
            TokenErrorResponse errorResponse = response.toErrorResponse();
            throw new RuntimeException(errorResponse.toString());
        }
        AccessTokenResponse accessTokenResponse = response.toSuccessResponse();
        // Get the access token, the server may also return a refresh token
        AccessToken accessToken = accessTokenResponse.getTokens().getAccessToken();
        RefreshToken refreshToken = accessTokenResponse.getTokens().getRefreshToken();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", "Bearer "+accessToken);
        HttpEntity<String> entity = new HttpEntity<>(null,headers);
        ResponseEntity<HashMap> result = restTemplate.exchange(property.getUserInfoUrl(), HttpMethod.GET, entity, HashMap.class);
        HashMap data = result.getBody();
        ////返回字符串类型的id用于关联本地账户
        return String.valueOf(data.get(property.getUserIdKey()));
    }


}
