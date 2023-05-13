package com.blackgaryc.library.myservice;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackgaryc.library.domain.admin.book.BookDto;
import com.blackgaryc.library.domain.user.admin.BookUpdateDto;
import com.blackgaryc.library.domain.user.admin.SimpleBookVO;

public interface AdminBookService{
    Page<SimpleBookVO> getPageList(String name, Integer categoryId, Integer publisherId);

    void deleteBook(Long id);

    BookDto getBookInfo(Long id);

    void changeStatus(Long id);

    void updateBookInfo(Long id, BookUpdateDto bookUpdateDto);
}
