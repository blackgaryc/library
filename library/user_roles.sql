create table library.user_roles
(
    uid     bigint not null,
    role_id int    not null,
    constraint user_roles_pk
        unique (role_id, uid)
);

