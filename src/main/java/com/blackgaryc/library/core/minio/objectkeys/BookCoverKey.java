package com.blackgaryc.library.core.minio.objectkeys;

import com.blackgaryc.library.core.minio.IObjectKey;
import org.springframework.util.Assert;

public class BookCoverKey implements IObjectKey {
    private final String key;

    public BookCoverKey(String filename,String mimetype) {
        Assert.hasLength(filename,"filename must has length");
        Assert.hasLength(mimetype,"mimetype must has length");
        this.key = "cover/"+mimetype+"/"+filename;
    }

    public BookCoverKey(String filename) {
        this.key = "cover/"+filename;
    }

    @Override
    public String getKey() {
        return this.key;
    }
}
