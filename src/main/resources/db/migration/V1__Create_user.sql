CREATE SEQUENCE IF NOT EXISTS users_id_seq start with 1 INCREMENT 1;

CREATE TABLE IF NOT EXISTS USERS (
    ID BIGINT DEFAULT nextval('users_id_seq') NOT NULL,
    username varchar(255) not null,
    password varchar(255) not null,
    email varchar(255) not null,
    PRIMARY KEY (id)
);