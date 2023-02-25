package com.blackgaryc.library.core.error;

import com.blackgaryc.library.core.file.processor.IFileProcessBaseResult;

public class FileProcessorErrorException extends FileProcessorException{

    IFileProcessBaseResult baseResult;
    public FileProcessorErrorException(IFileProcessBaseResult baseResult) {
        super("["+baseResult.getFilename()+"]处理失败");
        this.baseResult = baseResult;
    }

    public IFileProcessBaseResult getBaseResult() {
        return baseResult;
    }
}
