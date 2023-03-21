package com.blackgaryc.library.domain.booklist;

import com.blackgaryc.library.entity.BooklistEntity;

public class SimpleBookList {
    private String id;


    private Long uid;

    private String name;

    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SimpleBookList() {
    }

    public SimpleBookList(BooklistEntity entity) {
        this.id = entity.getId();
        this.description = entity.getDescription();
        this.name = entity.getName();
        this.uid = entity.getUid();
    }
}
