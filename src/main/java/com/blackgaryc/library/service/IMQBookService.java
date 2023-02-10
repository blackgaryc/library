package com.blackgaryc.library.service;

import com.blackgaryc.library.core.file.processor.IFileProcessBaseResult;
import com.blackgaryc.library.core.file.processor.IFileProcessPageableResult;

public interface IMQBookService {
    void save(IFileProcessBaseResult result);
}
