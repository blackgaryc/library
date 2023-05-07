package com.blackgaryc.library.myservice;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackgaryc.library.entity.BooklistEntity;

public interface AdminBookListService {
    Page<BooklistEntity> getPageList(String name);

    void deleteBookList(Long id);

    void enableBookList(Long id);
}
