package com.blackgaryc.library.core.minio.objectkeys;

public class UserInfoKey extends UserKey {
    // user/{uid}/info
    private String key;

    public UserInfoKey(Long uid) {
        super(uid);
        this.key = super.getKey()+"/info";
    }

    @Override
    public String getKey() {
        return this.key;
    }
}
