-- liquibase formatted sql
-- changeset gnori:V3_insert_into_phase_description_types_table

INSERT INTO phase_description_types (name)
    VALUES ('SEND'),
            ('SCHEDULE_SEND')
