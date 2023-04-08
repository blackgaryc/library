package com.blackgaryc.library.myservice;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackgaryc.library.domain.user.admin.SimpleBookVO;

import java.util.Map;

public interface AdminBookService{
    Page<SimpleBookVO> getPageList(String name);

    void deleteBook(Long id);
}
