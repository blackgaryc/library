package com.blackgaryc.library.domain.category;

import com.blackgaryc.library.entity.CategoryEntity;

import java.io.Serializable;
import java.util.Optional;

public class Category implements Serializable {
    private final int id;
    private final int parentId;
    private final String name;

    public Category(CategoryEntity entity) {
        this.id = entity.getId();
        //父节点为空时置为-1
        this.parentId = entity.getParentId() == null ? -1 : entity.getParentId();
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
