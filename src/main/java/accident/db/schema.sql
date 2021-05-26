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