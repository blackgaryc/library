package com.blackgaryc.library.core.file.processor;

import com.blackgaryc.library.core.error.FileProcessorNotSupportException;
import com.blackgaryc.library.tools.StringTools;
import org.springframework.beans.factory.InitializingBean;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class AbstractFileProcessor<T extends FileProcessBaseResult> implements IFileProcessor , InitializingBean {
    private final FileProcessorFactory factory;

    protected AbstractFileProcessor(FileProcessorFactory factory) {
        this.factory = factory;
    }

    @Override
    public T process(File file, FileProcessBaseResult result) throws FileProcessorNotSupportException {

        return doProcess(file,result);
    }

    abstract T doProcess(File file, IFileProcessBaseResult result);




    @Override
    public void afterPropertiesSet() throws Exception {
        factory.apply(this);
    }

}
