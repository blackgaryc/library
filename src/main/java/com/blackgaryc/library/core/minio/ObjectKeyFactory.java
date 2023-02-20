package com.blackgaryc.library.core.minio;

import cn.dev33.satoken.stp.StpUtil;
import com.blackgaryc.library.core.minio.objectkeys.UserInfoAvatarKey;
import com.blackgaryc.library.tools.StringTools;
import com.rometools.utils.Strings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;

public class ObjectKeyFactory {

    /***
     * upload file in target filename and path,
     * you can set filename by what you what
     * @param type type to match IObjectKey
     * @param filename target filename
     * @return if type exist, return IObjectKey,
     * other else it will be null
     */
    public static IObjectKey getInstance(String type, String filename) {
        if (type.equals("user_info_avatar")) {
            return new UserInfoAvatarKey(StpUtil.getLoginIdAsLong(), filename);
        }
        return null;
    }

    /**
     * upload file in target path with random filename,
     * random file name is generate by uuid
     *
     * @param type type to match IObjectKey
     * @return if type exist, return IObjectKey,
     * other else it will be null
     */
    public static IObjectKey getInstance(String type) {
        if (type.equals("user_info_avatar")) {
            return new UserInfoAvatarKey(StpUtil.getLoginIdAsLong());
        }
        return null;
    }


    public static IObjectKey getInstanceWithMd5Filename(@NotNull String type, @NotNull File file, @Nullable String originFilename) {
        if (Strings.isBlank(originFilename)) {
            originFilename = StringTools.getMd5Filename("", file);
        } else {
            originFilename = StringTools.getMd5AsFilenameWithOriginException(originFilename, file);
        }
        return getInstance(type, originFilename);
    }

}
