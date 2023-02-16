package com.blackgaryc.library.core.file.thumbnail;

import com.blackgaryc.library.core.error.LibraryException;

public class ThumbnailGeneratorNotFoundException extends LibraryException {
    public ThumbnailGeneratorNotFoundException(String type) {
        super("thumbnail generator not found by type[" + type + "]");
    }
}
