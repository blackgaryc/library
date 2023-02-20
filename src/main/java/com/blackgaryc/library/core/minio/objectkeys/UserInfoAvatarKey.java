package com.blackgaryc.library.core.minio.objectkeys;

import com.blackgaryc.library.tools.StringTools;
import org.apache.logging.log4j.util.Strings;

import java.io.File;
import java.util.UUID;

public class UserInfoAvatarKey extends UserInfoKey {

    // user/{uid}/info/avatar/filename
    private String key;

    public UserInfoAvatarKey(Long uid, String filename) {
        super(uid);
        this.key = super.getKey() + "/avatar";
        if (!Strings.isNotBlank(filename)) {
            filename = UUID.randomUUID().toString();
        }
        this.key += '/' + filename;
    }

    public UserInfoAvatarKey(Long uid) {
        this(uid, null);
    }

    @Override
    public String getKey() {
        return this.key;
    }
}
