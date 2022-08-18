show databases;

create database board_db;

use board_db;

create table board
(
    bno int unsigned,
    title varchar(200) not null,
    content varchar(500) not null,
    writer varchar(100) not null,
    regdate datetime default NOW(),
    updatedate timestamp default NOW()
);

alter table board add constraint pk_board primary key (bno);
alter table board modify bno int unsigned auto_increment;
alter table board auto_increment = 1;
insert into board (title, content, writer)
values ('테스트 제목', '테스트 내용', 'Tester');

select * from board;

select *
from board
where title like concat('%', '테스트', '%');

select now();