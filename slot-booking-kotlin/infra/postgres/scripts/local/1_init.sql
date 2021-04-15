CREATE DATABASE slot_booking;
\connect slot_booking;

CREATE SCHEMA dev;

create table if not exists vaccination_site
(
    id              uuid primary key,
    name            varchar(128) not null unique,
    locality_name    varchar(128),
    address         varchar(128) not null unique,
    county_name      varchar(128)
);


create table if not exists time_slot
(
    id              uuid primary key,
    slot_time            timestamp NULL
);

create table if not exists available_slot
(
    id                  uuid primary key,
    slots               smallint not null,
    vaccination_site_id uuid NOT NULL,
    time_slot_id        uuid NOT NULL
);

ALTER TABLE available_slot ADD CONSTRAINT fk_vaccination_site
FOREIGN KEY (vaccination_site_id) references vaccination_site(id);

ALTER TABLE available_slot ADD CONSTRAINT fk_time_slot
FOREIGN KEY (time_slot_id) references time_slot(id);

INSERT INTO vaccination_site (id, name, locality_name, address, county_name) VALUES
('54b1e1dc-87f8-11eb-8dcd-0242ac130003', 'Centrul 2_SPITALUL ORASENESC FAUREI', 'Faurei', 'Strada Păcii 6, Făurei', 'Braila');

INSERT INTO vaccination_site (id, name, locality_name, address, county_name) VALUES
('ffab9827-0a90-4b5c-a711-71613ea28d7f', 'CentrulP_COMANDAMENTUL BRIGĂZII 10 GENIU', 'Braila', 'Calea cășărașilor nr. 309, Brăila', 'Braila');

INSERT INTO vaccination_site (id, name, locality_name, address, county_name) VALUES
('851a79c4-87f8-11eb-8dcd-0242ac130003', 'Centru_S_Sala de sport a Scolii Gimnaziale nr. 23 Mihai Eminescu', 'Braila', 'STR. ALEEA CUTEZATORILOR NR 2', 'Braila');

INSERT INTO vaccination_site (id, name, locality_name, address, county_name) VALUES
('abce6936-87f8-11eb-8dcd-0242ac130003', 'Sala de sport a Liceului Tehnologic „Anghel Saligny', 'Braila', 'Strada General Eremia Grigorescu nr. 32', 'Braila');

INSERT INTO vaccination_site (id, name, locality_name, address, county_name) VALUES
('f4fd3574-87f8-11eb-8dcd-0242ac130003', 'Spitalul Judetean de Urgenta', 'Braila', 'sos.Buzaului nr. 2', 'Braila');

INSERT INTO time_slot(id, slot_time) VALUES
('c86b2ff6-87f9-11eb-8dcd-0242ac130003', '2020-01-02');

INSERT INTO time_slot(id, slot_time) VALUES
('f723fbe8-87f9-11eb-8dcd-0242ac130003', '2020-01-03');

INSERT INTO time_slot(id, slot_time) VALUES
('fa828e44-87f9-11eb-8dcd-0242ac130003', '2020-01-04');

INSERT INTO time_slot(id, slot_time) VALUES
('fd8086b4-87f9-11eb-8dcd-0242ac130003', '2020-01-05');

INSERT INTO time_slot(id, slot_time) VALUES
('00759dc8-87fa-11eb-8dcd-0242ac130003', '2020-01-06');

INSERT INTO available_slot(id, slots, vaccination_site_id, time_slot_id) VALUES
('c4d6c99e-87fa-11eb-8dcd-0242ac130003', 2, '54b1e1dc-87f8-11eb-8dcd-0242ac130003', 'c86b2ff6-87f9-11eb-8dcd-0242ac130003');

INSERT INTO available_slot(id, slots, vaccination_site_id, time_slot_id) VALUES
('e1786eb8-87fa-11eb-8dcd-0242ac130003', 5, '54b1e1dc-87f8-11eb-8dcd-0242ac130003', 'fa828e44-87f9-11eb-8dcd-0242ac130003');

ALTER TABLE available_slot
    ADD COLUMN optlock_version integer NOT NULL
    DEFAULT(0);

create table if not exists userinfo(
    id                      bigserial primary key,
    first_name              varchar(128) not null,
    last_name               varchar(128) not null,
    user_name               varchar(128) not null unique,
    email                   varchar(128) not null unique,
    pass_word               varchar(128) not null,
    account_non_expired     boolean,
    account_non_locked      boolean,
    credentials_non_expired boolean,
    enabled                 boolean
);

CREATE TABLE if not exists role(
    id          bigserial primary key,
    role_name        varchar (120) not null
);

CREATE TABLE userinfo_role (
    userinfo_id bigserial REFERENCES userinfo,
    role_id  bigserial REFERENCES  role,
    PRIMARY KEY(userinfo_id, role_id)
);

INSERT INTO role(id, role_name) values (1, 'ROLE_USER');

INSERT INTO userinfo (id, first_name, last_name, pass_word, user_name, email, account_non_expired, account_non_locked, credentials_non_expired, enabled)
VALUES (1, 'deepak', 'sp', '$2a$10$2nU5xMCyzPMeNiGQsrjzQeWNcHf9NjtGzVFgy6kVRcZlLh/ABTgZW', 'spdeepak', 'example@example.com', TRUE, TRUE, TRUE, TRUE);

INSERT INTO userinfo_role(userinfo_id, role_id) values (1, 1);

