package com.blackgaryc.library.core.mq.resut;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MinioObject {
    @JsonProperty("key")
    public String getKey() { 
		 return this.key; } 
    public void setKey(String key) { 
		 this.key = key; } 
    String key;
    @JsonProperty("size") 
    public int getSize() { 
		 return this.size; } 
    public void setSize(int size) { 
		 this.size = size; } 
    int size;
    @JsonProperty("eTag") 
    public String getETag() { 
		 return this.eTag; } 
    public void setETag(String eTag) { 
		 this.eTag = eTag; } 
    String eTag;
    @JsonProperty("content-type")
    public String getContentType() { 
		 return this.contentType; } 
    public void setContentType(String contentType) { 
		 this.contentType = contentType; } 
    String contentType;
    @JsonProperty("userMetadata") 
    public UserMetadata getUserMetadata() { 
		 return this.userMetadata; } 
    public void setUserMetadata(UserMetadata userMetadata) { 
		 this.userMetadata = userMetadata; } 
    UserMetadata userMetadata;
    @JsonProperty("sequencer") 
    public String getSequencer() { 
		 return this.sequencer; } 
    public void setSequencer(String sequencer) { 
		 this.sequencer = sequencer; } 
    String sequencer;

    @Override
    public String toString() {
        return "MinioObject{" +
                "key='" + key + '\'' +
                ", size=" + size +
                ", eTag='" + eTag + '\'' +
                ", contentType='" + contentType + '\'' +
                ", userMetadata=" + userMetadata +
                ", sequencer='" + sequencer + '\'' +
                '}';
    }
}
