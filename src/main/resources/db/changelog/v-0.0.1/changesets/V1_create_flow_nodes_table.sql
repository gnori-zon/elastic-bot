-- liquibase formatted sql
-- changeset gnori:V1_create_flow_nodes_table

CREATE TABLE flow_nodes
(
    id         UUID PRIMARY KEY                  DEFAULT gen_random_uuid(),
    name       VARCHAR(256)             NOT NULL,
    payload    JSONB                    NOT NULL,
    parent_id  UUID,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at TIMESTAMP WITH TIME ZONE
)