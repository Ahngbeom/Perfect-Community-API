create table users
(
    user_id     varchar(50)                           not null
        primary key,
    password    varchar(100)                          not null,
    nickname    varchar(50)                           not null,
    enabled     tinyint   default 1                   null,
    reg_date    timestamp default current_timestamp() not null,
    update_date timestamp default current_timestamp() not null,
    constraint users_pk
        unique (nickname)
);

