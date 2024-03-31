-- liquibase formatted sql
-- changeset gnori:V711907760_create_link_elements_table

CREATE TABLE link_elements
(
    id         UUID PRIMARY KEY                                 DEFAULT gen_random_uuid(),
    name       VARCHAR(200)                            NOT NULL,
    link       VARCHAR,
    type_id    UUID REFERENCES link_element_types (id) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE                NOT NULL DEFAULT now(),
    updated_at TIMESTAMP WITH TIME ZONE
)