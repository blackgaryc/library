package com.blackgaryc.library.core.error;

public class FileNotAllowedToUploadException extends FileException{
    public FileNotAllowedToUploadException(String name) {
        super(name+"文件类型检查失败");
    }
}
