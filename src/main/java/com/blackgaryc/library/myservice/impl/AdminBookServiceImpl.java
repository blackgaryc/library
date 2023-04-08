package com.blackgaryc.library.myservice.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackgaryc.library.domain.user.admin.SimpleBookVO;
import com.blackgaryc.library.entity.BookEntity;
import com.blackgaryc.library.entity.StatusEnum;
import com.blackgaryc.library.myservice.AdminBookService;
import com.blackgaryc.library.service.BookService;
import com.blackgaryc.library.tools.context.HttpContextTool;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminBookServiceImpl implements AdminBookService {
    @Autowired
    BookService bookService;
    @Override
    public Page<SimpleBookVO> getPageList(String name) {
        Page<BookEntity> page = this.bookService.lambdaQuery()
                .ne(BookEntity::getStatus, StatusEnum.DELETED.getCode())
                .like(Strings.isNotBlank(name), BookEntity::getTitle, name)
                .page(HttpContextTool.getDefaultPage());
        Page<SimpleBookVO> simpleBookVOPage = new Page<>();
        BeanUtils.copyProperties(page,simpleBookVOPage);
        List<SimpleBookVO> collect = page.getRecords().stream().map(SimpleBookVO::new).collect(Collectors.toList());
        simpleBookVOPage.setRecords(collect);
        return simpleBookVOPage;
    }

    @Override
    public void deleteBook(Long id) {
        BookEntity byId = this.bookService.getById(id);
        if (null == byId){
            throw new IllegalArgumentException("id not found");
        }
        byId.setStatus(StatusEnum.DELETED.getCode());
        bookService.updateById(byId);
    }
}
