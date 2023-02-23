create table library.user
(
    id       bigint auto_increment
        primary key,
    email    varchar(100)                                                                               null,
    password varchar(100)                                                                               not null,
    nickname varchar(100)                                                                               not null,
    account  varchar(100)                                                                               not null,
    avatar   varchar(200) default 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png' not null,
    constraint user_account_uindex
        unique (account)
);

