create table post_recommend
(
    post_no       int unsigned               not null,
    recommend     int(11) unsigned default 0 not null,
    not_recommend int(11) unsigned default 0 not null,
    constraint post_recommned_posts_post_no_fk
        foreign key (post_no) references posts (post_no)
            on update cascade on delete cascade
);

