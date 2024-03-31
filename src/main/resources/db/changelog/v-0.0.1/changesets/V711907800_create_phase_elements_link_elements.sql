-- liquibase formatted sql
-- changeset gnori:V711907800_create_phase_elements_link_elements

CREATE TABLE phase_elements_link_elements
(
    phase_element_id UUID REFERENCES phase_elements (id) NOT NULL,
    link_element_id  UUID REFERENCES link_elements (id)  NOT NULL,
    created_at       TIMESTAMP WITH TIME ZONE            NOT NULL DEFAULT now(),
    updated_at       TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY (phase_element_id, link_element_id)
)