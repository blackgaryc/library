package com.blackgaryc.library.core.mq.resut;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class UserIdentity implements Serializable {
    @JsonProperty("principalId")
    public String getPrincipalId() { 
		 return this.principalId; } 
    public void setPrincipalId(String principalId) { 
		 this.principalId = principalId; } 
    String principalId;

    @Override
    public String toString() {
        return "UserIdentity{" +
                "principalId='" + principalId + '\'' +
                '}';
    }
}
