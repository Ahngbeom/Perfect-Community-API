create table login_history
(
    user_id        varchar(20)                           null,
    ip_address     varchar(50)                           null,
    user_agent     varchar(200)                          null,
    logged_in_date timestamp default current_timestamp() not null,
    constraint login_history_users_user_id_fk
        foreign key (user_id) references users (user_id)
            on update cascade on delete cascade
);

