package com.blackgaryc.library.domain.book;

import com.baomidou.mybatisplus.annotation.TableField;
import com.blackgaryc.library.entity.FileEntity;

public class BookFile {
    private String id;
    private String extension;
    private String mimetype;
    private String md5;
    private Long size;
    public BookFile(FileEntity file) {
        this.id = file.getId();
        this.extension = file.getExtension();
        this.mimetype = file.getMimetype();
        this.md5 = file.getMd5();
        this.size = file.getSize();
    }

    public String getId() {
        return id;
    }

    public String getExtension() {
        return extension;
    }

    public String getMimetype() {
        return mimetype;
    }

    public String getMd5() {
        return md5;
    }

    public Long getSize() {
        return size;
    }
}
