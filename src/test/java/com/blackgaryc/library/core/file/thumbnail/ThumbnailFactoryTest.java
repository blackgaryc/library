package com.blackgaryc.library.core.file.thumbnail;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

class ThumbnailFactoryTest {

    @Test
    void getInstance() throws IOException, ThumbnailGeneratorNotFoundException {
        ThumbnailGenerator instance = ThumbnailFactory.getInstance(ThumbnailSourceFileTypeEnum.PDF);
        InputStream generate = instance.generate(new FileInputStream("/media/alex/0daed936-8a78-4983-8170-769ec92020b2/pdf/深入理解Java虚拟机：JVM高级特性与最佳实践（第3版） (华章原创精品).pdf"));
        FileUtils.copyInputStreamToFile(generate,new File("example.jpeg"));
    }
}