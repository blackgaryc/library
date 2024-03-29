package com.blackgaryc.library.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 
 * @TableName basics_book_tags
 */
@TableName(value ="basics_book_tags")
public class BookTagsEntity implements Serializable {
    /**
     * 图书id
     */
    @TableField(value = "book_id")
    private Long bookId;

    /**
     * 标签id
     */
    @TableField(value = "tag_id")
    private Integer tagId;

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
     * 标签id
     */
    public Integer getTagId() {
        return tagId;
    }

    /**
     * 标签id
     */
    public void setTagId(Integer tagId) {
        this.tagId = tagId;
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
        BookTagsEntity other = (BookTagsEntity) that;
        return (this.getBookId() == null ? other.getBookId() == null : this.getBookId().equals(other.getBookId()))
            && (this.getTagId() == null ? other.getTagId() == null : this.getTagId().equals(other.getTagId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBookId() == null) ? 0 : getBookId().hashCode());
        result = prime * result + ((getTagId() == null) ? 0 : getTagId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", bookId=").append(bookId);
        sb.append(", tagId=").append(tagId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}