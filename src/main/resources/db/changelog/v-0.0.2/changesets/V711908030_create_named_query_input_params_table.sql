-- liquibase formatted sql
-- changeset gnori:V711908030_create_named_query_input_params_table

CREATE TABLE named_query_input_params
(
    id             UUID PRIMARY KEY                                            DEFAULT gen_random_uuid(),
    named_query_id UUID REFERENCES named_queries (id)                 NOT NULL,
    type_id        UUID REFERENCES named_query_input_param_types (id) NOT NULL,
    name           VARCHAR,
    value          VARCHAR,
    created_at     TIMESTAMP WITH TIME ZONE                           NOT NULL DEFAULT now(),
    updated_at     TIMESTAMP WITH TIME ZONE,
    CONSTRAINT named_query_id_name_unique UNIQUE (named_query_id, name)
)
