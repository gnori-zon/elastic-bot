-- liquibase formatted sql
-- changeset gnori:V711907950_insert_into_phase_description_types_table

INSERT INTO phase_description_types (name)
    VALUES ('SEND'),
            ('SCHEDULE_SEND')
