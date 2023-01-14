create table users_authorities
(
    user_id   varchar(50) not null,
    authority varchar(20) null,
    constraint users_authorities_authorities_authority_fk
        foreign key (authority) references authorities (authority),
    constraint users_authorities_users_user_id_fk
        foreign key (user_id) references users (user_id)
            on update cascade on delete cascade
);

