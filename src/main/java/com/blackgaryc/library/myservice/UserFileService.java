package com.blackgaryc.library.myservice;

import com.blackgaryc.library.core.error.FileDownloadTimesEndException;

public interface UserFileService {
    String download(String fileId) throws FileDownloadTimesEndException;
}
