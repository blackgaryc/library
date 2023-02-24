package com.blackgaryc.library.core.file.processor;

import java.io.InputStream;

public interface IFileProcessBaseResult {
    String getObjectKey();
    String getUploadUid();
    String getFilename();
    String getExtension();
    String getMimetype();
    String getMd5();
    Long getSize();
    InputStream getThumbnail();

    String getThumbnailExtension();
}
