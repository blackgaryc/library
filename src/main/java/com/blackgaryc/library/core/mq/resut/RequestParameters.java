package com.blackgaryc.library.core.mq.resut;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestParameters{
    @JsonProperty("principalId")
    public String getPrincipalId() { 
		 return this.principalId; } 
    public void setPrincipalId(String principalId) { 
		 this.principalId = principalId; } 
    String principalId;
    @JsonProperty("region") 
    public String getRegion() { 
		 return this.region; } 
    public void setRegion(String region) { 
		 this.region = region; } 
    String region;
    @JsonProperty("sourceIPAddress") 
    public String getSourceIPAddress() { 
		 return this.sourceIPAddress; } 
    public void setSourceIPAddress(String sourceIPAddress) { 
		 this.sourceIPAddress = sourceIPAddress; } 
    String sourceIPAddress;

    @Override
    public String toString() {
        return "RequestParameters{" +
                "principalId='" + principalId + '\'' +
                ", region='" + region + '\'' +
                ", sourceIPAddress='" + sourceIPAddress + '\'' +
                '}';
    }
}
