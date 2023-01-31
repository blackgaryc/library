package com.blackgaryc.library.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 
 * @TableName cos_config
 */
@TableName(value ="cos_config")
public class CosConfigEntity implements Serializable {
    /**
     * 
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 
     */
    @TableField(value = "endpoint")
    private String endpoint;

    /**
     * 
     */
    @TableField(value = "access_key")
    private String accessKey;

    /**
     * 
     */
    @TableField(value = "secret_key")
    private String secretKey;

    /**
     * 
     */
    @TableField(value = "region")
    private String region;

    /**
     * 
     */
    @TableField(value = "bucket")
    private String bucket;

    /**
     * 
     */
    @TableField(value = "max_bucket_size")
    private Long maxBucketSize;

    /**
     * 
     */
    @TableField(value = "current_bucket_size")
    private Long currentBucketSize;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     */
    public String getEndpoint() {
        return endpoint;
    }

    /**
     * 
     */
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * 
     */
    public String getAccessKey() {
        return accessKey;
    }

    /**
     * 
     */
    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    /**
     * 
     */
    public String getSecretKey() {
        return secretKey;
    }

    /**
     * 
     */
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    /**
     * 
     */
    public String getRegion() {
        return region;
    }

    /**
     * 
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * 
     */
    public String getBucket() {
        return bucket;
    }

    /**
     * 
     */
    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    /**
     * 
     */
    public Long getMaxBucketSize() {
        return maxBucketSize;
    }

    /**
     * 
     */
    public void setMaxBucketSize(Long maxBucketSize) {
        this.maxBucketSize = maxBucketSize;
    }

    /**
     * 
     */
    public Long getCurrentBucketSize() {
        return currentBucketSize;
    }

    /**
     * 
     */
    public void setCurrentBucketSize(Long currentBucketSize) {
        this.currentBucketSize = currentBucketSize;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        CosConfigEntity other = (CosConfigEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getEndpoint() == null ? other.getEndpoint() == null : this.getEndpoint().equals(other.getEndpoint()))
            && (this.getAccessKey() == null ? other.getAccessKey() == null : this.getAccessKey().equals(other.getAccessKey()))
            && (this.getSecretKey() == null ? other.getSecretKey() == null : this.getSecretKey().equals(other.getSecretKey()))
            && (this.getRegion() == null ? other.getRegion() == null : this.getRegion().equals(other.getRegion()))
            && (this.getBucket() == null ? other.getBucket() == null : this.getBucket().equals(other.getBucket()))
            && (this.getMaxBucketSize() == null ? other.getMaxBucketSize() == null : this.getMaxBucketSize().equals(other.getMaxBucketSize()))
            && (this.getCurrentBucketSize() == null ? other.getCurrentBucketSize() == null : this.getCurrentBucketSize().equals(other.getCurrentBucketSize()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getEndpoint() == null) ? 0 : getEndpoint().hashCode());
        result = prime * result + ((getAccessKey() == null) ? 0 : getAccessKey().hashCode());
        result = prime * result + ((getSecretKey() == null) ? 0 : getSecretKey().hashCode());
        result = prime * result + ((getRegion() == null) ? 0 : getRegion().hashCode());
        result = prime * result + ((getBucket() == null) ? 0 : getBucket().hashCode());
        result = prime * result + ((getMaxBucketSize() == null) ? 0 : getMaxBucketSize().hashCode());
        result = prime * result + ((getCurrentBucketSize() == null) ? 0 : getCurrentBucketSize().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", endpoint=").append(endpoint);
        sb.append(", accessKey=").append(accessKey);
        sb.append(", secretKey=").append(secretKey);
        sb.append(", region=").append(region);
        sb.append(", bucket=").append(bucket);
        sb.append(", maxBucketSize=").append(maxBucketSize);
        sb.append(", currentBucketSize=").append(currentBucketSize);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}