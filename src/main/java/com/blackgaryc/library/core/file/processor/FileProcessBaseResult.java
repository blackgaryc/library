package com.blackgaryc.library.core.file.processor;

import io.swagger.v3.oas.models.security.SecurityScheme;

import java.io.InputStream;

public class FileProcessBaseResult implements IFileProcessBaseResult{
    private String objectKey;
    private String uploadUid;
    private String filename;
    private String extension;
    private String mimetype;
    private String md5;
    private Long size;
    private InputStream thumbnail;
    private String thumbnailExtension;

    @Override
    public String getThumbnailExtension() {
        return thumbnailExtension;
    }

    public void setThumbnailExtension(String thumbnailExtension) {
        this.thumbnailExtension = thumbnailExtension;
    }

    @Override
    public InputStream getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(InputStream thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String getFilename() {
        return this.filename;
    }

    @Override
    public String getExtension() {
        return this.extension;
    }

    @Override
    public String getMimetype() {
        return this.mimetype;
    }

    @Override
    public String getMd5() {
        return this.md5;
    }

    @Override
    public Long getSize() {
        return this.size;
    }

    @Override
    public String getUploadUid() {
        return uploadUid;
    }

    @Override
    public String getObjectKey() {
        return objectKey;
    }

    public void setObjectKey(String objectKey) {
        this.objectKey = objectKey;
    }

    public void setUploadUid(String uploadUid) {
        this.uploadUid = uploadUid;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "FileProcessBaseResult{" +
                "objectKey='" + objectKey + '\'' +
                ", uploadUid='" + uploadUid + '\'' +
                ", filename='" + filename + '\'' +
                ", extension='" + extension + '\'' +
                ", mimetype='" + mimetype + '\'' +
                ", md5='" + md5 + '\'' +
                ", size=" + size +
                ", thumbnail='" + thumbnail + '\'' +
                '}';
    }
}
