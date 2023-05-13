package com.blackgaryc.library.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackgaryc.library.entity.BookFavListEntity;
import com.blackgaryc.library.service.BookFavListService;
import com.blackgaryc.library.mapper.BookFavListMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* @author alex
* @description 针对表【basics_book_fav_list(图书收藏表)】的数据库操作Service实现
* @createDate 2023-04-01 14:09:17
*/
@Service
public class BookFavListServiceImpl extends ServiceImpl<BookFavListMapper, BookFavListEntity>
    implements BookFavListService{
    @Override
    public Page<Map<String, Object>> getFavBooks(Page<Object> defaultPage) {

        return baseMapper.getFavBooks(defaultPage, StpUtil.getLoginIdAsLong());
    }
}




