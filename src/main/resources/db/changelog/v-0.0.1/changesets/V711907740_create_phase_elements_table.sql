-- liquibase formatted sql
-- changeset gnori:V711907740_create_phase_elements_table

CREATE TABLE phase_elements
(
    id          UUID PRIMARY KEY                     DEFAULT gen_random_uuid(),
    phase_id    UUID REFERENCES phases (id) NOT NULL,
    name        VARCHAR(200)                NOT NULL,
    description VARCHAR,
    created_at  TIMESTAMP WITH TIME ZONE    NOT NULL DEFAULT now(),
    updated_at  TIMESTAMP WITH TIME ZONE
)