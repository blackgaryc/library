package com.blackgaryc.library.core.file.thumbnail;

import com.blackgaryc.library.core.error.LibraryException;

public class ThumbnailGeneratorException extends LibraryException {
    public ThumbnailGeneratorException() {
        super();
    }

    public ThumbnailGeneratorException(String message) {
        super(message);
    }

    public ThumbnailGeneratorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ThumbnailGeneratorException(Throwable cause) {
        super(cause);
    }

    public ThumbnailGeneratorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
