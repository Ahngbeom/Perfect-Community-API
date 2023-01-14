create table posts
(
    post_no     int unsigned auto_increment
        primary key,
    board_no    int unsigned                          null,
    type        varchar(20)                           not null,
    title       varchar(200)                          not null,
    contents    varchar(1000)                         not null,
    writer      varchar(50)                           not null,
    reg_date    timestamp default current_timestamp() not null,
    update_date timestamp default current_timestamp() not null,
    constraint posts_users_user_id_fk
        foreign key (writer) references users (user_id)
            on update cascade on delete cascade
);

