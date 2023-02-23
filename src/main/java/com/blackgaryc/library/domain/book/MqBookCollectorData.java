package com.blackgaryc.library.domain.book;

import java.io.Serializable;

public class MqBookCollectorData implements Serializable {
    private long bookId;
    private long uid;
    private String object;
    private String etag;

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public MqBookCollectorData(long bookId, long uid, String object, String etag) {
        this.bookId = bookId;
        this.uid = uid;
        this.object = object;
        this.etag = etag;
    }

    public long getBookId() {
        return bookId;
    }

    public long getUid() {
        return uid;
    }

    public String getObject() {
        return object;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public void setObject(String object) {
        this.object = object;
    }
}
