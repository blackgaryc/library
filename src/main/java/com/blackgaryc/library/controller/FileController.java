package com.blackgaryc.library.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.blackgaryc.library.LibraryApplication;
import com.blackgaryc.library.core.error.MinioObjectKeyGenerateException;
import com.blackgaryc.library.core.minio.IObjectKey;
import com.blackgaryc.library.core.minio.ObjectKeyFactory;
import com.blackgaryc.library.core.minio.ObjectUploadService;
import com.blackgaryc.library.core.minio.ObjectUploadStrategy;
import com.blackgaryc.library.core.result.BaseResult;
import com.blackgaryc.library.core.result.Results;
import com.blackgaryc.library.service.MinioClientService;
import io.minio.errors.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    /**
     * using spring to upload file form user between minio
     * @param file file uploaded
     * @param type file key generate type
     * @return
     */
    @PostMapping("/upload")
    public BaseResult uploadFileV2(MultipartFile file,String type) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException, MinioObjectKeyGenerateException {
        //transfer file to local file
        Path tempFile = Files.createTempFile(LibraryApplication.PREFIX, UUID.randomUUID() +".tmp");
        file.transferTo(tempFile);
        File tmpfile = tempFile.toFile();
        //get file upload object key
        IObjectKey objectKey = ObjectKeyFactory.getInstance(type, tmpfile, file.getOriginalFilename());
        //use different objectKey to find strategy for file upload
        //response data can be return by any type
        ObjectUploadStrategy strategy = objectUploadService.getInstance(objectKey);
        return Results.successData(strategy.upload(objectKey,tmpfile));
    }

    @Resource
    ObjectUploadService objectUploadService;
    @Resource
    MinioClientService minioClientService;

    /**
     * get pre signed url to direct upload from user client
     * @param type string to generate upload url
     * @return base result that contains url in data
     * @throws ServerException
     * @throws InsufficientDataException
     * @throws ErrorResponseException
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws InvalidResponseException
     * @throws XmlParserException
     * @throws InternalException
     * @throws MinioObjectKeyGenerateException if not found any predefined key generator, will raise this exception.
     */
    @GetMapping("upload/url")
    public BaseResult getMinioUploadUrl(String type) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException, MinioObjectKeyGenerateException {
        IObjectKey userInfoAvatar = ObjectKeyFactory.getInstance(type);
        return Results.successData(minioClientService.getUploadSignedUrl(userInfoAvatar));
    }

}
