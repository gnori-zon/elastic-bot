-- liquibase formatted sql
-- changeset gnori:V711907800_create_phases_link_elements

CREATE TABLE phases_link_elements
(
    phase_id UUID REFERENCES phases (id) NOT NULL,
    link_element_id  UUID REFERENCES link_elements (id)  NOT NULL,
    created_at       TIMESTAMP WITH TIME ZONE            NOT NULL DEFAULT now(),
    updated_at       TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY (phase_id, link_element_id)
)