package com.blackgaryc.library.service;

import com.blackgaryc.library.core.elasticsearch.domain.Book;
import com.blackgaryc.library.core.file.processor.IFileProcessBaseResult;
import com.blackgaryc.library.core.file.processor.IFileProcessPageableResult;

public interface IMQBookService {
    void save(IFileProcessBaseResult result);
    Book findBookByMd5AndObjectKey(String md5,String objectKey);
}
