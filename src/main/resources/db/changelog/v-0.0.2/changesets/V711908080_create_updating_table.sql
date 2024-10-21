-- liquibase formatted sql
-- changeset gnori:V711908080_create_updating_table

CREATE TABLE updating
(
    id              UUID PRIMARY KEY                             DEFAULT gen_random_uuid(),
    parent_phase_id UUID REFERENCES phases (id)         NOT NULL,
    type_id         UUID REFERENCES updating_types (id) NOT NULL,
    created_at      TIMESTAMP WITH TIME ZONE            NOT NULL DEFAULT now(),
    updated_at      TIMESTAMP WITH TIME ZONE
)