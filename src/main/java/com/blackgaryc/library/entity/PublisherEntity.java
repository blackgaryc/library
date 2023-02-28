package com.blackgaryc.library.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 
 * @TableName publisher
 */
@TableName(value ="publisher")
public class PublisherEntity implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @TableField(value = "name")
    private String name;

    /**
     * 城市
     */
    @TableField(value = "city")
    private String city;

    /**
     * 类型
     */
    @TableField(value = "type")
    private String type;

    /**
     * 国际标准书号代码
     */
    @TableField(value = "isbn_code")
    private String isbnCode;

    /**
     * 出版社编号
     */
    @TableField(value = "isbn_publisher")
    private String isbnPublisher;

    /**
     * 中国 统一书号
     */
    @TableField(value = "china_union_publisher_id")
    private String chinaUnionPublisherId;

    /**
     * 备注
     */
    @TableField(value = "comments")
    private String comments;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     */
    public String getName() {
        return name;
    }

    /**
     * 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 城市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 类型
     */
    public String getType() {
        return type;
    }

    /**
     * 类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 国际标准书号代码
     */
    public String getIsbnCode() {
        return isbnCode;
    }

    /**
     * 国际标准书号代码
     */
    public void setIsbnCode(String isbnCode) {
        this.isbnCode = isbnCode;
    }

    /**
     * 出版社编号
     */
    public String getIsbnPublisher() {
        return isbnPublisher;
    }

    /**
     * 出版社编号
     */
    public void setIsbnPublisher(String isbnPublisher) {
        this.isbnPublisher = isbnPublisher;
    }

    /**
     * 中国 统一书号
     */
    public String getChinaUnionPublisherId() {
        return chinaUnionPublisherId;
    }

    /**
     * 中国 统一书号
     */
    public void setChinaUnionPublisherId(String chinaUnionPublisherId) {
        this.chinaUnionPublisherId = chinaUnionPublisherId;
    }

    /**
     * 备注
     */
    public String getComments() {
        return comments;
    }

    /**
     * 备注
     */
    public void setComments(String comments) {
        this.comments = comments;
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
        PublisherEntity other = (PublisherEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getIsbnCode() == null ? other.getIsbnCode() == null : this.getIsbnCode().equals(other.getIsbnCode()))
            && (this.getIsbnPublisher() == null ? other.getIsbnPublisher() == null : this.getIsbnPublisher().equals(other.getIsbnPublisher()))
            && (this.getChinaUnionPublisherId() == null ? other.getChinaUnionPublisherId() == null : this.getChinaUnionPublisherId().equals(other.getChinaUnionPublisherId()))
            && (this.getComments() == null ? other.getComments() == null : this.getComments().equals(other.getComments()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getIsbnCode() == null) ? 0 : getIsbnCode().hashCode());
        result = prime * result + ((getIsbnPublisher() == null) ? 0 : getIsbnPublisher().hashCode());
        result = prime * result + ((getChinaUnionPublisherId() == null) ? 0 : getChinaUnionPublisherId().hashCode());
        result = prime * result + ((getComments() == null) ? 0 : getComments().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", city=").append(city);
        sb.append(", type=").append(type);
        sb.append(", isbnCode=").append(isbnCode);
        sb.append(", isbnPublisher=").append(isbnPublisher);
        sb.append(", chinaUnionPublisherId=").append(chinaUnionPublisherId);
        sb.append(", comments=").append(comments);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}