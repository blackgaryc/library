package com.blackgaryc.library.tools;

import com.blackgaryc.library.LibraryApplication;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

public class FileTool {
    public static Path trans2localTempFile(InputStream inputStream, String suffix) throws IOException {
        suffix = suffix == null ? ".tmp" : suffix;
        Path tempFile = Files.createTempFile(LibraryApplication.PREFIX, UUID.randomUUID() + suffix);
        OutputStream outputStream = Files.newOutputStream(tempFile);
        IOUtils.copy(inputStream, outputStream);
        outputStream.close();
        return tempFile;
    }
}
