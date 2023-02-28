package com.blackgaryc.library.core.file.processor;

import com.blackgaryc.library.core.error.FileProcessorErrorException;
import io.documentnode.epub4j.domain.Book;
import io.documentnode.epub4j.epub.EpubReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class EpubFileProcessor extends AbstractFileProcessor<FileProcessBaseResult> {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private static final Set<String> extensions = new HashSet<>(List.of(".epub"));
    private static final Set<String> mimetypes = new HashSet<>(List.of("application/epub+zip"));

    protected EpubFileProcessor(FileProcessorFactory factory) {
        super(factory);
    }

    @Override
    FileProcessBaseResult doProcess(File file, FileProcessBaseResult result) throws FileProcessorErrorException {
        try {
            EpubReader epubReader = new EpubReader();
            Book book = epubReader.readEpub(new FileInputStream(file));
            result.setThumbnail(book.getCoverImage().getInputStream());
            result.setThumbnailExtension(book.getCoverImage().getMediaType().getDefaultExtension());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Set<String> getSupportedExtensions() {
        return extensions;
    }

    @Override
    public Set<String> getSupportedMimetypes() {
        return mimetypes;
    }
}
