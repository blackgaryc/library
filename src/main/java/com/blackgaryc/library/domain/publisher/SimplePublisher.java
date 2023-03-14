package com.blackgaryc.library.domain.publisher;

import com.blackgaryc.library.entity.PublisherEntity;

import java.io.Serializable;

public class SimplePublisher implements Serializable {
    private final Integer id;

    /**
     *
     */
    private final String name;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public SimplePublisher(PublisherEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
