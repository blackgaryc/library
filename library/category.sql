create table library.category
(
    id        int auto_increment
        primary key,
    name      varchar(100) not null,
    parent_id int          null
);

