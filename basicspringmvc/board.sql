create table board
(
    bno        int unsigned auto_increment
        primary key,
    title      varchar(200)                        not null,
    content    varchar(500)                        not null,
    writer     varchar(100)                        not null,
    regdate    datetime  default CURRENT_TIMESTAMP null,
    updatedate timestamp default CURRENT_TIMESTAMP null
);

