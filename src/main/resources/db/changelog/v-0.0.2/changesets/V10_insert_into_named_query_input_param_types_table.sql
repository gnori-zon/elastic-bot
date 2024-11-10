-- liquibase formatted sql
-- changeset gnori:V10_insert_into_named_query_input_param_types_table

INSERT INTO named_query_input_param_types (name)
    VALUES ('FROM_ENTITY_FIELDS_CONTEXT'),
           ('STATIC')
