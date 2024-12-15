-- liquibase formatted sql
-- changeset gnori:V1_create_flow_nodes_table

CREATE TABLE flow_nodes
(
    id         UUID PRIMARY KEY                  DEFAULT gen_random_uuid(),
    parent_id  UUID references flow_nodes (id),
    payload    JSONB                    NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at TIMESTAMP WITH TIME ZONE
)