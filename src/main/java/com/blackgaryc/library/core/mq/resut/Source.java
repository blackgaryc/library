package com.blackgaryc.library.core.mq.resut;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Source implements Serializable {
    @JsonProperty("host")
    public String getHost() { 
		 return this.host; } 
    public void setHost(String host) { 
		 this.host = host; } 
    String host;
    @JsonProperty("port") 
    public String getPort() { 
		 return this.port; } 
    public void setPort(String port) { 
		 this.port = port; } 
    String port;
    @JsonProperty("userAgent") 
    public String getUserAgent() { 
		 return this.userAgent; } 
    public void setUserAgent(String userAgent) { 
		 this.userAgent = userAgent; } 
    String userAgent;

    @Override
    public String toString() {
        return "Source{" +
                "host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", userAgent='" + userAgent + '\'' +
                '}';
    }
}
