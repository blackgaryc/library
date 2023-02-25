package com.blackgaryc.library.core.elasticsearch.domain;

import com.blackgaryc.library.entity.FileEntity;

public class BookFile {
    private String id;
    private String filename;
    private String extension;
    private String mimetype;
    private String md5;
    private Long uid;
    private Long size;

    public BookFile(FileEntity fileEntity) {
        this.id= fileEntity.getId();
        this.filename = fileEntity.getFilename();
        this.extension = fileEntity.getExtension();
        this.mimetype = fileEntity.getMimetype();
        this.md5 = fileEntity.getMd5();
        this.uid = fileEntity.getUid();
        this.size = fileEntity.getSize();
    }

    public BookFile() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
