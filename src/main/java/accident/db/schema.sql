CREATE TABLE accident
(
    id      serial primary key,
    name    varchar(2000),
    text    varchar(2000),
    address varchar(2000),
    type_id int
);

CREATE TABLE rule
(
    id   serial primary key,
    name varchar(2000)
);

CREATE TABLE accident_type
(
    id   serial primary key,
    name varchar(2000)
);

CREATE TABLE accident_rules
(
    acciden_id int,
    rule_id    int
);

CREATE TABLE authorities
(
    id        serial primary key,
    authority VARCHAR(50) NOT NULL unique
);

CREATE TABLE users
(
    id           serial primary key,
    username     VARCHAR(50)  NOT NULL unique,
    password     VARCHAR(100) NOT NULL,
    enabled      boolean default true,
    authority_id int          not null references authorities (id)
);

insert into authorities (authority)
values ('ROLE_USER');
insert into authorities (authority)
values ('ROLE_ADMIN');

insert into users (username, password, authority_id)
values ('root', true, '$2a$10$SCBNjC/HFiX4WhxxjWD2s.c0ERVemygNzVMq4MYaTJIE3wPGMoIES',
        (select id from authorities where authority = 'ROLE_ADMIN'));