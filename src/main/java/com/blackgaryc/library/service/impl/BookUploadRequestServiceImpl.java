package com.blackgaryc.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackgaryc.library.entity.BookUploadRequestEntity;
import com.blackgaryc.library.service.BookUploadRequestService;
import com.blackgaryc.library.mapper.BookUploadRequestMapper;
import org.springframework.stereotype.Service;

/**
* @author alex
* @description 针对表【book_upload_request】的数据库操作Service实现
* @createDate 2023-02-24 15:26:16
*/
@Service
public class BookUploadRequestServiceImpl extends ServiceImpl<BookUploadRequestMapper, BookUploadRequestEntity>
    implements BookUploadRequestService{

}




