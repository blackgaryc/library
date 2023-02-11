package com.blackgaryc.library.core.elasticsearch.repository;

import com.blackgaryc.library.core.elasticsearch.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BookRepository extends ElasticsearchRepository<Book,Long> {
    Page<Book> findBooksByTitle(String title, Pageable pageable);
}
