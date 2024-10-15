CREATE SEQUENCE IF NOT EXISTS wallets_id_seq start with 1 INCREMENT 1;

CREATE TABLE IF NOT EXISTS WALLETS (
    ID BIGINT DEFAULT nextval('wallets_id_seq') NOT NULL,
    balance numeric(20,2) not null,
    creationDate timestamp not null,
    updateDate timestamp not null,
    user_id BIGINT not null,
    PRIMARY KEY (id)
);