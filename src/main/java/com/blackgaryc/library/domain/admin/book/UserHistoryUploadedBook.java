package com.blackgaryc.library.domain.admin.book;

import com.blackgaryc.library.entity.BookUploadRequestStatusEnum;
import com.blackgaryc.library.entity.LogFileUploadEntity;

import java.util.Arrays;

public class UserHistoryUploadedBook {
    private final long id;
    private final String filename;
    private final String message;
    private final String status;
    private final int statusCode;
    private final Long bookId;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public UserHistoryUploadedBook(LogFileUploadEntity entity) {
        assert entity != null;
        this.id = entity.getId();
        this.filename = entity.getFilename();
        this.message = entity.getMessage();
        this.statusCode = entity.getStatus();
        this.status = Arrays.stream(
                        BookUploadRequestStatusEnum.values()).filter(
                        e -> e.getCode() == entity.getStatus()
                ).findFirst()
                .orElse(BookUploadRequestStatusEnum.REFUSED).getMessage();
        this.bookId = entity.getBookId();
        this.url = entity.getFileId();
    }

    public String getFilename() {
        return filename;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public Long getBookId() {
        return bookId;
    }

    public long getId() {
        return id;
    }
}
