package com.blackgaryc.library.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackgaryc.library.core.error.FileDownloadTimesEndException;
import com.blackgaryc.library.core.error.LibraryException;
import com.blackgaryc.library.core.minio.IObjectKey;
import com.blackgaryc.library.core.minio.ObjectKeyFactory;
import com.blackgaryc.library.core.minio.ObjectUploadStrategy;
import com.blackgaryc.library.core.minio.ObjectUploadStrategyBean;
import com.blackgaryc.library.core.result.BaseResult;
import com.blackgaryc.library.core.result.PageableResult;
import com.blackgaryc.library.core.result.Results;
import com.blackgaryc.library.domain.book.HistoryUploadedBook;
import com.blackgaryc.library.entity.BookUploadRequestEntity;
import com.blackgaryc.library.myservice.UserFileService;
import com.blackgaryc.library.service.BookUploadRequestService;
import com.blackgaryc.library.tools.FileTool;
import com.blackgaryc.library.tools.context.HttpContextTool;
import io.minio.errors.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/file")
@SaCheckLogin
public class FileController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Resource
    UserFileService userFileService;
    @Resource
    BookUploadRequestService bookUploadRequestService;
    @Resource
    ObjectUploadStrategyBean objectUploadService;

    /**
     * using spring to upload file form user between minio
     *
     * @param file file uploaded
     * @param type file key generate type
     * @return
     */
    @PostMapping("/upload")
    public BaseResult upload(MultipartFile file, String type) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException, LibraryException {
        //transfer file to local file
        Path tempFile = FileTool.trans2localTempFile(file.getInputStream(), ".tmp");
        File tmpfile = tempFile.toFile();
        //get file upload object key
        IObjectKey objectKey = ObjectKeyFactory.getInstance(type, tmpfile, file.getOriginalFilename());
        //use different objectKey to find strategy for file upload
        //response data can be return by any type
        ObjectUploadStrategy strategy = objectUploadService.getInstance(objectKey);
        Object upload = strategy.upload(objectKey, tmpfile);
        //delete tmp file
        boolean delete = tempFile.toFile().delete();
        if (!delete) {
            log.error(tempFile + " is unable to delete");
        }
        return Results.successData(upload);
    }

    //待完善
    /*
     * get pre signed url to direct upload from user client
     *
     * @param type string to generate upload url
     * @return base result that contains url in data
     * @throws MinioObjectKeyGenerateException if not found any predefined key generator, will raise this exception.
     */
//    @GetMapping("upload/url")
//    public BaseResult getMinioUploadUrl(String type) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException, MinioObjectKeyGenerateException {
//        IObjectKey userInfoAvatar = ObjectKeyFactory.getInstance(type);
//        return Results.successData(minioClientService.getUploadSignedUrl(userInfoAvatar));
//    }

    @GetMapping("download")
    @SaIgnore
    public BaseResult download(String id) throws FileDownloadTimesEndException {
        return Results.successData(userFileService.download(id));
    }

    /**
     * to query user history uploads
     * @return 返回用户上传文件的分页数据
     */
    @GetMapping("upload/history/book")
    public PageableResult<HistoryUploadedBook> userUploadedBookFiles() {
        Page<BookUploadRequestEntity> pageResult = bookUploadRequestService.lambdaQuery()
                .eq(BookUploadRequestEntity::getUid, StpUtil.getLoginIdAsString())
                .orderByDesc(true,BookUploadRequestEntity::getId)
                .page(HttpContextTool.getDefaultPage());
        return Results.successMybatisPageData(pageResult,HistoryUploadedBook::new);
    }

}
