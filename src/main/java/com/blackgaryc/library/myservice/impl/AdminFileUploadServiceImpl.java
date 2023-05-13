package com.blackgaryc.library.myservice.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackgaryc.library.core.error.LibraryException;
import com.blackgaryc.library.core.result.BaseResult;
import com.blackgaryc.library.core.result.PageableResult;
import com.blackgaryc.library.core.result.Results;
import com.blackgaryc.library.domain.admin.book.UserHistoryUploadedBook;
import com.blackgaryc.library.entity.*;
import com.blackgaryc.library.myservice.AdminFileUploadService;
import com.blackgaryc.library.myservice.MinioClientService;
import com.blackgaryc.library.service.BookService;
import com.blackgaryc.library.service.FileService;
import com.blackgaryc.library.service.LogFileUploadService;
import com.blackgaryc.library.service.impl.FileServiceImpl;
import com.blackgaryc.library.tools.MinioFileService;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.errors.*;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AdminFileUploadServiceImpl implements AdminFileUploadService {
    @Resource
    LogFileUploadService bookUploadRequestService;
    @Resource
    BookService bookService;
    @Autowired
    MinioClientService minioClientService;

    @Autowired
    FileService fileService;
    @Override
    public PageableResult<UserHistoryUploadedBook> queryBookFileUploadRequest(BookUploadRequestStatusEnum status, Page<LogFileUploadEntity> ipage) {
        Page<LogFileUploadEntity> page = bookUploadRequestService.lambdaQuery()
                .eq(LogFileUploadEntity::getStatus, status.getCode())
                .orderByDesc(LogFileUploadEntity::getId).page(ipage);
        PageableResult<UserHistoryUploadedBook> result = Results.successMybatisPageData(page, UserHistoryUploadedBook::new);
        List<UserHistoryUploadedBook> data = result.getData();
        List<String> collect = page.getRecords().stream().map(LogFileUploadEntity::getFileId).collect(Collectors.toList());
        List<FileEntity> fileEntities = fileService.listByIds(collect);
        data.forEach(entity -> {
            //文件链接
            try {
                List<FileEntity> fileEntityStream = fileEntities.stream().filter(entity1 -> entity1.getId().equals(entity.getUrl())).toList();
                if(fileEntityStream.size()==1){
                    Optional<FileEntity> first = fileEntityStream.stream().findFirst();
                    String presignedUrl = minioClientService.getPresignedUrl(GetPresignedObjectUrlArgs.builder()
                            .expiry(30, TimeUnit.MINUTES)
                            .method(Method.GET)
                            .object(first.get().getObject()));
                    entity.setUrl(presignedUrl);
                }
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        });
        return result;
    }

    @Override
    public BaseResult queryBookFileUploadRequest(Long id) {
        LogFileUploadEntity entity = bookUploadRequestService.getById(id);
        return Results.successData(new UserHistoryUploadedBook(entity));
    }

    @Override
    public BaseResult updateBookFileUploadRequest(List<Long> ids, BookUploadRequestStatusEnum status) {

        List<LogFileUploadEntity> collect = bookUploadRequestService
                .listByIds(ids).stream().peek(entity -> {
                    entity.setStatus(status.getCode());
                    if (status.equals(BookUploadRequestStatusEnum.CONFORMED)){
                        entity.setMessage("文件审核通过，感谢您的上传。" +
                                "主页搜索可能存在延迟，若15分钟后依旧无法搜索到该文件，请联系我们。");
                    }else if (status.equals(BookUploadRequestStatusEnum.REFUSED)){
                        entity.setMessage("文件审核失败，感谢您的上传。" +
                                "该文件不会被公开且在一段时间后会被清理。" +
                                "我们必须尊重并保护版权方的权益。");
                    }
                })
                .toList();
        bookUploadRequestService.updateBatchById(collect);
        Stream<Long> bookIds = collect.stream().map(LogFileUploadEntity::getBookId);
        bookService.lambdaUpdate()
                .in(BookEntity::getId,bookIds.toList())
                .set(BookEntity::getStatus, StatusEnum.valueOf(status).getCode())
                .update();
        return Results.success();
    }
}
