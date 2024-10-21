-- liquibase formatted sql
-- changeset gnori:V711907955_insert_into_phase_description_format_types_table

INSERT INTO phase_description_format_types (name)
    VALUES ('HTML'),
            ('MARKDOWN'),
            ('NULL')