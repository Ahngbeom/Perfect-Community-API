create table users
(
    userid     varchar(50)                          not null
        primary key,
    password   varchar(500)                         not null,
    username   varchar(50)                          not null,
    enabled    tinyint(1) default 1                 not null,
    regDate    datetime   default CURRENT_TIMESTAMP null,
    updateDate datetime   default CURRENT_TIMESTAMP null
);

INSERT INTO basicspringmvc.users (userid, password, username, enabled, regDate, updateDate) VALUES ('11', '$2a$10$1jXzIeMufDyIW0MWe5pdUurvzLaPBIvjRquSRJUoJeunMkSJ0F5hG', '11', 1, '2022-05-16 21:08:17', '2022-05-16 21:08:17');
INSERT INTO basicspringmvc.users (userid, password, username, enabled, regDate, updateDate) VALUES ('aa', '$2a$10$ZEOjxo4zJte0Wmgiwdda/OPgBDqEBCErn99Tdyn/UiqHSBmPNhLdW', 'aa', 1, '2022-05-16 21:51:46', '2022-05-16 21:51:46');
INSERT INTO basicspringmvc.users (userid, password, username, enabled, regDate, updateDate) VALUES ('admin', '$2a$10$QNLuQ/INCmkGA/wQPAD7bOvUPggimo/36BUcHAkVqGwIshRU/dpqC', 'Administrator', 1, '2022-05-15 18:10:35', '2022-05-15 18:10:35');
