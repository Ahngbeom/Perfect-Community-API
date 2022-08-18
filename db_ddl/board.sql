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

alter table board add userid varchar(50) null;
alter table board drop column boardPassword;
alter table board add boardPassword varchar(500) null;

select * from board;

select * from board inner join users on board.writer = users.username
where bno = 1 and userid = 'tester1';

create table board_password
(
    bno int unsigned not null unique key,
    password varchar(500) not null,
    foreign key (bno) references board (bno)
);

select * from board_password;

insert into board_password(bno, password) values(1, 'abcde');
insert into board_password(bno, password) values(2, 'abcde');

