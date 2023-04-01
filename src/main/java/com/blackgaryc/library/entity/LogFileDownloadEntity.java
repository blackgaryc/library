package com.blackgaryc.library.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @TableName sys_log_file_download
 */
@TableName(value ="sys_log_file_download")
public class LogFileDownloadEntity implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    @TableField(value = "uid")
    private Long uid;

    /**
     * 文件id
     */
    @TableField(value = "file_id")
    private String fileId;

    /**
     * 书名
     */
    @TableField(value = "book_name")
    private String bookName;

    /**
     * 下载时间
     */
    @TableField(value = "time")
    private LocalDateTime time;

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
     * 用户id
     */
    public Long getUid() {
        return uid;
    }

    /**
     * 用户id
     */
    public void setUid(Long uid) {
        this.uid = uid;
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

    /**
     * 书名
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * 书名
     */
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    /**
     * 下载时间
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * 下载时间
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
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
        LogFileDownloadEntity other = (LogFileDownloadEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getFileId() == null ? other.getFileId() == null : this.getFileId().equals(other.getFileId()))
            && (this.getBookName() == null ? other.getBookName() == null : this.getBookName().equals(other.getBookName()))
            && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getFileId() == null) ? 0 : getFileId().hashCode());
        result = prime * result + ((getBookName() == null) ? 0 : getBookName().hashCode());
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", uid=").append(uid);
        sb.append(", fileId=").append(fileId);
        sb.append(", bookName=").append(bookName);
        sb.append(", time=").append(time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}