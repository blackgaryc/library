package com.blackgaryc.library.myservice;

import com.blackgaryc.library.entity.BookDetailEntity;
import com.blackgaryc.library.entity.BookEntity;
import com.blackgaryc.library.entity.FileEntity;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserBookService {
    Optional<BookEntity> getBookEntityByFileId(String fileId);
    List<FileEntity> getFileEntityByBookId(String bookId);
    List<BookDetailEntity> getBookDetailEntitiesByBookIds(Collection<Long> bookIds);
    List<BookDetailEntity> getBookDetailEntitiesByBookEntity(BookEntity bookEntity);
}
