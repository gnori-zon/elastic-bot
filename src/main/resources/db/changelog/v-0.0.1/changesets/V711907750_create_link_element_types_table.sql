-- liquibase formatted sql
-- changeset gnori:V711907750_create_link_element_types_table

CREATE TABLE link_element_types
(
    id         UUID PRIMARY KEY                  DEFAULT gen_random_uuid(),
    name       VARCHAR(200)             NOT NULL UNIQUE,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at TIMESTAMP WITH TIME ZONE
)