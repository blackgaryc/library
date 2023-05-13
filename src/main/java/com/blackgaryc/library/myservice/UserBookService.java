package com.blackgaryc.library.myservice;

import com.blackgaryc.library.core.error.BookNotExistException;
import com.blackgaryc.library.domain.book.Book;
import com.blackgaryc.library.domain.book.SimpleBook;
import com.blackgaryc.library.entity.BookDetailEntity;
import com.blackgaryc.library.entity.BookEntity;
import com.blackgaryc.library.entity.FileEntity;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 用户图书操作接口
 */
public interface UserBookService {
    Optional<BookEntity> getBookEntityByFileId(String fileId);
    List<FileEntity> getFileEntityByBookId(String bookId);
    List<BookDetailEntity> getBookDetailEntitiesByBookIds(Collection<Long> bookIds);
    List<BookDetailEntity> getBookDetailEntitiesByBookEntity(BookEntity bookEntity);


    List<SimpleBook> getLatestBooks();
    Book getBookDetail(Long id) throws BookNotExistException;

    List<Map<String,Object>> getBookDownloadHistory();
}
