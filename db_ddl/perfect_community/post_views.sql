create table post_views
(
    post_no int unsigned               null,
    views   int(11) unsigned default 0 not null,
    constraint post_views_posts_post_no_fk
        foreign key (post_no) references posts (post_no)
            on update cascade on delete cascade
);

