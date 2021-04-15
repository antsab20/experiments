CREATE DATABASE bank;
\connect bank;



CREATE TABLE role(
    id          bigserial primary key,
    name        varchar (120) not null
);

CREATE TABLE account(
    id          bigserial primary key,
    amount      int not null
);

CREATE TABLE users(
    id          bigserial primary key,
    first_name  varchar (120) not null,
    last_name   varchar (120) not null,
    email       varchar (120) not null,
    password    varchar (120) not null,
    account_id  bigserial,

    UNIQUE(account_id),
    FOREIGN KEY(account_id) REFERENCES account(id)
);

CREATE TABLE users_role (
    users_id bigserial REFERENCES users,
    role_id bigserial REFERENCES role,
    PRIMARY KEY(users_id, role_id)
);


INSERT INTO role(id, name) values (1, 'ROLE_USER');