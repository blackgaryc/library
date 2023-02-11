package com.blackgaryc.library.core.elasticsearch.repository;

import com.blackgaryc.library.core.elasticsearch.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BookRepository extends ElasticsearchRepository<Book,Long> {
    Page<Book> findByTitle(String title, Pageable pageable);
//    @Query("{\"query\":{\"bool\":{\"must\":[{\"term\":{\"bookDetails.file.md5\":\"?0\"}},{\"match\":{\"title\":\"?0\"}}],\"must_not\":[],\"should\":[]}},\"from\":0,\"size\":10,\"sort\":[],\"aggs\":{}}")
    @Query("{\"bool\":{\"should\":[{\"term\":{\"bookDetails.file.md5\":\"?0\"}},{\"match\":{\"title\":\"?0\"}}],\"must_not\":[],\"must\":[]}}")
    Page<Book> findByTitleOrMd5(String data,Pageable pageable);
}
