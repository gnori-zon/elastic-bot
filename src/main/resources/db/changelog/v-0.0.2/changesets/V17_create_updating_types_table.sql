-- liquibase formatted sql
-- changeset gnori:V17_create_updating_types_table

CREATE TABLE updating_types
(
    id         UUID PRIMARY KEY                  DEFAULT gen_random_uuid(),
    name       VARCHAR(200) UNIQUE      NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at TIMESTAMP WITH TIME ZONE
)