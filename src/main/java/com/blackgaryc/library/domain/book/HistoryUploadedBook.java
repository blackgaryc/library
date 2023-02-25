package com.blackgaryc.library.domain.book;

import com.blackgaryc.library.entity.BookUploadRequestEntity;
import com.blackgaryc.library.entity.BookUploadRequestStatusEnum;

import java.util.Arrays;

public class HistoryUploadedBook {
    private final String filename;
    private final String message;
    private final String status;
    private final int statusCode;
    private final Long bookId;

    public int getStatusCode() {
        return statusCode;
    }

    public HistoryUploadedBook(BookUploadRequestEntity entity) {
        this.filename = entity.getFilename();
        this.message = entity.getMessage();
        this.statusCode = entity.getStatus();
        this.status = Arrays.stream(
                        BookUploadRequestStatusEnum.values()).filter(
                        e -> e.getCode() == entity.getStatus()
                ).findFirst()
                .orElse(BookUploadRequestStatusEnum.REFUSED).getMessage();
        this.bookId = entity.getBookId();
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
}
