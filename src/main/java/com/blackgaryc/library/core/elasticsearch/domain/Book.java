package com.blackgaryc.library.core.elasticsearch.domain;

import com.blackgaryc.library.entity.BookDetailEntity;
import com.blackgaryc.library.entity.BookEntity;
import com.blackgaryc.library.entity.FileEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Collections;
import java.util.List;

@Document(indexName = "book_library_elasticsearch")
public class Book {
    @Id
    private Long id;
    private String title;
    private String description;
    private String language;
    @Field(type = FieldType.Nested, includeInParent = true)
    private Category category;
    @Field(type = FieldType.Nested, includeInParent = true)
    private List<Author> authors;
    @Field(type = FieldType.Nested, includeInParent = true)
    private List<BookDetail> bookDetails;

    public Book(BookEntity bookEntity, BookDetailEntity bookDetailEntity, FileEntity fileEntity) {
        this.id=bookEntity.getId();
        this.title=bookEntity.getTitle();
        this.description=bookEntity.getDescription();
        this.language=bookEntity.getLanguage();
        this.category=null;
        this.authors= Collections.emptyList();
        this.bookDetails=Collections.singletonList(new BookDetail(bookDetailEntity,fileEntity));
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