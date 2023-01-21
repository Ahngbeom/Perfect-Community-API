create table perfect_community.login_history
(
    user_id        varchar(20)                           null,
    ip_address     varchar(50)                           null,
    user_agent     varchar(100)                          null,
    logged_in_date timestamp default current_timestamp() not null,
    constraint login_history_users_user_id_fk
        foreign key (user_id) references perfect_community.users (user_id)
);

