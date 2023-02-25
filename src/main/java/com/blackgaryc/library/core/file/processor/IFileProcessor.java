package com.blackgaryc.library.core.file.processor;

import com.blackgaryc.library.core.error.FileProcessorErrorException;
import com.blackgaryc.library.core.error.FileProcessorNotSupportException;

import java.io.File;
import java.util.List;
import java.util.Set;

/**
 * different file processor interface
 */
public interface IFileProcessor {
    FileProcessBaseResult process(File file,FileProcessBaseResult result) throws FileProcessorNotSupportException, FileProcessorErrorException;
    Set<String> getSupportedExtensions();
    Set<String> getSupportedMimetypes();
}
