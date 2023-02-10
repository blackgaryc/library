package com.blackgaryc.library.core.error;

public class FileProcessorException extends LibraryException{
    public FileProcessorException() {
        super();
    }

    public FileProcessorException(String message) {
        super(message);
    }

    public FileProcessorException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileProcessorException(Throwable cause) {
        super(cause);
    }

    public FileProcessorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
