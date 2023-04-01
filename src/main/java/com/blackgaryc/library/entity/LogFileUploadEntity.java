package com.blackgaryc.library.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @TableName sys_log_file_upload
 */
@TableName(value ="sys_log_file_upload")
public class LogFileUploadEntity implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 文件名称
     */
    @TableField(value = "filename")
    private String filename;

    /**
     * 散列值md5
     */
    @TableField(value = "md5")
    private String md5;

    /**
     * 云存储文件位置
     */
    @TableField(value = "object")
    private String object;

    /**
     * 0待处理 1待审核 2确认 3拒绝
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 额外消息，用户提示用户处理结果的消息
     */
    @TableField(value = "message")
    private String message;

    /**
     * 上传文件的用户id
     */
    @TableField(value = "uid")
    private String uid;

    /**
     * 上传时间
     */
    @TableField(value = "upload_time")
    private LocalDateTime uploadTime;

    /**
     * 图书id
     */
    @TableField(value = "book_id")
    private Long bookId;

    /**
     * 文件id
     */
    @TableField(value = "file_id")
    private String fileId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键
     */
    public void setId(Long id) {
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
     * 散列值md5
     */
    public String getMd5() {
        return md5;
    }

    /**
     * 散列值md5
     */
    public void setMd5(String md5) {
        this.md5 = md5;
    }

    /**
     * 云存储文件位置
     */
    public String getObject() {
        return object;
    }

    /**
     * 云存储文件位置
     */
    public void setObject(String object) {
        this.object = object;
    }

    /**
     * 0待处理 1待审核 2确认 3拒绝
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 0待处理 1待审核 2确认 3拒绝
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 额外消息，用户提示用户处理结果的消息
     */
    public String getMessage() {
        return message;
    }

    /**
     * 额外消息，用户提示用户处理结果的消息
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 上传文件的用户id
     */
    public String getUid() {
        return uid;
    }

    /**
     * 上传文件的用户id
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * 上传时间
     */
    public LocalDateTime getUploadTime() {
        return uploadTime;
    }

    /**
     * 上传时间
     */
    public void setUploadTime(LocalDateTime uploadTime) {
        this.uploadTime = uploadTime;
    }

    /**
     * 图书id
     */
    public Long getBookId() {
        return bookId;
    }

    /**
     * 图书id
     */
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    /**
     * 文件id
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * 文件id
     */
    public void setFileId(String fileId) {
        this.fileId = fileId;
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
        LogFileUploadEntity other = (LogFileUploadEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getFilename() == null ? other.getFilename() == null : this.getFilename().equals(other.getFilename()))
            && (this.getMd5() == null ? other.getMd5() == null : this.getMd5().equals(other.getMd5()))
            && (this.getObject() == null ? other.getObject() == null : this.getObject().equals(other.getObject()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getMessage() == null ? other.getMessage() == null : this.getMessage().equals(other.getMessage()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getUploadTime() == null ? other.getUploadTime() == null : this.getUploadTime().equals(other.getUploadTime()))
            && (this.getBookId() == null ? other.getBookId() == null : this.getBookId().equals(other.getBookId()))
            && (this.getFileId() == null ? other.getFileId() == null : this.getFileId().equals(other.getFileId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getFilename() == null) ? 0 : getFilename().hashCode());
        result = prime * result + ((getMd5() == null) ? 0 : getMd5().hashCode());
        result = prime * result + ((getObject() == null) ? 0 : getObject().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getMessage() == null) ? 0 : getMessage().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getUploadTime() == null) ? 0 : getUploadTime().hashCode());
        result = prime * result + ((getBookId() == null) ? 0 : getBookId().hashCode());
        result = prime * result + ((getFileId() == null) ? 0 : getFileId().hashCode());
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
        sb.append(", md5=").append(md5);
        sb.append(", object=").append(object);
        sb.append(", status=").append(status);
        sb.append(", message=").append(message);
        sb.append(", uid=").append(uid);
        sb.append(", uploadTime=").append(uploadTime);
        sb.append(", bookId=").append(bookId);
        sb.append(", fileId=").append(fileId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}