CREATE SEQUENCE user_id_seq START WITH 1;
CREATE SEQUENCE role_id_seq START WITH 1;

CREATE TABLE role (
    id                   BIGSERIAL NOT NULL,
    name                 VARCHAR(30) not null unique,
    allowed_resource     VARCHAR(200),
    allowed_read         boolean not null default false,
    allowed_create       boolean not null default false,
    allowed_update       boolean not null default false,
    allowed_delete       boolean not null default false
);

ALTER TABLE role ADD CONSTRAINT role_pk PRIMARY KEY ( id );

CREATE TABLE users_role (
    user_id    bigint NOT NULL,
    role_id    bigint NOT NULL
);

ALTER TABLE users_role
    ADD CONSTRAINT users_fk FOREIGN KEY ( user_id )
        REFERENCES users ( user_id );

ALTER TABLE users_role
    ADD CONSTRAINT role_fk FOREIGN KEY ( role_id )
        REFERENCES role ( id );
