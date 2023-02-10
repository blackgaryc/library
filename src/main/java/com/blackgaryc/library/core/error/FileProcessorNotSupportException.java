package com.blackgaryc.library.core.error;

public class FileProcessorNotSupportException extends FileProcessorException{
    public FileProcessorNotSupportException(String name) {
        super("do not support "+name+" file");
    }
}
