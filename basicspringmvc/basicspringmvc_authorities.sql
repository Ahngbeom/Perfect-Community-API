create table authorities
(
    userid    varchar(50) not null,
    authority varchar(50) not null,
    constraint ix_auth_username
        unique (userid, authority),
    constraint fk_authorities_users
        foreign key (userid) references users (userid)
);

INSERT INTO basicspringmvc.authorities (userid, authority) VALUES ('11', 'ROLE_USER');
INSERT INTO basicspringmvc.authorities (userid, authority) VALUES ('aa', 'ROLE_USER');
INSERT INTO basicspringmvc.authorities (userid, authority) VALUES ('admin', 'ROLE_ADMIN');
