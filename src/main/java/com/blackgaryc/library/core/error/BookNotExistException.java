package com.blackgaryc.library.core.error;

public class BookNotExistException extends LibraryException{
    public BookNotExistException(Long id) {
        super("id="+id+"的书籍不存在");
    }

}
