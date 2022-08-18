create table users
(
    userid     varchar(50)                          not null
        primary key,
    password   varchar(500)                         not null,
    username   varchar(50)                          not null,
    enabled    tinyint(1) default 1                 not null,
    regDate    datetime   default CURRENT_TIMESTAMP not null,
    updateDate datetime   default CURRENT_TIMESTAMP not null
);

select * from users;

