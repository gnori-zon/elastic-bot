-- liquibase formatted sql
-- changeset gnori:V711908020_create_named_queries_table

CREATE TABLE named_queries
(
    id         UUID PRIMARY KEY                  DEFAULT gen_random_uuid(),
    name       VARCHAR(200) UNIQUE      NOT NULL,
    query      VARCHAR                  NOT NULL,
    type_id    UUID REFERENCES named_query_types (id),
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at TIMESTAMP WITH TIME ZONE
)
