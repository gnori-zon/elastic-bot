-- liquibase formatted sql
-- changeset gnori:V711907970_insert_into_phase_description_value_types_table

INSERT INTO phase_description_value_types (name)
VALUES ('STATIC'),
       ('DYNAMIC')
