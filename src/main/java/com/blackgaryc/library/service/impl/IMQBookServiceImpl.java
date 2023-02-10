package com.blackgaryc.library.service.impl;

import com.blackgaryc.library.core.file.processor.IFileProcessBaseResult;
import com.blackgaryc.library.core.file.processor.IFileProcessPageableResult;
import com.blackgaryc.library.entity.BookDetailEntity;
import com.blackgaryc.library.entity.BookEntity;
import com.blackgaryc.library.entity.FileEntity;
import com.blackgaryc.library.service.BookDetailService;
import com.blackgaryc.library.service.BookService;
import com.blackgaryc.library.service.FileService;
import com.blackgaryc.library.service.IMQBookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class IMQBookServiceImpl implements IMQBookService {
    @Resource
    BookService bookService;
    @Resource
    FileService fileService;
    @Resource
    BookDetailService bookDetailService;
    @Override
    @Transactional
    public void save(IFileProcessBaseResult result) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle(result.getFilename());
        bookService.save(bookEntity);
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFilename(result.getFilename());
        fileEntity.setExtension(result.getExtension());
        fileEntity.setMd5(result.getMd5());
        fileEntity.setMimetype(result.getMimetype());
        fileEntity.setUid(Long.valueOf(result.getUploadUid()));
        fileEntity.setSize(result.getSize());
        fileEntity.setObject(result.getObjectKey());
        fileService.save(fileEntity);
        BookDetailEntity bookDetailEntity = new BookDetailEntity();
        bookDetailEntity.setBookId(bookEntity.getId());
        bookDetailEntity.setFileId(fileEntity.getId());
        if (result instanceof IFileProcessPageableResult pageableResult){
            bookDetailEntity.setPage(pageableResult.getNumberOfPage());
        }
        bookDetailService.save(bookDetailEntity);
    }
}
