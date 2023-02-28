package com.blackgaryc.library.core.file.processor;

import com.blackgaryc.library.core.error.FileProcessorErrorException;
import com.blackgaryc.library.core.error.FileProcessorNotSupportException;
import org.springframework.beans.factory.InitializingBean;

import java.io.File;

public abstract class AbstractFileProcessor<T extends FileProcessBaseResult> implements IFileProcessor , InitializingBean {
    private final FileProcessorFactory factory;

    protected AbstractFileProcessor(FileProcessorFactory factory) {
        this.factory = factory;
    }

    @Override
    public T process(File file, FileProcessBaseResult result) throws FileProcessorNotSupportException, FileProcessorErrorException {

        return doProcess(file, result);
    }

    abstract T doProcess(File file, FileProcessBaseResult result) throws FileProcessorErrorException;




    @Override
    public void afterPropertiesSet() throws Exception {
        factory.apply(this);
    }

}
