-- liquibase formatted sql
-- changeset gnori:V711907780_create_display_elements_table

CREATE TABLE display_elements
(
    id         UUID PRIMARY KEY                                    DEFAULT gen_random_uuid(),
    name       VARCHAR(200)                               NOT NULL UNIQUE,
    type_id    UUID REFERENCES display_element_types (id) NOT NULL,
    link       VARCHAR,
    created_at TIMESTAMP WITH TIME ZONE                   NOT NULL DEFAULT now(),
    updated_at TIMESTAMP WITH TIME ZONE
)