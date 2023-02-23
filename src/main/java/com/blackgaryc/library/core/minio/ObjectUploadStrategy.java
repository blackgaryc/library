package com.blackgaryc.library.core.minio;

import com.blackgaryc.library.core.error.FileAlreadyExistException;
import com.blackgaryc.library.core.error.FileNotAllowedToUploadException;
import io.minio.errors.*;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface ObjectUploadStrategy {
    Object upload(IObjectKey objectKey, File file) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException, FileAlreadyExistException, FileNotAllowedToUploadException;
}
