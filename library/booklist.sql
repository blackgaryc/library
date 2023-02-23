create table library.booklist
(
    id          varchar(64)  not null
        primary key,
    uid         bigint       not null,
    name        varchar(100) not null,
    description varchar(100) not null,
    published   tinyint(1)   not null
);

