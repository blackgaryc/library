package com.blackgaryc.library.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 
 * @TableName file
 */
@TableName(value ="file")
public class FileEntity implements Serializable {
    /**
     * 
     */
    @TableId(value = "id")
    private String id;

    /**
     * 
     */
    @TableField(value = "filename")
    private String filename;

    /**
     * 
     */
    @TableField(value = "extension")
    private String extension;

    /**
     * 
     */
    @TableField(value = "mimetype")
    private String mimetype;

    /**
     * 
     */
    @TableField(value = "cos_config_id")
    private Integer cosConfigId;

    /**
     * 
     */
    @TableField(value = "cos_path")
    private String cosPath;

    /**
     * 
     */
    @TableField(value = "sha256")
    private String sha256;

    /**
     * 
     */
    @TableField(value = "uid")
    private Long uid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public String getId() {
        return id;
    }

    /**
     * 
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     */
    public String getFilename() {
        return filename;
    }

    /**
     * 
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * 
     */
    public String getExtension() {
        return extension;
    }

    /**
     * 
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }

    /**
     * 
     */
    public String getMimetype() {
        return mimetype;
    }

    /**
     * 
     */
    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    /**
     * 
     */
    public Integer getCosConfigId() {
        return cosConfigId;
    }

    /**
     * 
     */
    public void setCosConfigId(Integer cosConfigId) {
        this.cosConfigId = cosConfigId;
    }

    /**
     * 
     */
    public String getCosPath() {
        return cosPath;
    }

    /**
     * 
     */
    public void setCosPath(String cosPath) {
        this.cosPath = cosPath;
    }

    /**
     * 
     */
    public String getSha256() {
        return sha256;
    }

    /**
     * 
     */
    public void setSha256(String sha256) {
        this.sha256 = sha256;
    }

    /**
     * 
     */
    public Long getUid() {
        return uid;
    }

    /**
     * 
     */
    public void setUid(Long uid) {
        this.uid = uid;
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
        FileEntity other = (FileEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getFilename() == null ? other.getFilename() == null : this.getFilename().equals(other.getFilename()))
            && (this.getExtension() == null ? other.getExtension() == null : this.getExtension().equals(other.getExtension()))
            && (this.getMimetype() == null ? other.getMimetype() == null : this.getMimetype().equals(other.getMimetype()))
            && (this.getCosConfigId() == null ? other.getCosConfigId() == null : this.getCosConfigId().equals(other.getCosConfigId()))
            && (this.getCosPath() == null ? other.getCosPath() == null : this.getCosPath().equals(other.getCosPath()))
            && (this.getSha256() == null ? other.getSha256() == null : this.getSha256().equals(other.getSha256()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getFilename() == null) ? 0 : getFilename().hashCode());
        result = prime * result + ((getExtension() == null) ? 0 : getExtension().hashCode());
        result = prime * result + ((getMimetype() == null) ? 0 : getMimetype().hashCode());
        result = prime * result + ((getCosConfigId() == null) ? 0 : getCosConfigId().hashCode());
        result = prime * result + ((getCosPath() == null) ? 0 : getCosPath().hashCode());
        result = prime * result + ((getSha256() == null) ? 0 : getSha256().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", filename=").append(filename);
        sb.append(", extension=").append(extension);
        sb.append(", mimetype=").append(mimetype);
        sb.append(", cosConfigId=").append(cosConfigId);
        sb.append(", cosPath=").append(cosPath);
        sb.append(", sha256=").append(sha256);
        sb.append(", uid=").append(uid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}