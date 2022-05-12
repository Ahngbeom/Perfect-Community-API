create table authorities
(
    userid    varchar(50) not null,
    authority varchar(50) not null,
    constraint ix_auth_username
        unique (userid, authority),
    constraint fk_authorities_users
        foreign key (userid) references users (userid)
);

