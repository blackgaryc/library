package com.blackgaryc.library.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
    @TableField(value = "object")
    private String object;

    /**
     * 
     */
    @TableField(value = "md5")
    private String md5;

    /**
     * 
     */
    @TableField(value = "uid")
    private Long uid;

    /**
     * 
     */
    @TableField(value = "size")
    private Long size;

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
    public String getObject() {
        return object;
    }

    /**
     * 
     */
    public void setObject(String object) {
        this.object = object;
    }

    /**
     * 
     */
    public String getMd5() {
        return md5;
    }

    /**
     * 
     */
    public void setMd5(String md5) {
        this.md5 = md5;
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

    /**
     * 
     */
    public Long getSize() {
        return size;
    }

    /**
     * 
     */
    public void setSize(Long size) {
        this.size = size;
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
            && (this.getObject() == null ? other.getObject() == null : this.getObject().equals(other.getObject()))
            && (this.getMd5() == null ? other.getMd5() == null : this.getMd5().equals(other.getMd5()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getSize() == null ? other.getSize() == null : this.getSize().equals(other.getSize()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getFilename() == null) ? 0 : getFilename().hashCode());
        result = prime * result + ((getExtension() == null) ? 0 : getExtension().hashCode());
        result = prime * result + ((getMimetype() == null) ? 0 : getMimetype().hashCode());
        result = prime * result + ((getObject() == null) ? 0 : getObject().hashCode());
        result = prime * result + ((getMd5() == null) ? 0 : getMd5().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getSize() == null) ? 0 : getSize().hashCode());
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
        sb.append(", object=").append(object);
        sb.append(", md5=").append(md5);
        sb.append(", uid=").append(uid);
        sb.append(", size=").append(size);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}