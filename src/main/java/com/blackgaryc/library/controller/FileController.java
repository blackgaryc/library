package com.blackgaryc.library.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import co.elastic.clients.elasticsearch.xpack.usage.Base;
import com.blackgaryc.library.LibraryApplication;
import com.blackgaryc.library.core.minio.IObjectKey;
import com.blackgaryc.library.core.minio.MinioProperty;
import com.blackgaryc.library.core.minio.ObjectKeyFactory;
import com.blackgaryc.library.core.minio.ObjectKeyPrefixEnum;
import com.blackgaryc.library.core.minio.objectkeys.UserInfoAvatarKey;
import com.blackgaryc.library.core.result.BaseResult;
import com.blackgaryc.library.core.result.Results;
import com.blackgaryc.library.service.MinioClientService;
import com.blackgaryc.library.tools.StringTools;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.UploadObjectArgs;
import io.minio.errors.*;
import org.apache.tika.Tika;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@RestController
@RequestMapping("/file")
@SaCheckLogin
public class FileController {

    @Resource
    Tika tika;
    @Resource
    MinioClient minioClient;
    @Resource
    MinioProperty minioProperty;

    @PostMapping("/upload_v0")
    public BaseResult uploadFile(MultipartFile file,String type) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        // type must be resolved , it contains object prefix which is important of upload file.
        ObjectKeyPrefixEnum objectKeyPrefixEnum = ObjectKeyPrefixEnum.valueOf(type);
        String originalFilename = file.getOriginalFilename();
        Path tempFile = Files.createTempFile(LibraryApplication.PREFIX, originalFilename);
        file.transferTo(tempFile);
        ObjectWriteResponse objectWriteResponse = uploadFile(objectKeyPrefixEnum, originalFilename, tempFile);
        tempFile.toFile().delete();
        String object = objectWriteResponse.object();
        return Results.successData(object);
    }

    /**
     * using spring to upload file form user between minio
     * @param file file uploaded
     * @param type file key generate type
     * @return
     */
    @PostMapping("/upload")
    public BaseResult uploadFileV2(MultipartFile file,String type) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        Path tempFile = Files.createTempFile(LibraryApplication.PREFIX, UUID.randomUUID() +".tmp");
        file.transferTo(tempFile);
        File tmpfile = tempFile.toFile();
        IObjectKey instanceWithMd5Filename = ObjectKeyFactory.getInstanceWithMd5Filename(type, tmpfile, file.getOriginalFilename());
        Assert.notNull(instanceWithMd5Filename,"无法找到"+type+"对应的文件路径");
        String finalObjectKey = minioClientService.uploadObject(tmpfile, instanceWithMd5Filename);
        tmpfile.delete();
        return Results.successData(minioClientService.getFullUrl(finalObjectKey));
    }

    private ObjectWriteResponse uploadFile(ObjectKeyPrefixEnum objectKeyPrefixEnum, String originalFilename, Path tempFile) throws IOException, ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException {
        String detect = tika.detect(tempFile);
        ObjectWriteResponse objectWriteResponse = minioClient.uploadObject(UploadObjectArgs.builder()
                .bucket(minioProperty.getBucket())
                .object(objectKeyPrefixEnum.getPrefix() + detect + '/'
                        + StringTools.getMd5AsFilenameWithOriginException(originalFilename, tempFile.toFile()))
                .filename(tempFile.toAbsolutePath().toString())
                .build());
        return objectWriteResponse;
    }

    @Resource
    MinioClientService minioClientService;

    @GetMapping("upload/url")
    public BaseResult getMinioUploadUrl(String type) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        IObjectKey userInfoAvatar = ObjectKeyFactory.getInstance(type);
        Assert.notNull(userInfoAvatar,"无法找到"+type+"对应的文件路径");
        return Results.successData(minioClientService.getUploadSignedUrl(userInfoAvatar));
//        String loginId = StpUtil.getLoginIdAsString();
//        String path= "user/"+loginId+"/info/avatar/"+ UUID.randomUUID();
//        try {
//            return Results.successData(minioClientService.getUploadSignedUrl(path));
//        } catch (ServerException | InsufficientDataException | ErrorResponseException | IOException |
//                 NoSuchAlgorithmException | InvalidKeyException | InvalidResponseException | XmlParserException |
//                 InternalException e) {
//            throw new RuntimeException(e);
//        }
    }

}
