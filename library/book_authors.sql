create table library.book_authors
(
    book_id   bigint not null,
    author_id bigint not null
);

create index book_author_FK
    on library.book_authors (book_id);

create index book_author_FK_1
    on library.book_authors (author_id);

