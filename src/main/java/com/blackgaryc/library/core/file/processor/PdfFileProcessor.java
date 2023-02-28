package com.blackgaryc.library.core.file.processor;

import com.blackgaryc.library.core.error.FileProcessorErrorException;
import com.blackgaryc.library.core.file.thumbnail.PdfThumbnailGenerator;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class PdfFileProcessor extends AbstractFileProcessor<FileProcessPageableBaseResult> {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private static final Set<String> extensions = new HashSet<>(List.of(".pdf"));
    private static final Set<String> mimetypes = new HashSet<>(List.of("application/pdf"));

    public PdfFileProcessor(FileProcessorFactory factory) {
        super(factory);
    }

    @Override
    FileProcessPageableBaseResult doProcess(File file, FileProcessBaseResult result) throws FileProcessorErrorException {
        //copy result to res
        FileProcessPageableBaseResult res = new FileProcessPageableBaseResult(result);
        try (PDDocument load = PDDocument.load(file)) {
            int numberOfPages = load.getNumberOfPages();
            res.setNumberOfPage(numberOfPages);
            PdfThumbnailGenerator pdfThumbnailGenerator = new PdfThumbnailGenerator();
            ByteArrayInputStream generate = pdfThumbnailGenerator.generate(load);
            res.setThumbnail(generate);
            res.setThumbnailExtension(pdfThumbnailGenerator.getExtension());
        } catch (IOException e) {
            throw new FileProcessorErrorException(res);
        }
        return res;
    }

    @Override
    public Set<String> getSupportedExtensions() {
        return Collections.unmodifiableSet(extensions);
    }

    @Override
    public Set<String> getSupportedMimetypes() {
        return Collections.unmodifiableSet(mimetypes);
    }
}
