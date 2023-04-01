package com.blackgaryc.library.myservice.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackgaryc.library.core.result.BaseResult;
import com.blackgaryc.library.core.result.PageableResult;
import com.blackgaryc.library.core.result.Results;
import com.blackgaryc.library.domain.admin.book.UserHistoryUploadedBook;
import com.blackgaryc.library.entity.BookEntity;
import com.blackgaryc.library.entity.BookStatusEnum;
import com.blackgaryc.library.entity.BookUploadRequestStatusEnum;
import com.blackgaryc.library.entity.LogFileUploadEntity;
import com.blackgaryc.library.myservice.AdminFileUploadService;
import com.blackgaryc.library.service.BookService;
import com.blackgaryc.library.service.LogFileUploadService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Stream;

@Service
public class AdminFileUploadServiceImpl implements AdminFileUploadService {
    @Resource
    LogFileUploadService bookUploadRequestService;
    @Resource
    BookService bookService;

    @Override
    public PageableResult<UserHistoryUploadedBook> queryBookFileUploadRequest(BookUploadRequestStatusEnum status, Page<LogFileUploadEntity> ipage) {
        Page<LogFileUploadEntity> page = bookUploadRequestService.lambdaQuery()
                .eq(LogFileUploadEntity::getStatus, status.getCode())
                .orderByDesc(LogFileUploadEntity::getId).page(ipage);
        return Results.successMybatisPageData(page, UserHistoryUploadedBook::new);
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
                .set(BookEntity::getStatus, BookStatusEnum.valueOf(status).getCode())
                .update();
        return Results.success();
    }
}
