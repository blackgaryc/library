package com.blackgaryc.library.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackgaryc.library.entity.BookFavListEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
* @author alex
* @description 针对表【basics_book_fav_list(图书收藏表)】的数据库操作Mapper
* @createDate 2023-04-01 14:09:17
* @Entity com.blackgaryc.library.entity.BookFavListEntity
*/
public interface BookFavListMapper extends BaseMapper<BookFavListEntity> {

    Page<Map<String, Object>> getFavBooks(@Param(value = "page") Page page,@Param(value = "uid") Long uid);
}




