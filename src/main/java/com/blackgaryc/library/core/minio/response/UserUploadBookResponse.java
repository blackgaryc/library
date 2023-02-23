package com.blackgaryc.library.core.minio.response;

public class UserUploadBookResponse {
    private Long id;

    public UserUploadBookResponse(Long id) {
        this.id = id;
    }

    public UserUploadBookResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
