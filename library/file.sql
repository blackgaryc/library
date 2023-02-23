create table library.file
(
    id        bigint auto_increment
        primary key,
    filename  varchar(100) not null,
    extension varchar(100) not null,
    mimetype  varchar(100) not null,
    object    varchar(100) not null,
    md5       varchar(100) not null,
    uid       bigint       not null,
    size      bigint       not null
);

