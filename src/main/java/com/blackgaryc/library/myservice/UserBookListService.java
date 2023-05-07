package com.blackgaryc.library.myservice;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackgaryc.library.core.error.LibraryException;
import com.blackgaryc.library.domain.book.SimpleBook;
import com.blackgaryc.library.domain.booklist.CreateBookListRequest;
import com.blackgaryc.library.domain.booklist.SimpleBookList;
import com.blackgaryc.library.domain.booklist.UpdateBookListRequest;
import com.blackgaryc.library.entity.BooklistEntity;
import org.springframework.transaction.annotation.Transactional;

public interface UserBookListService {

    /**
     * 创建一个书单
     * @param req
     */
    void create(CreateBookListRequest req);

    /**
     * 返回用户的书单
     * @param id
     * @return
     * @throws LibraryException 如果书单不是用户的会引起异常
     */
    BooklistEntity getById(String id) throws LibraryException;

    /**
     * 更新一个书单
     * @param req
     * @throws LibraryException 如果书单不是用户的会引起异常
     */
    void update(UpdateBookListRequest req) throws LibraryException;

    /**
     * 列出所有的用户书单
     * @return
     */
    Page<BooklistEntity> listByPage();

    /**
     * 添加图书到书单
     * @param id
     * @param bookId
     * @throws LibraryException 如果书单不是用户的会引起异常，书籍不存在也会引起异常。
     */
    @Transactional
    void addBook2BookList(String id, Long bookId) throws LibraryException;

    /**
     * 删除书单里的图书
     * @param id
     * @param bookId
     * @throws LibraryException 如果书单不存在 或者 书单不存在书籍 会引起异常
     */
    @Transactional
    void deleteBookInBookList(String id, Long bookId) throws LibraryException;

    Page<SimpleBook> listBookInBookList(String id) throws LibraryException;

    /**
     * 删除书单
     * @param id
     */
    void deleteBookList(String id) throws LibraryException;

    Page<BooklistEntity> getAllPublishedBookList();

    BooklistEntity getInfo(Long id);
}
