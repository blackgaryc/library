package com.blackgaryc.library.core.file.thumbnail;

public class ThumbnailFactory {
    private static final PdfThumbnailGenerator pdfThumbnailGenerator = new PdfThumbnailGenerator();
    public static ThumbnailGenerator getInstance(String type) throws ThumbnailGeneratorNotFoundException{
        if (type.equals(".pdf")){
            return pdfThumbnailGenerator;
        }
        throw new ThumbnailGeneratorNotFoundException(type);
    }
    public static ThumbnailGenerator getInstance(ThumbnailSourceFileTypeEnum type) throws ThumbnailGeneratorNotFoundException{
        if (type.equals(ThumbnailSourceFileTypeEnum.PDF)){
            return pdfThumbnailGenerator;
        }
        throw new ThumbnailGeneratorNotFoundException(type.name());
    }
}
