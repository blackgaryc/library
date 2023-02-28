package com.blackgaryc.library.core.elasticsearch.domain;

import com.blackgaryc.library.entity.BookDetailEntity;
import com.blackgaryc.library.entity.BookEntity;
import com.blackgaryc.library.entity.FileEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.*;
import java.util.stream.Collectors;

@Document(indexName = "book_library_elasticsearch")
public class Book {
    @Id
    private Long id;
    private String title;
    private String description;
    private String language;
    private String thumbnail;
    private String isbn10;
    private String isbn12;

    @Field(type = FieldType.Nested, includeInParent = true)
    private Category category;
    @Field(type = FieldType.Nested, includeInParent = true)
    private List<Author> authors;
    @Field(type = FieldType.Nested, includeInParent = true)
    private List<BookDetail> bookDetails;

    public Book(BookEntity bookEntity, BookDetailEntity bookDetailEntity, FileEntity fileEntity) {
        this.id = bookEntity.getId();
        this.title = bookEntity.getTitle();
        this.description = bookEntity.getDescription();
        this.language = bookEntity.getLanguage();
        this.thumbnail = bookEntity.getThumbnail();
        this.category = null;
        this.authors = Collections.emptyList();
        this.bookDetails = Collections.singletonList(new BookDetail(bookDetailEntity, fileEntity));
    }
    public Book(BookEntity bookEntity) {
        this.id = bookEntity.getId();
        this.title = bookEntity.getTitle();
        this.description = bookEntity.getDescription();
        this.language = bookEntity.getLanguage();
        this.thumbnail = bookEntity.getThumbnail();
        this.category = null;
        this.authors = Collections.emptyList();
    }
    public Book(BookEntity bookEntity, Collection<BookDetailEntity> bookDetailEntities, Collection<FileEntity> fileEntities) {
        this(bookEntity);
        //current book detail list
        List<BookDetailEntity> currentBookDetails = bookDetailEntities.stream().filter(e -> e.getBookId().equals(bookEntity.getId())).toList();
        bookDetailEntities.removeAll(currentBookDetails);

        Set<String> fileIdSet = currentBookDetails.stream().map(BookDetailEntity::getFileId).collect(Collectors.toSet());
        //current book file list
        List<FileEntity> currentFileList = fileEntities.stream().filter(file -> fileIdSet.contains(file.getId())).toList();
        fileEntities.removeAll(currentFileList);
        HashMap<String, FileEntity> map = new HashMap<>(currentFileList.size());
        currentFileList.forEach(file -> {
            map.put(file.getId(), file);
        });
        this.bookDetails = currentBookDetails.stream()
                .map(bookDetailEntity -> new BookDetail(bookDetailEntity, map.get(bookDetailEntity.getFileId())))
                .collect(Collectors.toList());
    }

    public Book() {
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<BookDetail> getBookDetails() {
        return bookDetails;
    }

    public void setBookDetails(List<BookDetail> bookDetails) {
        this.bookDetails = bookDetails;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public static final class Builder {
        private Book book;

        private Builder() {
            book = new Book();
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(Long id) {
            book.setId(id);
            return this;
        }

        public Builder title(String title) {
            book.setTitle(title);
            return this;
        }

        public Builder description(String description) {
            book.setDescription(description);
            return this;
        }

        public Builder language(String language) {
            book.setLanguage(language);
            return this;
        }

        public Builder category(Category category) {
            book.setCategory(category);
            return this;
        }

        public Builder authors(List<Author> authors) {
            book.setAuthors(authors);
            return this;
        }

        public Builder bookDetails(List<BookDetail> bookDetails) {
            book.setBookDetails(bookDetails);
            return this;
        }

        public Book build() {
            return book;
        }
    }
}
