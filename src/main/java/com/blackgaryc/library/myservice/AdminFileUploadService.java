package com.blackgaryc.library.myservice;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackgaryc.library.core.result.BaseResult;
import com.blackgaryc.library.core.result.PageableResult;
import com.blackgaryc.library.domain.admin.book.UserHistoryUploadedBook;
import com.blackgaryc.library.entity.BookUploadRequestEntity;
import com.blackgaryc.library.entity.BookUploadRequestStatusEnum;

import java.util.List;

public interface AdminFileUploadService {

    PageableResult<UserHistoryUploadedBook> queryBookFileUploadRequest(BookUploadRequestStatusEnum status, Page<BookUploadRequestEntity> ipage);

    BaseResult queryBookFileUploadRequest(Long id);

    BaseResult updateBookFileUploadRequest(List<Long> ids, BookUploadRequestStatusEnum status);
}
