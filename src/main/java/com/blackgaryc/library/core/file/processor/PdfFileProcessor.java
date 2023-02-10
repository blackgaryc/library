package com.blackgaryc.library.core.file.processor;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class PdfFileProcessor extends AbstractFileProcessor<FileProcessPageableBaseResult>{
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private static final Set<String> extensions=new HashSet<>(Arrays.asList(".pdf"));
    private static final Set<String> mimetypes=new HashSet<>(Arrays.asList("application/pdf"));
    public PdfFileProcessor(FileProcessorFactory factory) {
        super(factory);
    }

    @Override
    FileProcessPageableBaseResult doProcess(File file, IFileProcessBaseResult result) {
        //copy result to res
        FileProcessPageableBaseResult res = new FileProcessPageableBaseResult(result);
        try {
            PDDocument load = PDDocument.load(file);
            int numberOfPages = load.getNumberOfPages();
            res.setNumberOfPage(numberOfPages);
            load.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //handle pdf result;
        log.info(res.toString());
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
