-- liquibase formatted sql
-- changeset gnori:V711907730_create_phases_table

CREATE TABLE phases
(
    id          UUID PRIMARY KEY                  DEFAULT gen_random_uuid(),
    name        VARCHAR(200)             NOT NULL,
    description VARCHAR,
    parent_id   UUID REFERENCES phases (id),
    created_at  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at  TIMESTAMP WITH TIME ZONE
)