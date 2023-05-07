package com.blackgaryc.library.mapper;

import com.blackgaryc.library.domain.file.UploadRankResult;
import com.blackgaryc.library.entity.LogFileUploadEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
* @author alex
* @description 针对表【sys_log_file_upload】的数据库操作Mapper
* @createDate 2023-04-01 14:10:10
* @Entity com.blackgaryc.library.entity.LogFileUploadEntity
*/
public interface LogFileUploadMapper extends BaseMapper<LogFileUploadEntity> {
    List<UploadRankResult> getRankList(@Param("date") String date);
}




