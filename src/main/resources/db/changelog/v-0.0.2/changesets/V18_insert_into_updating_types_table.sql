-- liquibase formatted sql
-- changeset gnori:V18_insert_into_updating_types_table

INSERT INTO updating_types (name)
    VALUES ('SCHEDULE'),
           ('EVENTS');