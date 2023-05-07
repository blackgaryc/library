package com.blackgaryc.library.myservice;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackgaryc.library.domain.admin.publisher.PublisherDto;
import com.blackgaryc.library.domain.admin.publisher.PublisherSelectVo;
import com.blackgaryc.library.domain.admin.publisher.PublisherVo;

import java.util.List;

public interface AdminPublisherService {
    List<PublisherSelectVo> getSelectData();

    Page<PublisherVo> getPageData();


    void addPublisher(PublisherDto dto);

    PublisherDto getInfo(Integer id);

    void updatePublisher(Integer id, PublisherDto dto);

    void deletePublisher(Integer id);
}
