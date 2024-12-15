-- liquibase formatted sql
-- changeset gnori:V8_create_telegram_chats_table

CREATE TABLE telegram_chats
(
    id         UUID PRIMARY KEY                  DEFAULT gen_random_uuid(),
    externalId BIGINT                   NOT NULL,
    payload    JSONB                    NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at TIMESTAMP WITH TIME ZONE
)