package com.blackgaryc.library.core.file.processor;

import com.blackgaryc.library.core.error.FileProcessorErrorException;
import com.blackgaryc.library.core.error.FileProcessorNotSupportException;
import io.minio.errors.*;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * different file processor interface
 */
public interface IGeneralFileProcessor<T extends IFileInfo> {
    IFileProcessBaseResult process(T fileInfo) throws FileProcessorNotSupportException, FileProcessorErrorException;
    boolean support(Class<T> clazz);
}
