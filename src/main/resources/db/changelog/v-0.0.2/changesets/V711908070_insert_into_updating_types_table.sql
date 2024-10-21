-- liquibase formatted sql
-- changeset gnori:V711908070_insert_into_updating_types_table

INSERT INTO updating_types (name)
    VALUES ('SCHEDULE'),
           ('EVENTS');