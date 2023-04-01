package com.blackgaryc.library.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 
 * @TableName basics_booklist_books
 */
@TableName(value ="basics_booklist_books")
public class BooklistBooksEntity implements Serializable {
    /**
     * 书单id
     */
    @TableField(value = "booklist_id")
    private String booklistId;

    /**
     * 图书id
     */
    @TableField(value = "book_id")
    private Long bookId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 书单id
     */
    public String getBooklistId() {
        return booklistId;
    }

    /**
     * 书单id
     */
    public void setBooklistId(String booklistId) {
        this.booklistId = booklistId;
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
        BooklistBooksEntity other = (BooklistBooksEntity) that;
        return (this.getBooklistId() == null ? other.getBooklistId() == null : this.getBooklistId().equals(other.getBooklistId()))
            && (this.getBookId() == null ? other.getBookId() == null : this.getBookId().equals(other.getBookId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBooklistId() == null) ? 0 : getBooklistId().hashCode());
        result = prime * result + ((getBookId() == null) ? 0 : getBookId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", booklistId=").append(booklistId);
        sb.append(", bookId=").append(bookId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}