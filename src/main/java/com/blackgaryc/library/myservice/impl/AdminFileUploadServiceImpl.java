package com.blackgaryc.library.myservice.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackgaryc.library.core.result.BaseResult;
import com.blackgaryc.library.core.result.PageableResult;
import com.blackgaryc.library.core.result.Results;
import com.blackgaryc.library.domain.admin.book.UserHistoryUploadedBook;
import com.blackgaryc.library.entity.BookEntity;
import com.blackgaryc.library.entity.BookStatusEnum;
import com.blackgaryc.library.entity.BookUploadRequestEntity;
import com.blackgaryc.library.entity.BookUploadRequestStatusEnum;
import com.blackgaryc.library.myservice.AdminFileUploadService;
import com.blackgaryc.library.service.BookService;
import com.blackgaryc.library.service.BookUploadRequestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Stream;

@Service
public class AdminFileUploadServiceImpl implements AdminFileUploadService {
    @Resource
    BookUploadRequestService bookUploadRequestService;
    @Resource
    BookService bookService;

    @Override
    public PageableResult<UserHistoryUploadedBook> queryBookFileUploadRequest(BookUploadRequestStatusEnum status, Page<BookUploadRequestEntity> ipage) {
        Page<BookUploadRequestEntity> page = bookUploadRequestService.lambdaQuery()
                .eq(BookUploadRequestEntity::getStatus, status.getCode())
                .orderByDesc(BookUploadRequestEntity::getId).page(ipage);
        return Results.successMybatisPageData(page, UserHistoryUploadedBook::new);
    }

    @Override
    public BaseResult queryBookFileUploadRequest(Long id) {
        BookUploadRequestEntity entity = bookUploadRequestService.getById(id);
        return Results.successData(new UserHistoryUploadedBook(entity));
    }

    @Override
    public BaseResult updateBookFileUploadRequest(List<Long> ids, BookUploadRequestStatusEnum status) {

        List<BookUploadRequestEntity> collect = bookUploadRequestService
                .listByIds(ids).stream().peek(entity -> {
                    entity.setStatus(status.getCode());
                    if (status.equals(BookUploadRequestStatusEnum.CONFORMED)){
                        entity.setMessage("??????????????????????????????????????????" +
                                "????????????????????????????????????15????????????????????????????????????????????????????????????");
                    }else if (status.equals(BookUploadRequestStatusEnum.REFUSED)){
                        entity.setMessage("??????????????????????????????????????????" +
                                "????????????????????????????????????????????????????????????" +
                                "????????????????????????????????????????????????");
                    }
                })
                .toList();
        bookUploadRequestService.updateBatchById(collect);
        Stream<Long> bookIds = collect.stream().map(BookUploadRequestEntity::getBookId);
        bookService.lambdaUpdate()
                .in(BookEntity::getId,bookIds.toList())
                .set(BookEntity::getStatus, BookStatusEnum.valueOf(status).getCode())
                .update();
        return Results.success();
    }
}
