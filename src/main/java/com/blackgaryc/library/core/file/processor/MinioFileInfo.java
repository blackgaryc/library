package com.blackgaryc.library.core.file.processor;

import com.blackgaryc.library.core.mq.resut.Record;
import com.blackgaryc.library.core.mq.resut.S3Notify;

public class MinioFileInfo implements IFileInfo {
    private final Record info;

    public MinioFileInfo(Record record) {
        this.info = record;
    }

    @Override
    public Object getInfo() {
        return this.info;
    }

}
