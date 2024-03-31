-- liquibase formatted sql
-- changeset gnori:V711907790_create_phase_elements_display_elements

CREATE TABLE phase_elements_display_elements
(
    phase_element_id   UUID REFERENCES phase_elements (id)   NOT NULL,
    display_element_id UUID REFERENCES display_elements (id) NOT NULL,
    created_at         TIMESTAMP WITH TIME ZONE              NOT NULL DEFAULT now(),
    updated_at         TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY (phase_element_id, display_element_id)
)