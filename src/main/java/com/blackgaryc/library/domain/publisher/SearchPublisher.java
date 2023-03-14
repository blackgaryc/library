package com.blackgaryc.library.domain.publisher;

import java.io.Serializable;

public class SearchPublisher implements Serializable {
    private String name;
    private Integer id;
    private String isbn;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }
}
