package com.blackgaryc.library.core.mq.resut;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ResponseElements implements Serializable {
    @JsonProperty("content-length")
    public String getContentLength() {
		 return this.contentLength; }
    public void setContentLength(String contentLength) {
		 this.contentLength = contentLength; }
    String contentLength;
    @JsonProperty("x-amz-request-id") 
    public String getXAmzRequestId() {
		 return this.xAmzRequestId; }
    public void setXAmzRequestId(String xAmzRequestId) {
		 this.xAmzRequestId= xAmzRequestId; }
    String xAmzRequestId;
    @JsonProperty("x-minio-deployment-id") 
    public String getXMinioDeploymentId() {
		 return this.xMinioDeploymentId; }
    public void setXMinioDeploymentId(String xMinioDeploymentId) {
		 this.xMinioDeploymentId = xMinioDeploymentId; }
    String xMinioDeploymentId;
    @JsonProperty("x-minio-origin-endpoint") 
    public String getXMinioOriginEndpoint() {
		 return this.xMinioOriginEndpoint; }
    public void setXMinioOriginEndpoint(String xMinioOriginEndpoint) {
		 this.xMinioOriginEndpoint = xMinioOriginEndpoint; }
    String xMinioOriginEndpoint;

    @Override
    public String toString() {
        return "ResponseElements{" +
                "contentLength='" + contentLength + '\'' +
                ", xAmzRequestId='" + xAmzRequestId + '\'' +
                ", xMinioDeploymentId='" + xMinioDeploymentId + '\'' +
                ", xMinioOriginEndpoint='" + xMinioOriginEndpoint + '\'' +
                '}';
    }
}
