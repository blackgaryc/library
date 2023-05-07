package com.blackgaryc.library.myservice.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackgaryc.library.entity.BooklistEntity;
import com.blackgaryc.library.entity.StatusEnum;
import com.blackgaryc.library.myservice.AdminBookListService;
import com.blackgaryc.library.service.BooklistService;
import com.blackgaryc.library.tools.context.HttpContextTool;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminBookListServiceImpl implements AdminBookListService {
    @Autowired
    BooklistService booklistService;
    @Override
    public Page<BooklistEntity> getPageList(String name) {

        return booklistService.lambdaQuery()
                .like(Strings.isNotBlank(name),BooklistEntity::getName,name)
                .ne(BooklistEntity::getStatus, StatusEnum.DELETED.getCode())
                .page(HttpContextTool.getDefaultPage());
    }

    @Override
    public void deleteBookList(Long id) {
        BooklistEntity byId = booklistService.getById(id);
        byId.setStatus(StatusEnum.DISABLE.getCode());
        booklistService.updateById(byId);
    }

    @Override
    public void enableBookList(Long id) {
        BooklistEntity byId = booklistService.getById(id);
        byId.setStatus(StatusEnum.ENABLE.getCode());
        booklistService.updateById(byId);
    }
}
