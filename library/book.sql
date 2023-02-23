create table library.book
(
    id           bigint auto_increment
        primary key,
    title        varchar(100)  default '' not null,
    description  varchar(1024) default '' null,
    language     varchar(100)  default '' null,
    category_id  int           default 0  null,
    thumbnail    varchar(200)  default '' not null,
    status       int           default 0  null,
    created_uid  bigint                   not null,
    publisher_id int           default 0  not null,
    isbn         varchar(20)   default '' not null,
    isbn13       varchar(26)   default '' not null,
    constraint book_FK
        foreign key (category_id) references library.category (id)
);

create index book_FK_123
    on library.book (language);

