-- liquibase formatted sql
-- changeset gnori:V4_insert_into_phase_description_format_types_table

INSERT INTO phase_description_format_types (name)
    VALUES ('HTML'),
            ('MARKDOWN'),
            ('NULL')