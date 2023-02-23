package com.blackgaryc.library.core.error;

public class FileAlreadyExistException extends FileException{
    public FileAlreadyExistException(String name) {
        super(name+"已被上传");
    }
}
