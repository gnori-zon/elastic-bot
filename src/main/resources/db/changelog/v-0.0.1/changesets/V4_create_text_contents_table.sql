-- liquibase formatted sql
-- changeset gnori:V5_create_text_contents_table

CREATE TABLE text_contents
(
    id         UUID PRIMARY KEY                  DEFAULT gen_random_uuid(),
    format     varchar                  NOT NULL,
    payload    JSONB                    NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at TIMESTAMP WITH TIME ZONE
)