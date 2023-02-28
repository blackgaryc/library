package com.blackgaryc.library.myservice;

import com.blackgaryc.library.core.error.FileDownloadTimesEndException;
import com.blackgaryc.library.entity.FileEntity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface UserFileService {
    String download(String fileId) throws FileDownloadTimesEndException;
    <T extends Serializable> List<FileEntity> getFileEntitiesByIds(Collection<T> ids);
}
