create table library.book_detail
(
    book_id bigint           not null,
    file_id bigint           not null,
    page    int    default 0 not null,
    size    bigint default 0 not null
);

