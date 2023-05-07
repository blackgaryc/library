package com.blackgaryc.library.domain.admin.publisher;

public class PublisherDto {
    private Integer id;
    private String name;
    private String city;
    private String type;
    private String isbnCode;
    private String isbnPublisher;
    private String chinaUnionPublisherId;
    private String comments;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsbnCode() {
        return isbnCode;
    }

    public void setIsbnCode(String isbnCode) {
        this.isbnCode = isbnCode;
    }

    public String getIsbnPublisher() {
        return isbnPublisher;
    }

    public void setIsbnPublisher(String isbnPublisher) {
        this.isbnPublisher = isbnPublisher;
    }

    public String getChinaUnionPublisherId() {
        return chinaUnionPublisherId;
    }

    public void setChinaUnionPublisherId(String chinaUnionPublisherId) {
        this.chinaUnionPublisherId = chinaUnionPublisherId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
