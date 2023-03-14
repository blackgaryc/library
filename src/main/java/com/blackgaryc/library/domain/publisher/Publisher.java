package com.blackgaryc.library.domain.publisher;

import java.io.Serializable;

public class Publisher implements Serializable {
    private Integer id;


    private String name;

    /**
     * 城市
     */
    private String city;

    /**
     * 类型
     */
    private String type;

    /**
     * 国际标准书号代码
     */
    private String isbnCode;

    /**
     * 出版社编号
     */
    private String isbnPublisher;

    /**
     * 中国 统一书号
     */
    private String chinaUnionPublisherId;

    /**
     * 备注
     */
    private String comments;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getType() {
        return type;
    }

    public String getIsbnCode() {
        return isbnCode;
    }

    public String getIsbnPublisher() {
        return isbnPublisher;
    }

    public String getChinaUnionPublisherId() {
        return chinaUnionPublisherId;
    }

    public String getComments() {
        return comments;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setIsbnCode(String isbnCode) {
        this.isbnCode = isbnCode;
    }

    public void setIsbnPublisher(String isbnPublisher) {
        this.isbnPublisher = isbnPublisher;
    }

    public void setChinaUnionPublisherId(String chinaUnionPublisherId) {
        this.chinaUnionPublisherId = chinaUnionPublisherId;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
