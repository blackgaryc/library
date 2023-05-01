package com.blackgaryc.library.domain.admin.category;

import java.util.List;

public class CategorySelectVo {
    private String label;
    private String value;
    private List<CategorySelectVo> children;

    public List<CategorySelectVo> getChildren() {
        return children;
    }

    public void setChildren(List<CategorySelectVo> children) {
        this.children = children;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
