package com.blackgaryc.library.domain.book;

import com.blackgaryc.library.entity.BookEntity;
import com.blackgaryc.library.entity.FileEntity;
import org.apache.logging.log4j.util.Strings;

import java.util.List;
import java.util.stream.Collectors;

public class Book {
    private Long id;
    private String title;
    private String description;
    private String language;
    private Integer categoryId;
    private String thumbnail;
    private Integer publisherId;
    private String isbn;
    private String isbn13;
    private List<BookFile> file;
    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }


    public Book(BookEntity bookEntity) {
        this.setId(bookEntity.getId());
        this.setTitle(bookEntity.getTitle());
        this.setDescription(bookEntity.getDescription());
        this.setLanguage(bookEntity.getLanguage());
        this.setCategoryId(bookEntity.getCategoryId());
        this.setThumbnail(bookEntity.getThumbnail());
        this.setPublisherId(bookEntity.getPublisherId());
        if (Strings.isNotBlank(bookEntity.getIsbn10()))
            this.setIsbn(bookEntity.getIsbn10());
        if (Strings.isNotBlank(bookEntity.getIsbn13()))
            this.setIsbn13(bookEntity.getIsbn13());
    }

    public static Book NoFiles(BookEntity book) {
        book.setDescription("书籍正在等待处理中");
        return new Book(book);
    }

    public static Book HasFiles(BookEntity bookEntity, List<FileEntity> fileEntities) {
        Book book = new Book(bookEntity);
        book.setFile(fileEntities.stream().map(BookFile::new).collect(Collectors.toList()));
        return book;
    }


    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public List<BookFile> getFile() {
        return file;
    }

    public void setFile(List<BookFile> file) {
        this.file = file;
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
}
