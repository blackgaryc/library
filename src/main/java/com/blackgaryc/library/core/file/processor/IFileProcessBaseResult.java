package com.blackgaryc.library.core.file.processor;

public interface IFileProcessBaseResult {
    String getObjectKey();
    String getUploadUid();
    String getFilename();
    String getExtension();
    String getMimetype();
    String getMd5();
    Long getSize();
}
