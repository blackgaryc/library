package com.blackgaryc.library.domain.admin.category;

public class CategoryUpdDto {
    private Integer id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 父级id
     */
    private Integer parentId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
