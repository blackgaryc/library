package com.blackgaryc.library.core.minio.objectkeys;

import com.blackgaryc.library.core.minio.IObjectKey;
import com.rometools.utils.Strings;

import java.util.UUID;

public class UserBookFileKey implements IObjectKey {

    private String key;
    public UserBookFileKey(Long uid,String filename) {
        if (Strings.isBlank(filename)){
            filename = UUID.randomUUID().toString();
        }
        this.key = "user/"+uid+"/book/"+filename;
    }

    @Override
    public String getKey() {
        return this.key;
    }
}
