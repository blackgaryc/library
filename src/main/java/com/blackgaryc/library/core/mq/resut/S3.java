package com.blackgaryc.library.core.mq.resut;

import com.fasterxml.jackson.annotation.JsonProperty;

public class S3{
    @JsonProperty("s3SchemaVersion")
    public String getS3SchemaVersion() { 
		 return this.s3SchemaVersion; } 
    public void setS3SchemaVersion(String s3SchemaVersion) { 
		 this.s3SchemaVersion = s3SchemaVersion; } 
    String s3SchemaVersion;
    @JsonProperty("configurationId") 
    public String getConfigurationId() { 
		 return this.configurationId; } 
    public void setConfigurationId(String configurationId) { 
		 this.configurationId = configurationId; } 
    String configurationId;
    @JsonProperty("bucket") 
    public Bucket getBucket() { 
		 return this.bucket; } 
    public void setBucket(Bucket bucket) { 
		 this.bucket = bucket; } 
    Bucket bucket;
    @JsonProperty("object") 
    public MinioObject getObject() {
		 return this.object; } 
    public void setObject(MinioObject object) {
		 this.object = object; } 
    MinioObject object;

    @Override
    public String toString() {
        return "S3{" +
                "s3SchemaVersion='" + s3SchemaVersion + '\'' +
                ", configurationId='" + configurationId + '\'' +
                ", bucket=" + bucket +
                ", object=" + object +
                '}';
    }
}
