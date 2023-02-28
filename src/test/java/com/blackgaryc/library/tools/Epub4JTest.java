package com.blackgaryc.library.tools;


import io.documentnode.epub4j.domain.Book;
import io.documentnode.epub4j.domain.MediaType;
import io.documentnode.epub4j.domain.MediaTypes;
import io.documentnode.epub4j.domain.Resource;
import io.documentnode.epub4j.epub.EpubReader;
import org.apache.commons.io.IOUtils;
import org.apache.tika.Tika;
import org.apache.tika.metadata.TikaMimeKeys;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

class Epub4JTest {
    @Test
    public void test() throws IOException {
//        EpubReader epubReader=new EpubReader();
        String name = "/home/alex/Documents/Project/IdeaProjects/GraduationDesign/library/鸟哥的Linux私房菜：服务器架设篇.epub";
//        Book book = epubReader.readEpub(new FileInputStream(name));
//        Resource coverImage = book.getCoverImage();
//        MediaType mediaType = coverImage.getMediaType();
//        String title = book.getTitle();
//        System.out.println("title = " + title);
//        System.out.println("mediaType = " + mediaType);
//        IOUtils.copy(coverImage.getInputStream(),new FileOutputStream("cover"+(mediaType.getDefaultExtension())));
        Tika tika = new Tika();
        String detect = tika.detect(new FileInputStream(name));
        System.out.println("detect = " + detect);

    }
}
