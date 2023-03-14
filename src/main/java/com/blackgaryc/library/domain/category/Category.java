package com.blackgaryc.library.domain.category;

import com.blackgaryc.library.entity.CategoryEntity;

import java.io.Serializable;

public class Category implements Serializable {
    private final int id;
    private final int parentId;
    private final String name;

    public Category(CategoryEntity entity) {
        this.id = entity.getId();
        this.parentId = entity.getParentId();
        this.name = entity.getName();
    }

    public int getId() {
        return id;
    }

    public int getParentId() {
        return parentId;
    }

    public String getName() {
        return name;
    }
}
