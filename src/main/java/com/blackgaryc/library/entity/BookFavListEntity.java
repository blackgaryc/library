package com.blackgaryc.library.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 图书收藏表
 * @TableName book_fav_list
 */
@TableName(value ="book_fav_list")
public class BookFavListEntity implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    @TableField(value = "book_id")
    private Long bookId;

    /**
     * 
     */
    @TableField(value = "book_name")
    private String bookName;

    /**
     * 
     */
    @TableField(value = "book_img")
    private String bookImg;

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
    public Long getId() {
        return id;
    }

    /**
     * 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 
     */
    public Long getBookId() {
        return bookId;
    }

    /**
     * 
     */
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    /**
     * 
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * 
     */
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    /**
     * 
     */
    public String getBookImg() {
        return bookImg;
    }

    /**
     * 
     */
    public void setBookImg(String bookImg) {
        this.bookImg = bookImg;
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
        BookFavListEntity other = (BookFavListEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBookId() == null ? other.getBookId() == null : this.getBookId().equals(other.getBookId()))
            && (this.getBookName() == null ? other.getBookName() == null : this.getBookName().equals(other.getBookName()))
            && (this.getBookImg() == null ? other.getBookImg() == null : this.getBookImg().equals(other.getBookImg()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBookId() == null) ? 0 : getBookId().hashCode());
        result = prime * result + ((getBookName() == null) ? 0 : getBookName().hashCode());
        result = prime * result + ((getBookImg() == null) ? 0 : getBookImg().hashCode());
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
        sb.append(", bookId=").append(bookId);
        sb.append(", bookName=").append(bookName);
        sb.append(", bookImg=").append(bookImg);
        sb.append(", uid=").append(uid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}