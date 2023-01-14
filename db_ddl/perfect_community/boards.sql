create table boards
(
    bno         int unsigned auto_increment
        primary key,
    create_user varchar(50)                           null,
    title       varchar(100)                          not null,
    comment     varchar(500)                          null,
    create_date timestamp default current_timestamp() null,
    update_date timestamp default current_timestamp() null,
    constraint boards_users_user_id_fk
        foreign key (create_user) references users (user_id)
);

