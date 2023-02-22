package com.blackgaryc.library.core.error;

public class MinioException extends LibraryException{
    public MinioException() {
        super();
    }

    public MinioException(String message) {
        super(message);
    }

    public MinioException(String message, Throwable cause) {
        super(message, cause);
    }

    public MinioException(Throwable cause) {
        super(cause);
    }

    public MinioException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
