package com.blackgaryc.library.core.file.thumbnail;

import java.io.InputStream;

public interface ThumbnailGenerator {
    InputStream generate(InputStream inputStream);
    String getExtension();
}
