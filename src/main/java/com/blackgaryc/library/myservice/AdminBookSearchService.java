package com.blackgaryc.library.myservice;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.blackgaryc.library.core.elasticsearch.domain.Book;
import com.blackgaryc.library.entity.BookEntity;

import java.util.Collection;
import java.util.List;

public interface AdminBookSearchService {
    Book getBook4ElasticSearchByBookEntity(BookEntity bookEntity);
    List<Book> getBook4ElasticSearchByBookEntities(Collection<BookEntity> bookEntities);
}
