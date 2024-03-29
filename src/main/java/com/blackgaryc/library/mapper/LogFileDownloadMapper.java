package com.blackgaryc.library.mapper;

import com.blackgaryc.library.entity.LogFileDownloadEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* @author alex
* @description 针对表【sys_log_file_download】的数据库操作Mapper
* @createDate 2023-04-01 14:10:10
* @Entity com.blackgaryc.library.entity.LogFileDownloadEntity
*/
public interface LogFileDownloadMapper extends BaseMapper<LogFileDownloadEntity> {

    List<Map<String, Object>> getBookDownloadHistory(@Param(value = "uid") Long uid);
}




