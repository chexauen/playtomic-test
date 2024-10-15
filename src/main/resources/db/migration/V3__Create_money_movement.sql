CREATE SEQUENCE IF NOT EXISTS money_movement_id_seq start with 1 INCREMENT 1;

CREATE TABLE IF NOT EXISTS MONEY_MOVEMENT (
    ID BIGINT DEFAULT nextval('money_movement_id_seq') NOT NULL,
    amount numeric(20,2) not null,
    creationDate timestamp not null,
    updateDate timestamp not null,
    wallet_id BIGINT not null,
    PRIMARY KEY (id)
);