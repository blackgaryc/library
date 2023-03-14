package com.blackgaryc.library.myservice;

import com.blackgaryc.library.domain.publisher.Publisher;
import com.blackgaryc.library.domain.publisher.SimplePublisher;

import java.util.List;

public interface UserPublisherService {
    List<SimplePublisher> getSimplePublisherList();

    Publisher getPublisherDetail(Integer id);
}
