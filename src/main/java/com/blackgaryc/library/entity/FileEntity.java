package com.blackgaryc.library.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 
 * @TableName basics_file
 */
@TableName(value ="basics_file")
public class FileEntity implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id")
    private String id;

    /**
     * 文件名称
     */
    @TableField(value = "filename")
    private String filename;

    /**
     * 文件扩展名
     */
    @TableField(value = "extension")
    private String extension;

    /**
     * 文件mime
     */
    @TableField(value = "mimetype")
    private String mimetype;

    /**
     * 对象存储位置
     */
    @TableField(value = "object")
    private String object;

    /**
     * md5散列值
     */
    @TableField(value = "md5")
    private String md5;

    /**
     * 上传用户id
     */
    @TableField(value = "uid")
    private Long uid;

    /**
     * 文件大小
     */
    @TableField(value = "size")
    private Long size;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 文件名称
     */
    public String getFilename() {
        return filename;
    }

    /**
     * 文件名称
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * 文件扩展名
     */
    public String getExtension() {
        return extension;
    }

    /**
     * 文件扩展名
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }

    /**
     * 文件mime
     */
    public String getMimetype() {
        return mimetype;
    }

    /**
     * 文件mime
     */
    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    /**
     * 对象存储位置
     */
    public String getObject() {
        return object;
    }

    /**
     * 对象存储位置
     */
    public void setObject(String object) {
        this.object = object;
    }

    /**
     * md5散列值
     */
    public String getMd5() {
        return md5;
    }

    /**
     * md5散列值
     */
    public void setMd5(String md5) {
        this.md5 = md5;
    }

    /**
     * 上传用户id
     */
    public Long getUid() {
        return uid;
    }

    /**
     * 上传用户id
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * 文件大小
     */
    public Long getSize() {
        return size;
    }

    /**
     * 文件大小
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