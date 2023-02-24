package com.blackgaryc.library.core.mq.resut;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class UserMetadata implements Serializable {
    @JsonProperty("contentType")
    String contentType;
    public String getContentType() {
		 return this.contentType; }
    public void setContentType(String contentType) {
		 this.contentType = contentType; }

    @Override
    public String toString() {
        return "UserMetadata{" +
                "contentType='" + contentType + '\'' +
                '}';
    }
}
