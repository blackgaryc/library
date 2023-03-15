package com.blackgaryc.library.mapper;

import com.blackgaryc.library.domain.file.UploadRankResult;
import com.blackgaryc.library.entity.BookUploadRequestEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Date;
import java.util.List;

/**
* @author alex
* @description 针对表【book_upload_request】的数据库操作Mapper
* @createDate 2023-02-28 16:08:18
* @Entity com.blackgaryc.library.entity.BookUploadRequestEntity
*/
public interface BookUploadRequestMapper extends BaseMapper<BookUploadRequestEntity> {
    List<UploadRankResult> getRankList(Date date);
}




