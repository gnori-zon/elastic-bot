-- liquibase formatted sql
-- changeset gnori:V711907940_create_phase_description_types_table

CREATE TABLE phase_description_types
(
    id         UUID PRIMARY KEY                  DEFAULT gen_random_uuid(),
    name       VARCHAR(200)             NOT NULL UNIQUE,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at TIMESTAMP WITH TIME ZONE
)
