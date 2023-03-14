package com.blackgaryc.library.domain.book;

import com.blackgaryc.library.entity.BookEntity;

import java.io.Serializable;

public class SimpleBook implements Serializable {
    private Long id;
    private String title;
    private String thumbnail;
    private String author;

    public SimpleBook() {
    }

    public SimpleBook(BookEntity entity) {
        this.id = entity.getId();
        this.thumbnail = entity.getThumbnail();
        this.title = entity.getTitle();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getAuthor() {
        return author;
    }
}
