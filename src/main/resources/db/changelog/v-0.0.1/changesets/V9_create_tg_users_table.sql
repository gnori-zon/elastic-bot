-- liquibase formatted sql
-- changeset gnori:V9_create_tg_users_table

CREATE TABLE tg_users
(
    id         UUID PRIMARY KEY                  DEFAULT gen_random_uuid(),
    username   VARCHAR,
    chat_id    BIGINT UNIQUE            NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at TIMESTAMP WITH TIME ZONE
)