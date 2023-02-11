package com.blackgaryc.library.core.elasticsearch.domain;

import com.blackgaryc.library.entity.BookDetailEntity;
import com.blackgaryc.library.entity.FileEntity;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

public class BookDetail {
    @Field(type = FieldType.Nested, includeInParent = true)
    private Publisher publisher;
    private String isbn;
    @Field(type = FieldType.Nested, includeInParent = true)
    private BookFile file;
    private Integer page;

    public BookDetail(BookDetailEntity bookDetailEntity, FileEntity fileEntity) {
        this.publisher = null;
        this.isbn = bookDetailEntity.getIsbn();
        this.file = new BookFile(fileEntity);
        this.page = bookDetailEntity.getPage();
    }

    public BookDetail() {
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public BookFile getFile() {
        return file;
    }

    public void setFile(BookFile file) {
        this.file = file;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }


    public static final class Builder {
        private BookDetail bookDetail;

        private Builder() {
            bookDetail = new BookDetail();
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder publisher(Publisher publisher) {
            bookDetail.setPublisher(publisher);
            return this;
        }

        public Builder isbn(String isbn) {
            bookDetail.setIsbn(isbn);
            return this;
        }

        public Builder file(BookFile file) {
            bookDetail.setFile(file);
            return this;
        }

        public Builder page(Integer page) {
            bookDetail.setPage(page);
            return this;
        }

        public BookDetail build() {
            return bookDetail;
        }
    }
}
