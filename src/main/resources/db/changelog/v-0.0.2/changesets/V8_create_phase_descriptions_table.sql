-- liquibase formatted sql
-- changeset gnori:V8_create_phase_descriptions_table

CREATE TABLE phase_descriptions
(
    id             UUID PRIMARY KEY                                             DEFAULT gen_random_uuid(),
    phase_id       UUID REFERENCES phases (id)                         NOT NULL,
    format_type_id UUID REFERENCES phase_description_format_types (id) NOT NULL,
    type_id        UUID REFERENCES phase_description_types (id)        NOT NULL,
    value_id       UUID REFERENCES phase_description_values (id)       NOT NULL,
    created_at     TIMESTAMP WITH TIME ZONE                            NOT NULL DEFAULT now(),
    updated_at     TIMESTAMP WITH TIME ZONE
)
