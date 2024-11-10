-- liquibase formatted sql
-- changeset gnori:V7_create_phase_description_values_table

CREATE TABLE phase_description_values
(
    id      UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    type_id UUID REFERENCES phase_description_value_types (id) NOT NULL,
    value   VARCHAR                                            NOT NULL
)
