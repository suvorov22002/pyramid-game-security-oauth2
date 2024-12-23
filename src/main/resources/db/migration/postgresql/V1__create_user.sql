create table pyram_user (
    ID BIGSERIAL NOT NULL,
    ENABLED boolean,
    LOCKED boolean,
    created_at timestamp(6),
    update_at timestamp(6),
    created_by varchar(255),
    PASSWORD varchar(255),
    ROLE varchar(255) check (role in ('ADMIN','USER')),
    update_by varchar(255),
    USER_NAME varchar(255),
    primary key (ID),
    unique (USER_NAME)
)