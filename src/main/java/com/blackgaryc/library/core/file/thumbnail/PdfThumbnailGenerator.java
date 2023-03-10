package com.blackgaryc.library.core.file.thumbnail;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class PdfThumbnailGenerator implements ThumbnailGenerator {
    boolean enableScale = true;
    int targetWidth = 300;
    String extension = "jpeg";

    @Override
    public InputStream generate(InputStream inputStream) {
        try (PDDocument load = PDDocument.load(inputStream)) {
            return generate(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    public ByteArrayInputStream generate(PDDocument load) throws IOException {
        int numberOfPages = load.getNumberOfPages();
        if (numberOfPages < 1) {
            throw new RuntimeException("can't read any page");
        }
        PDFRenderer pdfRenderer = new PDFRenderer(load);
        BufferedImage bufferedImage = pdfRenderer.renderImageWithDPI(0, 288, ImageType.RGB);
        int width = bufferedImage.getWidth();
        if (enableScale && width > targetWidth) {
            float scale = 1.0f * targetWidth / width;
            int height = (int) (bufferedImage.getHeight() * scale);
            Image scaleImage = bufferedImage.getScaledInstance(targetWidth, height, Image.SCALE_DEFAULT);
            bufferedImage = new BufferedImage(targetWidth, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.getGraphics().drawImage(scaleImage, 0, 0, null);

        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, extension, os);
        return new ByteArrayInputStream(os.toByteArray());
    }

    @Override
    public String getExtension() {
        return '.'+this.extension;
    }
}
