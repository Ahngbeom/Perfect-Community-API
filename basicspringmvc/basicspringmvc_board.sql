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

INSERT INTO basicspringmvc.board (bno, title, content, writer, regdate, updatedate) VALUES (1, 'a', 'a', 'Administrator', '2022-05-18 13:15:00', '2022-05-18 13:15:00');
INSERT INTO basicspringmvc.board (bno, title, content, writer, regdate, updatedate) VALUES (2, 'asdad', 'asdad', '익명', '2022-05-18 14:46:38', '2022-05-18 14:46:38');
