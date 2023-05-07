package com.blackgaryc.library.domain.booklist;

import java.io.Serializable;

public class UpdateBookListRequest implements Serializable {
    private String id;
    private String name;
    private String description;
    private Boolean published;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean publish) {
        this.published = publish;
    }
}
