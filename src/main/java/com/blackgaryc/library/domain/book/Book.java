package com.blackgaryc.library.domain.book;

import com.blackgaryc.library.entity.BookEntity;

import java.util.List;

public class Book {
    private Long id;
    private String title;
    private String description;
    private String language;
    private Integer categoryId;
    private List<BookDetail> bookDetails;

    public Book(BookEntity bookEntity) {
        this.setId(bookEntity.getId());
        this.setTitle(bookEntity.getTitle());
        this.setDescription(bookEntity.getDescription());
        this.setLanguage(bookEntity.getLanguage());
        this.setCategoryId(bookEntity.getCategoryId());
    }

    public static Book NoDetails(BookEntity book) {
        book.setDescription("书籍正在等待处理中");
        return new Book(book);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public List<BookDetail> getBookDetails() {
        return bookDetails;
    }

    public void setBookDetails(List<BookDetail> bookDetails) {
        this.bookDetails = bookDetails;
    }
}
