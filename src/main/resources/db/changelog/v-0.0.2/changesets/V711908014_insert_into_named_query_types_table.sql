-- liquibase formatted sql
-- changeset gnori:V711908014_insert_into_named_query_types_table

INSERT INTO named_query_types (name)
    VALUES ('SELECT'),
           ('UPDATE'),
           ('INSERT'),
           ('DELETE')
