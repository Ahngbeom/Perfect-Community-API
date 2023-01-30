create table user_recently_viewed_posts
(
    user_id   varchar(20)                           null,
    post_no   int unsigned                          not null,
    view_date timestamp default current_timestamp() not null,
    constraint user_recently_viewed_posts_posts_null_fk
        foreign key (post_no) references posts (post_no)
            on update cascade on delete cascade,
    constraint user_recently_viewed_posts_users_user_id_fk
        foreign key (user_id) references users (user_id)
            on update cascade on delete cascade
);

