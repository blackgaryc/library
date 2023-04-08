package com.blackgaryc.library.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName basics_book
 */
@TableName(value ="basics_book")
public class BookEntity implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 书名
     */
    @TableField(value = "title")
    private String title;

    /**
     * 作者名称
     */
    @TableField(value = "author")
    private String author;

    /**
     * 图书描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 语言
     */
    @TableField(value = "language")
    private String language;

    /**
     * 图书分类id
     */
    @TableField(value = "category_id")
    private Integer categoryId;

    /**
     * 图书缩略图
     */
    @TableField(value = "thumbnail")
    private String thumbnail;

    /**
     * 图书状态
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 创建者id
     */
    @TableField(value = "created_uid")
    private Long createdUid;

    /**
     * 出版社id
     */
    @TableField(value = "publisher_id")
    private Integer publisherId;

    /**
     * 图书的ISBN10
     */
    @TableField(value = "isbn10")
    private String isbn10;

    /**
     * 图书的ISBN13
     */
    @TableField(value = "isbn13")
    private String isbn13;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

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
     * 书名
     */
    public String getTitle() {
        return title;
    }

    /**
     * 书名
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 作者名称
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 作者名称
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 图书描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 图书描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 语言
     */
    public String getLanguage() {
        return language;
    }

    /**
     * 语言
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * 图书分类id
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * 图书分类id
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 图书缩略图
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * 图书缩略图
     */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * 图书状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 图书状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 创建者id
     */
    public Long getCreatedUid() {
        return createdUid;
    }

    /**
     * 创建者id
     */
    public void setCreatedUid(Long createdUid) {
        this.createdUid = createdUid;
    }

    /**
     * 出版社id
     */
    public Integer getPublisherId() {
        return publisherId;
    }

    /**
     * 出版社id
     */
    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    /**
     * 图书的ISBN10
     */
    public String getIsbn10() {
        return isbn10;
    }

    /**
     * 图书的ISBN10
     */
    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    /**
     * 图书的ISBN13
     */
    public String getIsbn13() {
        return isbn13;
    }

    /**
     * 图书的ISBN13
     */
    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        BookEntity other = (BookEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getAuthor() == null ? other.getAuthor() == null : this.getAuthor().equals(other.getAuthor()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getLanguage() == null ? other.getLanguage() == null : this.getLanguage().equals(other.getLanguage()))
            && (this.getCategoryId() == null ? other.getCategoryId() == null : this.getCategoryId().equals(other.getCategoryId()))
            && (this.getThumbnail() == null ? other.getThumbnail() == null : this.getThumbnail().equals(other.getThumbnail()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreatedUid() == null ? other.getCreatedUid() == null : this.getCreatedUid().equals(other.getCreatedUid()))
            && (this.getPublisherId() == null ? other.getPublisherId() == null : this.getPublisherId().equals(other.getPublisherId()))
            && (this.getIsbn10() == null ? other.getIsbn10() == null : this.getIsbn10().equals(other.getIsbn10()))
            && (this.getIsbn13() == null ? other.getIsbn13() == null : this.getIsbn13().equals(other.getIsbn13()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getAuthor() == null) ? 0 : getAuthor().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getLanguage() == null) ? 0 : getLanguage().hashCode());
        result = prime * result + ((getCategoryId() == null) ? 0 : getCategoryId().hashCode());
        result = prime * result + ((getThumbnail() == null) ? 0 : getThumbnail().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreatedUid() == null) ? 0 : getCreatedUid().hashCode());
        result = prime * result + ((getPublisherId() == null) ? 0 : getPublisherId().hashCode());
        result = prime * result + ((getIsbn10() == null) ? 0 : getIsbn10().hashCode());
        result = prime * result + ((getIsbn13() == null) ? 0 : getIsbn13().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", author=").append(author);
        sb.append(", description=").append(description);
        sb.append(", language=").append(language);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", thumbnail=").append(thumbnail);
        sb.append(", status=").append(status);
        sb.append(", createdUid=").append(createdUid);
        sb.append(", publisherId=").append(publisherId);
        sb.append(", isbn10=").append(isbn10);
        sb.append(", isbn13=").append(isbn13);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}