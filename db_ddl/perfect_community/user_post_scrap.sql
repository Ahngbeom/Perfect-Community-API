create table user_post_scrap
(
    user_id    varchar(20)                           not null,
    post_no    int unsigned                          not null,
    scrap_date timestamp default current_timestamp() not null,
    constraint user_post_scrap_user_id_post_no_uindex
        unique (user_id, post_no),
    constraint user_post_scrap_posts_post_no_fk
        foreign key (post_no) references posts (post_no)
            on update cascade on delete cascade,
    constraint user_post_scrap_users_user_id_fk
        foreign key (user_id) references users (user_id)
            on update cascade on delete cascade
);

