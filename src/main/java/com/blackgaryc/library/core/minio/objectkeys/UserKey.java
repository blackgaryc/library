package com.blackgaryc.library.core.minio.objectkeys;

import com.blackgaryc.library.core.minio.IObjectKey;

public class UserKey implements IObjectKey {
    // user/{uid}
    private String key = "user";

    @Override
    public String getKey() {
        return this.key;
    }

    public UserKey(Long uid) {
        this.key = key + '/' + uid;
    }
}
