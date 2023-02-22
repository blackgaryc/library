package com.blackgaryc.library.core.minio.response;

public class UserUploadBookResponse {
    private Long id;
    private String url;

    public UserUploadBookResponse(Long id, String url) {
        this.id = id;
        this.url = url;
    }

    public UserUploadBookResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
