package com.blackgaryc.library.tools;

import com.blackgaryc.library.LibraryApplication;
import com.blackgaryc.library.core.file.thumbnail.ThumbnailFactory;
import com.blackgaryc.library.core.file.thumbnail.ThumbnailGenerator;
import com.blackgaryc.library.core.file.thumbnail.ThumbnailGeneratorNotFoundException;
import com.blackgaryc.library.core.file.thumbnail.ThumbnailSourceFileTypeEnum;
import com.blackgaryc.library.core.minio.MinioProperty;
import com.blackgaryc.library.core.minio.ObjectKeyPrefixEnum;
import com.blackgaryc.library.entity.BookDetailEntity;
import com.blackgaryc.library.entity.BookEntity;
import com.blackgaryc.library.entity.FileEntity;
import com.blackgaryc.library.service.BookDetailService;
import com.blackgaryc.library.service.BookService;
import com.blackgaryc.library.service.FileService;
import io.minio.GetObjectArgs;
import io.minio.GetObjectResponse;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.errors.*;
import org.apache.commons.io.IOUtils;
import org.apache.tika.mime.MimeTypeException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ThumbnailTools {
    @Resource
    BookService bookService;
    @Resource
    BookDetailService bookDetailService;
    @Resource
    FileService fileService;
    @Resource
    MinioProperty minioProperty;
    @Resource
    MinioClient minioClient;

    @Resource
    MinioFileService minioFileService;

    public void generateThumbnailFormFile(Long bookId){
        List<BookDetailEntity> list = bookDetailService.lambdaQuery().eq(BookDetailEntity::getBookId,bookId).list();
        List<FileEntity> fileEntities = fileService.listByIds(list.stream().map(BookDetailEntity::getFileId).toList());
        Optional<FileEntity> first = fileEntities.stream().filter(
                fileEntity -> (fileEntity.getExtension().equals(".pdf"))
        ).findFirst();
        FileEntity fileEntity = first.orElse(null);
        if (null!=fileEntity){
            String object = fileEntity.getObject();
            try {
                //get file
                GetObjectResponse fis = minioClient.getObject(GetObjectArgs.builder().bucket(minioProperty.getBucket()).object(object).build());
                Path tempFile = Files.createTempFile(LibraryApplication.PREFIX, UUID.randomUUID() + fileEntity.getExtension());
                //convert to img
                ThumbnailGenerator thumbnailGenerator = ThumbnailFactory.getInstance(ThumbnailSourceFileTypeEnum.PDF);
                InputStream imagefis = thumbnailGenerator.generate(fis);
                IOUtils.copy(imagefis,new FileOutputStream(tempFile.toFile()));
                //upload img
                ObjectWriteResponse response = minioFileService.uploadFile(ObjectKeyPrefixEnum.BOOK_COVER, tempFile);
                String objectKey = response.object();
                //update thumbnail url
                BookEntity bookEntity = bookService.getBaseMapper().selectById(bookId);
                bookEntity.setThumbnail(objectKey);
                bookService.updateById(bookEntity);
                //complete
            } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException |
                     InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException |
                     XmlParserException | ThumbnailGeneratorNotFoundException | MimeTypeException e) {
                throw new RuntimeException(e);
            }

        }
    }

}
