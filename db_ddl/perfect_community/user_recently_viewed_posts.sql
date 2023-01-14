create table user_recently_viewed_posts
(
    user_id varchar(20) null,
    constraint user_recently_viewed_posts_users_user_id_fk
        foreign key (user_id) references users (user_id)
            on update cascade on delete cascade
);

