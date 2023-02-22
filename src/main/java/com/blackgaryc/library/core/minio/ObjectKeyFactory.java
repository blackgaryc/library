package com.blackgaryc.library.core.minio;

import cn.dev33.satoken.stp.StpUtil;
import com.blackgaryc.library.core.minio.objectkeys.UserBookFileKey;
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
        switch (type) {
            case "user_info_avatar":
                return new UserInfoAvatarKey(StpUtil.getLoginIdAsLong(), filename);
            case "user_upload_book_file":
                return new UserBookFileKey(StpUtil.getLoginIdAsLong(), filename);
            default:
                return null;
        }
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

    /**
     * return object key to upload file
     *
     * @param type           which kind file to upload,
     *                       if type contains '_RANDOM_FILE' or originFilename is empty or blank,
     *                       it's filename will be generated as simple uuid with origin extension.
     *                       if type contains '_MD5_FILENAME',
     *                       it's filename will be generated with file md5 and origin extension.
     *                       any else,it uploaded filename will be origin filename.
     * @param file           file to upload
     * @param originFilename origin filename
     * @return
     */
    public static IObjectKey getInstance(@NotNull String type, @NotNull File file, @Nullable String originFilename) {
        String randomFilename = "_RANDOM_FILENAME";
        if (Strings.isBlank(originFilename) || type.contains(randomFilename)) {
            type = type.replace(randomFilename, "");
            originFilename = StringTools.getMd5Filename("", file);
        } else {
            String md5Filename = "_MD5_FILENAME";
            if (type.contains(md5Filename)) {
                type = type.replace(md5Filename, "");
                originFilename = StringTools.getMd5AsFilenameWithOriginException(originFilename, file);
            }
        }
        return getInstance(type, originFilename);
    }

}
