package com.blackgaryc.library.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 
 * @TableName basics_book_detail
 */
@TableName(value ="basics_book_detail")
public class BookDetailEntity implements Serializable {
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

    /**
     * 文件页数
     */
    @TableField(value = "page")
    private Integer page;

    /**
     * 文件大小
     */
    @TableField(value = "size")
    private Long size;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

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

    /**
     * 文件页数
     */
    public Integer getPage() {
        return page;
    }

    /**
     * 文件页数
     */
    public void setPage(Integer page) {
        this.page = page;
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
        BookDetailEntity other = (BookDetailEntity) that;
        return (this.getBookId() == null ? other.getBookId() == null : this.getBookId().equals(other.getBookId()))
            && (this.getFileId() == null ? other.getFileId() == null : this.getFileId().equals(other.getFileId()))
            && (this.getPage() == null ? other.getPage() == null : this.getPage().equals(other.getPage()))
            && (this.getSize() == null ? other.getSize() == null : this.getSize().equals(other.getSize()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBookId() == null) ? 0 : getBookId().hashCode());
        result = prime * result + ((getFileId() == null) ? 0 : getFileId().hashCode());
        result = prime * result + ((getPage() == null) ? 0 : getPage().hashCode());
        result = prime * result + ((getSize() == null) ? 0 : getSize().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", bookId=").append(bookId);
        sb.append(", fileId=").append(fileId);
        sb.append(", page=").append(page);
        sb.append(", size=").append(size);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}