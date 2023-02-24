package com.blackgaryc.library.core.mq.resut;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

public class Record implements Serializable {
    @JsonProperty("eventVersion")
    public String getEventVersion() { 
		 return this.eventVersion; } 
    public void setEventVersion(String eventVersion) { 
		 this.eventVersion = eventVersion; } 
    String eventVersion;
    @JsonProperty("eventSource") 
    public String getEventSource() { 
		 return this.eventSource; } 
    public void setEventSource(String eventSource) { 
		 this.eventSource = eventSource; } 
    String eventSource;
    @JsonProperty("awsRegion") 
    public String getAwsRegion() { 
		 return this.awsRegion; } 
    public void setAwsRegion(String awsRegion) { 
		 this.awsRegion = awsRegion; } 
    String awsRegion;
    @JsonProperty("eventTime") 
    public Date getEventTime() {
		 return this.eventTime; } 
    public void setEventTime(Date eventTime) { 
		 this.eventTime = eventTime; } 
    Date eventTime;
    @JsonProperty("eventName") 
    public String getEventName() { 
		 return this.eventName; } 
    public void setEventName(String eventName) { 
		 this.eventName = eventName; } 
    String eventName;
    @JsonProperty("userIdentity") 
    public UserIdentity getUserIdentity() { 
		 return this.userIdentity; } 
    public void setUserIdentity(UserIdentity userIdentity) { 
		 this.userIdentity = userIdentity; } 
    UserIdentity userIdentity;
    @JsonProperty("requestParameters") 
    public RequestParameters getRequestParameters() { 
		 return this.requestParameters; } 
    public void setRequestParameters(RequestParameters requestParameters) { 
		 this.requestParameters = requestParameters; } 
    RequestParameters requestParameters;
    @JsonProperty("responseElements") 
    public ResponseElements getResponseElements() { 
		 return this.responseElements; } 
    public void setResponseElements(ResponseElements responseElements) { 
		 this.responseElements = responseElements; } 
    ResponseElements responseElements;
    @JsonProperty("s3") 
    public S3 getS3() { 
		 return this.s3; } 
    public void setS3(S3 s3) { 
		 this.s3 = s3; } 
    S3 s3;
    @JsonProperty("source") 
    public Source getSource() { 
		 return this.source; } 
    public void setSource(Source source) { 
		 this.source = source; } 
    Source source;

    @Override
    public String toString() {
        return "Record{" +
                "eventVersion='" + eventVersion + '\'' +
                ", eventSource='" + eventSource + '\'' +
                ", awsRegion='" + awsRegion + '\'' +
                ", eventTime=" + eventTime +
                ", eventName='" + eventName + '\'' +
                ", userIdentity=" + userIdentity +
                ", requestParameters=" + requestParameters +
                ", responseElements=" + responseElements +
                ", s3=" + s3 +
                ", source=" + source +
                '}';
    }
}
